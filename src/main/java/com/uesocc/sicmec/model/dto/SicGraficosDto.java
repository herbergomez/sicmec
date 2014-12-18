/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;

/**
 * @autor pablo portillo
 * @fecha 17/12/2014
 */
/* Esta clase sera la encargada de representar las listas que 
 * llenarán los graficos de la progresión de salud de los
 * pacientes. 
 */
public class SicGraficosDto implements Serializable 
{

	/**
	 * @param tipo
	 * @param fx
	 * @param comentario
	 * @param resultado
	 */
	public SicGraficosDto(String tipo, String fx, String comentario,
			String resultado) {
		super();
		this.tipo = tipo;
		this.fx = fx;
		this.comentario = comentario;
		this.resultado = resultado;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tipo de examén
	 */
	private String tipo;
	/**
	 * Fecha de la cita a la que se adjunto el examén
	 */
	private String fx;
	/**
	 * Comentario agregado por la persona que digito el examén
	 */
	private String comentario;
	/**
	 * Resultado del examén
	 */
	private String resultado;
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * 
	 */
	public SicGraficosDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the fx
	 */
	public String getFx() {
		return fx;
	}
	/**
	 * @param fx the fx to set
	 */
	public void setFx(String fx) {
		this.fx = fx;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SicGraficosDto [tipo=" + tipo + ", fx=" + fx + ", comentario="
				+ comentario + ", resultado=" + resultado + "]";
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
		result = prime * result + ((fx == null) ? 0 : fx.hashCode());
		result = prime * result
				+ ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		SicGraficosDto other = (SicGraficosDto) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (fx == null) {
			if (other.fx != null)
				return false;
		} else if (!fx.equals(other.fx))
			return false;
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}
