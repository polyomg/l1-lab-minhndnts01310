package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import poly.edu.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailFormController {

    @Autowired
    MailService mailService;

    @GetMapping("/form")
    public String form() {
        return "mail/form";
    }

    @PostMapping("/send-form")
    public String sendForm(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(required = false) String cc,
            @RequestParam(required = false) String bcc,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam(required = false) String filenames,
            @RequestParam String action
    ) {
        MailService.Mail mail = MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .filenames(filenames)
                .build();

        if ("send".equals(action)) {
            mailService.send(mail);
            System.out.println("Gửi trực tiếp mail đến: " + to);
        } else {
            mailService.push(mail);
            System.out.println("Mail đã được thêm vào hàng đợi: " + to);
        }

        return "redirect:/mail/form";
    }
}
