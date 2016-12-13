package com.ballogomezharo.databaseRepositories;


import com.ballogomezharo.domain.Categoria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CategoriaRepository {

    private JdbcTemplate jdbcTemplate;


    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Categoria findOne(int categoria) {
        return jdbcTemplate.queryForObject("Select * from CATEGORIA where IDCATEGORIA = ?", new Object[]{categoria}, new CategoriaRepository.categoriaMapper());
    }

    public Categoria findOne(String categoria) {
        return jdbcTemplate.queryForObject("Select * from CATEGORIA where  NOMCATEGORIA = ?", new Object[]{categoria}, new CategoriaRepository.categoriaMapper());
    }

    public int findId(Categoria categoria) {
        return jdbcTemplate.queryForObject("Select IDCATEGORIA from CATEGORIA where NOMCATEGORIA = ?", Integer.class, categoria.getCategoria());
    }

    private final class categoriaMapper implements RowMapper<Categoria> {
        @Override
        public Categoria mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Categoria(resultSet.getString("NOMCATEGORIA"));
        }
    }
}