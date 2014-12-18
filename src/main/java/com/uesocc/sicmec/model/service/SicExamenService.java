/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.service;

import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicExamenDto;
import com.uesocc.sicmec.model.dto.SicGraficosDto;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public interface SicExamenService extends BaseService<SicExamenDto, Integer> 
{

	List<SicExamenDto> findAllByfkSicCitaMedica_idSicCitaMedica(int id);
	/**
	 * @param paciente
	 * @return Lista de resultados de examenes por paciente
	 * Esta lista se va a representar en los graficos 
	 * a nivel de vista en la parte de control de citas.
	 */
	List<SicGraficosDto> findAllExamsResultsByPaciente(int tipoExam,int paciente);
	
	
}
