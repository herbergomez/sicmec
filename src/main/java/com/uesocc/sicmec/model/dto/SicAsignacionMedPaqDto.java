package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

import com.uesocc.sicmec.model.entity.SicCatMedicamentos;
import com.uesocc.sicmec.model.entity.SicMedicamento;

public class SicAsignacionMedPaqDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idAsignacionMedPaq;
	private SicDrugDto idMedicamento;
	private SicPaqMedDto idMedPaq;
	private String medPaqStatus;
	
	
	
	/**
	 * @return the medPaqStatus
	 */
	public String getMedPaqStatus() {
		return medPaqStatus;
	}




	/**
	 * @param medPaqStatus the medPaqStatus to set
	 */
	public void setMedPaqStatus(String medPaqStatus) {
		this.medPaqStatus = medPaqStatus;
	}




	/**
	 * @return the idAsignacionMedPaq
	 */
	public String getIdAsignacionMedPaq() {
		return idAsignacionMedPaq;
	}




	/**
	 * @param idAsignacionMedPaq the idAsignacionMedPaq to set
	 */
	public void setIdAsignacionMedPaq(String idAsignacionMedPaq) {
		this.idAsignacionMedPaq = idAsignacionMedPaq;
	}




	/**
	 * @return the idMedicamento
	 */
	public SicDrugDto getIdMedicamento() {
		return idMedicamento;
	}




	/**
	 * @param idMedicamento the idMedicamento to set
	 */
	public void setIdMedicamento(SicDrugDto idMedicamento) {
		this.idMedicamento = idMedicamento;
	}




	/**
	 * @return the idMedPaq
	 */
	public SicPaqMedDto getIdMedPaq() {
		return idMedPaq;
	}




	/**
	 * @param idMedPaq the idMedPaq to set
	 */
	public void setIdMedPaq(SicPaqMedDto idMedPaq) {
		this.idMedPaq = idMedPaq;
	}




	/**
	 * Overriding toString to show the structure of DTO
	 */
	@Override
	public String toString() {
		return "SicAsignacionMedPaqDto [idAsignacion=" + this.idAsignacionMedPaq + ", idMed="
				+ this.idMedicamento + "idMedPaq="+ this.idMedPaq +"]";
	}
	
}
