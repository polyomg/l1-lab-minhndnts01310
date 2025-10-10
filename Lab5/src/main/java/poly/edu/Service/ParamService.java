package poly.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    
    @Autowired
    HttpServletRequest request;
    
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }
    
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
    
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
    
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
    
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return dateFormat.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException("Lỗi sai định dạng thời gian: " + e.getMessage());
            }
        }
        return null;
    }
    
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        
        try {
            String realPath = request.getServletContext().getRealPath(path);
            File uploadDir = new File(realPath);
            
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            File savedFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(savedFile);
            
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }
}