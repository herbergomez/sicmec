package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
import java.util.Date;

public class SicPatologiaDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  idSicPatologia;
	private String nombrePatologia;
	private String descripcionPatologia;
	private String fxCreado;
	private String creadoPor;
	private String fxModicado;
	private String modifcadoPor;
	/**
	 * @return the idSicPatologia
	 */
	public String getIdSicPatologia() {
		return idSicPatologia;
	}
	/**
	 * @param idSicPatologia the idSicPatologia to set
	 */
	public void setIdSicPatologia(String idSicPatologia) {
		this.idSicPatologia = idSicPatologia;
	}
	/**
	 * @return the nombrePatologia
	 */
	public String getNombrePatologia() {
		return nombrePatologia;
	}
	/**
	 * @param nombrePatologia the nombrePatologia to set
	 */
	public void setNombrePatologia(String nombrePatologia) {
		this.nombrePatologia = nombrePatologia;
	}
	/**
	 * @return the descripcionPatologia
	 */
	public String getDescripcionPatologia() {
		return descripcionPatologia;
	}
	/**
	 * @param descripcionPatologia the descripcionPatologia to set
	 */
	public void setDescripcionPatologia(String descripcionPatologia) {
		this.descripcionPatologia = descripcionPatologia;
	}
	/**
	 * @return the fxCreado
	 */
	public String getFxCreado() {
		return fxCreado;
	}
	/**
	 * @param fxCreado the fxCreado to set
	 */
	public void setFxCreado(String fxCreado) {
		this.fxCreado = fxCreado;
	}
	/**
	 * @return the creadoPor
	 */
	public String getCreadoPor() {
		return creadoPor;
	}
	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	/**
	 * @return the fxModicado
	 */
	public String getFxModicado() {
		return fxModicado;
	}
	/**
	 * @param fxModicado the fxModicado to set
	 */
	public void setFxModicado(String fxModicado) {
		this.fxModicado = fxModicado;
	}
	/**
	 * @return the modifcadoPor
	 */
	public String getModifcadoPor() {
		return modifcadoPor;
	}
	/**
	 * @param modifcadoPor the modifcadoPor to set
	 */
	public void setModifcadoPor(String modifcadoPor) {
		this.modifcadoPor = modifcadoPor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicPatologiaDto [idSicPatologia=" + idSicPatologia
				+ ", nombrePatologia=" + nombrePatologia
				+ ", descripcionPatologia=" + descripcionPatologia
				+ ", fxCreado=" + fxCreado + ", creadoPor=" + creadoPor
				+ ", fxModicado=" + fxModicado + ", modifcadoPor="
				+ modifcadoPor + "]";
	}
	
	

}
