package com.ballogomezharo.domain.weather;

public class Service {
    private String city;
    private double temp;
    private double MaxTemp;
    private double MinTemp;
    private String iconUrl;
    private String main;
    private int humidity;
    private String date;

    public Service(String city, double temp, double MaxTemp, double MinTemp, String icon, String main, int humidity, String date){
        this.city = city;
        this.temp = Math.rint((temp - 272.15)*10)/10; //Convert to celcius
        this.MaxTemp = Math.rint((MaxTemp - 272.15)*10)/10; //Convert to celcius
        this.MinTemp =Math.rint((MinTemp - 272.15)*10)/10; //Convert to celcius
        this.iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
        this.main = main;
        this.humidity = humidity;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        MaxTemp = maxTemp;
    }

    public double getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(double minTemp) {
        MinTemp = minTemp;
    }

    public String getIcon() { return iconUrl;}

    public void setIcon(String icon) { this.iconUrl = icon; }

    public String getIconUrl() { return iconUrl; }

    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public String getMain() { return main; }

    public void setMain(String main) { this.main = main; }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

