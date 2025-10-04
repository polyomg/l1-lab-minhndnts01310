package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
@RequestMapping("/poly/hello")
public String Myhello (Model model) {
	model.addAttribute("title","Hello Boi");
	model.addAttribute("subject","Hello");
	return "/poly/hello.html";
}
}
