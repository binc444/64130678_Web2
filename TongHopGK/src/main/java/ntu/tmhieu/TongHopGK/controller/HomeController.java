package ntu.tmhieu.TongHopGK.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ntu.tmhieu.TongHopGK.model.SinhVien;

@Controller
public class HomeController {
    private List<SinhVien> studentList = new ArrayList<>(); // Khai báo danh sách sinh viên

    @GetMapping("/")
    public String trangChu() {
        return "frontEndViews/index";
    }
    
    @GetMapping("/about")
    public String gioiThieu() {
        return "frontEndViews/about";
    }
    
    @GetMapping("/addNew")
    public String showAddStudentForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "frontEndViews/addNew";
    }

    @PostMapping("/addNew")
    public String addStudent(@ModelAttribute SinhVien sinhVien) {
        studentList.add(sinhVien);
        return "redirect:/studentList";
    }

    @GetMapping("/studentList")
    public String showStudentList(Model model) {
        model.addAttribute("students", studentList);
        return "frontEndViews/studentList";
    }
}
