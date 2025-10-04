package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl")
public class OkController {
	@RequestMapping("/ok")
	public String ok() {
	return "ctrl/ok";
	}
	@PostMapping("/ok")
	public String m1(Model model) {
		model.addAttribute("ketqua","m1");
	return "ctrl/ok";
	}
	@GetMapping("/ok")
	public String m2(Model model) {
		model.addAttribute("ketqua","m2");
	return "ctrl/ok";
	}
	@PostMapping(value="/ok",params = "x")
	public String m3(Model model) {
		model.addAttribute("ketqua","m3");
		return "ctrl/ok";
	}
}
