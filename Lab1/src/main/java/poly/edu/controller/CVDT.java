package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Tinh")
public class CVDT {
@RequestMapping("/form")
public String form( Model model) {
	return "poly/cvs";
}
@RequestMapping("/click")
public String tinh (@RequestParam("dai") int dai,
		            @RequestParam("rong") int rong,
		Model model) {
    // Tính chu vi
    int CV = (dai + rong) * 2;
    int S = (dai*rong);
    model.addAttribute("CV", CV);
    model.addAttribute("S",S);

    return "/poly/cvs";
}
}
