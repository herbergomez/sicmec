package com.uesocc.sicmec.model.service;

import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.entity.SicMunicipio;

public interface SicMunicipioService extends BaseService<SicMunicipioDto, Integer>{


	/**
	* @param id Id de Departamento
	* @return Lista de Municipios.
	*/
	List <SicMunicipioDto> getMunicipiosPorDepartamento(int idDepartamento);

	SicMunicipioDto findOneBynombreMunicipio(String name);
}
