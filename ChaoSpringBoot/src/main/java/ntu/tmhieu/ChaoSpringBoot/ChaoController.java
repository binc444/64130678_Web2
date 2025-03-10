package ntu.tmhieu.ChaoSpringBoot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChaoController {
	
	@GetMapping("/hi")
	//action method
	public String xin_Chao() {
		//code xử lý ở đây
		return "helloView";
	}
}
