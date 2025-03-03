package jp.ivs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class welcomeController {
	 @GetMapping("/viewWelcome.html") // Truy cập bằng /viewWelcome.html
	    public String showWelcomePage() {
	        return "viewWelcome"; // Spring sẽ tìm viewWelcome.jsp trong /WEB-INF/views/
	    }
}
