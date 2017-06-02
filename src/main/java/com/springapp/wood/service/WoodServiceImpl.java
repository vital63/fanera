
package com.springapp.wood.service;

import com.springapp.wood.domain.Wood;

import com.springapp.light.util.ImageUtilLight;
import com.springapp.light.util.UploadMultipartFileUtilLight;
import com.springapp.wood.dao.WoodDao;
import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import com.springapp.wood.domain.WoodWidth;
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
    
    private List<WoodLength> getFakeListWoodLength(){
        List<WoodLength> result = new ArrayList<WoodLength>();
        result.add(new WoodLength(1250));
        result.add(new WoodLength(1540));
        result.add(new WoodLength(1840));
        result.add(new WoodLength(2140));
        result.add(new WoodLength(2440));
        return result;
    }
    
    @Override 
    @Transactional
    public List<WoodLength> getListWoodLength() {
        return getFakeListWoodLength();
//        return  lightOfficeDao.getListLightOfficePower();
    }
    
    private List<WoodWidth> getFakeListWoodWidth() {
        List<WoodWidth> result = new ArrayList<WoodWidth>();
        result.add(new WoodWidth(1250));
        result.add(new WoodWidth(1540));
        result.add(new WoodWidth(1840));
        return result;
    }
    
    @Override 
    @Transactional
    public List<WoodWidth> getListWoodWidth(){
        return getFakeListWoodWidth();
//        return  lightOfficeDao.getListLightOfficeSize();
    }
    
    private List<WoodThickness> getFakeListWoodThickness() {
        List<WoodThickness> result = new ArrayList<WoodThickness>();
        result.add(new WoodThickness(12));
        result.add(new WoodThickness(14));
        result.add(new WoodThickness(16));
        return result;
    }    
    
    @Override 
    @Transactional
    public List<WoodThickness> getListWoodThickness(){
        return getFakeListWoodThickness();
//        return  lightOfficeDao.getListLightOfficeSize();
    }
    
    
    private List<WoodType> getFakeListWoodType() {
        List<WoodType> result = new ArrayList<WoodType>();
        result.add(new WoodType("Laminated particle board"));
        result.add(new WoodType("Raw plywood"));
        return result;
    }
    
    @Override 
    @Transactional
    public List<WoodType> getListWoodType() {
        return getFakeListWoodType();
//        return  lightOfficeDao.getListLightOfficeType();
    }
    
    @Override 
    @Transactional
    public List<Wood> getListWood(String length, String width, String thickness, String type){
//        if (length == null && width == null && thickness == null && type == null)
            return getListWood();
//        else
            
//        String[] arrPowers = (powers != null) ? powers.split(",") : null;
//        return  lightOfficeDao.getListLightOffice(emergency, arrPowers, size, type);
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
