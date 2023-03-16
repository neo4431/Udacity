package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.ICredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    private ICredentialService credentialService;

    @PostMapping("/save-credential")
    public String addCredential(@ModelAttribute("credential") CredentialEntity credential, Principal principal, Model model) throws Exception {
        credentialService.createOrUpdateCredential(credential, Integer.parseInt(principal.getName()));
        return "redirect:/home";
    }

    @PostMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Principal principal, Model model) {
        credentialService.deleteByCredentialId(credentialId, Integer.parseInt(principal.getName()));
        return "redirect:/home";
    }
}
