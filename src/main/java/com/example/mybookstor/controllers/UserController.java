package com.example.mybookstor.controllers;

import com.example.mybookstor.requests.UserRequest;
import com.example.mybookstor.response.LoginDto;
import com.example.mybookstor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/home")
    public String landingPage(){
        return "redirect:/books";
    }

    @GetMapping("/register")
    public String signUp(Model model){
        UserRequest userRequest = new UserRequest();

        model.addAttribute("user",userRequest);
        return "register-page";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") UserRequest userRequest ){
        userService.createNewUser(userRequest);
        return "home-page";
    }

    @GetMapping("/login")
    public String userLogin(Model model ){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login",loginDto);
        return "login-page";
    }


}
