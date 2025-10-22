package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nav {
@GetMapping("/nav")
public String nav(Model model) {
	return "nav";
}
}
