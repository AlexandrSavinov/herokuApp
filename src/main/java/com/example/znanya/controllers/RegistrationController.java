package com.example.znanya.controllers;

import com.example.znanya.domain.Message;
import com.example.znanya.domain.Role;
import com.example.znanya.domain.User;
import com.example.znanya.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {

    String separator = File.separator;

    @Autowired
    private UserRepo userRepo;

    @Value("${upload.patch}")
    private String uploadPatch;

    @GetMapping("/registr")
    public String registrPage(){
        return "registr";
    }

    @PostMapping("/registr")
    public String addUser(User user,
                          Map <String,Object> map,
                          @RequestParam("file") MultipartFile file){

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            map.put("message","User Exists!");
            return "registr";
        }

        if(file != null && !file.isEmpty()){

            File uploadDir = new File(uploadPatch);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String UUIDfile = UUID.randomUUID().toString();
            String resultFileName = UUIDfile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPatch+separator+resultFileName+separator));

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("EROR for FILE!!!!!!!");
            }

            user.setFilename(resultFileName);
        }


        user.setActive(true);
        user.setRoles(Collections.singleton(new Role(1L,"USER")));
        userRepo.save(user);

        return "redirect:/login";
    }
}
