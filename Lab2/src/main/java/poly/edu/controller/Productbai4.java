package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import poly.edu.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
public class Productbai4 {
	ArrayList<Product> Danhsach3 = new ArrayList<Product>(
			Arrays.asList(
		            new Product("A", 1.0),
		            new Product("B", 12.0)
		        )
			);
	@GetMapping("/form4/form")
    public String form(Model model) {
        Product p = new Product();
        p.setName("IPhone 30");
        p.setPrice(5000.0);
        model.addAttribute("product1", p); 
        model.addAttribute("items", getItems());
        model.addAttribute("danhsach3", Danhsach3);
        return "form4"; 
    }

    @PostMapping("/form4/save")
    public String save(@ModelAttribute("product2") Product p, Model model) { 
        model.addAttribute("product1", new Product("IPhone 30", 5000.0)); 
        model.addAttribute("product2", p);
        model.addAttribute("items", getItems()); 
        Danhsach3.add(p);
        model.addAttribute("danhsach3", Danhsach3);
        return "form4";
    }

    public List<Product> getItems() {
        return Arrays.asList();
    }
}

