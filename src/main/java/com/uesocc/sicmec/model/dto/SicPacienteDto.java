package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

import com.uesocc.sicmec.model.entity.SicContactoPaciente;
/**
 * @author Herber
 *
 */

public class SicPacienteDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicPaciente;
    private String numExpediente;
    private String telefonoPaciente;
    private String direccionPaciente;
    private String sexoPaciente;
    private String fxNacimiento;
    private SicPersonaDto fkSicPersona;
    private SicMunicipioDto fkSicMunicipio;
    private String documentoIdentidad;
    private String edad;
    private String fxCreacion;
    private SicEstadoPacienteDto fkSicEstadoPaciente;
    private SicContactoPacienteDto fkSicContactoPaciente;
    private SicTipoPatologiaDto fkSicTipoPatologia;
	/**
	 * @return the idSicPaciente
	 */
	public String getIdSicPaciente() {
		return idSicPaciente;
	}
	/**
	 * @param idSicPaciente the idSicPaciente to set
	 */
	public void setIdSicPaciente(String idSicPaciente) {
		this.idSicPaciente = idSicPaciente;
	}
	
	/**
	 * @return the numExpediente
	 */
	public String getNumExpediente() {
		return numExpediente;
	}
	/**
	 * @param numExpediente the numExpediente to set
	 */
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	/**
	 * @return the telefonoPaciente
	 */
	public String getTelefonoPaciente() {
		return telefonoPaciente;
	}
	/**
	 * @param telefonoPaciente the telefonoPaciente to set
	 */
	public void setTelefonoPaciente(String telefonoPaciente) {
		this.telefonoPaciente = telefonoPaciente;
	}
	/**
	 * @return the direccionPaciente
	 */
	public String getDireccionPaciente() {
		return direccionPaciente;
	}
	/**
	 * @param direccionPaciente the direccionPaciente to set
	 */
	public void setDireccionPaciente(String direccionPaciente) {
		this.direccionPaciente = direccionPaciente;
	}
	/**
	 * @return the sexoPaciente
	 */
	public String getSexoPaciente() {
		return sexoPaciente;
	}
	/**
	 * @param sexoPaciente the sexoPaciente to set
	 */
	public void setSexoPaciente(String sexoPaciente) {
		this.sexoPaciente = sexoPaciente;
	}
	/**
	 * @return the fxNacimiento
	 */
	public String getFxNacimiento() {
		return fxNacimiento;
	}
	/**
	 * @param fxNacimiento the fxNacimiento to set
	 */
	public void setFxNacimiento(String fxNacimiento) {
		this.fxNacimiento = fxNacimiento;
	}
	/**
	 * @return the fkSicTipoPatologia
	 */
	public SicTipoPatologiaDto getFkSicTipoPatologia() {
		return fkSicTipoPatologia;
	}
	/**
	 * @param fkSicTipoPatologia the fkSicTipoPatologia to set
	 */
	public void setFkSicTipoPatologia(SicTipoPatologiaDto fkSicTipoPatologia) {
		this.fkSicTipoPatologia = fkSicTipoPatologia;
	}
	/**
	 * @return the fkSicPersona
	 */
	public SicPersonaDto getFkSicPersona() {
		return fkSicPersona;
	}
	/**
	 * @param fkSicPersona the fkSicPersona to set
	 */
	public void setFkSicPersona(SicPersonaDto fkSicPersona) {
		this.fkSicPersona = fkSicPersona;
	}
	/**
	 * @return the fkSicMunicipio
	 */
	public SicMunicipioDto getFkSicMunicipio() {
		return fkSicMunicipio;
	}
	/**
	 * @param fkSicMunicipio the fkSicMunicipio to set
	 */
	public void setFkSicMunicipio(SicMunicipioDto fkSicMunicipio) {
		this.fkSicMunicipio = fkSicMunicipio;
	}
	/**
	 * @return the fkSicEstadoPaciente
	 */
	public SicEstadoPacienteDto getFkSicEstadoPaciente() {
		return fkSicEstadoPaciente;
	}
	/**
	 * @param fkSicEstadoPaciente the fkSicEstadoPaciente to set
	 */
	public void setFkSicEstadoPaciente(SicEstadoPacienteDto fkSicEstadoPaciente) {
		this.fkSicEstadoPaciente = fkSicEstadoPaciente;
	}
	
	/**
	 * @return the edad
	 */
	public String getEdad() {
		return edad;
	}
	
	
	/**
	 * @return the documentoIdentidad
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	/**
	 * @return the fkSicContactoPaciente
	 */
	public SicContactoPacienteDto getFkSicContactoPaciente() {
		return fkSicContactoPaciente;
	}
	/**
	 * @param fkSicContactoPaciente the fkSicContactoPaciente to set
	 */
	public void setFkSicContactoPaciente(SicContactoPacienteDto fkSicContactoPaciente) {
		this.fkSicContactoPaciente = fkSicContactoPaciente;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	
	/**
	 * @return the fxCreacion
	 */
	public String getFxCreacion() {
		return fxCreacion;
	}
	/**
	 * @param fxCreacion the fxCreacion to set
	 */
	public void setFxCreacion(String fxCreacion) {
		this.fxCreacion = fxCreacion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicPacienteDto [idSicPaciente=" + idSicPaciente
				+ ", numExpediente=" + numExpediente + ", telefonoPaciente="
				+ telefonoPaciente + ", direccionPaciente=" + direccionPaciente
				+ ", sexoPaciente=" + sexoPaciente + ", fxNacimiento="
				+ fxNacimiento + ", fkSicPersona=" + fkSicPersona
				+ ", fkSicMunicipio=" + fkSicMunicipio
				+ ", documentoIdentidad=" + documentoIdentidad + ", edad="
				+ edad + ", fxCreacion=" + fxCreacion
				+ ", fkSicEstadoPaciente=" + fkSicEstadoPaciente
				+ ", fkSicContactoPaciente=" + fkSicContactoPaciente
				+ ", fkSicTipoPatologia=" + fkSicTipoPatologia + "]";
	}
	
	    
}
