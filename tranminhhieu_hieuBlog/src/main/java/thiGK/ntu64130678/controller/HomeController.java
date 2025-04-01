package thiGK.ntu64130678.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userName", "Admin");
        return "frontEndViews/index";  // Chỉ ra đường dẫn chính xác tới trang index trong thư mục con 'frontEndViews'
    }
}
