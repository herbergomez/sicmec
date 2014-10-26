package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
/**
 * @author herber
 *
 */
public class SicMunicipioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicMunicipio;
    private String nombreMunicipio;
    private SicDepartamentoDto fkSicDepartamento;
	/**
	 * @return the idSicMunicipio
	 */
	public String getIdSicMunicipio() {
		return idSicMunicipio;
	}
	/**
	 * @param idSicMunicipio the idSicMunicipio to set
	 */
	public void setIdSicMunicipio(String idSicMunicipio) {
		this.idSicMunicipio = idSicMunicipio;
	}
	/**
	 * @return the nombreMunicipio
	 */
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	/**
	 * @param nombreMunicipio the nombreMunicipio to set
	 */
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	/**
	 * @return the fkSicDepartamento
	 */
	public SicDepartamentoDto getFkSicDepartamento() {
		return fkSicDepartamento;
	}
	/**
	 * @param fkSicDepartamento the fkSicDepartamento to set
	 */
	public void setFkSicDepartamento(SicDepartamentoDto fkSicDepartamento) {
		this.fkSicDepartamento = fkSicDepartamento;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicMunicipioDto [idSicMunicipio=" + idSicMunicipio
				+ ", nombreMunicipio=" + nombreMunicipio
				+ ", fkSicDepartamento=" + fkSicDepartamento + "]";
	}
	
    
	
    
}
