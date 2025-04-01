package thiGK.ntu64130678.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import thiGK.ntu64130678.model.DataStorage;
import thiGK.ntu64130678.model.Page;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userName", "Admin");
        return "frontEndViews/index"; 
    }
    @GetMapping("/page/all")
    public String listPages(Model model) {
        List<Page> pages = DataStorage.getPages();
        
        if (pages == null || pages.isEmpty()) {
            model.addAttribute("errorMessage", "Không có trang nào được tìm thấy!");
            return "frontEndViews/error";
        }
        
        model.addAttribute("pages", pages);
        return "frontEndViews/page-list"; 
    }


    @GetMapping("/view/{id}")
    public String viewPage(@PathVariable int id, Model model) {
        Page page = DataStorage.getPages().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (page == null) {
            model.addAttribute("errorMessage", "Page not found!");
            return "frontEndViews/error";
        }

        model.addAttribute("page", page);
        return "frontEndViews/page-view"; // Cập nhật đường dẫn
    }

    @GetMapping("/add")
    public String addPageForm(Model model) {
        model.addAttribute("page", new Page(0, "", "", "", null));
        return "frontEndViews/page-add"; // Cập nhật đường dẫn
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute Page page) {
        int newId = DataStorage.getPages().size() + 1;
        page.setId(newId);
        DataStorage.getPages().add(page);
        return "redirect:/page/all"; // Chuyển hướng về danh sách
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable int id) {
        DataStorage.getPages().removeIf(p -> p.getId() == id);
        return "redirect:/page/all"; // Chuyển hướng về danh sách
    }
}
