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
import com.miss.domain.AlumnoRepository;
import com.miss.domain.Hojas;
import com.miss.domain.HojasRepository;
import com.miss.domain.Pedido;
import com.miss.domain.PedidoRepository;
import com.miss.domain.Usuario;
import com.miss.domain.UsuarioRepository;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class PedidoTest {

	@Autowired
	AlumnoRepository alumnoRepository; //new repository lo hace por la parte de atras
	
	@Autowired
	HojasRepository hojasRepository;
			
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;

	
	@Test
	public void guardarAlumnoHojasPedidoTest(){
		Usuario usuario=new Usuario();
		usuario.setMail("israel@gmail.com");
		usuario.setPassword("258");
		
		Alumno alumno = new Alumno();
		alumno.setNivel("B15");
		alumno.setCantidad(20);
		List<Alumno> alumnos=new ArrayList<Alumno>();
		alumnos.add(alumno);
		usuario.setListaalumnos(alumnos);
		usuarioRepository.save(usuario);
		
		
		Hojas hoja = new Hojas();
		hoja.setNivel("B20");
		hoja.setCantidad(30);
		
		
		
		//logica primero debe existir la hoja, y despues el alumno
		List<Hojas> hojas=new ArrayList<Hojas>();
		hojas.add(hoja);
		alumno.setListaHojas(hojas);
		
		Collection<Hojas> hoja1=new ArrayList<Hojas>(); 
		hoja1.add(hoja);
			
		Pedido pedido = new Pedido();
		pedido.setNivel("B20");
		
		pedido.setMinimo(10);
		
		
		pedido.setMaximo(800);
		pedido.setHojas(hoja1);
		Collection<Pedido> pedidos=new ArrayList<Pedido>(); 
		pedidos.add(pedido);
		pedido.setUsuario(usuario);
		alumno.setUsuario(usuario);
		alumnoRepository.save(alumno);
		hoja.setAlumno(alumno);
		hoja.setPedidos(pedidos);
		
		hojasRepository.save(hoja);
		pedidoRepository.save(pedido);
		
		alumno.setUsuario(usuario);
		alumnoRepository.save(alumno);
		
		
			}
	
	//@Test
	public void obtenerPrimerRegistro(){
		long id=1;
		Pedido pedido = pedidoRepository.findOne(new Long(id));
		Assert.assertNotNull(pedido.getNivel());
		Assert.assertNotNull(pedido.getMaximo());
		Assert.assertNotNull(pedido.getMinimo());
		Assert.assertNotNull(pedido.getUsuario());
		Assert.assertNotNull(pedido.getHojas());
		
			
	}

	
	
}