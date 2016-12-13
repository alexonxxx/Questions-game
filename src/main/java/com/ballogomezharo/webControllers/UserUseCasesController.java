package com.ballogomezharo.webControllers;


import com.ballogomezharo.databaseRepositories.UsuariRepository;
import com.ballogomezharo.domain.Puntuacio;
import com.ballogomezharo.domain.Usuari;
import com.ballogomezharo.security.SecurityService;
import com.ballogomezharo.useCases.UserUseCases;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserUseCasesController {
    private UserUseCases userUseCases;
    private SecurityService securityService;
    private UsuariRepository usuariRepository;
    private int cont;

    public UserUseCasesController(UserUseCases userUseCases,SecurityService securityService,UsuariRepository usuariRepository) {
        this.userUseCases = userUseCases;
        this.usuariRepository= usuariRepository;
        this.securityService=securityService;


    }


    @GetMapping("/")
    public String noGo(Model model){
        model.addAttribute("usuari", userUseCases.retornarUsuari());
        return "showUser";
    }

    @GetMapping("usuaris")
    public List<Usuari> listUsers() {
        return userUseCases.getUsuaris();
    }


    @GetMapping("usuaris/{usuari}")
    public String mostrarUsuari(@PathVariable("usuari") String usuari, Model model)
    {
        model.addAttribute("usuari", userUseCases.getUsuari(usuari));
        return "showUser";
    }


    @GetMapping("createuser")
    public String createUser(Model model) {
        model.addAttribute(new Usuari());
        return "userform";
    }

    @PostMapping("createuser")
    public String processCreateUser(@Valid Usuari usuari, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) return "userform";

        loggingInUser(usuari);
        redirectAttributes.addAttribute("nom", usuari.getNom());
        redirectAttributes.addAttribute("mail", usuari.getMail()); // this attribute shows in the calling url as a parameter
        redirectAttributes.addAttribute("contrassenya", usuari.getContrasenya()); // this attribute shows in the calling url as a parameter
        return "redirect:/usuaris"; //in this way username is scaped and dangerous chars changed
    }

    @GetMapping("byebye")
    public String byebye() {
        return "byebye";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("puntuacio")
    public List<Puntuacio> puntuacio(){
      Usuari user=  this.userUseCases.retornarUsuari();
        return user.getPuntacions();
    }

    @GetMapping("canceledFlow")
    public String cancel(){
        return "canceledFlow";
    }
    @GetMapping("categoriaNotFound")
    public String noCategoria(){
        return "categoriaNotFound";
    }

    @GetMapping("menuInicial")
    public String menuInici(){
        return "menuInicial";
    }
    private void loggingInUser(Usuari usuari) {
        //saving to authoritation database
        usuariRepository.inserirUsuari(usuari);

        //actually logging in
        securityService.login(usuari.getNom(),usuari.getContrasenya());
    }
}


