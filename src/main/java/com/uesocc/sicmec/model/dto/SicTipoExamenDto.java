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
public class SicTipoExamenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idSicTipoExamen;
    private String descripcion;
    
	public SicTipoExamenDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idSicTipoExamen
	 */
	public String getIdSicTipoExamen() {
		return idSicTipoExamen;
	}
	/**
	 * @param idSicTipoExamen the idSicTipoExamen to set
	 */
	public void setIdSicTipoExamen(String idSicTipoExamen) {
		this.idSicTipoExamen = idSicTipoExamen;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSicTipoExamen == null) ? 0 : idSicTipoExamen.hashCode());
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
		SicTipoExamenDto other = (SicTipoExamenDto) obj;
		if (idSicTipoExamen == null) {
			if (other.idSicTipoExamen != null)
				return false;
		} else if (!idSicTipoExamen.equals(other.idSicTipoExamen))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicTipoExamenDto [idSicTipoExamen=" + idSicTipoExamen
				+ ", descripcion=" + descripcion + "]";
	}


}
