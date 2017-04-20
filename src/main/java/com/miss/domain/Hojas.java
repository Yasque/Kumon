package com.miss.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hojas")
public class Hojas {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String nivel;
	
	@NotNull
	private int cantidad;
		
	
	@ManyToOne //muchos usuarios pueden dar de alta muhcos alumnos
	@JoinColumn(name ="alumno_id")//se hace la relacion de uno a muchos
	private Alumno alumno;

	
	@ManyToMany(mappedBy = "hojas")
    private Collection<Pedido> pedidos;
 
	
	public Collection<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(Collection<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		this.cantidad = cantidad;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	
		
}
