package idv.wilson.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class MainController {
 
    @RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
    public String testJspView(Model model) {
    	model.addAttribute("Message", "jsp");
        return "testJsp";
    }
 
    @RequestMapping(value = { "/testThymeleaf" }, method = RequestMethod.GET)
    public String testThymeleafView(Model model) {
    	model.addAttribute("Message", "Thymeleaf");
        return "th_page1";
    }
     
    @RequestMapping(value = { "/testFreeMarker" }, method = RequestMethod.GET)
    public String testFreeMarkerView(Model model) {
    	model.addAttribute("Message", "FreeMarker");
        return "testFreeMarker";
    }
     
}