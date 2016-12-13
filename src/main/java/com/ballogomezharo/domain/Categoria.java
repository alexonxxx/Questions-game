package com.ballogomezharo.domain;
import java.io.Serializable;

public class Categoria implements Serializable{
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return categoria;
    }

    public boolean equals(Object o) {
        if (o instanceof Categoria) {
            if (this.categoria.equals(((Categoria) o).getCategoria())) {
                return true;
            }
        }
        return false;
    }
}


