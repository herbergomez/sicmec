package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.entity.SicEntregaTratamiento;

public class SicEntregaTratamientoAdapter implements
		BaseAdapter<SicEntregaTratamiento, SicEntregaTratamientoDto> {

	@Override
	public SicEntregaTratamiento dtoToEntity(SicEntregaTratamientoDto obj)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		SicUsuarioAdapter adpp = new SicUsuarioAdapter();
		
		SicEntregaTratamiento entity = new SicEntregaTratamiento();
		entity.setIdSicEntregaTratamiento((obj.getIdSicEntregaTratamiento()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicEntregaTratamiento()):null);
		entity.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		entity.setTipo((obj.getTipo()!=null) ? SicDataTypeFormat.toStringValue(obj.getTipo()):"");
		entity.setFxEntregaTratamiento((obj.getFxEntregaTratamiento()!=null) ? SicDataTypeFormat.toDateTimeValue(obj.getFxEntregaTratamiento()):new Date());
		entity.setFkSicTratamiento(adp.dtoToEntity(obj.getFkSicTratamiento()));
		entity.setFkSicUsuario((obj.getFkSicUsuario()!=null) ? adpp.dtoToEntity(obj.getFkSicUsuario()):null);
		return entity;
	}

	@Override
	public SicEntregaTratamientoDto entityToDto(SicEntregaTratamiento obj) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		SicUsuarioAdapter adpp = new SicUsuarioAdapter();
		SicEntregaTratamientoDto dto = new SicEntregaTratamientoDto();
		dto.setIdSicEntregaTratamiento((obj.getIdSicEntregaTratamiento()!=null)?(obj.getIdSicEntregaTratamiento().toString()):"");
		dto.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		dto.setTipo((obj.getTipo()!=null) ? SicDataTypeFormat.toStringValue(obj.getTipo()):"");
		dto.setFxEntregaTratamiento((obj.getFxEntregaTratamiento()!=null) ? format.format(obj.getFxEntregaTratamiento()):null);
		dto.setFkSicTratamiento(adp.entityToDto(obj.getFkSicTratamiento()));
		dto.setFkSicUsuario((obj.getFkSicUsuario()!=null) ? adpp.entityToDto(obj.getFkSicUsuario()):null);
		return dto;
	}

}
