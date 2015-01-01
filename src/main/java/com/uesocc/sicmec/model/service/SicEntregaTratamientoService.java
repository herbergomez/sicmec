package com.uesocc.sicmec.model.service;

import java.util.Date;
import java.util.List;
import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;

public interface SicEntregaTratamientoService extends
		BaseService<SicEntregaTratamientoDto, Integer> {

	List<SicEntregaTratamientoDto> findAllTreatmentDeliveryByPaciente(String id);
	Long countAllByPacienteAndFecha(int paciente,Date fxOne,Date fxTwo);
}
