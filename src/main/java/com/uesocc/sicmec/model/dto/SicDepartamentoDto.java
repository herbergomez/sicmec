package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

public class SicDepartamentoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicDepartamento;
    private String nombreDepartamento;
	
    
	/**
	 * @return the idSicDepartamento
	 */
	public String getIdSicDepartamento() {
		return idSicDepartamento;
	}


	/**
	 * @param idSicDepartamento the idSicDepartamento to set
	 */
	public void setIdSicDepartamento(String idSicDepartamento) {
		this.idSicDepartamento = idSicDepartamento;
	}


	/**
	 * @return the nombreDepartamento
	 */
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}


	/**
	 * @param nombreDepartamento the nombreDepartamento to set
	 */
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicDepartamentoDto [idSicDepartamento=" + idSicDepartamento
				+ ", nombreDepartamento=" + nombreDepartamento + "]";
	}

    
    
}
