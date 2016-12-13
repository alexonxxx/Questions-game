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
public class PreguntaRepteRepository {

    private JdbcTemplate jdbcTemplate;
    private CategoriaRepository categoriaRepository;
    private RespostaRepteRepository respostaRepteRepository;


    public PreguntaRepteRepository(JdbcTemplate jdbcTemplate, CategoriaRepository categoria, RespostaRepteRepository resposta) {
        this.categoriaRepository = categoria;
        this.jdbcTemplate = jdbcTemplate;
        this.respostaRepteRepository = resposta;
    }


    public List<Pregunta> findAll(int id) {
        return jdbcTemplate.query("Select * from PREGUNTESREPTE where IDREPTE =  ?", new Object[]{id}, new preguntaRepteMapper());
    }

    public Pregunta findOne(int id) {
        return jdbcTemplate.queryForObject("Select * from PREGUNTESREPTE where IDPREGUNTA =  ?", new Object[]{id}, new PreguntaRepteRepository.preguntaRepteMapper());
    }

    public void addPregunta(Pregunta pregunta){
        int idSeg = (this.jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PREGUNTESREPTE", Integer.class))+1;
        jdbcTemplate.update("INSERT INTO PREGUNTESREPTE VALUES (?, ?, ?, ?)", idSeg, categoriaRepository.findId(pregunta.getCategoria()), 1, pregunta.getEnunciat());
        this.respostaRepteRepository.addRespostesdePregunta(pregunta, idSeg);

    }


    private final class preguntaRepteMapper implements RowMapper<Pregunta> {

        @Override
        public Pregunta mapRow(ResultSet resultSet, int i) throws SQLException {

            List<Resposta> respostes = respostaRepteRepository.findAll(resultSet.getInt("IDPREGUNTAREPTE"));
            Collections.shuffle(respostes);
            Resposta resposta1 = respostes.get(0);
            Resposta resposta2 = respostes.get(1);
            Resposta resposta3 = respostes.get(2);
            Resposta resposta4 = respostes.get(3);
            int categoria = resultSet.getInt("IDCATEGORIA");
            return new Pregunta(resultSet.getString("ENUNCIATPREGUNTAREPTE"), categoriaRepository.findOne(categoria), resposta1, resposta2, resposta3, resposta4);

        }
    }
}
