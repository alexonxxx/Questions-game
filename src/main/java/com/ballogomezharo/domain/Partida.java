package com.ballogomezharo.domain;


import java.util.ArrayList;



public class Partida {

    private Usuari jugador;
    private ArrayList<Pregunta> preguntes;
    private int respostesCorrectes;
    private int respostesIncorrectes;


    public Partida(Usuari jugador){
        this.jugador=jugador;
        this.preguntes= new ArrayList<Pregunta>();
        this.respostesCorrectes=0;
        this.respostesIncorrectes=0;

    }



    public void addPregunta(Pregunta pregunta){
        this.preguntes.add(pregunta);
    }

    public void removePreguntes(){
        this.preguntes.removeAll(this.preguntes);
    }

    public Pregunta getPregunta(int i) {
        return preguntes.get(i);
    }



    public int getRespostesIncorrectes() {
        return respostesIncorrectes;
    }
    public int getRespostesCorrectes() {
        return respostesCorrectes;
    }
    public void sumarCorrecte(){
        this.respostesCorrectes++;
    }
    public void sumarIncorrecte(){
        this.respostesIncorrectes++;
    }

public boolean comprobarResposta(int i,Resposta resposta) {
    if (this.preguntes.get(i).respostaCorrecte() == resposta) {
        sumarCorrecte();
        return true;
    } else {
        sumarIncorrecte();
        return false;
    }
}


    public String toString() {
        return "El jugador és: " + this.jugador.getNom() + " i ha encertat: " + this.respostesCorrectes + " preguntes";
    }







}
