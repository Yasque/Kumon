package com.miss.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private int maximo;
	
	@NotNull
	private int minimo=0;
	
	
	@NotNull
	private String nivel;
		
	@JoinTable(name = "Pedido_hojas", 
            joinColumns = { 
                   @JoinColumn(name = "pedido_id", referencedColumnName = "id")
            }, 
            inverseJoinColumns = { 
                   @JoinColumn(name = "hojas_id", referencedColumnName = "id")
            }
     )


@ManyToMany
private Collection<Hojas> hojas;
		
	@ManyToOne //muchos accesorios un solo vehiculo
	@JoinColumn(name ="usuario_id")//se hace la relacion de uno a muchos
	private Usuario usuario;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getMaximo() {
		return maximo;
	}


	public void setMaximo(int maximo) {
		
		
		this.maximo = maximo;
	}


	public int getMinimo() {
		return minimo;
	}


	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public Collection<Hojas> getHojas() {
		return hojas;
	}


	public void setHojas(Collection<Hojas> hojas) {
		this.hojas = hojas;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/*public int generarPedido(){
		for(int i=0; i<nivel.length();i++){
			if (nivel==alumno.getNivel()){
				if(hoja.getCantidad()<alumno.getCantidad()*5){
						minimo=alumno.getCantidad()*5-hoja.getCantidad();
			   	}else{
			   		if(hoja.getCantidad()==alumno.getCantidad()*5){
			   			minimo=hoja.getCantidad();
			   		}
			   		
			   	}	
			
			}else{
				System.out.println("El nivel no coincide");
			}
			
		}return minimo;
	}*/

	
}
