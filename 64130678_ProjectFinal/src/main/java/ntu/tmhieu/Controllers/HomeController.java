package ntu.tmhieu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Service.ArticleService;

@Controller
public class HomeController {

	@Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "frontEndView/index";
    }
}
