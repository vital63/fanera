
package com.springapp.wood.service;

import com.springapp.wood.domain.Wood;

import com.springapp.light.util.ImageUtilLight;
import com.springapp.light.util.UploadMultipartFileUtilLight;
import com.springapp.wood.dao.WoodDao;
import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import com.springapp.wood.domain.WoodWidth;
import com.springapp.wood.util.Utils;
import com.springapp.wood.util.excel.ParserExcelWood;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class WoodServiceImpl implements WoodService {

        
    @Autowired WoodDao woodDao;
    
    @Override 
    @Transactional
    public void uploadWood(String path, MultipartFile[] file) {
        for (int i = 0; i < file.length; i++) {
            try {
                File uploadFile = UploadMultipartFileUtilLight.uploadFile(path, file[i]);
                Wood wood = ParserExcelWood.readWood(uploadFile);
                woodDao.saveWood(wood);
                uploadFile.delete();
                System.out.println("Successfully uploaded machine: " + file[i].getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Failed to upload machine file: " + e.getMessage());
            }
        }
    }
    
    @Override
    @Transactional
    public void saveWood(Wood wood) {
        System.out.println("saveWood: " + wood);
        woodDao.saveWood(wood);
    }

    @Override 
    @Transactional
    public List<Wood> getListWood() {
        return  woodDao.getListWood();
    }
    
    @Override 
    @Transactional
    public List<WoodLength> getListWoodLength() {
        return  woodDao.getListWoodLength();
    }
    
    @Override 
    @Transactional
    public List<WoodWidth> getListWoodWidth(){
        return  woodDao.getListWoodWidth();
    }
    
    @Override 
    @Transactional
    public List<WoodThickness> getListWoodThickness(){
        return  woodDao.getListWoodThickness();
    }
    
    @Override 
    @Transactional
    public List<WoodType> getListWoodType() {
        return  woodDao.getListWoodType();
    }
    
    @Override 
    @Transactional
    public List<Wood> getListWood(String lengths, String widths, String thicknesses, String type){
        if (lengths == null && widths == null && thicknesses == null && type == null)
            return getListWood();
            
        List<Integer> listLengths = Utils.stringDelimToListInt(lengths);
        List<Integer> listWidths = Utils.stringDelimToListInt(widths);
        List<Integer> listThicknesses = Utils.stringDelimToListInt(thicknesses);
        List<String> listTypes = Utils.stringDelimToListString(type);
        
        return woodDao.getListWood(listLengths, listWidths, listThicknesses, listTypes);
    }
    
    @Override 
    @Transactional
    public Wood getWoodByUrl(String url) {
        return  woodDao.getWoodByUrl(url);
    }
    
    @Transactional
    public String[] listImage(String path) {
        return ImageUtilLight.getListImage(path);
    }
    
    @Transactional
    public void uploadImagesWood(String path, MultipartFile[] images) {
        for (int i = 0; i < images.length; i++) {
            try {
                UploadMultipartFileUtilLight.uploadImage(path, images[i]);
                System.out.println("Successfully uploaded photo: " + images[i].getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Failed to upload photo: " + e.getMessage());
            }
        }
    }
    
    @Override 
    @Transactional
    public Wood getWoodById(String id) {
        return  woodDao.getWoodById(id);
    }
    
    @Override 
    @Transactional
    public void renewFiltersWoods() {
        woodDao.renewWoodLength();
        woodDao.renewWoodWidth();
        woodDao.renewWoodThickness();
        woodDao.renewWoodType();
    }


    @Override 
    @Transactional
    public List<Wood> getListWoodByIds(String ids) {
        String[] idsArr = (ids != null) ? ids.split(",") : null;
        if(idsArr == null)
            return null;
        
        List<Wood> result = new ArrayList<Wood>();
        for (String ids1 : idsArr) 
            result.add(getWoodById(ids1));

        return result;
        
//        String[] idsArr = (ids != null) ? ids.split(",") : null;
//        return lightOfficeDao.getListLighByIds(idsArr);
    }
    
    @Override 
    @Transactional
    public List<Wood> getListWoodFromSearch(String word) {
      return woodDao.getListWoodFromSearch( word);
    }
    
}
