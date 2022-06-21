package com.example.todowiththymeleaf.controller;


import com.example.todowiththymeleaf.model.Todo;
import com.example.todowiththymeleaf.request.CreateTodoRequest;
import com.example.todowiththymeleaf.request.UpdateTodoRequest;
import com.example.todowiththymeleaf.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class TodoController {
    private final TodoService todoService;

    //lay danh sach tat ca cong viec
    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam(required = false, defaultValue = "") String status) {
        return todoService.getTodos(status);
    }

    //lay cong viec theo id

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {

        return todoService.getTodoById(id);
    }

    //tao moi cong viec
    @PostMapping("/todos")
    //tra ve thong tin
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoRequest request) {
        Todo todo = todoService.createTodo(request);
        return new ResponseEntity<>(todo,HttpStatus.CREATED);
    }

    // sua cong viec
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id,request);
    }

    //xoa cong viec
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
