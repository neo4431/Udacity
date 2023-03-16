package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/save-note")
    public String addNote(@ModelAttribute("note") NoteEntity noteEntity, Principal principal, Model model) {
        if(noteService.createOrUpdateNote(noteEntity, Integer.parseInt(principal.getName())) == 0) {
            model.addAttribute("isSuccess", false);
            return "/result";
        }
        return "redirect:/home";
    }

    @PostMapping("/delete/{noteId}")
    public String editNote(@PathVariable("noteId") Integer noteId, Principal principal, Model model) {
        noteService.deleteByNoteId(noteId, Integer.parseInt(principal.getName()));
        return "redirect:/home";
    }
}
