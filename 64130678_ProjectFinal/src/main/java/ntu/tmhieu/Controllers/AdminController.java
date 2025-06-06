package ntu.tmhieu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Model.Category;
import ntu.tmhieu.Repository.ArticleRepository;
import ntu.tmhieu.Repository.CategoryRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(
            Model model,
            @RequestParam(defaultValue = "articleId") String sortBy, // Lấy tham số 'sortBy' từ URL, mặc định là "articleId"
            @RequestParam(defaultValue = "desc") String sortOrder) { // Lấy tham số 'sortOrder' từ URL, mặc định là "desc"

        // Xác định hướng sắp xếp (tăng dần hoặc giảm dần) dựa trên giá trị của 'sortOrder'.
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        // Tạo đối tượng Sort với hướng và tên cột cần sắp xếp.
        Sort sort = Sort.by(direction, sortBy);

        // Lấy tất cả bài viết từ cơ sở dữ liệu và áp dụng sắp xếp.
        List<Article> articles = articleRepository.findAll(sort);
        // Thêm danh sách bài viết vào Model để Thymeleaf có thể truy cập.
        model.addAttribute("articles", articles);

        // Lấy tất cả danh mục từ cơ sở dữ liệu.
        List<Category> categories = categoryRepository.findAll();
        // Thêm danh sách danh mục vào Model.
        model.addAttribute("categories", categories);

        // Thêm các tham số sắp xếp hiện tại vào Model.
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        return "admin/dashboard";
    }

    @GetMapping("/api/articles/{id}")
    public Article getArticleById(@PathVariable Integer id) {
        // Tìm bài viết bằng ID. orElse(null) nếu không tìm thấy, trả về null.
        return articleRepository.findById(id).orElse(null);
    }

    @GetMapping("/api/categories/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        // Tìm danh mục bằng ID.
        return categoryRepository.findById(id).orElse(null);
    }
}