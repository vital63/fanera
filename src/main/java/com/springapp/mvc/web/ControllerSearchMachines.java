/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.springapp.mvc.web;

import com.springapp.light.service.LighOfficeService;
import com.springapp.mvc.util.PrintInFile;



import com.springapp.mvc.domain.message.Message;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerSearchMachines extends PrintInFile {
    
    @Autowired  LighOfficeService lighService;
    
    
    private void putLightFilters (ModelAndView mv){
        mv.addObject("listLightType", lighService.getListLightOfficeType()); //  for   filter
    }
    
    
    @RequestMapping(value = "/searchmachines", method = RequestMethod.GET)
    public ModelAndView vmc_select( @RequestParam(value = "search", defaultValue = "dmg") String key,
            HttpSession session  ) {
        
        ModelAndView mv ;
        String search_page = (String) session.getAttribute("search");
        if (search_page==null)  search_page = "vmc";
        
        switch(search_page) {
            
            case "light":
                mv = new ModelAndView ("light/light_all");
                mv.addObject("listLightOffice", lighService.getListLightFromSearch(key));
                putLightFilters(mv);
                break;
                
            default:
                mv = new ModelAndView ("light/light_all");
                mv.addObject("listLightOffice", lighService.getListLightFromSearch(key));
                putLightFilters(mv);
        }
        
        mv.addObject("message", new Message()); // it is need for feed back !!!!!!
        session.setAttribute("currentpagewithpage", "/searchmachines?search="+key);
        
        return mv ;
    }
    
}
