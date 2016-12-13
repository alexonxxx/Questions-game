package com.ballogomezharo.databaseRepositories;

import com.ballogomezharo.domain.Pregunta;
import com.ballogomezharo.domain.Repte;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepteRepository {

    private JdbcTemplate jdbcTemplate;
    private PreguntaRepteRepository preguntaRepteRepository;

    public RepteRepository(JdbcTemplate jdbcTemplate, PreguntaRepteRepository preguntaRepteRepository){
        this.jdbcTemplate=jdbcTemplate;
        this.preguntaRepteRepository= preguntaRepteRepository;
    }
    public Repte findOne(int id){
        return this.jdbcTemplate.queryForObject("SELECT * FROM REPTE WHERE IDREPTE=?", new Object[]{id},new RepteRepositoryMapper());
    }

    public void addRepte(Repte repte){
         this.jdbcTemplate.update("INSERT INTO REPTE VALIES(?,?)", 1, 0);
        for (Pregunta p: repte.getPreguntes()) {
            this.preguntaRepteRepository.addPregunta(p);
        }
    }



    private final class RepteRepositoryMapper implements RowMapper<Repte>{
        @Override
        public Repte mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Repte(preguntaRepteRepository.findAll(resultSet.getInt("IDREPTE")));
        }


    }
}
