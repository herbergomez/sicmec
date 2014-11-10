package com.uesocc.sicmec.model.service;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicPatologiaDto;

public interface SicPatologiaService extends BaseService<SicPatologiaDto, Integer> {

	boolean validacionPatologia(String nombre);
}
