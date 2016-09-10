package main.java.com.maptag;

import com.maxmind.geoip2.record.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    private AddressService addressService;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ModelAndView address() {
        return new ModelAndView("address", "command", new Address());
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("MapTagSpring")Address address, ModelMap model) throws InterruptedException {

        addressService = new AddressService();
        Location location = addressService.findCoordinatesOfIP(address.getIp());

        model.addAttribute("ip", address.getIp());
        model.addAttribute("latitude", location.getLatitude());
        model.addAttribute("longitude", location.getLongitude());

        return "result";
    }
}
