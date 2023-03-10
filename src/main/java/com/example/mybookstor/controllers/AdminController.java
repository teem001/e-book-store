package com.example.mybookstor.controllers;

import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.enums.Role;
import com.example.mybookstor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController
{
   private final UserService userService;

    @GetMapping
    public String getAllUser(Model model){
        Map<Role,List<UserEntity> > users = userService.getAllUser();

        model.addAttribute("allUser", users);

        return "super_admin_page";

    }

    @GetMapping("/update")
    public String updateUserRole(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "update_page";
    }
    @PutMapping
    public String updateUserRole(@ModelAttribute ("user") UserEntity user){
       userService.switchUserRole(user.getRole(), user.getUserId());
       return "redirect:/admin";
    }




}
