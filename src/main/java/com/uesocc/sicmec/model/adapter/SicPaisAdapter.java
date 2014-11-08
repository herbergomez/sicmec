package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicPaisDto;
import com.uesocc.sicmec.model.entity.SicPais;

public class SicPaisAdapter implements BaseAdapter<SicPais, SicPaisDto> {

	@Override
	public SicPais dtoToEntity(SicPaisDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicPais entidad = new SicPais();
		entidad.setIdSicPais((obj.getIdSicPais()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicPais()):null);
		entidad.setNombrePais((obj.getNombrePais()!=null)?SicDataTypeFormat.toStringValue(obj.getNombrePais()):"");
		return entidad;
	}

	@Override
	public SicPaisDto entityToDto(SicPais obj) {
		// TODO Auto-generated method stub
		SicPaisDto dto = new SicPaisDto();
		dto.setIdSicPais((obj.getIdSicPais()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicPais().toString()):"");
		dto.setNombrePais((obj.getNombrePais()!=null)?SicDataTypeFormat.toStringValue(obj.getNombrePais()):"");
		return dto;
	}

}
