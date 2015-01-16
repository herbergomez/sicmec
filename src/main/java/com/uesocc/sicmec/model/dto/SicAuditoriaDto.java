package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

public class SicAuditoriaDto implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idSicAuditoria;
    private String accion;
    private String descripcion;
    private String fxAuditoria;
    private SicUsuarioDto fkSicUsuario;
    
    
    public SicAuditoriaDto()
    {
    	
    }
	/**
	 * @param idSicAuditoria
	 * @param accion
	 * @param descripcion
	 * @param fxAuditoria
	 * @param fkSicUsuario
	 */
	public SicAuditoriaDto(String idSicAuditoria, String accion,String descripcion, String fxAuditoria, SicUsuarioDto fkSicUsuario) 
	{
		super();
		this.idSicAuditoria = idSicAuditoria;
		this.accion = accion;
		this.descripcion = descripcion;
		this.fxAuditoria = fxAuditoria;
		this.fkSicUsuario = fkSicUsuario;
	}
	
	/**
	 * @return the idSicAuditoria
	 */
	public String getIdSicAuditoria() {
		return idSicAuditoria;
	}
	/**
	 * @param idSicAuditoria the idSicAuditoria to set
	 */
	public void setIdSicAuditoria(String idSicAuditoria) {
		this.idSicAuditoria = idSicAuditoria;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	/**
	 * @return the fxAuditoria
	 */
	public String getFxAuditoria() {
		return fxAuditoria;
	}
	/**
	 * @param fxAuditoria the fxAuditoria to set
	 */
	public void setFxAuditoria(String fxAuditoria) {
		this.fxAuditoria = fxAuditoria;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accion == null) ? 0 : accion.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fkSicUsuario == null) ? 0 : fkSicUsuario.hashCode());
		result = prime * result
				+ ((fxAuditoria == null) ? 0 : fxAuditoria.hashCode());
		result = prime * result
				+ ((idSicAuditoria == null) ? 0 : idSicAuditoria.hashCode());
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
		SicAuditoriaDto other = (SicAuditoriaDto) obj;
		if (accion == null) {
			if (other.accion != null)
				return false;
		} else if (!accion.equals(other.accion))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fkSicUsuario == null) {
			if (other.fkSicUsuario != null)
				return false;
		} else if (!fkSicUsuario.equals(other.fkSicUsuario))
			return false;
		if (fxAuditoria == null) {
			if (other.fxAuditoria != null)
				return false;
		} else if (!fxAuditoria.equals(other.fxAuditoria))
			return false;
		if (idSicAuditoria == null) {
			if (other.idSicAuditoria != null)
				return false;
		} else if (!idSicAuditoria.equals(other.idSicAuditoria))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicAuditoriaDto [idSicAuditoria=" + idSicAuditoria
				+ ", accion=" + accion + ", descripcion=" + descripcion
				+ ", fxAuditoria=" + fxAuditoria + ", fkSicUsuario="
				+ fkSicUsuario + "]";
	}

}
