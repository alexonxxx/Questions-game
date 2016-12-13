package com.ballogomezharo.domain;


import java.util.*;
import java.io.Serializable;

public class Usuari implements Serializable{
    private String nom;
    private String mail;
    private String contrasenya;
    private int puntuacioTotal;
    private int preguntesProposades;
    private int preguntesModerades;
    private List<Puntuacio> puntuacions;
    private String role;

    public Usuari(){

        afegirPuntuacions();
        puntuacioTotal=0;
        preguntesModerades=0;
        preguntesProposades=0;
        this.role="ROLE_USER";
    }

    public Usuari(String nom, String mail, String contrasenya) {
        this.nom = nom;
        this.mail = mail;
        this.contrasenya = contrasenya;
        afegirPuntuacions();
        puntuacioTotal=0;
        preguntesModerades=0;
        preguntesProposades=0;
        this.role="ROLE_USER";

    }

    public Usuari(String nom, String mail, String contrasenya,List<Puntuacio> puntuacions, int puntuacioTotal, int preguntesProposades, int preguntesModerades){
        this(nom, mail, contrasenya);
        this.puntuacions=puntuacions;
        this.puntuacioTotal = puntuacioTotal;
        this.preguntesProposades = preguntesProposades;
        this.preguntesModerades = preguntesModerades;
        this.role="ROLE_USER";
    }
    private void afegirPuntuacions(){
        this.puntuacions= new ArrayList<Puntuacio>();
        this.puntuacions.add(new Puntuacio(new Categoria("CIENCIA"),0,0));
        this.puntuacions.add(new Puntuacio(new Categoria("ESPORTS"),0,0));
        this.puntuacions.add(new Puntuacio(new Categoria("ENTRETENIMENT"),0,0));
        this.puntuacions.add(new Puntuacio(new Categoria("HISTORIA"),0,0));
        this.puntuacions.add(new Puntuacio(new Categoria("GEOGRAFIA"),0,0));
        this.puntuacions.add(new Puntuacio(new Categoria("ART"),0,0));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    public List<Puntuacio> getPuntacions(){
        return this.puntuacions;
    }

    public int getPuntuacioTotal() {
        return puntuacioTotal;
    }

    public void setPuntuacioTotal(int puntuacioTotal) {
        this.puntuacioTotal = puntuacioTotal;
    }

    public int getPreguntesProposades() {
        return preguntesProposades;
    }

    public void setPreguntesProposades(int preguntesProposades) {
        this.preguntesProposades = preguntesProposades;
    }

    public int getPreguntesModerades() {
        return preguntesModerades;
    }

    public void setPreguntesModerades(int preguntesModerades) {
        this.preguntesModerades = preguntesModerades;
    }


    public String getRole() {
        return role;
    }
    public void addRole(String role) {
        this.role=role;
    }


    public String toString(){
        return this.nom +", " + this.mail +", " + this.contrasenya + ". Puntuació total: " + this.puntuacioTotal + " " +
                "Preguntes proposades: " + this.preguntesProposades + " Preguntes moderades: " + this.preguntesModerades;
    }
}

