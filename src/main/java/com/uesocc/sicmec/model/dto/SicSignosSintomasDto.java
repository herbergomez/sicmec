package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
/**
 * @autor pablo portillo
 * @fecha 20/02/2015
 */
public class SicSignosSintomasDto implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private String idSicSignosSintomas;
	 private String nombre;
	 private String descripcion;
	 /**
	 * @param nombre
	 * @param descripcion
	 */
	public SicSignosSintomasDto(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the idSicSignosSintomas
	 */
	public String getIdSicSignosSintomas() {
		return idSicSignosSintomas;
	}
	/**
	 * @param idSicSignosSintomas the idSicSignosSintomas to set
	 */
	public void setIdSicSignosSintomas(String idSicSignosSintomas) {
		this.idSicSignosSintomas = idSicSignosSintomas;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
}
