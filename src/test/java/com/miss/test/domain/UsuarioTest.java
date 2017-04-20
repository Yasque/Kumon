package com.miss.test.domain;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.miss.Application;
import com.miss.domain.Alumno;
import com.miss.domain.Hojas;
import com.miss.domain.Pedido;
import com.miss.domain.Role;
import com.miss.domain.RoleRepository;
import com.miss.domain.Usuario;
import com.miss.domain.UsuarioRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class UsuarioTest {

	@Autowired
	UsuarioRepository usuarioRepository; //new repository lo hace por la parte de atras
	
	@Autowired
	RoleRepository roleRepository; //new repository lo hace por la parte de atra
	
	
	
	//@Test
	public void obtenerPrimerRegistro(){
		long id=1;
		Usuario auto = usuarioRepository.findOne(new Long(id));
		
		Assert.assertNotNull(auto.getMail());
		Assert.assertNotNull(auto.getPassword());
		
		
	}
	//@Test
	public void guardarAlumnoTest(){
		Usuario usuario = new Usuario();
		
		usuario.setPassword("235");
		usuario.setMail("atziry@gmail.com");
		usuarioRepository.save(usuario);
		}
	
	@Test
		public void guardarUsuarioRoleTest(){
			Role role = new Role();
			role.setNombre("gerente1");
		
			Collection<Role> roles=new ArrayList<Role>(); 
			roles.add(role);
			
			Usuario usuario = new Usuario();
			usuario.setMail("angel@gmail.com");
			usuario.setPassword("456");
				
			Collection<Usuario> usuarios=new ArrayList<Usuario>(); 
			usuarios.add(usuario);
			usuario.setRoles(roles);
				
			
			role.setUsuarios(usuarios);
			roleRepository.save(role);
			usuarioRepository.save(usuario);
		
					
}
		    
	
	
	
	
	
	
}