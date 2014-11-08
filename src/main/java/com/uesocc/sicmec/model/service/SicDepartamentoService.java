package com.uesocc.sicmec.model.service;
import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicDepartamentoDto;

public interface SicDepartamentoService extends BaseService<SicDepartamentoDto, Integer> {

	/**
	* @param id Id de Pais
	* @return Lista de Departamentos.
	*/
	List <SicDepartamentoDto> getDepartamentosPorPais(int idPais);
}
