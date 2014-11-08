package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

import com.uesocc.sicmec.model.entity.SicPais;

public class SicDepartamentoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicDepartamento;
    private String nombreDepartamento;
    private SicPaisDto fkSicPais;
    
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


	/**
	 * @return the fkSicPais
	 */
	public SicPaisDto getFkSicPais() {
		return fkSicPais;
	}


	/**
	 * @param fkSicPais the fkSicPais to set
	 */
	public void setFkSicPais(SicPaisDto fkSicPais) {
		this.fkSicPais = fkSicPais;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicDepartamentoDto [idSicDepartamento=" + idSicDepartamento
				+ ", nombreDepartamento=" + nombreDepartamento + ", fkSicPais="
				+ fkSicPais + "]";
	}    
}
