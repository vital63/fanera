/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.dao.interfaces;


import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.Wood;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import com.springapp.wood.domain.WoodWidth;
import java.util.List;



public interface WoodDao {
    
    public List<Wood> getListWood();
    public List<Wood> getListWood(List<Integer> listLengths, List<Integer> listWidths, List<Integer> listThicknesses, List<String> types);
    public Wood getWoodByUrl(String url);
    public Wood getWoodById(String id);
    
    public void saveWood(Wood wood);
 
    public List<WoodLength> getListWoodLength();
    public List<WoodWidth> getListWoodWidth();
    public List<WoodThickness> getListWoodThickness();
    public List<WoodType> getListWoodType();

    
    public void  renewWoodLength();
    public void  renewWoodWidth();
    public void  renewWoodThickness();
    public void  renewWoodType();

    public List<Wood> getListWoodByIds(String [] ids);
    public List<Wood> getListWoodFromSearch(String word);
    
}
