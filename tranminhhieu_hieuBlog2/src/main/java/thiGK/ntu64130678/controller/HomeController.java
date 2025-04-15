package thiGK.ntu64130678.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thiGK.ntu64130678.model.ListPostNPage;
import thiGK.ntu64130678.model.Page;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
        return "index";
    }
	
	//Xử lý cho trang page-list
	@GetMapping("/page/all")
    public String getAllPages(Model model) {
        List<Page> pages = ListPostNPage.getPages();
        model.addAttribute("pages", pages);
        model.addAttribute("pageTitle", "Danh sách Page");
        return "page-list";
    }
}
