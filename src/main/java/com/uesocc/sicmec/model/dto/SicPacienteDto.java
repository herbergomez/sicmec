package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

import com.uesocc.sicmec.model.entity.SicContactoPaciente;
/**
 * @author Herber
 *
 */

public class SicPacienteDto implements Serializable{

	public SicPacienteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SicPacienteDto(String idSicPaciente, String numExpediente,
			String telefonoPaciente, String direccionPaciente,
			String sexoPaciente, String fxNacimiento,
			SicPersonaDto fkSicPersona, SicMunicipioDto fkSicMunicipio,
			String documentoIdentidad, String edad, String fxCreacion,
			SicEstadoPacienteDto fkSicEstadoPaciente,
			SicContactoPacienteDto fkSicContactoPaciente,
			SicTipoPatologiaDto fkSicTipoPatologia) {
		super();
		this.idSicPaciente = idSicPaciente;
		this.numExpediente = numExpediente;
		this.telefonoPaciente = telefonoPaciente;
		this.direccionPaciente = direccionPaciente;
		this.sexoPaciente = sexoPaciente;
		this.fxNacimiento = fxNacimiento;
		this.fkSicPersona = fkSicPersona;
		this.fkSicMunicipio = fkSicMunicipio;
		this.documentoIdentidad = documentoIdentidad;
		this.edad = edad;
		this.fxCreacion = fxCreacion;
		this.fkSicEstadoPaciente = fkSicEstadoPaciente;
		this.fkSicContactoPaciente = fkSicContactoPaciente;
		this.fkSicTipoPatologia = fkSicTipoPatologia;
	}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((documentoIdentidad == null) ? 0 : documentoIdentidad
						.hashCode());
		result = prime * result
				+ ((numExpediente == null) ? 0 : numExpediente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SicPacienteDto other = (SicPacienteDto) obj;
		if (documentoIdentidad == null) {
			if (other.documentoIdentidad != null)
				return false;
		} else if (!documentoIdentidad.equals(other.documentoIdentidad))
			return false;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exp:" + numExpediente;
				
	}

	
	    
}
