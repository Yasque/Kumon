package com.miss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.miss.domain.Alumno;
import com.miss.domain.AlumnoRepository;
import com.miss.domain.Usuario;
import com.miss.domain.UsuarioRepository;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AlumnoRepository alumnoRepository;
	//crear una alumno servicie
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value="/guardar",method=RequestMethod.POST)
	public String guardarAlumno( @ModelAttribute Alumno alumno){
		log.debug("----entrando a guardarAlumno---------");
		
		log.debug(alumno.toString());;
		UserDetails usuario=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//usuarioRepository.findByMail(usuario.getUsername());
		
		Usuario usuarioObtenido=usuarioRepository.findByMail(usuario.getUsername());
		
		alumno.setUsuario(usuarioObtenido);
		
		alumnoRepository.save(alumno);
		
		
		
		return "redirect:/alumnos.html";
	}
	
	
}
