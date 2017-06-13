package com.springapp.wood.web.cart;

import com.springapp.mvc.domain.message.Message;
import com.springapp.wood.cart.ShoppingCart;
import com.springapp.wood.domain.ShoppingCartItem;
import com.springapp.wood.service.interfaces.WoodOrdersService;
import com.springapp.wood.domain.Wood;
import com.springapp.wood.domain.WoodOrder;
import com.springapp.wood.service.interfaces.WoodService;
import com.springapp.wood.util.Utils;
import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerCartWood {
    
    @Autowired
    private WoodOrdersService woodOrdersService;
    
    @Autowired
    private WoodService woodService;
    
    @RequestMapping(value = "/wood-cart", method = RequestMethod.GET)
    public void cart(HttpSession session, Map<String, Object> map) 
    {
        ShoppingCart cart = Utils.getAttributeFromSession(session, "cart", new ShoppingCart());
        cart.calculateTotal(); // GDP (for example)
        session.setAttribute("cart", cart);
        session.setAttribute("currentpagewithpage", "wood-cart"); //
        map.put("message", new Message()); // it is need for feed back !!!!!!
    }  
    
    @RequestMapping(value = "/wood-cart-add",  method = RequestMethod.GET)
    public String addProductToShoppingCart(@RequestParam(value = "id") String id, HttpSession session)
    {
        ShoppingCart cart = Utils.getAttributeFromSession(session, "cart", new ShoppingCart());

        Wood wood = woodService.getWoodById(id);
        if(wood!=null)  
            cart.addItem(wood);
 
        session.setAttribute("cart", cart); 
        String currentpagewithpage = Utils.getAttributeFromSession(session, "currentpagewithpage", "index");
        return "redirect:" + currentpagewithpage; 
    }
    
//    @RequestMapping(value = "/wood-cart-from", method = RequestMethod.GET)
//    public String from_cart( @RequestParam(value = "id") int id, HttpSession session)
//    {
//       if((  vmcService.getVmc(id, model))!=null ) return "redirect:/vmc"+id; 
//       else if(( latheService.getLathe(id, model))!=null ) return "redirect:/lathe"+id;
//       else if(( hmcService.getHmc(id, model))!=null ) return "redirect:/hmc"+id;
//       else if(( grinderService.getGrinder(id, model))!=null ) return "redirect:/grinder"+id;
//       else
//       return "redirect:/index";
//    }
 
    @RequestMapping(value = "/wood-cart-del", method = RequestMethod.GET)
    public String deleleFromCart(@RequestParam("id") String id, HttpSession session) 
    {
        return updateQuantity(id, 0, session);  
    }
    
    @RequestMapping(value = "/updatequantity", method = RequestMethod.GET)
    public String updateQuantity(@RequestParam(value = "id") String id,
            @RequestParam(value = "quantity") int quantity,
            HttpSession session ) 
    {
        ShoppingCart cart = Utils.getAttributeFromSession(session, "cart", new ShoppingCart());
        Wood wood = woodService.getWoodById(id);
        cart.update(wood, "" + quantity);
        session.setAttribute("cart", cart);
        String currentpagewithpage = Utils.getAttributeFromSession(session, "currentpagewithpage", "index");
        return "redirect:"+currentpagewithpage;
    }
    /// ------------  </ Basket  :End > ---------------   
          
    // -------- ---------  checkout : start ------------- -----------
        
    @RequestMapping(value = "/wood-checkout", method = RequestMethod.GET)
    public void checkout(HttpSession session,  Map<String, Object> map) 
    {
        ShoppingCart cart = Utils.getAttributeFromSession(session, "cart", new ShoppingCart());
        cart.calculateTotal();
        session.setAttribute("cart", cart);
        WoodOrder order = new WoodOrder();
        map.put("order", order); 
        session.setAttribute("currentpagewithpage", "wood-checkout");
    }  
    
    @RequestMapping(value = "/wood-checkout", method = RequestMethod.POST)
    public ModelAndView checkoutPOST(@Valid @ModelAttribute ("order") WoodOrder order, BindingResult result, HttpSession session) 
    {
        if(result.hasErrors())
            return new ModelAndView("wood-checkout").addObject("order", order);
      
        List <ShoppingCartItem> list = Utils.getAttributeFromSession(session, "cart", new ShoppingCart()).getItems();
        String query = "";
        for(ShoppingCartItem item: list)
        {
            query += item.getWood().getId() + "<br>";
            query += item.getQuantity() + "<br>";

            order.getCartItems().add(item);
            item.setOrder(order);
        }
        
        order.setRequest(query);
        order.setDatatime(new Date());
        order.setStatus("in");
        
        woodOrdersService.addOrder(order);
        
        session.setAttribute("cart", null);
        session.setAttribute("currentpagewithpage", "wood-checkout");
        
        return new ModelAndView("checkoutfinish"); 
    }  
}
