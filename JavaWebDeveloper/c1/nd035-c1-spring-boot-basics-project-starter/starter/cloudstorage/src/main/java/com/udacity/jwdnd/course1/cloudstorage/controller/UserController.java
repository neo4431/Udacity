package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserEntity());
        }

        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errorMessage", "Username and password cannot be blank.");
            return "redirect:/signup";
        }

        if (userService.insert(user) == 0) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists!!!");
            return "redirect:/signup";
        }

        redirectAttributes.addFlashAttribute("isSuccess", true);
        return "redirect:/signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
