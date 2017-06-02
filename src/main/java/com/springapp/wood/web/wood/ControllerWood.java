package com.springapp.wood.web.wood;


import com.springapp.mvc.domain.message.Message;
import com.springapp.mvc.util.PrintInFile;
import com.springapp.wood.service.WoodService;

import com.springapp.wood.domain.Wood;
import com.springapp.wood.util.Utils;
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
public class ControllerWood extends PrintInFile {

    @Autowired
    WoodService woodService;
 
    @RequestMapping(value = "/wood", method = RequestMethod.GET)
    public ModelAndView wood_all(@RequestParam(value = "types", required = false) String types,
                                 @RequestParam(value = "lengths", required = false) String lengths,
                                 @RequestParam(value = "widths", required = false) String widths,
                                 @RequestParam(value = "thicknesses", required = false) String thicknesses,
                                 HttpSession session ) {
        ModelAndView mv = new ModelAndView("wood/wood_all"); 
        
        mv.addObject("listWood", woodService.getListWood(lengths, widths, thicknesses, types));
        mv.addObject("listWoodType", woodService.getListWoodType()); //  for   filter
        mv.addObject("listWoodLength", woodService.getListWoodLength()); //  for   filter
        mv.addObject("listWoodWidth", woodService.getListWoodWidth()); //  for   filter
        mv.addObject("listWoodThickness", woodService.getListWoodThickness()); //  for   filter

        mv.addObject("types", Utils.stringDelimToListString(types)); // it is need for feed back !!!!!!
        mv.addObject("lengths", Utils.stringDelimToListInt(lengths)); // it is need for feed back !!!!!!
        mv.addObject("widths", Utils.stringDelimToListInt(widths)); // it is need for feed back !!!!!!
        mv.addObject("thicknesses", Utils.stringDelimToListInt(thicknesses)); // it is need for feed back !!!!!!
        
        mv.addObject("message", new Message()); // it is need for feed back !!!!!!
        session.setAttribute("search", "plywood"); // for search

        String params = "" ;
        if (lengths!=null) params += "&length=" + lengths;
        if (widths!=null) params += "&width=" + widths;
        if (thicknesses!=null && !thicknesses.equals("")) params += "&thickness=" + thicknesses;
        if (types!=null && !types.equals("")) params += "&type=" + types;
        session.setAttribute("currentpagewithpage", "/wood?" + params);
          
        return mv;
    }
    
    @RequestMapping(value = "/wood-{url}", method = RequestMethod.GET)
    public ModelAndView wood_one(@PathVariable ("url") String url, HttpSession session) {
    
        ModelAndView mv = new ModelAndView("wood/wood_one");
        Wood wood = woodService.getWoodByUrl(url);
        mv.addObject("wood", wood);
        
        mv.addObject("message", new Message("Wood " +wood.getType() , wood.getId())); // it is need for feed back !!!!!!
        session.setAttribute("search", "wood"); // for search
        session.setAttribute("currentpagewithpage", "/wood-" + url);
        return mv;
    }
 

//
//    @RequestMapping(value =  "/plywood/pdf/{url}", method = RequestMethod.GET)
//public ResponseEntity<byte[]> getPdfSingle(HttpServletRequest request, @PathVariable ("url") String url )throws Exception {
////    public ResponseEntity<byte[]> getPdfSingle(@RequestParam("productId") String productId,
////                                               @RequestParam(value = "company", required = false) String company,
////                                               @RequestParam(value = "director", required = false) String director,
////                                               @RequestParam("showPrice") String showPrice,
////                                               HttpServletRequest request) throws Exception {
//
//        String path = request.getServletContext().getRealPath("") + "/resources";
////        return getPDFOfferSingle(path, productId, company, "director", Boolean.getBoolean("true"));
//        return getPDFOfferSingle(path, url, "company", "director", Boolean.getBoolean("true"));
//      }
    
    
    

//    public ResponseEntity<byte[]> getPDFOfferSingle(String path, String url, String company, String director, boolean showPrice) throws Exception {
////        Hmc machine = hmcDAO.getMachine(productId);
//        Wood wood = woodService.getWoodByUrl(url);
//        String pathPdf = LightPdf.createPdfLight(path, wood, company, director, showPrice);
//
//        File file = new File(pathPdf);
//        byte[] contents = new byte[(int) file.length()];
//        new FileInputStream(file).read(contents);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        String filename = "Light-" + light.getModel() + ".pdf";
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        return new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
//    }
    
}
