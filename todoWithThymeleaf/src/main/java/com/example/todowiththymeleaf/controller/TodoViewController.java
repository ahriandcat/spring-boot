package com.example.todowiththymeleaf.controller;


import com.example.todowiththymeleaf.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class TodoViewController {
    private final TodoService todoService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("todos",todoService.getTodos(""));
        return "index";
    }
}
