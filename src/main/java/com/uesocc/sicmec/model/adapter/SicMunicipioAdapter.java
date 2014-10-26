package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.entity.SicMunicipio;

public class SicMunicipioAdapter implements 
								BaseAdapter<SicMunicipio, SicMunicipioDto> {

	@Override
	public SicMunicipio dtoToEntity(SicMunicipioDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicDepartamentoAdapter adp = new SicDepartamentoAdapter();
		
		SicMunicipio entidad = new SicMunicipio();
		entidad.setIdSicMunicipio((obj.getFkSicDepartamento()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicMunicipio()):null);
		entidad.setNombreMunicipio((obj.getNombreMunicipio()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreMunicipio()):"");
		entidad.setFkSicDepartamento(adp.dtoToEntity(obj.getFkSicDepartamento()));
		
		return entidad;
	}

	@Override
	public SicMunicipioDto entityToDto(SicMunicipio obj) {
		// TODO Auto-generated method stub
		SicDepartamentoAdapter adp = new SicDepartamentoAdapter();
		SicMunicipioDto dto = new SicMunicipioDto();
		dto.setIdSicMunicipio((obj.getIdSicMunicipio()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicMunicipio().toString()):"");
		dto.setNombreMunicipio((obj.getNombreMunicipio()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreMunicipio()):"");
		dto.setFkSicDepartamento(adp.entityToDto(obj.getFkSicDepartamento()));
		return dto;
	}

}
