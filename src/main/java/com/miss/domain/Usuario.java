package com.miss.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



import javax.persistence.JoinColumn;

@Table(name="usuario")
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String mail;
	
	@NotNull
	private String password;
	
	@JoinTable(name = "Usuario_roles", 
	            joinColumns = { 
	                   @JoinColumn(name = "usuario_id", referencedColumnName = "id")
	            }, 
	            inverseJoinColumns = { 
	                   @JoinColumn(name = "role_id", referencedColumnName = "id")
	            }
	     )
	
	
	@ManyToMany
    private Collection<Role> roles;
		
	@OneToMany(mappedBy="usuario")//hace la relacion con la tabla vehiculo
	private List<Pedido> listaPedidos;
	 
	@OneToMany(mappedBy="usuario")//hace la relacion con la tabla vehiculo
	private List<Alumno> listaalumnos;
	
	
	
	
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<Alumno> getListaalumnos() {
		return listaalumnos;
	}

	public void setListaalumnos(List<Alumno> listaalumnos) {
		this.listaalumnos = listaalumnos;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
