package ntu.tmhieu.TongHopGK.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String trangChu() {
		return "frontEndViews/index";
	}
	
	@GetMapping("/about")
	public String gioiThieu() {
		return "frontEndViews/about";
	}
}
