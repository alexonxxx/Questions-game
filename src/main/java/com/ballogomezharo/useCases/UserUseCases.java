package com.ballogomezharo.useCases;

import com.ballogomezharo.databaseRepositories.PreguntaRepository;
import com.ballogomezharo.databaseRepositories.PuntuacioRepository;
import com.ballogomezharo.domain.*;
import com.ballogomezharo.databaseRepositories.PreguntaProposadaRepository;
import com.ballogomezharo.databaseRepositories.UsuariRepository;
import com.ballogomezharo.exception.UsuariNotFoundException;
import com.ballogomezharo.security.SecurityService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserUseCases {
    private UsuariRepository usuariRepository;
    private PreguntaProposadaRepository preguntaProposadaRepository;
    private PreguntaRepository preguntaRepository;
    private ControladorPartida controladorPartida;
    private PuntuacioRepository puntuacioRepository;

    public UserUseCases(UsuariRepository usuariRepository,PreguntaRepository preguntaRepository, PreguntaProposadaRepository preguntaProposadaRepository, PuntuacioRepository puntuacioRepository) {
        this.usuariRepository = usuariRepository;
        this.preguntaRepository=preguntaRepository;
        this.preguntaProposadaRepository=preguntaProposadaRepository;
        this.puntuacioRepository=puntuacioRepository;
        this.controladorPartida=null;
    }

    public void novaPartida(){
        controladorPartida= new ControladorPartida(retornarUsuari(), preguntaRepository,puntuacioRepository);
    }

    public List<Usuari> getUsuaris() {
        return this.usuariRepository.findAll();
    }

    public Usuari getUsuari(String nom) {
        try {
            return this.usuariRepository.getOne(nom);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuariNotFoundException("Usuari " + nom + " not found");
        }
    }
    public Partida getPartida(){
        return  this.controladorPartida.getPartida();
    }

    public boolean comprobarResposta(String resposta,Categoria categoria){
        return this.controladorPartida.comprobarResposta(resposta, categoria);

    }


    public void cambiarPuntuacio(){
        this.controladorPartida.cambiarPuntuacio();
    }

    public Usuari retornarUsuari(){
        String nom= SecurityService.findLoggedInUsername();
        Usuari usuari= this.usuariRepository.getOne(nom);
        return usuari;
    }

    public Pregunta getPregunta(int i){
//comprobar Excepcio
        return this.controladorPartida.getPregunta(i);
    }

    public void afegirPreguntaProposada(Usuari usuari, PreguntaProposada pregunta){
        this.preguntaProposadaRepository.addPregunta(pregunta);
        this.usuariRepository.updatePreguntesProposades(usuari.getNom(),usuari.getPreguntesProposades()+1);
    }
    public void addBag(Usuari userLab, BagPreguntesProposades bagPreguntesProposades) {
        bagPreguntesProposades.getPreguntesProposades().forEach(preguntaProposada -> {afegirPreguntaProposada(userLab, preguntaProposada);});
    }

    public void registerUser(Usuari usuari) {
        try {
            usuariRepository.inserirUsuari(usuari);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



