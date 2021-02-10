package com.example.znanya.controllers;

import com.example.znanya.domain.Message;
import com.example.znanya.domain.Role;
import com.example.znanya.domain.User;
import com.example.znanya.repos.MessageRepo;
import com.example.znanya.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String userList(@RequestParam(required = false) String filter,Model model){

        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByAuthor(filter);
        } else {
            messages = messageRepo.findAll();
        }


        model.addAttribute("messageList", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("userList",userRepo.findAll());
        return "userList";
    }



    @GetMapping("{user}")
    public String getUserId(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "userEdit";
    }
    @PostMapping("/update")
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam String username,
                           @RequestParam String password){
        user.setUsername(username);
        user.setPassword(password);

        userRepo.save(user);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") User user){
        userRepo.delete(user);
        return  "redirect:/user";
    }

}
