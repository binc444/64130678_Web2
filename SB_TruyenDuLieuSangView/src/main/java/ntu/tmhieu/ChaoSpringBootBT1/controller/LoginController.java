package ntu.tmhieu.ChaoSpringBootBT1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller  
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String pw = request.getParameter("password");

        // Kiểm tra tài khoản
        if ("admin".equals(id) && "12345".equals(pw)) {
            return "redirect:/danhsachsinhvien"; 
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu"); 
        return "login"; 
    }
}
