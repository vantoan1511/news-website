package com.vtoan1517.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class UploadController {

    @GetMapping
    public String showUploadPage() {
        return "admin/upload/upload";
    }

    @PostMapping
    public String handleFileUpload(HttpServletRequest request,
                                   @RequestParam CommonsMultipartFile[] files, Model model) throws IOException {
        if (files != null) {
            String path = request.getContextPath();
            String desPath = path + "/uploads/";
            File desDir = new File(desPath);

            if (!desDir.exists()) {
                desDir.mkdirs();
            }

            /*for (CommonsMultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    assert fileName != null;
                    String filePath = desPath + fileName;
                    File des = new File(filePath);
                    file.transferTo(des);
                }
            }*/
        }
        model.addAttribute("uploadSuccess", "Upload successfully!!!");
        return "admin/upload/upload";
    }
}
