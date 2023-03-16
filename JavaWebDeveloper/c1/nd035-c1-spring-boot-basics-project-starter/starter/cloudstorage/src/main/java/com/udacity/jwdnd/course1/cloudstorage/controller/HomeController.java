package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.ICredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.IFileService;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private INoteService noteService;

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private IFileService fileService;

    @GetMapping("/home")
    public String getHomePage(Model model, Principal principal) {
        Integer userId = Integer.parseInt(principal.getName());

        // Fetch Notes
        model.addAttribute("note", new NoteEntity());
        model.addAttribute("noteList", noteService.findAllByUserId(userId));

        // Fetch Credentials
        List<CredentialEntity> credentialList = credentialService.findAllByUserId(userId);

        model.addAttribute("credentialList", credentialService.findAllByUserId(userId));
        model.addAttribute("pwMap", credentialService.decryptPasswords(credentialList));

        // Fetch Files
        model.addAttribute("fileList", fileService.getAllFiles(userId));
        return "home";
    }
}
