package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import poly.edu.entity.Staff2;

@Controller
public class Bai2Controller {
	@RequestMapping("/staff2/create/save")
	public String createSave(Model model,

	@RequestParam("photo_file") MultipartFile photoFile,
	@Valid @ModelAttribute("staff") Staff2 staff, Errors errors) {
	if(!photoFile.isEmpty()) {
	staff.setPhoto(photoFile.getName());
	}
	if(errors.hasErrors()) {
	model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
	} else {
	model.addAttribute("message", "Dữ liệu đã nhập đúng!");
	}
	return "/demo/staff-validate";
	}
}
