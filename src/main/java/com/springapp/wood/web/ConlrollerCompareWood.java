
package com.springapp.wood.web;

import com.springapp.mvc.domain.message.Message;
import com.springapp.wood.service.WoodService;
import com.springapp.wood.domain.Wood;
import com.springapp.wood.util.compare_wood.CompareCartWood;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConlrollerCompareWood {
    
    final private String COMPARE_WOOD = "compare-wood";
    
    @Autowired  WoodService woodService;
    
    @RequestMapping(value =  "/" + COMPARE_WOOD, method = RequestMethod.GET)
    public String compareWood(HttpSession session) {
        CompareCartWood compareCartWood = getAttributeFromSession(session, COMPARE_CART_WOOD, new CompareCartWood());
        return "redirect:/" + COMPARE_WOOD + "?ids=" + compareCartWood.getIds();
    }
    
    @RequestMapping(value =  "/" + COMPARE_WOOD, method = RequestMethod.GET, params = "ids")
    public ModelAndView compareWoodWithID( @RequestParam(value = "ids", required = false) String ids, HttpSession session)
    {
        ModelAndView mv = new ModelAndView("wood/wood_compare");
        List<Wood> listWood;
        if(ids!=null && !ids.isEmpty()) 
            listWood = woodService.getListWoodByIds(ids);
        else 
            listWood = new ArrayList<>();
        
        Message mes = new Message(listWood .toString()); 
        
        mv.addObject("message", mes); // it is need for feed back !!!!!!
        mv.addObject("listWood", listWood );
        session.setAttribute("currentpagewithpage", COMPARE_WOOD + "?ids=" + ids);
        return mv;
    }
    
    private static final String COMPARE_CART_WOOD = "compareCartWood";
    
    @RequestMapping(value =  "/compare-wood-add" , method = RequestMethod.GET)
    public String addProductToCompareList(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pagereturn") String pagereturn,
            HttpSession session ) 
    {
        CompareCartWood compareCartWood = getAttributeFromSession(session, COMPARE_CART_WOOD, new CompareCartWood());
        
        if(woodService.getWoodById(id) != null)
            compareCartWood.addId(id);
        
        session.setAttribute(COMPARE_CART_WOOD, compareCartWood);
        return "redirect:" + getAttributeFromSession(session, "currentpagewithpage", "index");
    }
    
    @RequestMapping(value = "/compare-wood-del-{id}", method = RequestMethod.GET)
    public String delFromCompare(@PathVariable("id") String id, HttpSession session) 
    {
        CompareCartWood compareCartWood = getAttributeFromSession(session, COMPARE_CART_WOOD, new CompareCartWood());
        compareCartWood.removeId(id); // delete from compare
        session.setAttribute(COMPARE_CART_WOOD, compareCartWood);
        return "redirect:/" + COMPARE_WOOD;
    }
    
    private static <T> T getAttributeFromSession(HttpSession session, String attrName, T defValue) {
        T result = (T) session.getAttribute(attrName);
        if (result == null) 
            result = defValue;
        
        return result;
    }
}
