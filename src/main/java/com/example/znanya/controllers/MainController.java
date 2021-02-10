package com.example.znanya.controllers;

import com.example.znanya.domain.Message;
import com.example.znanya.domain.User;
import com.example.znanya.repos.MessageRepo;
import com.example.znanya.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;
    private String messages;


    @GetMapping("/")
    public String home(Model model, HttpSession httpSession){

        messages = (String) httpSession.getAttribute("MY_SESSION_MESSAGES");

        model.addAttribute("sessionMessages", messages);

        System.out.println(messages);

        return "index";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {

        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByAuthor(filter);
        } else {
            messages = messageRepo.findAll();
        }


        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/get")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model
                      ) {

        Message message = new Message(text, tag, user.getUsername(), user.getFilename());
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @GetMapping("main/{id}")
    public String delMsg(@PathVariable Long id){
        messageRepo.deleteById(id);
        return "redirect:/user";
    }

}
