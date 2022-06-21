package com.example.userdemo.controller;


import com.example.userdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class UserViewController {

    private final UserService userService;

    @GetMapping("/")
    public String getUser(Model model) {
        model.addAttribute("users",userService.getUsers());
        return "index";
    }
    @GetMapping("/create")
    public String getCreatePage(){
        return "create";
    }
    @GetMapping("/detail/{id}")
    public String getDetailPage(@PathVariable int id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("images",userService.getFile(id));
        return "detail";
    }
}
