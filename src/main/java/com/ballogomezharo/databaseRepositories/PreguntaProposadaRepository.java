package com.ballogomezharo.databaseRepositories;

import com.ballogomezharo.domain.PreguntaProposada;
import com.ballogomezharo.domain.Resposta;
import com.ballogomezharo.domain.Usuari;
import com.ballogomezharo.exception.CategoriaNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class PreguntaProposadaRepository {
    private JdbcTemplate jdbcTemplate;
    private CategoriaRepository categoriaRepository;
    private RespostaProposadaRepository respostaProposadaRepository;
    private UsuariRepository usuariRepository;


    public PreguntaProposadaRepository(JdbcTemplate jdbcTemplate, CategoriaRepository categoria, RespostaProposadaRepository respostaProposada, UsuariRepository usuariRepository) {
        this.categoriaRepository = categoria;
        this.jdbcTemplate = jdbcTemplate;
        this.respostaProposadaRepository = respostaProposada;
        this.usuariRepository = usuariRepository;
    }


    public PreguntaProposada findOne(int id) {
        return jdbcTemplate.queryForObject("Select * from PREGUNTAPROPOSADA where IDPREGUNTAPROPOSADA =  ?", new Object[]{id}, new PreguntaProposadaRepository.preguntaMapper());
    }

    public void addPregunta(PreguntaProposada pregunta){
        int idSeg = (this.jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PREGUNTAPROPOSADA", Integer.class))+1;
       try {
           categoriaRepository.findId(pregunta.getCategoria());
           jdbcTemplate.update("INSERT INTO PREGUNTAPROPOSADA VALUES (?, ?, ?, ?)",pregunta.getUsuari().getNom(), idSeg, categoriaRepository.findId(pregunta.getCategoria()), pregunta.getEnunciat());
           this.respostaProposadaRepository.addRespostesdePregunta(pregunta, idSeg);
       } catch (EmptyResultDataAccessException e) {
           throw new CategoriaNotFoundException("Categoria" + pregunta.getCategoria() + " not found");
       }


    }

    public void eliminarPregunta(int id){
        jdbcTemplate.update("DELETE FROM PREGUNTAPROPOSADA WHERE IDPREGUNTAPROPOSADA= ?", id);

    }



    private final class preguntaMapper implements RowMapper<PreguntaProposada> {

        @Override
        public PreguntaProposada mapRow(ResultSet resultSet, int i) throws SQLException {

            List<Resposta> respostes = respostaProposadaRepository.findAll(resultSet.getInt("IDPREGUNTAPROPOSADA"));
            Collections.shuffle(respostes);
            Resposta resposta = respostes.get(0);
            Resposta resposta2 = respostes.get(1);
            Resposta resposta3 = respostes.get(2);
            Resposta resposta4 = respostes.get(3);
            int categoria = resultSet.getInt("IDCATEGORIA");
            Usuari usuari = usuariRepository.getOne(resultSet.getString(1));
            return new PreguntaProposada(usuari, resultSet.getString("ENUNCIATPROPOSADA"), categoriaRepository.findOne(categoria), resposta, resposta2, resposta3, resposta4);

        }
    }
}
