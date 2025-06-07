package ntu.tmhieu.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Model.Reaction;
import ntu.tmhieu.Repository.ArticleRepository;
import ntu.tmhieu.Repository.ReactionRepository;
import ntu.tmhieu.Service.ArticleService;

@Controller
public class HomeController {

    // Inject Service để tương tác với logic nghiệp vụ bài viết (nếu có)
    @Autowired
    private ArticleService articleService;

    // Inject Repository để tương tác trực tiếp với dữ liệu bài viết
    @Autowired
    private ArticleRepository articleRepository;
    
    // Inject Repository để tương tác với dữ liệu về cảm xúc (reactions)
    @Autowired
    private ReactionRepository reactionRepository;

    /**
     * Hiển thị trang chủ của ứng dụng.
     * Lấy danh sách tất cả bài viết và 5 bài viết mới nhất để hiển thị.
     * @param model Đối tượng Model để truyền dữ liệu tới view.
     * @return Tên view của trang chủ.
     */
    @GetMapping("/")
    public String home(Model model) {
        // Lấy tất cả bài viết để hiển thị trên trang chủ
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);

        // Lấy 5 bài viết mới nhất để hiển thị trong sidebar hoặc phần nổi bật
        List<Article> latestArticles = articleRepository.findTop5ByOrderByPublicationDateDesc();
        model.addAttribute("latestArticles", latestArticles);

