package com.ballogomezharo.domain.weather;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Sys {
    @JsonProperty
    private String pod;

    public Sys() {
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        return "MSys{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
