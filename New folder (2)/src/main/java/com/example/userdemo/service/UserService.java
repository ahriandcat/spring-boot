package com.example.userdemo.service;


import com.example.userdemo.dto.UserDto;
import com.example.userdemo.exception.BadRequestException;
import com.example.userdemo.exception.NotFoundException;
import com.example.userdemo.mapper.UserMapper;
import com.example.userdemo.model.User;
import com.example.userdemo.request.CreateUserRequest;
import com.example.userdemo.request.UpdatePasswordRequest;
import com.example.userdemo.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class UserService {
    private List<User> users;

    @Autowired
    public FileService fileService;

    public UserService(){
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }


    //lay danh sach user

    public List<UserDto> getUsers() {
        return users.stream().map(user -> UserMapper.toUserDto(user)).collect(Collectors.toList());
    }


    //tim user theo ten
    public List<UserDto> searchUser(String name){
        return users.stream().filter(user -> user.getName().toLowerCase().contains(name.toLowerCase())).map(UserMapper::toUserDto).collect(Collectors.toList());

    }

    public void deleteUser(int id){
        if (findUser(id).isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }
        users.removeIf(user -> user.getId() == id);
    }

    // helper method
    public Optional<User> findUser(int id){
        return  users.stream().filter(user -> user.getId()==id).findFirst();
    }

    //tao user moi
    public UserDto createUser(CreateUserRequest request){
        //kiem tra email da ton tai chua
        if (findUser(request.getEmail()).isPresent()){
            throw new BadRequestException("Email = "+request.getEmail()+" da ton tai");
        }

        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt(100-4+1)+4);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }
    public Optional<User> findUser(String email){
        return  users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    //lay thong tin user theo id

    public UserDto getUserById(int id){
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }

        return UserMapper.toUserDto(userOptional.get());
    }

    //cap nhat thng tin
    public UserDto updateUser(int id, UpdateUserRequest request){
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }
        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        return UserMapper.toUserDto(user);
    }

    //cap nhat password
    public void updatePassword(int id, UpdatePasswordRequest request){
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }
        User user = userOptional.get();
        //kiem tra old password co chinh xac ko
        if (!user.getPassword().equals(request.getOldPassword())){
            throw new BadRequestException ("mat khau ko chinh xac");
        }

        //kiem tra old = new ko?
        if (request.getNewPassword().equals(request.getOldPassword())){
            throw new BadRequestException ("mat khau cu va moi ko dc trung nhau");
        }

        //tao ra password moi
        user.setPassword(request.getNewPassword());
    }

    //forgot pw
    public String forgotPassword(int id){
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }
        User user = userOptional.get();
        Random rd = new Random();
        String password = String.valueOf(rd.nextInt(1000-100)+100);
        user.setPassword(password);

        return password;
    }

    //upload file
    public String uploadFile(int id, MultipartFile file){
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()){
            throw new NotFoundException("khong co user co id = "+id);
        }

        return fileService.uploadFile(id,file);
    }



    public byte[] readFile(int id, String fileId){
        return fileService.readfile(id,fileId);
    }


    //xoa file
    public void deleteFile(int id, String fileId){
        fileService.deleteFile(id,fileId);
    }

    //lay danh sach file
    public List<String> getFile(int id){
        if (findUser(id).isEmpty()){
            throw new BadRequestException("khong ton tai id = "+id);
        }
        return fileService.getFile(id);

    }
}
