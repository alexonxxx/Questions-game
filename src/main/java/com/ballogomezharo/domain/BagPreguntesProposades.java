package com.ballogomezharo.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Àlex on 29/10/2016.
 */
public class BagPreguntesProposades implements Serializable {

        private ArrayList<PreguntaProposada> preguntesProposades;

        public BagPreguntesProposades() {
            this.preguntesProposades = new ArrayList<>();
        }

        public void addPreguntaProposada(PreguntaProposada pregunta) {

                preguntesProposades.add(pregunta);

        }

        public List<PreguntaProposada> getPreguntesProposades() {
            return preguntesProposades;
        }


}
