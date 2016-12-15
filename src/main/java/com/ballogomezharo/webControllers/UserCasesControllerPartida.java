package com.ballogomezharo.webControllers;

import com.ballogomezharo.databaseRepositories.UsuariRepository;
import com.ballogomezharo.domain.Categoria;
import com.ballogomezharo.domain.Partida;
import com.ballogomezharo.domain.Pregunta;
import com.ballogomezharo.domain.Usuari;
import com.ballogomezharo.security.SecurityService;
import com.ballogomezharo.useCases.UserUseCases;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class UserCasesControllerPartida {
    private UserUseCases userUseCases;
    private SecurityService securityService;
    private UsuariRepository usuariRepository;
    private int cont;
    private Categoria categoriaActual;
    private int correcte;
    private ArrayList<Pregunta> preguntes;



    public UserCasesControllerPartida(UserUseCases userUseCases, SecurityService securityService, UsuariRepository usuariRepository) {
        this.userUseCases = userUseCases;
        this.usuariRepository = usuariRepository;
        this.securityService = securityService;
        this.categoriaActual = null;
        this.preguntes = new ArrayList<Pregunta>();
    }


    @GetMapping("partidaNova")
    public String comensarPartida(){
        this.userUseCases.novaPartida();
        this.preguntes.removeAll(this.preguntes);
        this.cont=0;
        return "partidaNova";
    }

    @PostMapping("partidaNova")
    public String processPartida() {
        return "redirect:/pregunta";
    }

    @GetMapping("pregunta")
    public Pregunta pregunta() {

        Pregunta pregunta = this.userUseCases.getPregunta(cont);
        while(this.preguntes.contains(pregunta) == true)
            pregunta = this.userUseCases.getPregunta(cont);
        this.preguntes.add(pregunta);
        this.categoriaActual = pregunta.getCategoria();
        this.cont++;
        return pregunta;
    }

    @GetMapping("/pregunta/{resposta}")
    public String preg(@PathVariable String resposta){


        boolean correcte= this.userUseCases.comprobarResposta(resposta,this.categoriaActual);
        Usuari user= this.userUseCases.retornarUsuari();
        if (correcte) this.usuariRepository.updatePuntuacioTotal(user.getNom(),user.getPuntuacioTotal()+1);

        if (cont == 10) {
            this.userUseCases.cambiarPuntuacio();
            return "redirect:/fiPartida";
        } else {


            return "redirect:/pregunta";
        }

    }


    @GetMapping("fiPartida")
    public Partida finalitzarPartida() {
        Partida partida = userUseCases.getPartida();
        return partida;
    }
}
