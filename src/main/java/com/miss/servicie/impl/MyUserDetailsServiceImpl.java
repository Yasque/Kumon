package com.miss.servicie.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miss.domain.Role;
import com.miss.domain.RoleRepository;
import com.miss.domain.Usuario;
import com.miss.domain.UsuarioRepository;
import com.miss.servicie.MyUserDetailsService;




@Service("userDetailsService")
@Transactional
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		 try {
	            final Usuario usuario = usuarioRepository.findByMail(mail);
	            log.debug("usuario: "+ usuario);
	            if (usuario == null) {
	                return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, getAuthorities(Arrays.asList(roleRepository.findByNombre("ROLE_USER"))));
	            }

	            return new org.springframework.security.core.userdetails.User(usuario.getMail(), usuario.getPassword(), true, true, true, true, getAuthorities(usuario.getRoles()));
	        } catch (final Exception e) {
	            throw new RuntimeException(e);
	        }

	}
	
	// UTIL

    public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getRoles(roles));
    }

    private final List<String> getRoles(final Collection<Role> roles) {
        final List<String> rolesAux = new ArrayList<String>();
        for (final Role item : roles) {
            rolesAux.add(item.getNombre());
        }
        return rolesAux;
    }

	
	 private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
	        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (final String privilege : privileges) {
	            authorities.add(new SimpleGrantedAuthority(privilege));
	        }
	        return authorities;
	    }


}
