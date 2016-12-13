package com.ballogomezharo.domain;


import java.io.Serializable;

public class Resposta implements Serializable {
    private String resposta;
    private boolean correcta;


    public Resposta(String resposta) {
        this.resposta=resposta;
        this.correcta = false;
    }

    public Resposta(String resposta, boolean correcta) {
        this.resposta = resposta;
        this.correcta = correcta;
    }

    public String getResposta() {
        return resposta;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


    public String toString() {
        return this.resposta;
    }

    public boolean equals(Object o){
        if(!(o instanceof Resposta)){
            return false;
        }
        else{
           Resposta res= (Resposta) o;
            return (this.resposta.equals(res.getResposta()) && this.correcta==res.getCorrecta());
        }
    }
}



