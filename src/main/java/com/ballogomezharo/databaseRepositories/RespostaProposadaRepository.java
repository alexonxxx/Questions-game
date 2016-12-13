package com.ballogomezharo.databaseRepositories;


import com.ballogomezharo.domain.PreguntaProposada;
import com.ballogomezharo.domain.Resposta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RespostaProposadaRepository {
    private JdbcTemplate jdbcTemplate;


    public RespostaProposadaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Resposta> findAll(int id) {
        return jdbcTemplate.query("Select * from RESPOSTAPROPOSADA where IDPREGUNTAPROPOSADA =  ?", new Object[]{id}, new RespostaProposadaRepository.respostaMapper());
    }

    public void addRespostesdePregunta(PreguntaProposada pregunta, int idPregunta){
        jdbcTemplate.update("INSERT INTO RESPOSTAPROPOSADA VALUES(?, ?, ?, ?, ?)", pregunta.getUsuari().getNom(), idPregunta, 1, pregunta.getResposta1().getResposta(), pregunta.getResposta1().getCorrecta());
        jdbcTemplate.update("INSERT INTO RESPOSTAPROPOSADA VALUES(?, ?, ?, ?, ?)", pregunta.getUsuari().getNom(), idPregunta, 2, pregunta.getResposta2().getResposta(), pregunta.getResposta2().getCorrecta());
        jdbcTemplate.update("INSERT INTO RESPOSTAPROPOSADA VALUES(?, ?, ?, ?, ?)", pregunta.getUsuari().getNom(), idPregunta, 3, pregunta.getResposta3().getResposta(), pregunta.getResposta3().getCorrecta());
        jdbcTemplate.update("INSERT INTO RESPOSTAPROPOSADA VALUES(?, ?, ?, ?, ?)", pregunta.getUsuari().getNom(), idPregunta, 4, pregunta.getResposta4().getResposta(), pregunta.getResposta4().getCorrecta());
    }


    private final class respostaMapper implements RowMapper<Resposta> {
        @Override
        public Resposta mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Resposta(resultSet.getString("TEXT"), resultSet.getBoolean("CORRECTA"));
        }
    }
}
