package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.DataNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.SameNameException;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute("fileUpload") MultipartFile fileUpload, Model model, Principal principal) throws IOException {
        if (fileUpload.isEmpty()) {
            model.addAttribute("errorMessage", "Please select a file to upload.");
            return "/result";
        }

        fileService.saveFile(fileUpload, Integer.parseInt(principal.getName()));
        return "redirect:/home";
    }

    @GetMapping("/download/{id}")
    public String downloadFile(@PathVariable("id") Integer fileId , Model model, HttpServletResponse response, Principal principal) throws IOException {
        fileService.download(fileId, response, Integer.parseInt(principal.getName()));
        return null;
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable("id") Integer fileId, Model model, Principal principal) {
        fileService.deleteFile(fileId, Integer.parseInt(principal.getName()));
        return "redirect:/home";
    }
}
