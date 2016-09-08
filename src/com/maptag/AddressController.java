package com.maptag;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AddressController {
    private AddressService addressService;

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ModelAndView address() {
        return new ModelAndView("address", "command", new Address());
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("MapTagSpring")Address address, ModelMap model) {
        addressService = new AddressService();
        try {
            ServerCoordinates coordinates = addressService.getCoordinatesOfIP(address.getIp());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("ip", address.getIp());
        model.addAttribute("latitude", address.getLatitude());

        return "result";
    }
}
