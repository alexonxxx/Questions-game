package com.ballogomezharo;

import com.ballogomezharo.databaseRepositories.*;
import com.ballogomezharo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class TrivialBalloGomezHaroApplication {
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    public static void main(String[] args) {
        SpringApplication.run(TrivialBalloGomezHaroApplication.class, args);
    }


    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private UsuariRepository usuariRepository;

    @Autowired
    private PreguntaProposadaRepository preguntaProposadaRepository;

    @Autowired
    private PuntuacioRepository puntuacioRepository;

    @Autowired
    private PreguntaRepteRepository preguntaRepteRepository;

    @Autowired
    private RepteRepository repteRepository;

    @Bean
    CommandLineRunner runner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                System.out.println("Obtenim una pregunta de la base de dades");
                System.out.println("***********************************************");
                Pregunta pregunta = preguntaRepository.findOne(41);
                System.out.println(pregunta);
                System.out.println(pregunta.getResposta1());
                System.out.println(pregunta.getResposta2());
                System.out.println(pregunta.getResposta3());
                System.out.println(pregunta.getResposta4());
                System.out.println("_______________________________________________");
                Usuari usuari = new Usuari("Alex", "alex@mail.com", "9966");
                System.out.println("Inserim l'usuari: " + usuari);
                System.out.println("***********************************************");
                usuariRepository.inserirUsuari(usuari);
                System.out.println("Obtenim l'usuari inserit prèviament");
                System.out.println("***********************************************");
                System.out.println(usuariRepository.getOne("Alex"));
                System.out.println("_______________________________________________");

                System.out.println(usuariRepository.getOne("Alex"));
                System.out.println("_______________________________________________");
                System.out.println("Actualitzem la puntuació total a 25");
                System.out.println("***********************************************");
                usuariRepository.updatePuntuacioTotal(usuari.getNom(), 25);
                System.out.println(usuariRepository.getOne("Alex"));
                System.out.println("_______________________________________________");
                System.out.println("Actualitzem les preguntes proposades a 12  i les preguntes moderades a 50");
                System.out.println("***********************************************");
                usuariRepository.updatePreguntesProposades(usuari.getNom(), 12);
                usuariRepository.updatePreguntesModerades(usuari.getNom(), 50);
                System.out.println(usuariRepository.getOne("Alex"));
                System.out.println("_______________________________________________");

                //Creació d'una pregunta de prova per a afegir a la base de dades
                PreguntaProposada pregunta1 = new PreguntaProposada(usuari, "¿Quién es Fernando Alonso?", new Categoria("ESPORTS"), new Resposta("Piloto de F1", true),
                        new Resposta("Piloto de F2", false),new Resposta("Piloto de F6", false),new Resposta("Piloto de F7", false));

                System.out.println("Afegim una pregunta a la base de dades");
                System.out.println("***********************************************");
                preguntaProposadaRepository.addPregunta(pregunta1);
                System.out.println("_______________________________________________");
                System.out.println("Obtenim la pregunta afegida anteriorment");
                System.out.println("***********************************************");
                pregunta1 = preguntaProposadaRepository.findOne(1);
                System.out.println(pregunta1);
                System.out.println(pregunta1.getResposta1());
                System.out.println(pregunta1.getResposta2());
                System.out.println(pregunta1.getResposta3());
                System.out.println(pregunta1.getResposta4());


                System.out.println("Obtenim de la base de dades la puntuacio d'esports de l'usuari");
                System.out.println("************************************************");

                List<Puntuacio> puntuacions= puntuacioRepository.findAll(usuari.getNom());
                System.out.println(puntuacions.get(1));


               /* List<Pregunta> preguntesRepte = new ArrayList<Pregunta>();
                preguntesRepte.add(pregunta);
                preguntesRepte.add(pregunta1);
                Repte repte = new Repte(preguntesRepte);

                System.out.println("Afegim pregunta al repositori de reptes");
                System.out.println("************************************************");
                preguntaRepteRepository.addPregunta(pregunta1);

                System.out.println(preguntaRepteRepository.findOne(1));*/
            }
        };
    }
}
