package poly.edu.controller;

import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    
    @Autowired
    CookieService cookieService;
    
    @Autowired
    ParamService paramService;
    
    @Autowired
    SessionService sessionService;
    
    @GetMapping("/account/login")
    public String login1(Model model) {
        
        String rememberedUser = cookieService.getValue("user");
        if (!rememberedUser.isEmpty()) {
            model.addAttribute("rememberedUsername", rememberedUser);
        }
        return "account/login";
    }
    
    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        
        if ("poly".equals(un) && "123".equals(pw)) {
            
            sessionService.set("username", un);
            
            if (rm) {
          
                cookieService.add("user", un, 240);
            } else {
                cookieService.remove("user");
            }
            
           
            sessionService.remove("error");
            
            return "redirect:/home";
        } else {
      
            sessionService.set("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            model.addAttribute("username", un);
            return "account/login";
        }
    }
    
    @GetMapping("/account/logout")
    public String logout() {
        sessionService.remove("username");
        return "redirect:/account/login";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        String username = sessionService.get("username");
        if (username == null) {
            return "redirect:/account/login";
        }
        model.addAttribute("username", username);
        return "home";
    }
}