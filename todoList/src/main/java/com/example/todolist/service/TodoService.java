package com.example.todolist.service;


import com.example.todolist.exception.NotFoundException;
import com.example.todolist.model.Todo;
import com.example.todolist.request.CreateTodoRequest;
import com.example.todolist.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    // tao 1 so doi tuong todo
    public TodoService(){
        Random rd = new Random();
        todos = new ArrayList<>();
        todos.add(new Todo(rd.nextInt(100),"lam bai tap",true));
        todos.add(new Todo(rd.nextInt(100),"di hoc",false));
        todos.add(new Todo(rd.nextInt(100),"di lam",true));
    }

    public List<Todo> getTodos(String status) {
        return switch(status){
            case "true" -> todos.stream().filter(p -> p.isStatus()).collect(Collectors.toList());
            case "false" -> todos.stream().filter(p -> !p.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }


    public Todo getTodoById (int id) {
        Optional<Todo> todoOptional = findById(id);
//        if (todoOptional.isPresent()) {
//            return todoOptional.get();
//        }
//      throw new NotFoundException("Khong tim thay cong viec co id = "+id);
        return todoOptional.orElseThrow(() -> {
            throw new NotFoundException("Khong tim thay cong viec co id = "+id);
        });
    }
    //Helper method : tim kiem 1 todo theo id
    public Optional<Todo> findById (int id) {
        return todos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Todo createTodo (CreateTodoRequest request){
        //co the validate title neu de trong
        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100),request.getTitle(),false);
        todos.add(todo);

        return todo;
    }

    public Todo updateTodo (int id, UpdateTodoRequest request) {
//        Optional<Todo> todoOptional = findById(id);
//        if (todoOptional.isEmpty()) {
//            throw new NotFoundException("Khong tim thay cong viec co id = "+id);
//        }
        Todo todo = getTodoById(id);
        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        return todo;
    }

    public void deleteTodo(int id){
        Todo todo = getTodoById(id);
        todos.removeIf(p -> p.getId() == todo.getId());
    }
}
