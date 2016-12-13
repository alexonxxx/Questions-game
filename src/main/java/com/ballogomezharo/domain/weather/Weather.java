package com.ballogomezharo.domain.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex Haro Medina on 05/12/2016.
 */
public class Weather {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String main;

    @JsonProperty
    private String description;

    @JsonProperty
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
