package thiGK.ntu64130678.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	//Xử lý khi muốn thêm mới một page
	@GetMapping("/page/new")
	public String showCreatePageForm(Model model) {
	    model.addAttribute("page", new Page(0, "", "", "", 0)); // ID tạm là 0
	    model.addAttribute("pageTitle", "Thêm Page mới");
	    return "page-form";
	}
	
	// lưu page vào lớp Page
	@PostMapping("/page/save")
	public String savePage(@ModelAttribute Page page) {
	    int newId = ListPostNPage.getPages().size() + 1;
	    page.setId(newId);
	    ListPostNPage.addPage(page); // <-- lưu trong danh sách tĩnh
	    return "redirect:/page/all";
	}
	
	
	// Xử lý khi nhấn view
	@GetMapping("/page/view/{id}")
	public String viewPage(@PathVariable("id") int id, Model model) {
	    Page pageToView = null;
	    for (Page p : ListPostNPage.getPages()) {
	    	//Nếu tìm thấy Page có id đúng với id người dùng yêu cầu, gán nó vào biến pageToView
	        if (p.getId() == id) {
	            pageToView = p;
	            break;
	        }
	    }
	    if (pageToView != null) {
	        model.addAttribute("page", pageToView); //Gửi pageToView vào Model để đưa sang Thymeleaf
	        model.addAttribute("pageTitle", "Chi tiết Page");
	        return "page-view"; // tên file HTML
	    } else {
	        return "redirect:/page/all"; // nếu không tìm thấy thì quay lại danh sách
	    }
	}
}
