package com.ballogomezharo.domain;


public class Pregunta {
    private String enunciat;
    private Categoria categoria;
    private Resposta resposta1;
    private Resposta resposta2;
    private Resposta resposta3;
    private Resposta resposta4;


    public Pregunta(String enunciat, Categoria categoria, Resposta resposta1, Resposta resposta2, Resposta resposta3, Resposta resposta4) {
        this.enunciat = enunciat;
        this.categoria = categoria;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.resposta4 = resposta4;
    }



    public Resposta getResposta1() {
        return resposta1;
    }

    public Resposta getResposta2() {
        return resposta2;
    }

    public Resposta getResposta3() {
        return resposta3;
    }

    public Resposta getResposta4() {
        return resposta4;
    }

    public String getEnunciat() {
        return enunciat;
    }

    public Resposta respostaCorrecte(){
        if(this.resposta1.getCorrecta()==true){
            return resposta1;
        }

        if(this.resposta2.getCorrecta()==true){
                return resposta2;
        }
        if (this.resposta3.getCorrecta() == true) {
            return resposta3;
        }
        return resposta4;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String toString() {
        String resultat = "";
        resultat += this.categoria.toString() + "\n" + enunciat ;
        return resultat;
    }

    public void setEnunciat(String enunciat) {
        this.enunciat = enunciat;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setResposta1(Resposta resposta1) {
        this.resposta1 = resposta1;
    }

    public void setResposta2(Resposta resposta2) {
        this.resposta2 = resposta2;
    }

    public void setResposta3(Resposta resposta3) {
        this.resposta3 = resposta3;
    }

    public void setResposta4(Resposta resposta4) {
        this.resposta4 = resposta4;
    }
}
