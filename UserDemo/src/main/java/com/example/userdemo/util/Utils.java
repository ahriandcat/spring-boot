package com.example.userdemo.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    //lay extension file
    public static String getExtensionFile(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1){
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }


    //kiem tra extension co nam trong danh sach cho phep upload ko

    public static boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png","jpg","jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
