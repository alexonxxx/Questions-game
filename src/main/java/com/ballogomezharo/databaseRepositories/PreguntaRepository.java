package com.ballogomezharo.databaseRepositories;


import com.ballogomezharo.domain.Pregunta;
import com.ballogomezharo.domain.Resposta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Repository
public class PreguntaRepository {


    private JdbcTemplate jdbcTemplate;
    private CategoriaRepository categoriaRepository;
    private RespostaRepository respostaRepository;


    public PreguntaRepository(JdbcTemplate jdbcTemplate, CategoriaRepository categoria, RespostaRepository resposta) {
        this.categoriaRepository = categoria;
        this.jdbcTemplate = jdbcTemplate;
        this.respostaRepository = resposta;
    }


    public Pregunta findOne(int id) {
        return jdbcTemplate.queryForObject("Select * from PREGUNTA where IDPREGUNTA =  ?", new Object[]{id}, new preguntaMapper());
    }
    public int numFiles(){
        return this.jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PREGUNTA", Integer.class);
    }

    public void addPregunta(Pregunta pregunta){
        int idSeg = (this.jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PREGUNTA", Integer.class))+1;
        jdbcTemplate.update("INSERT INTO PREGUNTA VALUES (?, ?, ?)", idSeg, categoriaRepository.findId(pregunta.getCategoria()), pregunta.getEnunciat());
        this.respostaRepository.addRespostesdePregunta(pregunta, idSeg);
    }



    private final class preguntaMapper implements RowMapper<Pregunta> {

        @Override
        public Pregunta mapRow(ResultSet resultSet, int i) throws SQLException {

            List<Resposta> respostes = respostaRepository.findAll(resultSet.getInt("IDPREGUNTA"));
            Collections.shuffle(respostes);
            Resposta resposta1 = respostes.get(0);
            Resposta resposta2 = respostes.get(1);
            Resposta resposta3 = respostes.get(2);
            Resposta resposta4 = respostes.get(3);
            int categoria = resultSet.getInt("IDCATEGORIA");
            return new Pregunta(resultSet.getString("ENUNCIATPREGUNTA"), categoriaRepository.findOne(categoria), resposta1, resposta2, resposta3, resposta4);

        }
    }
}

