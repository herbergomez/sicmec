package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicTipoPatologiaDto;
import com.uesocc.sicmec.model.entity.SicTipoPatologia;

public class SicTipoPatologiaAdapter implements BaseAdapter<SicTipoPatologia, SicTipoPatologiaDto>{

	@Override
	public SicTipoPatologia dtoToEntity(SicTipoPatologiaDto obj)
												throws ParseException {
		// TODO Auto-generated method stub
		SicPatologiaAdapter adptp = new SicPatologiaAdapter();
		SicTipoPatologia entidad = new SicTipoPatologia();
		entidad.setIdSicTipoPatologia((obj.getIdSicTipoPatologia()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicTipoPatologia()):null);
		entidad.setNombreTipoPatologia((obj.getNombreTipoPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreTipoPatologia()):"");
		entidad.setDescripcionTipoPatologia((obj.getDescripcionTipoPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcionTipoPatologia()):"");
		entidad.setFkSicPatologia(adptp.dtoToEntity(obj.getFkSicPatologia()));
		return entidad;
	}

	@Override
	public SicTipoPatologiaDto entityToDto(SicTipoPatologia obj) {
		// TODO Auto-generated method stub
		SicPatologiaAdapter adptp = new SicPatologiaAdapter();
		SicTipoPatologiaDto dto = new SicTipoPatologiaDto();
		dto.setIdSicTipoPatologia((obj.getIdSicTipoPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicTipoPatologia().toString()):"");
		dto.setNombreTipoPatologia((obj.getNombreTipoPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreTipoPatologia()):"");
		dto.setDescripcionTipoPatologia((obj.getDescripcionTipoPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcionTipoPatologia()):"");		
		dto.setFkSicPatologia(adptp.entityToDto(obj.getFkSicPatologia()));
		return dto;
	}

}

