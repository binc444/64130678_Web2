package ntu.tmhieu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Model.Category;
import ntu.tmhieu.Repository.ArticleRepository;
import ntu.tmhieu.Repository.CategoryRepository;

@Controller
@RequestMapping("/admin") // Tất cả các endpoint trong Controller này sẽ bắt đầu bằng /admin
public class AdminController {
	@Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Lấy tất cả bài viết từ cơ sở dữ liệu
        // findAll() trả về tất cả, sắp xếp theo ID mặc định hoặc theo thứ tự trong CSDL
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/dashboard";
    }
}