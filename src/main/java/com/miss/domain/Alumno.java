package com.miss.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


//Esta clase servira solo para conocer la cantidad de alumnos que se tiene en el centro miss
@Entity
@Table(name ="alumno")
public class Alumno {
//mapeo
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	//generar Getter and setter
		@NotNull
		private String nivel;
		
		@NotNull
		private int cantidad;

	
	@ManyToOne //muchos alumnos los puede dar de alta un solo usuario
	@JoinColumn(name ="usuario_id")//se hace la relacion de uno a muchos
	private Usuario usuario;
	
	
	@OneToMany(mappedBy="alumno")//un alumno tiene una o muchas hojas
	private List<Hojas> listaHojas;
	
	
	public List<Hojas> getListaHojas() {
		return listaHojas;
	}

	public void setListaHojas(List<Hojas> listaHojas) {
		this.listaHojas = listaHojas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		if(cantidad>0){
				this.cantidad = cantidad;
		}
	}
	
	@Override
	public String toString() {

		return "nivel: "+ this.getNivel() + " cantidad: " + this.getCantidad();
	}
	
	
	
}