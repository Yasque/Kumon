package com.miss.test.domain;



import java.util.ArrayList;
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
import com.miss.domain.AlumnoRepository;
import com.miss.domain.Hojas;
import com.miss.domain.HojasRepository;
import com.miss.domain.Usuario;
import com.miss.domain.UsuarioRepository;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class AlumnoTest {

	@Autowired
	AlumnoRepository alumnoRepository; //new repository lo hace por la parte de atras
	
	@Autowired
	HojasRepository hojasRepository;
			
	@Autowired
	UsuarioRepository usuarioRepository;
	
	

	//@Test
	public void guardarAlumnoTest(){
		Alumno alumno = new Alumno();
		
		alumno.setNivel("B1");
		alumno.setCantidad(12);
	
		
		Usuario usuario=new Usuario();
		usuario.setMail("gdhdbhbh@gmail.com");
		usuario.setPassword("1234");
		usuarioRepository.save(usuario);
		
		alumno.setUsuario(usuario);
		alumnoRepository.save(alumno);
		
		
			}
	
	@Test
	public void obtenerPrimerRegistro(){
		long id=1;
		Alumno alumno = alumnoRepository.findOne(new Long(id));
		Assert.assertNotNull(alumno.getNivel());
		Assert.assertNotNull(alumno.getCantidad());
		
			
	}
    
	@Test
	public void guardarAlumnoHojasTest(){

		Usuario usuario=new Usuario();
		usuario.setMail("Vivi@gmail.com");
		usuario.setPassword("1789");
		
		Alumno alumno = new Alumno();
		alumno.setNivel("B25");
		alumno.setCantidad(20);
		List<Alumno> alumnos=new ArrayList<Alumno>();
		alumnos.add(alumno);
		usuario.setListaalumnos(alumnos);
		usuarioRepository.save(usuario);
		
		
		Hojas hoja = new Hojas();
		hoja.setNivel("B25");
		hoja.setCantidad(20);
		
		
		
				
		//logica primero debe existir la hoja, y despues el alumno
		List<Hojas> hojas=new ArrayList<Hojas>();
		hojas.add(hoja);
		alumno.setListaHojas(hojas);
		
		alumno.setUsuario(usuario);
		alumnoRepository.save(alumno);
		hoja.setAlumno(alumno);
		hojasRepository.save(hoja);
		
				
		
		//se crea una lista de alumnos por que un solo se puede dar de alta por un usuario
		
	
	}
	
	
}