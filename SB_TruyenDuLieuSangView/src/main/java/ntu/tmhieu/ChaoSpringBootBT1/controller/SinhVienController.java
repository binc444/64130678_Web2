package ntu.tmhieu.ChaoSpringBootBT1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ntu.tmhieu.ChaoSpringBootBT1.model.SinhVien;

@Controller
public class SinhVienController {

    @GetMapping("/sinhvien")
    public String sinhVienInfo(Model model) {
        SinhVien sinhVien = new SinhVien("64130678", "Trần Minh Hiếu", 2004, "Nam");
        model.addAttribute("sinhVien", sinhVien);
        return "sinhVienView";
    }
}
