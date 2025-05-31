package ntu.tmhieu.Controllers;

import java.util.List;
import java.util.concurrent.TimeUnit; // Import này

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl; // Import này
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Model.Image;
import ntu.tmhieu.Repository.ImageRepository;
import ntu.tmhieu.Service.ArticleService;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "frontEndView/index";
    }

    // Endpoint để phục vụ hình ảnh từ dữ liệu BLOB
    @GetMapping("/images/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer imageId) {
        Image image = imageRepository.findById(imageId).orElse(null);

        if (image != null && image.getImageUrl() != null && image.getMimeType() != null) {
            // Sử dụng MediaType.parseMediaType() để chuyển đổi chuỗi MIME Type thành đối tượng MediaType
            MediaType contentType = MediaType.parseMediaType(image.getMimeType());

            CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic();

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .cacheControl(cacheControl)
                    .body(image.getImageUrl());
        } else {
            // Trả về 404 Not Found nếu không tìm thấy ảnh, dữ liệu ảnh rỗng hoặc không có MIME Type
            return ResponseEntity.notFound().build();
        }
    }
}