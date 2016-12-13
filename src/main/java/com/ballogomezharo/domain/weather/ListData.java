package com.ballogomezharo.domain.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ListData {
    @JsonProperty
    private long dt;
    @JsonProperty
    private Main main;
    @JsonProperty
    private List<Weather> weather;
    @JsonProperty
    private Clouds clouds;
    @JsonProperty
    private Wind wind;
    @JsonProperty
    private Sys sys;
    @JsonProperty
    private String dt_txt;


    public ListData() {
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return
                "{dt:" + dt +"}";/*
                ", main:" + main +
                ", weather:" + weather +
                ", clouds:" + clouds +
                ", wind:" + wind +
                ", sys:" + sys +
                ", dt_txt:" + dt_txt +
                '}';*/
    }
}
