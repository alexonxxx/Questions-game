package com.ballogomezharo.serveis;

import com.ballogomezharo.domain.weather.Forecast;
import com.ballogomezharo.domain.weather.Service;
import org.springframework.web.client.RestTemplate;


@org.springframework.stereotype.Service
public class FService {


    public Forecast getForecast(String cityname){
        RestTemplate restTemplate = new RestTemplate();
            Forecast forecast = restTemplate
            .getForObject("http://api.openweathermap.org/data/2.5/forecast/city?q="+cityname+"&APPID=4f8c8d9db21577eb9daa190c3b9b419b",
                    Forecast.class);

        return forecast;

    }

    public Service getWeather(String city){
        Forecast forecast = this.getForecast(city);
        return new Service(forecast.getCity().getName(), forecast.getList().get(0).getMain().getTemp(),
                forecast.getList().get(0).getMain().getTemp_max(), forecast.getList().get(0).getMain().getTemp_min(),
                forecast.getList().get(0).getWeather().get(0).getIcon(), forecast.getList().get(0).getWeather().get(0).getMain(),
                forecast.getList().get(0).getMain().getHumidity(), forecast.getList().get(0).getDt_txt());
    }


}
