package com.example.userdemo.controller;

import com.example.userdemo.dto.UserDto;
import com.example.userdemo.model.User;
import com.example.userdemo.request.CreateUserRequest;
import com.example.userdemo.request.UpdatePasswordRequest;
import com.example.userdemo.request.UpdateUserRequest;
import com.example.userdemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/search")
    public List<UserDto> searchUsers(@RequestParam String name) {
        return userService.searchUser(name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUsers(@PathVariable int id){

        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable int id,@RequestBody UpdateUserRequest request){
        return userService.updateUser(id,request);
    }

    @PutMapping("/users/{id}/update-password")
    public void updatePassword(@PathVariable int id,@RequestBody UpdatePasswordRequest request){
        userService.updatePassword(id,request);
    }

    @PostMapping("/users/{id}/forgot-password")
    public String forgotPassword(@PathVariable int id){
        return userService.forgotPassword(id);
    }

    //upload file
    @PostMapping("/users/{id}/upload-file")
    public  String uploadFile(@PathVariable int id, @ModelAttribute("file") MultipartFile file){
        return userService.uploadFile(id,file);
    }

    //xem file
    @GetMapping(value = "/users/{id}/files/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readFile(@PathVariable int id, @PathVariable String fileId){
        return userService.readFile(id, fileId);
    }


    //xoa file
    @DeleteMapping("/users/{id}/files/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable int id, @PathVariable String fileId){
        userService.deleteFile(id,fileId);
    }

    //lay danh sach file
    @GetMapping("users/{id}/files")
    public List<String> getFiles(@PathVariable int id){
        return userService.getFile(id);
    }
}
