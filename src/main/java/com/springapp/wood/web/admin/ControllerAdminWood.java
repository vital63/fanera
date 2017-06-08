package com.springapp.wood.web.admin;

import com.springapp.wood.service.interfaces.WoodService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class ControllerAdminWood {

    final private String WOOD_URL = "wood";
    
    @Autowired  WoodService woodService;
        
    @RequestMapping(value = "/wood/renewFiltersWood", method = RequestMethod.GET)
    public String renewFiltersWood(){
        woodService.renewFiltersWoods();
        return "redirect:/admin/" + WOOD_URL;
    }
    
//    @Secured(value =  "ROLE_ADMIN_LIGHT") //   ???
    @RequestMapping(value = "/" + WOOD_URL, method = RequestMethod.GET)
    public ModelAndView adminWesold() {
        ModelAndView mv = new ModelAndView("admin/wood_admin");
        mv.addObject("listWood", woodService.getListWood());
        return mv;
    }
     
    @RequestMapping(value = "/wood/add", method = RequestMethod.POST)
    public String adminWesold_Add_POST(@RequestParam("textFile") MultipartFile[] wesoldinf, HttpServletRequest request){
        if (wesoldinf != null && wesoldinf.length > 0) {
            String path = request.getServletContext().getRealPath("") + "/resources/";
            woodService.uploadWood(path, wesoldinf);
        }
        return "redirect:/admin/" + WOOD_URL;
    }
    
     ///       --------     Add pictures   ----------
    @RequestMapping(value = "/wood/photos", method = RequestMethod.GET)
    public ModelAndView galleryWood(HttpServletRequest request, Map<String,Object> map){
        ModelAndView mv = new ModelAndView("admin/gallery_wood");
        String path = request.getServletContext().getRealPath("") + "/resources/assets/images/products/wood";
        mv.addObject("imageListWood", woodService.listImage(path));
        return mv;
    }
    
    @RequestMapping(value = "/wood/photos/upload", method = RequestMethod.POST)
    public String adminUploadImagesLathe(@RequestParam("imageLathe") MultipartFile[] images, HttpServletRequest request){
        if (images != null && images.length > 0) {
            String path = request.getServletContext().getRealPath("") + "/resources/assets/images/products/wood";
//            lighOfficeService.uploadImagesLathe(path, images);
            woodService.uploadImagesWood(path, images);
        }
        return "redirect:/admin/wood/photos";
    }

//        @RequestMapping(value = "/gallerylathes/remove", method = RequestMethod.GET)
//    public String removeImageLathe(@RequestParam("image") String image, HttpServletRequest request) {
//        String path = request.getServletContext().getRealPath("") + "/resources/images/products/lathes";
//        workWithFilesService.removeImage(path,image);
//        return "redirect:/admin/gallerylathes";
//    }
  
}