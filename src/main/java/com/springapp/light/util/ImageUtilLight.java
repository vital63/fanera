package com.springapp.light.util;

import java.io.File;

public class ImageUtilLight {
    
    public static String[] getListImage(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        String[] listImage = new String[fList.length];
        for (int i = 0; i < fList.length; i++) {
            if (fList[i].isFile()) {
                listImage[i] = fList[i].getName();
            }
        }
        return listImage;
    }
    
    public static void removeImage(String path, String image) {
        File imgFile = new File(path + "/" + image);
        if (imgFile.delete()) {
            System.out.println(image + " is deleted!");
        } else {
            System.out.println("Deleting " + image + " is failed.");
        }
    }
    
    public static void removeAllImage(String path) {
        String[] img = getListImage( path);
        
        for(String img1 : img)
        {
            File imgFile = new File(path + "/" + img1);
            if (imgFile.delete()) {
                System.out.println(img1 + " is deleted!");
            } else {
                System.out.println("Deleting " + img1 + " is failed.");
            }
        }
        
    }
    
    
}
