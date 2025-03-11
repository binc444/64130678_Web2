package ntu.tmhieu.ChaoSpringBootBT1.controller;

import java.util.ArrayList;
import java.util.List;

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
    
 // Lấy danh sách sinh viên
    private List<SinhVien> getData() {
        List<SinhVien> dsSinhVien = new ArrayList<>();
        dsSinhVien.add(new SinhVien("64123456", "Hiếu", 2004, "Nam"));
        dsSinhVien.add(new SinhVien("64456789", "Hưng", 2004, "Nam"));
        dsSinhVien.add(new SinhVien("64789101", "Hoàng", 2004, "Nam"));
        return dsSinhVien;
    }

    // Action hiển thị danh sách sinh viên
    @GetMapping("/danhsachsinhvien")
    public String danhSachSinhVien(Model model) {
        List<SinhVien> dsSinhVien = getData();
        model.addAttribute("dsSV", dsSinhVien);
        return "dsSinhVienView";
    }
}
