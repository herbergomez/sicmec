package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

import com.uesocc.sicmec.model.entity.SicPatologia;

public class SicTipoPatologiaDto implements Serializable {
    /*
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private String idSicTipoPatologia;
	private String nombreTipoPatologia;
	private String descripcionTipoPatologia;
	private SicPatologiaDto fkSicPatologia;
	/**
	 * @return the idSicTipoPatologia
	 */
	public String getIdSicTipoPatologia() {
		return idSicTipoPatologia;
	}
	/**
	 * @param idSicTipoPatologia the idSicTipoPatologia to set
	 */
	public void setIdSicTipoPatologia(String idSicTipoPatologia) {
		this.idSicTipoPatologia = idSicTipoPatologia;
	}
	/**
	 * @return the nombreTipoPatologia
	 */
	public String getNombreTipoPatologia() {
		return nombreTipoPatologia;
	}
	/**
	 * @param nombreTipoPatologia the nombreTipoPatologia to set
	 */
	public void setNombreTipoPatologia(String nombreTipoPatologia) {
		this.nombreTipoPatologia = nombreTipoPatologia;
	}
	/**
	 * @return the descripcionTipoPatologia
	 */
	public String getDescripcionTipoPatologia() {
		return descripcionTipoPatologia;
	}
	/**
	 * @param descripcionTipoPatologia the descripcionTipoPatologia to set
	 */
	public void setDescripcionTipoPatologia(String descripcionTipoPatologia) {
		this.descripcionTipoPatologia = descripcionTipoPatologia;
	}
	/**
	 * @return the fkSicPatologia
	 */
	public SicPatologiaDto getFkSicPatologia() {
		return fkSicPatologia;
	}
	/**
	 * @param fkSicPatologia the fkSicPatologia to set
	 */
	public void setFkSicPatologia(SicPatologiaDto fkSicPatologia) {
		this.fkSicPatologia = fkSicPatologia;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicTipoPatologiaDto [idSicTipoPatologia=" + idSicTipoPatologia
				+ ", nombreTipoPatologia=" + nombreTipoPatologia
				+ ", descripcionTipoPatologia=" + descripcionTipoPatologia
				+ ", fkSicPatologia=" + fkSicPatologia + "]";
	}
	
	
}
