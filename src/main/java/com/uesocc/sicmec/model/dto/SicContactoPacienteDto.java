package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

public class SicContactoPacienteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idSicContactoPaciente;
	private String nombreContacto;
	private String apellidoContacto;
	private String dui;
    private String telefono;
	/**
	 * @return the idSicContactoPaciente
	 */
	public String getIdSicContactoPaciente() {
		return idSicContactoPaciente;
	}
	/**
	 * @param idSicContactoPaciente the idSicContactoPaciente to set
	 */
	public void setIdSicContactoPaciente(String idSicContactoPaciente) {
		this.idSicContactoPaciente = idSicContactoPaciente;
	}
	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}
	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	/**
	 * @return the apellidoContacto
	 */
	public String getApellidoContacto() {
		return apellidoContacto;
	}
	/**
	 * @param apellidoContacto the apellidoContacto to set
	 */
	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}
	/**
	 * @return the dui
	 */
	public String getDui() {
		return dui;
	}
	/**
	 * @param dui the dui to set
	 */
	public void setDui(String dui) {
		this.dui = dui;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicContactoPacienteDto [idSicContactoPaciente="
				+ idSicContactoPaciente + ", nombreContacto=" + nombreContacto
				+ ", apellidoContacto=" + apellidoContacto + ", dui=" + dui
				+ ", telefono=" + telefono + "]";
	}
    
}
