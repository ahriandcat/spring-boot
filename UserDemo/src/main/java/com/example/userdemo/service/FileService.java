package com.example.userdemo.service;


import com.example.userdemo.exception.BadRequestException;
import com.example.userdemo.util.Utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    //path folder de upload file
    private final Path rootPath = Paths.get("uploads");

    public FileService() {
        createFolder(rootPath.toString());
    }

    //tao folder
    public void createFolder (String path) {
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    //upload file
    public String uploadFile(int id, MultipartFile file){
        //tao folder tuong ung vs userId
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        //validate file
        validate(file);

        //b3 : tao path tuong ung voi file upload
        String generateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir+"/"+generateFileId);


        try {
            //su dung buffer de luu du lieu tu file
            BufferedOutputStream stream  = new BufferedOutputStream(new FileOutputStream(fileServer));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/"+id+"/files/"+generateFileId;

        }catch (Exception e) {
            throw new  RuntimeException("Loi khi upload");
        }
    }

    public void validate (MultipartFile file){
        //kiem tra ten file
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")){
            throw new BadRequestException ("ten file ko hop le");
        }


        //kiem tra dinh dang file
        String fileExtension = Utils.getExtensionFile(fileName);
        if (!Utils.checkFileExtension(fileExtension)){
            throw new BadRequestException("file ko hop le");
        }


        //kiem tra dung luong file
        if ((double)file.getSize() / 1_000_000L > 2){
            throw new BadRequestException("file ko duoc vuot qua 2mb");
        }
    }

    //xem file
    public byte[] readfile(int id, String fileId){
        // lay duong dan file tuong ung voi user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        //kiem tra userPath co ton tai hay ko
        if (!Files.exists(userPath)){
            throw new RuntimeException("Loi doc file" + fileId);
        }
        try {
            Path file =  userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                InputStream inputStream = resource.getInputStream();
                byte[] byteArray = StreamUtils.copyToByteArray(inputStream);
                inputStream.close();
                return byteArray;
            }
            else {
                throw new RuntimeException("Loi doc file" + fileId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Loi doc file" + fileId);
        }
    }


    //xoa file
    public void deleteFile(int id,String fileId){
        Path userPath = rootPath.resolve(String.valueOf(id));


        //kiem tra userPath co ton tai hay ko
        if (!Files.exists(userPath)){
            throw new RuntimeException("Loi doc file" + fileId);
        }


        Path file = userPath.resolve(fileId);
        if (!file.toFile().exists()){
            throw new RuntimeException("file" + fileId + "khong tim thay");
        }
        file.toFile().delete();
    }

    //lay danh sach file
    public List<String> getFile(int id){
        Path userPath = rootPath.resolve(String.valueOf(id));


        //kiem tra userPath co ton tai hay ko
        if (!Files.exists(userPath)){
            return new ArrayList<>();
        }
        File[] files =  userPath.toFile().listFiles();

        return Arrays.stream(files).map(file -> file.getName()).sorted((f1, f2) -> f2.compareTo(f1))
                .map(file -> "/api/v1/users/"+id+"/files/"+file).collect(Collectors.toList());
    }
}
