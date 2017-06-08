package com.springapp.wood.web.cart;

import com.springapp.mvc.domain.message.Message;
import com.springapp.wood.cart.ShoppingCart;
import com.springapp.wood.service.interfaces.WoodOrdersService;
import com.springapp.wood.domain.Wood;
import com.springapp.wood.service.interfaces.WoodService;
import com.springapp.wood.util.Utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        
//    @RequestMapping(value = "/wood-checkout", method = RequestMethod.GET)
//    public void checkout(HttpSession session,  Map<String, Object> map) {
//        
////        ModelAndView mv = new ModelAndView("checkout");
//        
//        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//        if (cart==null)  cart = new ShoppingCart();
//        cart.calculateTotal(); // GDP (for example)
//         session.setAttribute("cart", cart); 
////         Order order = new Order();
////         order.setFirstname("Dimitry");
//        
//
//         map.put("order", new WoodOrder()); 
//         
//        String currentpagewithpage = (String)session.getAttribute("currentpagewithpage");              
//          if (currentpagewithpage == null) currentpagewithpage = "index";
//          
//         session.setAttribute("currentpagewithpage", "checkout");
//        
////       return mv; 
//    }  
//    
//    @RequestMapping(value = "/wood-checkout", method = RequestMethod.POST)
//    public ModelAndView checkoutPOST(@Valid @ModelAttribute ("order") WoodOrder order, BindingResult result, HttpSession session) 
//    {
//         ModelAndView mv = new ModelAndView("checkoutfinish");
//        
//           if(result.hasErrors()){
//             mv = new ModelAndView("checkout");
//             mv.addObject("order", order);
////           List<TypeStanina> listTypeStanina= typeStaninaDAO.listTypeStanina();
////           mv.addObject("listTypeStanina", listTypeStanina);
//             return mv;             
//             }  
//      
//        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//        if (cart==null)  cart = new ShoppingCart();
////        cart.calculateTotal("0"); // GDP (for example)
////        orderJDBCTemplate
////         session.setAttribute("cart", cart); 
//        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
//String dateInString = "2017-01-01";
////        try {
////            Date date = sdf.parse(dateInString);
//            Date date = new Date();
//        order.setDatatime(date);
////        } catch (ParseException ex) {
////            Logger.getLogger(ControllerCartWood.class.getName()).log(Level.SEVERE, null, ex);
////        }
//
////        order.setDatatime("2015-06-02");
//        order.setStatus("in");
//        
//      List <ShoppingCartItem> list =   cart.getItems();
//  String query ="";    
//      for(ShoppingCartItem ttt: list){
//          query += ttt.getWood().getId();
//          query += "<br>->" + ttt.getWood().getName();
//          query += "<br>";
//      }
//        order.setRequest(query);
//        
//        
//    woodOrdersService.addOrder(order);
//        cart = null;
//        session.setAttribute("cart", cart);
//        session.setAttribute("currentpagewithpage", "checkout");
//        
//        return mv; 
//    }  

}
