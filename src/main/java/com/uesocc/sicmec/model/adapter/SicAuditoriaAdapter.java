package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
import com.uesocc.sicmec.model.entity.SicAuditoria;

public class SicAuditoriaAdapter implements
		BaseAdapter<SicAuditoria, SicAuditoriaDto> {

	@Override
	public SicAuditoria dtoToEntity(SicAuditoriaDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicUsuarioAdapter adp = new SicUsuarioAdapter();
		SicAuditoria entity = new SicAuditoria();
		entity.setIdSicAuditoria((obj.getIdSicAuditoria()!=null) ? SicDataTypeFormat.toIntValue(obj.getIdSicAuditoria()):null);
		entity.setAccion(obj.getAccion());
		entity.setDescripcion(obj.getDescripcion());
		entity.setFkSicUsuario(adp.dtoToEntity(obj.getFkSicUsuario()));
		entity.setFxAuditoria(SicDataTypeFormat.toDateTimeValue(obj.getFxAuditoria()));
		return entity;
	}

	@Override
	public SicAuditoriaDto entityToDto(SicAuditoria obj) {
		// TODO Auto-generated method stub
		SicUsuarioAdapter adp = new SicUsuarioAdapter();
		SicAuditoriaDto dto = new SicAuditoriaDto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		dto.setIdSicAuditoria((obj.getIdSicAuditoria()!=null) ? (obj.getIdSicAuditoria().toString()):"");
		dto.setAccion(obj.getAccion());
		dto.setDescripcion(obj.getDescripcion());
		dto.setFkSicUsuario(adp.entityToDto(obj.getFkSicUsuario()));
		dto.setFxAuditoria(format.format(obj.getFxAuditoria()));
		return dto;
	}

}
