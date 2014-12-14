/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.service;

import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicExamenDto;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public interface SicExamenService extends BaseService<SicExamenDto, Integer> 
{

	List<SicExamenDto> findAllByfkSicCitaMedica_idSicCitaMedica(int id);
}