        return "frontEndView/index";
    }

    /**
     * Hiển thị trang chi tiết của một bài viết cụ thể.
     * Tìm bài viết theo ID, định dạng nội dung và lấy số lượng cảm xúc (like, happy, sad).
     * @param articleId ID của bài viết cần hiển thị.
     * @param model Đối tượng Model để truyền dữ liệu tới view.
     * @return Tên view của trang chi tiết bài viết.
     * @throws ResponseStatusException nếu không tìm thấy bài viết.
     */
    @GetMapping("/articles/{articleId}")
    public String showArticleDetail(@PathVariable Integer articleId, Model model) {
        // Tìm bài viết theo ID
        Optional<Article> articleOptional = articleRepository.findById(articleId);

        // Nếu không tìm thấy bài viết, trả về lỗi 404
        if (articleOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found with ID: " + articleId);
        }

        Article article = articleOptional.get();

        // Kích hoạt tải thông tin thể loại nếu nó được tải lười (LAZY loading)
        // (Thực tế chỉ cần truy cập getter là đủ để trigger Hibernate load)
        if (article.getCategory() != null) {
            article.getCategory().getName(); 
        }
        
        // Định dạng nội dung bài viết để hiển thị đúng các ký tự xuống dòng trong HTML
        String rawContent = article.getContent();
        String formattedContent = rawContent.replaceAll("\\r\\n", "<br/>"); // Thay thế xuống dòng Windows
        formattedContent = formattedContent.replaceAll("\\n", "<br/>");      // Thay thế xuống dòng Unix
        
        // Thêm dữ liệu bài viết và nội dung đã định dạng vào Model
        model.addAttribute("article", article);
        model.addAttribute("pageTitle", article.getTitle());
        model.addAttribute("formattedContent", formattedContent);
        
        // Lấy số lượt "Thích", "Vui", "Buồn" hiện tại của bài viết từ database
        long likes = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "like");
        long happy = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "happy");
        long sad = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "sad");

        // Thêm số lượt cảm xúc vào Model để hiển thị trên trang
        model.addAttribute("likes", likes);
        model.addAttribute("happy", happy);
        model.addAttribute("sad", sad);

        // Lấy 5 bài viết mới nhất cho sidebar (hiển thị trên trang chi tiết)
        List<Article> latestArticles = articleRepository.findTop5ByOrderByPublicationDateDesc();
        model.addAttribute("latestArticles", latestArticles);

        return "frontEndView/article-detail";
    }

    /**
     * API endpoint để lấy ảnh thumbnail của bài viết.
     * Trả về dữ liệu ảnh dưới dạng byte array với kiểu MIME phù hợp và thiết lập cache.
     * @param articleId ID của bài viết chứa ảnh thumbnail.
     * @return ResponseEntity chứa dữ liệu ảnh hoặc 404 nếu không tìm thấy.
     */
    @GetMapping("/article-thumbnails/{articleId}")
    public ResponseEntity<byte[]> getArticleThumbnail(@PathVariable Integer articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            // Kiểm tra xem bài viết có dữ liệu ảnh thumbnail và kiểu MIME không
            if (article.getThumbnailImageData() != null && article.getThumbnailMimeType() != null) {
                MediaType contentType = MediaType.parseMediaType(article.getThumbnailMimeType());
                // Thiết lập Cache-Control để trình duyệt lưu ảnh trong 1 giờ
                CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic(); 

                return ResponseEntity.ok()
                        .contentType(contentType)
                        .cacheControl(cacheControl)
                        .body(article.getThumbnailImageData());
            }
        }
        // Trả về lỗi 404 nếu không tìm thấy bài viết hoặc ảnh thumbnail.
        return ResponseEntity.notFound().build();
    }
    
    /**
     * API endpoint để xử lý việc người dùng bày tỏ cảm xúc (reactions) với bài viết.
     * Nhận loại cảm xúc và cảm xúc đã chọn trước đó (nếu có) từ frontend để cập nhật database.
     * @param articleId ID của bài viết được tương tác.
     * @param payload Map chứa loại cảm xúc mới ('emotion') và loại cảm xúc cũ ('previousEmotion').
     * @return ResponseEntity chứa số lượng cảm xúc cập nhật dưới dạng JSON.
     */
    @PostMapping("/api/articles/{articleId}/react")
    @ResponseBody // Đảm bảo Spring trả về dữ liệu JSON thay vì tìm kiếm view.
    public ResponseEntity<Map<String, Long>> reactToArticle(
            @PathVariable Integer articleId,
            @RequestBody Map<String, String> payload) {
        
        String reactionType = payload.get("emotion");         // Loại cảm xúc mới mà người dùng chọn
        String previousEmotion = payload.get("previousEmotion"); // Loại cảm xúc đã chọn trước đó (nếu có)

        // Tìm bài viết để đảm bảo nó tồn tại
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Article article = articleOptional.get();

        // Xử lý logic thêm/bớt cảm xúc dựa trên lựa chọn của người dùng
        if (reactionType != null && !reactionType.isEmpty()) {
            if ("null".equals(reactionType) && previousEmotion != null && !previousEmotion.isEmpty()) {
                // Trường hợp: Người dùng hủy cảm xúc đã chọn (nhấn lại nút)
                // Tìm một bản ghi của cảm xúc cũ và xóa nó
                List<Reaction> reactionsToDelete = reactionRepository.findByArticleArticleIdAndReactionType(articleId, previousEmotion);
                if (!reactionsToDelete.isEmpty()) {
                    reactionRepository.delete(reactionsToDelete.get(0)); // Xóa bản ghi đầu tiên tìm thấy
                }
            } else {
                // Trường hợp: Người dùng chọn một cảm xúc mới
                // Nếu người dùng đã có cảm xúc khác trước đó, xóa cảm xúc cũ đi
                if (previousEmotion != null && !previousEmotion.isEmpty() && !reactionType.equals(previousEmotion)) {
                    List<Reaction> reactionsToDelete = reactionRepository.findByArticleArticleIdAndReactionType(articleId, previousEmotion);
                    if (!reactionsToDelete.isEmpty()) {
                        reactionRepository.delete(reactionsToDelete.get(0));
                    }
                }
                // Thêm một bản ghi cảm xúc mới vào database
                Reaction newReaction = new Reaction(article, reactionType);
                reactionRepository.save(newReaction);
            }
        }

        // Cập nhật và trả về số lượt cảm xúc mới nhất sau khi thay đổi
        long updatedLikes = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "like");
        long updatedHappy = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "happy");
        long updatedSad = reactionRepository.countByArticleArticleIdAndReactionType(articleId, "sad");

        // Trả về số lượt đếm mới dưới dạng JSON
        return ResponseEntity.ok(Map.of("likes", updatedLikes, "happy", updatedHappy, "sad", updatedSad));
    }
}