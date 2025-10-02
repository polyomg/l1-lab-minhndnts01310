package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.entity.Product;

@Controller
public class ProductController {

    @GetMapping("/product/form")
    public String form() {
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(Product product, Model model) {
        // đưa object vào model để view hiển thị
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        return "product/form";
    }
}
