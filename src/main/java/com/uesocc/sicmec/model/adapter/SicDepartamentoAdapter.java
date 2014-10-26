package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicDepartamentoDto;
import com.uesocc.sicmec.model.entity.SicDepartamento;

public class SicDepartamentoAdapter implements
		BaseAdapter<SicDepartamento, SicDepartamentoDto> {

	@Override
	public SicDepartamento dtoToEntity(SicDepartamentoDto obj)
													throws ParseException {
		// TODO Auto-generated method stub
		SicDepartamento entidad = new SicDepartamento();
		entidad.setIdSicDepartamento((obj.getIdSicDepartamento()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicDepartamento().toString()):null);
		entidad.setNombreDepartamento((obj.getNombreDepartamento()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreDepartamento()):"");
		return entidad;
	}

	@Override
	public SicDepartamentoDto entityToDto(SicDepartamento obj) {
		// TODO Auto-generated method stub
		SicDepartamentoDto dto = new SicDepartamentoDto();
		dto.setIdSicDepartamento((obj.getIdSicDepartamento()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicDepartamento().toString()):"");
		dto.setNombreDepartamento((obj.getNombreDepartamento()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreDepartamento()):"");
		return dto;
	}

}
