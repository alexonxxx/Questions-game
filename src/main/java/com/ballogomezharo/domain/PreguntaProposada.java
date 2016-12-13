package com.ballogomezharo.domain;


import java.io.Serializable;

public class PreguntaProposada implements Serializable {

    private  Usuari usuari;
    private  String enunciat;
    private  Categoria categoria;
    private  Resposta resposta1;
    private  Resposta resposta2;
    private  Resposta resposta3;
    private  Resposta resposta4;


    public PreguntaProposada(Usuari usuari, String enunciat, Categoria categoria, Resposta resposta1, Resposta resposta2, Resposta resposta3, Resposta resposta4){

        this.usuari = usuari;
        this.enunciat = enunciat;
        this.categoria = categoria;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.resposta4 = resposta4;
    }

    public PreguntaProposada(Usuari usuari) {
        this(usuari,null,null,null,null,null,null);


    }

    public Usuari getUsuari(){
        return this.usuari;
    }

    public String getEnunciat(){
        return this.enunciat;
    }



    public void setEnunciat(String enunciat) {
        this.enunciat = enunciat;
}

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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



    public void setResposta1(Resposta resposta1) {
        this.resposta1 = resposta1;
    }

    public void setResposta3(Resposta resposta3) {
        this.resposta3 = resposta3;
    }
    public void setResposta2(Resposta resposta2) {
        this.resposta2 = resposta2;
    }

    public void setResposta4(Resposta resposta4) {
        this.resposta4 = resposta4;
    }

    public void setUsuari (Usuari usuari) {
        this.usuari = usuari;
    }

    public String toString() {
        String resultat = "";
        resultat += "Aquesta pregunta l'ha proposat: " + this.usuari.getNom() + "\n";
        resultat += this.categoria.toString() + "\n" + enunciat ;
        return resultat;
    }

}
