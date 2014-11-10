package com.uesocc.sicmec.model.service;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicTipoPatologiaDto;

public interface SicTipoPatologiaService extends BaseService<SicTipoPatologiaDto, Integer> {
	
	boolean validacionTipoPatologia(String nombre);
}