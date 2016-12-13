package com.ballogomezharo.webControllers;

import com.ballogomezharo.serveis.FService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alex Haro Medina on 05/12/2016.
 */
@Controller
@RequestMapping("/")
public class WeatherController {
    FService serveiTemps;

    public WeatherController(FService serveiTemps){
        this.serveiTemps = serveiTemps;
    }

    @GetMapping("Weather/{city}")
    public String obtenirTemps(@PathVariable("city") String city, Model model){
        model.addAttribute("forecast", serveiTemps.getWeather(city));
        return "weather";
    }

    @GetMapping("Weather")
    public String buscarCiutat(){
        return "SearchCity";
    }

}
