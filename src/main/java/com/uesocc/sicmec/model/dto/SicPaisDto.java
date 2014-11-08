package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

public class SicPaisDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idSicPais;
	private String nombrePais;
	/**
	 * @return the idSicPais
	 */
	public String getIdSicPais() {
		return idSicPais;
	}
	/**
	 * @param idSicPais the idSicPais to set
	 */
	public void setIdSicPais(String idSicPais) {
		this.idSicPais = idSicPais;
	}
	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}
	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicPaisDto [idSicPais=" + idSicPais + ", nombrePais="
				+ nombrePais + "]";
	}
	
	
	
}
