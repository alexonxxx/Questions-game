package com.ballogomezharo.domain.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds {
    @JsonProperty
    private int all;

    public Clouds() {
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
