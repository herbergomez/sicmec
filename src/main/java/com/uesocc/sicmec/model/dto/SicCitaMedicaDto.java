package com.uesocc.sicmec.model.dto;

import java.io.Serializable;


public class SicCitaMedicaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicCitaMedica;
    private String fxCitaMedica;
    private String diagnostico;
    private String comentario;
    private String signosSintomas;
    private String estatura;
    private String peso;
    private SicUsuarioDto fkSicUsuario;
    private SicPacienteDto fkSicPaciente;
    
	/**
	 * @return the idSicCitaMedica
	 */
	public String getIdSicCitaMedica() {
		return idSicCitaMedica;
	}
	/**
	 * @param idSicCitaMedica the idSicCitaMedica to set
	 */
	public void setIdSicCitaMedica(String idSicCitaMedica) {
		this.idSicCitaMedica = idSicCitaMedica;
	}
	/**
	 * @return the fxCitaMedica
	 */
	public String getFxCitaMedica() {
		return fxCitaMedica;
	}
	/**
	 * @param fxCitaMedica the fxCitaMedica to set
	 */
	public void setFxCitaMedica(String fxCitaMedica) {
		this.fxCitaMedica = fxCitaMedica;
	}
	/**
	 * @return the diagnostico
	 */
	public String getDiagnostico() {
		return diagnostico;
	}
	/**
	 * @param diagnostico the diagnostico to set
	 */
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the fkSicUsuario
	 */
	public SicUsuarioDto getFkSicUsuario() {
		return fkSicUsuario;
	}
	/**
	 * @param fkSicUsuario the fkSicUsuario to set
	 */
	public void setFkSicUsuario(SicUsuarioDto fkSicUsuario) {
		this.fkSicUsuario = fkSicUsuario;
	}
	/**
	 * @return the fkSicPaciente
	 */
	public SicPacienteDto getFkSicPaciente() {
		return fkSicPaciente;
	}
	/**
	 * @param fkSicPaciente the fkSicPaciente to set
	 */
	public void setFkSicPaciente(SicPacienteDto fkSicPaciente) {
		this.fkSicPaciente = fkSicPaciente;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicCitaMedicaDto [idSicCitaMedica=" + idSicCitaMedica
				+ ", fxCitaMedica=" + fxCitaMedica + ", diagnostico="
				+ diagnostico + ", comentario=" + comentario
				+ ", fkSicUsuario=" + fkSicUsuario + ", fkSicPaciente="
				+ fkSicPaciente + "]";
	}
	public SicCitaMedicaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the signosSintomas
	 */
	public String getSignosSintomas() {
		return signosSintomas;
	}
	/**
	 * @param signosSintomas the signosSintomas to set
	 */
	public void setSignosSintomas(String signosSintomas) {
		this.signosSintomas = signosSintomas;
	}
	/**
	 * @return the estatura
	 */
	public String getEstatura() {
		return estatura;
	}
	/**
	 * @param estatura the estatura to set
	 */
	public void setEstatura(String estatura) {
		this.estatura = estatura;
	}
	/**
	 * @return the peso
	 */
	public String getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(String peso) {
		this.peso = peso;
	}

}
