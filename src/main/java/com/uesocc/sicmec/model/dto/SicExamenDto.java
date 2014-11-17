/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;



/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public class SicExamenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SicExamenDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String idSicExamen;
    private String fxCreado;
    private String creadoPor;
    private String fxModificado;
    private String modifcadoPor;
    private String resultado;
    private String comentario;
    private SicTipoExamenDto fkSicTipoExamen;
    private SicCitaMedicaDto fkSicCitaMedica;
	/**
	 * @return the idSicExamen
	 */
	public String getIdSicExamen() {
		return idSicExamen;
	}
	/**
	 * @param idSicExamen the idSicExamen to set
	 */
	public void setIdSicExamen(String idSicExamen) {
		this.idSicExamen = idSicExamen;
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
	 * @return the fxModificado
	 */
	public String getFxModificado() {
		return fxModificado;
	}
	/**
	 * @param fxModificado the fxModificado to set
	 */
	public void setFxModificado(String fxModificado) {
		this.fxModificado = fxModificado;
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
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
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
	 * @return the fkSicTipoExamen
	 */
	public SicTipoExamenDto getFkSicTipoExamen() {
		return fkSicTipoExamen;
	}
	/**
	 * @param fkSicTipoExamen the fkSicTipoExamen to set
	 */
	public void setFkSicTipoExamen(SicTipoExamenDto fkSicTipoExamen) {
		this.fkSicTipoExamen = fkSicTipoExamen;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicExamenDto [idSicExamen=" + idSicExamen + ", fxCreado="
				+ fxCreado + ", creadoPor=" + creadoPor + ", fxModificado="
				+ fxModificado + ", modifcadoPor=" + modifcadoPor
				+ ", resultado=" + resultado + ", comentario=" + comentario
				+ ", fkSicTipoExamen=" + fkSicTipoExamen + ", fkSicCitaMedica="
				+ fkSicCitaMedica + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSicExamen == null) ? 0 : idSicExamen.hashCode());
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
		SicExamenDto other = (SicExamenDto) obj;
		if (idSicExamen == null) {
			if (other.idSicExamen != null)
				return false;
		} else if (!idSicExamen.equals(other.idSicExamen))
			return false;
		return true;
	}


}
