package com.ballogomezharo.domain;

import java.io.Serializable;

public class Puntuacio implements Serializable {

    private Categoria categoria;
    private int correctes;
    private int incorrectes;
    private double percentatgeCorrectes;



    public Puntuacio(Categoria categoria, int correctes, int incorrectes) {
        this.categoria = categoria;
        this.correctes = correctes;
        this.incorrectes = incorrectes;
        this.percentatgeCorrectes = this.calcularPercentatgeCorrectes();
    }

    private double calcularPercentatgeCorrectes() {
        int total = this.correctes + this.incorrectes;
        try {
            return Math.rint(100*((this.correctes * 100) / total))/100;
        }catch(ArithmeticException a){
            return 0;
        }
    }

    public void sumarCorrectes() {
        this.correctes += 1;
        this.percentatgeCorrectes = this.calcularPercentatgeCorrectes();
    }

    public void sumarIncorrectes() {
        this.incorrectes += 1;
        this.percentatgeCorrectes = this.calcularPercentatgeCorrectes();
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public int getCorrectes() {
        return correctes;
    }

    public int getIncorrectes() {
        return incorrectes;
    }

    public double getPercentatgeCorrectes() {
        return percentatgeCorrectes;
    }

    public void setCorrectes(int correctes) {
        this.correctes = correctes;
    }

    public void setIncorrectes(int incorrectes) {
        this.incorrectes = incorrectes;
    }


    public String toString() {
        return "Puntuació de: " + this.categoria + " té " + this.correctes + " respostes correctes i " + this.incorrectes + " respostes incorrectes";
    }
}