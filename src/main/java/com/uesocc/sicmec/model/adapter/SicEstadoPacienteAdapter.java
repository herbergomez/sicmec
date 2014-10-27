package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicEstadoPacienteDto;
import com.uesocc.sicmec.model.entity.SicEstadoPaciente;

public class SicEstadoPacienteAdapter implements BaseAdapter<SicEstadoPaciente, SicEstadoPacienteDto> {
     
	@Override
	public SicEstadoPaciente dtoToEntity(SicEstadoPacienteDto obj)
													throws ParseException {
		SicEstadoPaciente entidad = new SicEstadoPaciente();
		entidad.setIdSicEstadoPaciente((obj.getIdSicEstadoPaciente()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicEstadoPaciente()):null);
		entidad.setDescripcion((obj.getDescripcion()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcion()):"");
		return entidad;
	}

	@Override
	public SicEstadoPacienteDto entityToDto(SicEstadoPaciente obj) {
		SicEstadoPacienteDto dto = new SicEstadoPacienteDto();
		dto.setIdSicEstadoPaciente((obj.getIdSicEstadoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicEstadoPaciente().toString()):"");
		dto.setDescripcion((obj.getDescripcion()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcion()):"");
		return dto;
	}
   
}
