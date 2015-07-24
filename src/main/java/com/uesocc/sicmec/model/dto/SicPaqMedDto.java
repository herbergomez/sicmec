package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
import java.util.List;

import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;

/**
 * 
 * @author kevingcfcb88
 *
 */
public class SicPaqMedDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idPaq;
	private String name;
	private String description;
	private String active;
	private String listMed;
	/**
	 * @return the idPaq
	 */
	public String getIdPaq() {
		return idPaq;
	}
	/**
	 * @param idPaq the idPaq to set
	 */
	public void setIdPaq(String idPaq) {
		this.idPaq = idPaq;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * Overriding toString to show the structure of DTO
	 */
	@Override
	public String toString() {
		return "SicPaqMedDto [idPaq=" + this.idPaq + ", name="
				+ this.name + "desctiption="+ this.description +", active=" + this.active + "]";
	}
	public String getListMed() {
		return listMed;
	}
	public void setListMed(String listMed) {
		this.listMed = listMed;
	}
	

}
