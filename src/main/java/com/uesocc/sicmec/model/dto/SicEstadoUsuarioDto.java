/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

/**
 * @author xtiyo
 *
 */
public class SicEstadoUsuarioDto implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String idSicEstadoUsuario;
	  private String descripcion;
	  
	/**
	 * @return the idSicEstadoUsuario
	 */
	public String getIdSicEstadoUsuario() {
		return idSicEstadoUsuario;
	}
	/**
	 * @param idSicEstadoUsuario the idSicEstadoUsuario to set
	 */
	public void setIdSicEstadoUsuario(String idSicEstadoUsuario) {
		this.idSicEstadoUsuario = idSicEstadoUsuario;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicEstadoUsuarioDto [idSicEstadoUsuario=" + idSicEstadoUsuario
				+ ", descripcion=" + descripcion + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((idSicEstadoUsuario == null) ? 0 : idSicEstadoUsuario
						.hashCode());
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
		SicEstadoUsuarioDto other = (SicEstadoUsuarioDto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idSicEstadoUsuario == null) {
			if (other.idSicEstadoUsuario != null)
				return false;
		} else if (!idSicEstadoUsuario.equals(other.idSicEstadoUsuario))
			return false;
		return true;
	}
}
