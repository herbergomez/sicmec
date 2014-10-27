package com.uesocc.sicmec.model.service;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicEstadoPacienteDto;
import com.uesocc.sicmec.model.dto.SicEstadoUsuarioDto;

public interface SicEstadoPacienteService extends BaseService<SicEstadoPacienteDto, Integer> {

	SicEstadoPacienteDto findOneByDescripcion(String Descripcion);
}
