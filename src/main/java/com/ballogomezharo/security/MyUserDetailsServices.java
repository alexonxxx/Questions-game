package com.ballogomezharo.security;


import com.ballogomezharo.databaseRepositories.UsuariRepository;
import com.ballogomezharo.domain.Usuari;
import com.ballogomezharo.exception.UsuariNotFoundException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;


@Service
public class MyUserDetailsServices implements UserDetailsService{


     private final UsuariRepository usuariRepository;

    public MyUserDetailsServices(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsuariNotFoundException {
        System.out.println("myUserDetailsService.loadUserByUsername");
        System.out.println(username);
        System.out.println("hola");
        Usuari user=null;
try {
    user = usuariRepository.getOne(username);
} catch(IncorrectResultSizeDataAccessException e) {
        throw new UsernameNotFoundException("User " + username + " not found");
    }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

         return new User(user.getNom(), user.getContrasenya(), grantedAuthorities);
    }
}
