/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.dao;


import com.springapp.light.domain.LightOffice;
import com.springapp.light.domain.LightOfficePower;
import com.springapp.light.domain.LightOfficeSize;
import com.springapp.light.domain.LightOfficeType;


import java.util.List;



public interface WoodDao {
    
    public List<LightOffice> getListLightOffice();
    public List<LightOffice> getListLightOffice(String emergency, String [] arrPowers, String size, String type);
    public LightOffice getLightByUrl(String url);
    public LightOffice getLightById(String id);
    
    public void saveLightOffice(LightOffice lightOffice);
 
    public List<LightOfficePower> getListLightOfficePower();
    public List<LightOfficeSize> getListLightOfficeSize();
    public List<LightOfficeType> getListLightOfficeType();

    
    public void  renewLightOfficePower();
    public void  renewLightOfficeSize();

    public void  renewLightOfficeType();
    public List<LightOffice> getListLighByIds(String [] ids);
    public List<LightOffice> getListLightFromSearch(String word);
    
}
