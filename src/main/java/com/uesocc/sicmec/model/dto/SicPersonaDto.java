/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

/**
 * @author xtiyo
 *
 */

public class SicPersonaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicPersona;
    private String nombre;
    private String apellido;
    private String email;
    
	public String getIdSicPersona() {
		return idSicPersona;
	}
	public void setIdSicPersona(String idSicPersona) {
		this.idSicPersona = idSicPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return nombre + ", "+ apellido;
	}
	


}
