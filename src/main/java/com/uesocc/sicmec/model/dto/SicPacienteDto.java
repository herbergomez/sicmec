package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
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
    private String fkExpediente;
    private String telefonoPaciente;
    private String direccionPaciente;
    private String sexoPaciente;
    private String fxNacimiento;
    private SicTipoPatologiaDto fkSicTipoPatologia;
    private SicPersonaDto fkSicPersona;
    private SicMunicipioDto fkSicMunicipio;
    private SicEstadoPacienteDto fkSicEstadoPaciente;
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
	 * @return the fkExpediente
	 */
	public String getFkExpediente() {
		return fkExpediente;
	}
	/**
	 * @param fkExpediente the fkExpediente to set
	 */
	public void setFkExpediente(String fkExpediente) {
		this.fkExpediente = fkExpediente;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicPacienteDto [idSicPaciente=" + idSicPaciente
				+ ", fkExpediente=" + fkExpediente + ", telefonoPaciente="
				+ telefonoPaciente + ", direccionPaciente=" + direccionPaciente
				+ ", sexoPaciente=" + sexoPaciente + ", fxNacimiento="
				+ fxNacimiento + ", fkSicTipoPatologia=" + fkSicTipoPatologia
				+ ", fkSicPersona=" + fkSicPersona + ", fkSicMunicipio="
				+ fkSicMunicipio + ", fkSicEstadoPaciente="
				+ fkSicEstadoPaciente + "]";
	}	
	

    
}
