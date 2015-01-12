package com.uesocc.sicmec.model.dto;


public class SicEntregaTratamientoDto {

	private String idSicEntregaTratamiento;
    private String comentario;
    private String tipo;
    private String fxEntregaTratamiento;
    private SicTratamientoDto fkSicTratamiento;
    private SicUsuarioDto fkSicUsuario;
    
	/**
	 * @param idSicEntregaTratamiento
	 * @param comentario
	 * @param fxEntregaTratamiento
	 * @param fkSicTratamiento
	 */
	public SicEntregaTratamientoDto(String idSicEntregaTratamiento,
			String comentario, String fxEntregaTratamiento, String tipo
			) {
		super();
		this.idSicEntregaTratamiento = idSicEntregaTratamiento;
		this.comentario = comentario;
		this.fxEntregaTratamiento = fxEntregaTratamiento;
		this.tipo = tipo;
	}
	public SicEntregaTratamientoDto() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idSicEntregaTratamiento
	 */
	public String getIdSicEntregaTratamiento() {
		return idSicEntregaTratamiento;
	}
	/**
	 * @param idSicEntregaTratamiento the idSicEntregaTratamiento to set
	 */
	public void setIdSicEntregaTratamiento(String idSicEntregaTratamiento) {
		this.idSicEntregaTratamiento = idSicEntregaTratamiento;
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
	 * @return the fxEntregaTratamiento
	 */
	public String getFxEntregaTratamiento() {
		return fxEntregaTratamiento;
	}
	/**
	 * @param fxEntregaTratamiento the fxEntregaTratamiento to set
	 */
	public void setFxEntregaTratamiento(String fxEntregaTratamiento) {
		this.fxEntregaTratamiento = fxEntregaTratamiento;
	}
	/**
	 * @return the fkSicTratamiento
	 */
	public SicTratamientoDto getFkSicTratamiento() {
		return fkSicTratamiento;
	}
	/**
	 * @param fkSicTratamiento the fkSicTratamiento to set
	 */
	public void setFkSicTratamiento(SicTratamientoDto fkSicTratamiento) {
		this.fkSicTratamiento = fkSicTratamiento;
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
		result = prime
				* result
				+ ((fkSicTratamiento == null) ? 0 : fkSicTratamiento.hashCode());
		result = prime
				* result
				+ ((fxEntregaTratamiento == null) ? 0 : fxEntregaTratamiento
						.hashCode());
		result = prime
				* result
				+ ((idSicEntregaTratamiento == null) ? 0
						: idSicEntregaTratamiento.hashCode());
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
		SicEntregaTratamientoDto other = (SicEntregaTratamientoDto) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (fkSicTratamiento == null) {
			if (other.fkSicTratamiento != null)
				return false;
		} else if (!fkSicTratamiento.equals(other.fkSicTratamiento))
			return false;
		if (fxEntregaTratamiento == null) {
			if (other.fxEntregaTratamiento != null)
				return false;
		} else if (!fxEntregaTratamiento.equals(other.fxEntregaTratamiento))
			return false;
		if (idSicEntregaTratamiento == null) {
			if (other.idSicEntregaTratamiento != null)
				return false;
		} else if (!idSicEntregaTratamiento
				.equals(other.idSicEntregaTratamiento))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicEntregaTratamientoDto [idSicEntregaTratamiento="
				+ idSicEntregaTratamiento + ", comentario=" + comentario
				+ ", fxEntregaTratamiento=" + fxEntregaTratamiento
				+ ", fkSicTratamiento=" + fkSicTratamiento + "]";
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
}
