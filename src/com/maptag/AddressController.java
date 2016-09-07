package com.maptag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ModelAndView address() {
        return new ModelAndView("address", "command", new Address());
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("MapTagSpring")Address address, ModelMap model) {
        model.addAttribute("ip", address.getIp());

        return "result";
    }
}
