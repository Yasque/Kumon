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
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value="/guardar",method=RequestMethod.POST)
	public String guardarUsuario( @ModelAttribute Usuario usuario){
		log.debug("----entrando a guardarUsuario---------");
		
		log.debug(usuario.toString());;
		//UserDetails usuario=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//usuarioRepository.findByMail(usuario.getUsername());
		
		//Usuario usuarioObtenido=usuarioRepository.findByMail(usuario.getUsername());
		
		//alumno.setUsuario(usuarioObtenido);
		
		usuarioRepository.save(usuario);
		return "redirect:/usuario.html";
	}
	
	
}
