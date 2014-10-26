/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

/**
 * @author xtiyo
 *
 */
public class SicUsuarioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicUsuario;
    private String nombreUsuario;
    private String fxActivacion;
    private String fxDesactivacion;
    private String fxCreado;
    private String creadoPor;
    private String fxModicado;
    private String modicadoPor;
    private SicRolDto SicRol;
    private SicPersonaDto SicPersona;
    private SicEstadoUsuarioDto sicEstadoUsuario;
    
	/**
	 * @return the idSicUsuario
	 */
	public String getIdSicUsuario() {
		return idSicUsuario;
	}
	/**
	 * @param idSicUsuario the idSicUsuario to set
	 */
	public void setIdSicUsuario(String idSicUsuario) {
		this.idSicUsuario = idSicUsuario;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the fxActivacion
	 */
	public String getFxActivacion() {
		return fxActivacion;
	}
	/**
	 * @param fxActivacion the fxActivacion to set
	 */
	public void setFxActivacion(String fxActivacion) {
		this.fxActivacion = fxActivacion;
	}
	/**
	 * @return the fxDesactivacion
	 */
	public String getFxDesactivacion() {
		return fxDesactivacion;
	}
	/**
	 * @param fxDesactivacion the fxDesactivacion to set
	 */
	public void setFxDesactivacion(String fxDesactivacion) {
		this.fxDesactivacion = fxDesactivacion;
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
	 * @return the modicadoPor
	 */
	public String getModicadoPor() {
		return modicadoPor;
	}
	/**
	 * @param modicadoPor the modicadoPor to set
	 */
	public void setModicadoPor(String modicadoPor) {
		this.modicadoPor = modicadoPor;
	}
	/**
	 * @return the sicRol
	 */
	public SicRolDto getSicRol() {
		return SicRol;
	}
	/**
	 * @param sicRol the sicRol to set
	 */
	public void setSicRol(SicRolDto sicRol) {
		SicRol = sicRol;
	}
	/**
	 * @return the sicPersona
	 */
	public SicPersonaDto getSicPersona() {
		return SicPersona;
	}
	/**
	 * @param sicPersona the sicPersona to set
	 */
	public void setSicPersona(SicPersonaDto sicPersona) {
		SicPersona = sicPersona;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicUsuarioDto [idSicUsuario=" + idSicUsuario
				+ ", nombreUsuario=" + nombreUsuario + ", fxActivacion="
				+ fxActivacion + ", fxDesactivacion=" + fxDesactivacion
				+ ", fxCreado=" + fxCreado + ", creadoPor=" + creadoPor
				+ ", fxModicado=" + fxModicado + ", modicadoPor=" + modicadoPor
				+ ", SicRol=" + SicRol + ", SicPersona=" + SicPersona + "]";
	}
	/**
	 * @return the sicEstadoUsuario
	 */
	public SicEstadoUsuarioDto getSicEstadoUsuario() {
		return sicEstadoUsuario;
	}
	/**
	 * @param sicEstadoUsuario the sicEstadoUsuario to set
	 */
	public void setSicEstadoUsuario(SicEstadoUsuarioDto sicEstadoUsuario) {
		this.sicEstadoUsuario = sicEstadoUsuario;
	}


}
