/**
 * 
 */
package com.uesocc.sicmec.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author pportillo
 *
 */
public class SicImportarPacienteObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SicPacienteDto> data;
	private String log;
	private String error;
	private int ignorados;
	
	public List<SicPacienteDto> getData() {
		return data;
	}
	public String getLog() {
		return log;
	}
	public String getError() {
		return error;
	}
	public void setData(List<SicPacienteDto> data) {
		this.data = data;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getIgnorados() {
		return ignorados;
	}
	public void setIgnorados(int ignorados) {
		this.ignorados = ignorados;
	}

}
