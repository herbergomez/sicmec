package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

/**
 * 
 * @author kevingcfcb88
 *
 */
public class SicDrugDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idDrug;
	private String drugName;
	private String drugDescription;
	private String estado;
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the idDrug
	 */
	public String getIdDrug() {
		return idDrug;
	}
	/**
	 * @param idDrug the idDrug to set
	 */
	public void setIdDrug(String idDrug) {
		this.idDrug = idDrug;
	}
	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}
	/**
	 * @param drugName the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	/**
	 * @return the drugDescription
	 */
	public String getDrugDescription() {
		return drugDescription;
	}
	/**
	 * @param drugDescription the drugDescription to set
	 */
	public void setDrugDescription(String drugDescription) {
		this.drugDescription = drugDescription;
	}
	
	/**
	 * Overriding toString to show the structure of DTO
	 */
	@Override
	public String toString() {
		return "SicDrugDto [idDrug=" + this.idDrug + ", drugName="
				+ this.drugName + "estado="+ this.estado +", description=" + this.drugDescription + "]";
	}
}
