package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

public class SicEstadoPacienteDto  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String idSicEstadoPaciente;
	  private String descripcion;
	/**
	 * @return the idSicEstadoPaciente
	 */
	public String getIdSicEstadoPaciente() {
		return idSicEstadoPaciente;
	}
	/**
	 * @param idSicEstadoPaciente the idSicEstadoPaciente to set
	 */
	public void setIdSicEstadoPaciente(String idSicEstadoPaciente) {
		this.idSicEstadoPaciente = idSicEstadoPaciente;
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
		return "SicEstadoPacienteDto [idSicEstadoPaciente="
				+ idSicEstadoPaciente + ", descripcion=" + descripcion + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((idSicEstadoPaciente == null) ? 0 : idSicEstadoPaciente
						.hashCode());
		return result;
	}
	 (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SicEstadoPacienteDto other = (SicEstadoPacienteDto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idSicEstadoPaciente == null) {
			if (other.idSicEstadoPaciente != null)
				return false;
		} else if (!idSicEstadoPaciente.equals(other.idSicEstadoPaciente))
			return false;
		return true;
	}
*/
}
