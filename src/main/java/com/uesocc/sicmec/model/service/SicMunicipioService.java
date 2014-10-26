package com.uesocc.sicmec.model.service;

import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;

public interface SicMunicipioService extends BaseService<SicMunicipioDto, Integer>{


	/**
	* @param id Id de Departamento
	* @return Lista de Municipios.
	*/
	List <SicMunicipioDto> getMunicipiosPorDepartamento(int idDepartamento);
}
