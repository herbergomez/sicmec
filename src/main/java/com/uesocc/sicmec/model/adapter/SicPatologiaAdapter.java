package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicPatologiaDto;
import com.uesocc.sicmec.model.entity.SicPatologia;

public class SicPatologiaAdapter implements BaseAdapter<SicPatologia, SicPatologiaDto> {

	@Override
	public SicPatologia dtoToEntity(SicPatologiaDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicPatologia entidad = new SicPatologia();
		entidad.setIdSicPatologia((obj.getIdSicPatologia()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicPatologia()):null);
		entidad.setNombrePatologia((obj.getNombrePatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getNombrePatologia()):"");
		entidad.setDescripcionPatologia((obj.getDescripcionPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcionPatologia()):"");
		entidad.setCreadoPor((obj.getCreadoPor()!=null)?SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		entidad.setModifcadoPor((obj.getModifcadoPor()!=null)?SicDataTypeFormat.toStringValue(obj.getModifcadoPor()):"");
		entidad.setFxCreado((obj.getFxCreado()!=null) ?  SicDataTypeFormat.toDateTimeValue(obj.getFxCreado()):SicDataTypeFormat.toDateTimeValue(new Date().toString()));
		if (obj.getFxModicado()!=null) {
			entidad.setFxModicado((obj.getFxModicado()!=null) ? SicDataTypeFormat.toDateTimeValue(obj.getFxModicado()):SicDataTypeFormat.toDateTimeValue(new Date().toString()));
		}		
		return entidad;
	}

	@Override
	public SicPatologiaDto entityToDto(SicPatologia obj) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicPatologiaDto dto = new SicPatologiaDto();
		dto.setIdSicPatologia((obj.getIdSicPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicPatologia().toString()):null);
		dto.setNombrePatologia((obj.getNombrePatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getNombrePatologia()):"");
		dto.setDescripcionPatologia((obj.getDescripcionPatologia()!=null)?SicDataTypeFormat.toStringValue(obj.getDescripcionPatologia()):"");
		dto.setCreadoPor((obj.getCreadoPor()!=null)?SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		dto.setModifcadoPor((obj.getModifcadoPor()!=null)?SicDataTypeFormat.toStringValue(obj.getModifcadoPor()):"");
		dto.setFxCreado((obj.getFxCreado()!=null) ?  format.format(obj.getFxCreado()):format.format(new Date().toString()));
		if (obj.getFxModicado()!=null){
			dto.setFxModicado((obj.getFxModicado()!=null) ?  format.format(obj.getFxModicado()):format.format(new Date().toString()));
		}		
		return dto;
	}

}
