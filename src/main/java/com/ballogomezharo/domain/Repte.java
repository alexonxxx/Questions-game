package com.ballogomezharo.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Repte {

    private static Map<Usuari, Integer> ranquing;
    private static List<Pregunta> preguntes;

    public Repte(List<Pregunta> preguntes) {
        this.preguntes = preguntes;
    }

    public void addPlayer(Usuari usuari, int puntuacio) {
        this.ranquing.put(usuari, puntuacio);
    }

    public List<Pregunta> getPreguntes(){
        return this.preguntes;
    }


    public String toString() {
        String resultat = "";
        Set<Usuari> keys = (Set<Usuari>) this.ranquing.keySet();
        for (Usuari u : keys) {
            resultat += u.getNom() + this.ranquing.get(u) + "\n";
        }
        return resultat;
    }
}
