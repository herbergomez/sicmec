/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
import java.util.List;

import com.uesocc.sicmec.model.entity.SicMedicamento;


/**
 * @author xtiyo
 *
 */
public class SicTratamientoDto implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicTratamiento;
    private String comentario;
    private String periodisidad;
    private String dosis;
    private String fxTratamiento;
    private SicCitaMedicaDto fkSicCitaMedica;
    private SicPaqMedDto fkSicCatMedicamentos;
    
    private boolean entregaValida; 
    
    private List<SicDrugDto> listMeds;
    
	/**
	 * @return the idSicTratamiento
	 */
	public String getIdSicTratamiento() {
		return idSicTratamiento;
	}
	/**
	 * @param idSicTratamiento the idSicTratamiento to set
	 */
	public void setIdSicTratamiento(String idSicTratamiento) {
		this.idSicTratamiento = idSicTratamiento;
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
	 * @return the periodisidad
	 */
	public String getPeriodisidad() {
		return periodisidad;
	}
	/**
	 * @param periodisidad the periodisidad to set
	 */
	public void setPeriodisidad(String periodisidad) {
		this.periodisidad = periodisidad;
	}
	/**
	 * @return the dosis
	 */
	public String getDosis() {
		return dosis;
	}
	/**
	 * @param dosis the dosis to set
	 */
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	/**
	 * @return the fxTratamiento
	 */
	public String getFxTratamiento() {
		return fxTratamiento;
	}
	/**
	 * @param fxTratamiento the fxTratamiento to set
	 */
	public void setFxTratamiento(String fxTratamiento) {
		this.fxTratamiento = fxTratamiento;
	}
	/**
	 * @return the fkSicCitaMedica
	 */
	public SicCitaMedicaDto getFkSicCitaMedica() {
		return fkSicCitaMedica;
	}
	/**
	 * @param fkSicCitaMedica the fkSicCitaMedica to set
	 */
	public void setFkSicCitaMedica(SicCitaMedicaDto fkSicCitaMedica) {
		this.fkSicCitaMedica = fkSicCitaMedica;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dosis == null) ? 0 : dosis.hashCode());
		result = prime
				* result
				+ ((fkSicCatMedicamentos == null) ? 0 : fkSicCatMedicamentos
						.hashCode());
		result = prime * result
				+ ((fkSicCitaMedica == null) ? 0 : fkSicCitaMedica.hashCode());
		result = prime * result
				+ ((fxTratamiento == null) ? 0 : fxTratamiento.hashCode());
		result = prime
				* result
				+ ((idSicTratamiento == null) ? 0 : idSicTratamiento.hashCode());
		result = prime * result
				+ ((periodisidad == null) ? 0 : periodisidad.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SicTratamientoDto other = (SicTratamientoDto) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (dosis == null) {
			if (other.dosis != null)
				return false;
		} else if (!dosis.equals(other.dosis))
			return false;
		if (fkSicCatMedicamentos == null) {
			if (other.fkSicCatMedicamentos != null)
				return false;
		} else if (!fkSicCatMedicamentos.equals(other.fkSicCatMedicamentos))
			return false;
		if (fkSicCitaMedica == null) {
			if (other.fkSicCitaMedica != null)
				return false;
		} else if (!fkSicCitaMedica.equals(other.fkSicCitaMedica))
			return false;
		if (fxTratamiento == null) {
			if (other.fxTratamiento != null)
				return false;
		} else if (!fxTratamiento.equals(other.fxTratamiento))
			return false;
		if (idSicTratamiento == null) {
			if (other.idSicTratamiento != null)
				return false;
		} else if (!idSicTratamiento.equals(other.idSicTratamiento))
			return false;
		if (periodisidad == null) {
			if (other.periodisidad != null)
				return false;
		} else if (!periodisidad.equals(other.periodisidad))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicTratamientoDto [idSicTratamiento=" + idSicTratamiento
				+ ", comentario=" + comentario + ", periodisidad="
				+ periodisidad + ", dosis=" + dosis + ", fxTratamiento="
				+ fxTratamiento + ", fkSicCitaMedica=" + fkSicCitaMedica
				+ ", fkSicCatMedicamentos=" + fkSicCatMedicamentos + "]";
	}
	public SicTratamientoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the fkSicCatMedicamentos
	 */
	public SicPaqMedDto getFkSicCatMedicamentos() {
		return fkSicCatMedicamentos;
	}
	/**
	 * @param fkSicCatMedicamentos the fkSicCatMedicamentos to set
	 */
	public void setFkSicCatMedicamentos(SicPaqMedDto fkSicCatMedicamentos) {
		this.fkSicCatMedicamentos = fkSicCatMedicamentos;
	}
	/**
	 * @return the listMeds
	 */
	public List<SicDrugDto> getListMeds() {
		return listMeds;
	}
	/**
	 * @param listMeds the listMeds to set
	 */
	public void setListMeds(List<SicDrugDto> listMeds) {
		this.listMeds = listMeds;
	}
	/**
	 * @return the entregaValida
	 */
	public boolean esEntregaValida() {
		return entregaValida;
	}
	/**
	 * @param entregaValida the entregaValida to set
	 */
	public void setEntregaValida(boolean entregaValida) {
		this.entregaValida = entregaValida;
	}


}
