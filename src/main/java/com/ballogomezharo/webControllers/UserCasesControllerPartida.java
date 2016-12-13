package com.ballogomezharo.webControllers;

import com.ballogomezharo.databaseRepositories.UsuariRepository;
import com.ballogomezharo.domain.Categoria;
import com.ballogomezharo.domain.Partida;
import com.ballogomezharo.domain.Pregunta;
import com.ballogomezharo.domain.Resposta;
import com.ballogomezharo.security.SecurityService;
import com.ballogomezharo.useCases.UserUseCases;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class UserCasesControllerPartida {
    private UserUseCases userUseCases;
    private SecurityService securityService;
    private UsuariRepository usuariRepository;
    private int cont;
    private Categoria categoriaActual;


    public UserCasesControllerPartida(UserUseCases userUseCases, SecurityService securityService, UsuariRepository usuariRepository){
    this.userUseCases = userUseCases;
    this.usuariRepository= usuariRepository;
    this.securityService=securityService;
        this.categoriaActual=null;
    }


    @GetMapping("partidaNova")
    public String comensarPartida(){
        this.userUseCases.novaPartida();
        this.cont=0;
        return "partidaNova";
    }
    @PostMapping("partidaNova")
    public String processPartida(){
        return "redirect:/pregunta";
    }

    @GetMapping("pregunta")
    public Pregunta pregunta(){

        Pregunta pregunta= this.userUseCases.getPregunta(cont);
        this.categoriaActual=pregunta.getCategoria();
        this.cont++;
        return pregunta;
    }
    @PostMapping("pregunta")
    public String processPregunta(){

        this.userUseCases.comprobarResposta(new Resposta("efe",true),this.categoriaActual);

        if(cont==10){
            this.userUseCases.cambiarPuntuacio();
            return "redirect:/fiPartida";
        }
        else{
            return "redirect:/pregunta";
        }
    }
    @GetMapping("fiPartida")
    public Partida finalitzarPartida(){
        Partida partida= userUseCases.getPartida();
        return partida;
    }
}
