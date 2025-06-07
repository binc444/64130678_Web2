package ntu.tmhieu.Controllers;

import java.io.IOException;
import java.util.Base64;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Model.Category;
import ntu.tmhieu.Repository.ArticleRepository;
import ntu.tmhieu.Repository.CategoryRepository;
import ntu.tmhieu.Service.ArticleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Inject Repository để tương tác với dữ liệu bài viết
    @Autowired
    private ArticleRepository articleRepository;

    // Inject Repository để tương tác với dữ liệu danh mục
    @Autowired
    private CategoryRepository categoryRepository;
    
    // tương tác với dữ liệu danh mục
    @Autowired
    private ArticleService articleService;

    /**
     * Hiển thị trang dashboard quản trị, liệt kê danh sách các bài viết.
     * Cho phép sắp xếp danh sách bài viết theo các tiêu chí khác nhau.
     * @param model Đối tượng Model để truyền dữ liệu tới view.
     * @param sortBy Tiêu chí sắp xếp (mặc định là articleId).
     * @param sortOrder Thứ tự sắp xếp (mặc định là giảm dần).
     * @return Tên view của trang dashboard.
     */
    @GetMapping("/dashboard")
    public String adminDashboard(
            Model model,
            @RequestParam(defaultValue = "articleId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {

        // Xác định hướng và tiêu chí sắp xếp
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        // Lấy tất cả bài viết từ cơ sở dữ liệu và truyền vào model
        List<Article> articles = articleRepository.findAll(sort);
        model.addAttribute("articles", articles);

        // Truyền thông tin sắp xếp hiện tại để giữ trạng thái trên giao diện
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        return "admin/dashboard";
    }

    /**
     * Hiển thị form để thêm một bài viết mới.
     * Tạo một đối tượng Article rỗng và danh sách Category để người dùng lựa chọn.
     * @param model Đối tượng Model để truyền dữ liệu tới view.
     * @return Tên view của form bài viết.
     */
    @GetMapping("/articles/new")
    public String showArticleForm(Model model) {
        Article newArticle = new Article();
        newArticle.setCategory(new Category()); // Đảm bảo đối tượng Category không null cho form
        model.addAttribute("article", newArticle);
        model.addAttribute("categories", categoryRepository.findAll()); // Lấy tất cả danh mục
        return "admin/article-form";
    }

    /**
     * Hiển thị form để chỉnh sửa một bài viết hiện có.
     * Tìm bài viết theo ID và truyền dữ liệu của nó cùng danh sách Category vào form.
     * @param id ID của bài viết cần chỉnh sửa.
     * @param model Đối tượng Model để truyền dữ liệu tới view.
     * @param ra RedirectAttributes để thêm thông báo flash khi có lỗi.
     * @return Tên view của form bài viết hoặc chuyển hướng về dashboard nếu không tìm thấy.
     */
    @GetMapping("/articles/edit/{id}")
    public String showEditArticleForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            // Đảm bảo category không null để tránh lỗi khi render form
            if (article.getCategory() == null) {
                article.setCategory(new Category());
            }
            model.addAttribute("article", article);
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/article-form";
        } else {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy bài viết để sửa.");
            return "redirect:/admin/dashboard";
        }
    }

    /**
     * Xử lý việc lưu bài viết mới hoặc cập nhật bài viết hiện có.
     * Bao gồm xử lý upload ảnh thumbnail và các thông tin khác của bài viết.
     * @param article Đối tượng Article được gửi từ form.
     * @param thumbnailImageFile File ảnh thumbnail được upload.
     * @param currentThumbnailImageData Dữ liệu ảnh thumbnail hiện có (dạng Base64) nếu không upload ảnh mới.
     * @param currentThumbnailMimeType Kiểu MIME của ảnh thumbnail hiện có.
     * @param ra RedirectAttributes để thêm thông báo thành công/thất bại.
     * @return Chuyển hướng về trang dashboard sau khi lưu.
     */
    @PostMapping("/articles/save")
    public String saveArticle(
            Article article,
            @RequestParam("thumbnailImageFile") MultipartFile thumbnailImageFile,
            @RequestParam(value = "currentThumbnailImageData", required = false) String currentThumbnailImageData,
            @RequestParam(value = "currentThumbnailMimeType", required = false) String currentThumbnailMimeType,
            RedirectAttributes ra) {

        try {
            // Xử lý ảnh thumbnail: ưu tiên ảnh mới, nếu không thì giữ ảnh cũ, nếu không có thì đặt null
            if (!thumbnailImageFile.isEmpty()) {
                article.setThumbnailImageData(thumbnailImageFile.getBytes());
                article.setThumbnailMimeType(thumbnailImageFile.getContentType());
            } else if (article.getArticleId() != null && currentThumbnailImageData != null && !currentThumbnailImageData.isEmpty()) {
                // Giải mã Base64 thành byte[] nếu là ảnh cũ
                article.setThumbnailImageData(Base64.getDecoder().decode(currentThumbnailImageData));
                article.setThumbnailMimeType(currentThumbnailMimeType);
            } else {
                article.setThumbnailImageData(null);
                article.setThumbnailMimeType(null);
            }

            // Đặt ngày xuất bản: nếu là bài mới thì lấy thời gian hiện tại, nếu sửa thì giữ nguyên
            if (article.getArticleId() == null) {
                article.setPublicationDate(LocalDateTime.now());
            } else {
                articleRepository.findById(article.getArticleId()).ifPresent(existingArticle -> {
                    article.setPublicationDate(existingArticle.getPublicationDate());
                });
            }

            // Nếu người dùng không chọn Category (ID là null), đặt Category của bài viết về null
            if (article.getCategory() != null && article.getCategory().getCategoryId() == null) {
                article.setCategory(null);
            }

            // Lưu bài viết vào cơ sở dữ liệu
            articleRepository.save(article);
            ra.addFlashAttribute("successMessage", "Bài viết đã được lưu thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Lỗi khi xử lý ảnh: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Lỗi khi lưu bài viết: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }
    
    /**
     * Xử lý yêu cầu xóa bài viết.
     * @param id ID của bài viết cần xóa.
     * @param redirectAttributes Để thêm thông báo flash.
     * @return Chuyển hướng về trang quản lý bài viết.
     */
    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            articleService.deleteArticle(id);
            redirectAttributes.addFlashAttribute("message", "Bài viết đã được xóa thành công!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Xóa bài viết thất bại: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/admin/dashboard"; // Chuyển hướng về trang dashboard sau khi xóa
    }

    /**
     * API endpoint để lấy thông tin chi tiết của một bài viết theo ID (dùng cho AJAX).
     * @param id ID của bài viết cần lấy.
     * @return Đối tượng Article hoặc null nếu không tìm thấy.
     */
    @GetMapping("/api/articles/{id}")
    public Article getArticleById(@PathVariable Integer id) {
        return articleRepository.findById(id).orElse(null);
    }
}