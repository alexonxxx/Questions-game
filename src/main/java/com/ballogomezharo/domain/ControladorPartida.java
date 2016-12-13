package com.ballogomezharo.domain;



import com.ballogomezharo.databaseRepositories.PreguntaRepository;
import com.ballogomezharo.databaseRepositories.PuntuacioRepository;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.Random;


/**
 * Created by Àlex on 08/12/2016.
 */

public class ControladorPartida {


    private PreguntaRepository preguntaRepository;
    private PuntuacioRepository puntuacioRepository;
    private Usuari jugador;
    private Partida partida;
    private Random aleatori;
    private int cont;




    public ControladorPartida(Usuari jugador,PreguntaRepository preguntaRepository, PuntuacioRepository puntuacioRepository){
        this.partida= new Partida(jugador);
        this.preguntaRepository=preguntaRepository;
        this.puntuacioRepository=puntuacioRepository;
        this.jugador=jugador;
        this.aleatori= new Random();
        addPreguntes();
        cont=0;
    }




    public Pregunta getPregunta(int i){
        return this.partida.getPregunta(i);
    }
    public Partida getPartida(){return this.partida;}

    private  void addPreguntes(){

        int numFiles= preguntaRepository.numFiles();
        int fila=0;
        for(int i=0; i<10; i++) {
            fila = this.aleatori.nextInt(numFiles) + 1;
            Pregunta pregunta = (preguntaRepository.findOne(fila));
            this.partida.addPregunta(pregunta);
        }
    }
    public void removePreguntes(){

        this.partida.removePreguntes();

    }

    public void cambiarPuntuacio(){
        for(int i=0; i<jugador.getPuntacions().size();i++)
            this.puntuacioRepository.actualitzarPuntuacio(this.jugador.getPuntacions().get(i),this.jugador.getNom());
    }

    public void canviPuntuacio(){
        int punt= jugador.getPuntuacioTotal();
        this.jugador.setPuntuacioTotal(punt+this.partida.getRespostesCorrectes());
    }

    public void comprobarResposta(Resposta resposta, Categoria categoria) {
    int index;

        boolean correcte= this.partida.comprobarResposta(cont,resposta);
        if(categoria.getCategoria().equals("CIENCIA")){
            index=0;
        }else {
            if (categoria.getCategoria().equals("ESPORTS")) {
                index = 1;
            } else {
                if (categoria.getCategoria().equals("ENTRETENIMENT")) {
                    index = 2;
                } else {
                    if (categoria.getCategoria().equals("HISTORIA")) {
                        index = 3;
                    } else {
                        if (categoria.getCategoria().equals("GEOGRAFIA")) {
                            index = 4;
                        } else {
                            index = 5;
                        }

                    }
                }
            }
        }

        Puntuacio p=this.jugador.getPuntacions().get(index);
        System.out.println(p.getCategoria());
        if(correcte){

            p.setCorrectes(p.getCorrectes()+1);

        }
        else{
            p.setIncorrectes(p.getIncorrectes()+1);
        }
        System.out.println(p.getIncorrectes());

        cont++;
    }
    }




