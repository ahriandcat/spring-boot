package com.example.todowiththymeleaf.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private boolean status;
}
