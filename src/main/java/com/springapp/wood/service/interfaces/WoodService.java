/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.springapp.wood.service.interfaces;



import com.springapp.wood.domain.Wood;
import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import com.springapp.wood.domain.WoodWidth;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface WoodService {
    
    
    public String[] listImage(String path);
    
    public void uploadImagesWood(String path, MultipartFile[] images);
    public void saveWood(Wood wood);
    
    
//    public Wesold getWesold(int id);
    public Wood getWoodByUrl(String url);
    public Wood getWoodById(String id);
    
    public  List<Wood> getListWoodByIds(String ids);
    public  List<Wood> getListWoodFromSearch(String word);
//    public List<Wesold> getListWesold();
    public List<Wood> getListWood();
    public List<Wood> getListWood(String length, String width, String thickness, String type);
    public void uploadWood(String path, MultipartFile[] file);
    

    public List<WoodLength> getListWoodLength();
    public List<WoodWidth> getListWoodWidth();
    public List<WoodThickness> getListWoodThickness();
    public List<WoodType> getListWoodType();
    
    
    public void renewFiltersWoods();
    
    
}

