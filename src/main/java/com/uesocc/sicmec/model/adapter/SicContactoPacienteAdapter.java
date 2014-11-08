package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.entity.SicContactoPaciente;

public class SicContactoPacienteAdapter implements BaseAdapter<SicContactoPaciente, SicContactoPacienteDto> {

	@Override
	public SicContactoPaciente dtoToEntity(SicContactoPacienteDto obj)
			throws ParseException {
		// TODO Auto-generated method stub
		SicContactoPaciente entidad = new SicContactoPaciente();
		entidad.setIdSicContactoPaciente((obj.getIdSicContactoPaciente()!=null)?SicDataTypeFormat.toIntValue(obj.getIdSicContactoPaciente()):null);
		entidad.setNombreContacto((obj.getNombreContacto()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreContacto()):"");
		entidad.setApellidoContacto((obj.getApellidoContacto()!=null)?SicDataTypeFormat.toStringValue(obj.getApellidoContacto()):"");
		entidad.setDui((obj.getDui()!=null)?SicDataTypeFormat.toStringValue(obj.getDui()):"");
		entidad.setTelefono((obj.getTelefono()!=null)?SicDataTypeFormat.toStringValue(obj.getTelefono()):"");
		return entidad;
	}

	@Override
	public SicContactoPacienteDto entityToDto(SicContactoPaciente obj) {
		// TODO Auto-generated method stub
		SicContactoPacienteDto dto = new SicContactoPacienteDto();
		dto.setIdSicContactoPaciente((obj.getIdSicContactoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicContactoPaciente().toString()):"");
		dto.setNombreContacto((obj.getNombreContacto()!=null)?SicDataTypeFormat.toStringValue(obj.getNombreContacto()):"");
		dto.setApellidoContacto((obj.getApellidoContacto()!=null)?SicDataTypeFormat.toStringValue(obj.getApellidoContacto()):"");
		dto.setDui((obj.getDui()!=null)?SicDataTypeFormat.toStringValue(obj.getDui()):"");
		dto.setTelefono((obj.getTelefono()!=null)?SicDataTypeFormat.toStringValue(obj.getTelefono()):"");
		return dto;
	}

}
