package com.ballogomezharo.databaseRepositories;
import com.ballogomezharo.domain.Puntuacio;
import com.ballogomezharo.domain.Usuari;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuariRepository {
    private JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private PuntuacioRepository puntuacioRepository;

    public UsuariRepository(JdbcTemplate jdbcTemplate,PasswordEncoder passwordEncoder, PuntuacioRepository puntuacioRepository ) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder =passwordEncoder;
        this.puntuacioRepository=puntuacioRepository;
    }

    public void inserirUsuari(Usuari usuari) {
        this.jdbcTemplate.update("INSERT INTO USUARI (NOMUSUARI, MAIL, CONTRASSENYA) VALUES (?,?,?)", usuari.getNom(), usuari.getMail(), this.passwordEncoder.encode((usuari.getContrasenya())));
        jdbcTemplate.update("insert into user_roles (username, role) values(?, 'ROLE_USER')", usuari.getNom());
        this.puntuacioRepository.inserirPuntuacions(usuari.getPuntacions(),usuari.getNom());
    }


    public Usuari getOne(String nom) {
        Usuari user= this.jdbcTemplate.queryForObject("SELECT * FROM USUARI WHERE NOMUSUARI = ?", new Object[]{nom}, new UsuariMapper());
        user.addRole(findRole(nom));
        return user;
    }

    private String findRole(String nom) {
        return jdbcTemplate.queryForObject("Select * from user_roles where username = ?", new Object[]{nom}, new RoleMapper());
    }

   /* public int updateContrasenya ( String usuari, String contrassenya){
        return this.jdbcTemplate.update("UPDATE USUARI SET CONTRASSENYA = ? WHERE NOMUSUARI = ?" , contrassenya, usuari);
    }*/

    public int updatePuntuacioTotal ( String usuari, int puntuacioTotal){
        return this.jdbcTemplate.update("UPDATE USUARI SET PUNTUACIOTOTAL = ? WHERE NOMUSUARI = ?" , puntuacioTotal, usuari);
    }

    public int updatePreguntesProposades ( String usuari, int preguntesProposades){
        return this.jdbcTemplate.update("UPDATE USUARI SET PREGUNTESPROPOSADES = ? WHERE NOMUSUARI = ?" , preguntesProposades, usuari);
    }

    public int updatePreguntesModerades ( String usuari, int preguntesModerades){
        return this.jdbcTemplate.update("UPDATE USUARI SET PREGUNTESMODERADES = ? WHERE NOMUSUARI = ?" , preguntesModerades, usuari);
    }

    public List<Usuari> findAll() {
        return jdbcTemplate.query("SELECT * FROM USUARI", new UsuariMapper());
    }

    private final class UsuariMapper implements RowMapper<Usuari> {
        @Override
        public Usuari mapRow(ResultSet resultSet, int i) throws SQLException {

            List<Puntuacio> puntuacions = puntuacioRepository.findAll(resultSet.getString("NOMUSUARI"));
            return new Usuari(resultSet.getString("NOMUSUARI"), resultSet.getString("MAIL"), resultSet.getString("CONTRASSENYA"),puntuacions,
                    resultSet.getInt("PUNTUACIOTOTAL"), resultSet.getInt("PREGUNTESPROPOSADES"), resultSet.getInt("PREGUNTESMODERADES"));
        }
    }
    private final class RoleMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getString("role");
        }
    }
}
