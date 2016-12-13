package com.ballogomezharo.databaseRepositories;

import com.ballogomezharo.domain.Categoria;
import com.ballogomezharo.domain.Puntuacio;
import com.ballogomezharo.domain.Usuari;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PuntuacioRepository {

    private JdbcTemplate jdbcTemplate;
    private CategoriaRepository categoriaRepository;

    public PuntuacioRepository(JdbcTemplate jdbcTemplate, CategoriaRepository categoriaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoriaRepository = categoriaRepository;
    }

    public void inserirPuntuacions(List<Puntuacio> puntuacions, String username) {
        for (int i = 0; i < puntuacions.size(); i++) {
            jdbcTemplate.update("INSERT INTO PUNTUACIO VALUES (?, ?, ?, ?, ?)", categoriaRepository.findId(puntuacions.get(i).getCategoria()), username,
                    puntuacions.get(i).getCorrectes(), puntuacions.get(i).getIncorrectes(), puntuacions.get(i).getPercentatgeCorrectes());
        }
    }


    public void actualitzarPuntuacio(Puntuacio puntuacio, String nomUsuari) {
        jdbcTemplate.update("UPDATE PUNTUACIO SET PREGUNTESCORRECTES = ?, PREGUNTESINCORRECTES = ?, PERCENTATGE = ? WHERE IDCATEGORIA = ? AND NOMUSUARI = ?",
                puntuacio.getCorrectes(), puntuacio.getIncorrectes(), puntuacio.getPercentatgeCorrectes(), categoriaRepository.findId(puntuacio.getCategoria()),
                nomUsuari);
    }

    public List<Puntuacio> findAll (String nomUsuari) {
        return jdbcTemplate.query("SELECT * FROM PUNTUACIO WHERE NOMUSUARI = ?", new Object[]{ nomUsuari}, new PuntuacioRepository.PuntuacioMapper());
    }


    private final class PuntuacioMapper implements RowMapper<Puntuacio> {

        @Override
        public Puntuacio mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Puntuacio(categoriaRepository.findOne(resultSet.getInt("IDCATEGORIA")), resultSet.getInt("PREGUNTESCORRECTES"), resultSet.getInt("PREGUNTESINCORRECTES"));
        }
    }
}
