package poly.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import poly.edu.service.MailService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
    
    List<Mail> queue = new ArrayList<>();

    @Override
    public void send(Mail mail) {
        try {
            // 1. Tạo đối tượng Mail
            MimeMessage message = mailSender.createMimeMessage();

            // 2. Tạo đối tượng hỗ trợ ghi nội dung Mail
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Ghi thông tin người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2. Ghi thông tin người nhận
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // 2.3. Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);
            
            // 2.4. Đính kèm file 
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            // 3. Gửi mail
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void send2(Mail mail) {
        try {
            // 1. Tạo đối tượng Mail
            MimeMessage message = mailSender.createMimeMessage();

            // 2. Tạo đối tượng hỗ trợ ghi nội dung Mail
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Ghi thông tin người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2. Ghi thông tin người nhận
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // 2.3. Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);
            
            // 2.4. Đính kèm file 
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            // 3. Gửi mail
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }
    @Override
    public void push(Mail mail) {
        queue.add(mail);
        System.out.println("Mail đã được thêm vào hàng đợi: " + mail.getTo());
    }

    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queue.isEmpty()) {
            try {
                this.send(queue.remove(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
