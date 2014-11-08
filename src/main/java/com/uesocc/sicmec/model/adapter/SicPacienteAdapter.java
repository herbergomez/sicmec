package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.entity.SicEstadoPaciente;
import com.uesocc.sicmec.model.entity.SicPaciente;
import com.uesocc.sicmec.model.dto.SicPacienteDto;

/**
 * @author Herber
 *
 */

public class SicPacienteAdapter implements
		BaseAdapter<SicPaciente, SicPacienteDto> {

	@Override
	public SicPaciente dtoToEntity(SicPacienteDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicPersonaAdapter adp = new SicPersonaAdapter();
		SicMunicipioAdapter adpm = new SicMunicipioAdapter();
		SicEstadoPacienteAdapter adpa = new SicEstadoPacienteAdapter();
		SicContactoPacienteAdapter adpc = new SicContactoPacienteAdapter();
		SicTipoPatologiaAdapter adptp = new SicTipoPatologiaAdapter();
		
		SicPaciente entidad = new SicPaciente();
		entidad.setIdSicPaciente((obj.getIdSicPaciente()!=null)? SicDataTypeFormat.toIntValue(obj.getIdSicPaciente()):null);
		entidad.setNumeroExpediente((obj.getNumExpediente()!=null)?SicDataTypeFormat.toStringValue(obj.getNumExpediente()):"");
		entidad.setDireccionPaciente((obj.getDireccionPaciente()!=null)? SicDataTypeFormat.toStringValue(obj.getDireccionPaciente()):"");
		entidad.setTelefonoPaciente((obj.getTelefonoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getTelefonoPaciente()):"");
		entidad.setSexoPaciente((obj.getSexoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getSexoPaciente()):"");
		entidad.setFxNacimiento((obj.getFxNacimiento()!=null)?SicDataTypeFormat.toDateValue(obj.getFxNacimiento()):new Date());
		entidad.setDuiPaciente((obj.getDocumentoIdentidad()!=null)?SicDataTypeFormat.toStringValue(obj.getDocumentoIdentidad()):"");
		entidad.setFkSicTipoPatologia(adptp.dtoToEntity(obj.getFkSicTipoPatologia()));
		entidad.setFkSicPersona(adp.dtoToEntity(obj.getFkSicPersona()));
		entidad.setFkSicMunicipio(adpm.dtoToEntity(obj.getFkSicMunicipio()));
    	entidad.setFkSicEstadoPaciente(adpa.dtoToEntity(obj.getFkSicEstadoPaciente()));
    	if (obj.getFkSicContactoPaciente()!=null) {
    		entidad.setFkSicContactoPaciente(adpc.dtoToEntity(obj.getFkSicContactoPaciente()));
    	}    	
		return entidad;
	}

	@Override
	public SicPacienteDto entityToDto(SicPaciente obj) {
		SimpleDateFormat normalFormat = new SimpleDateFormat("yyyy-MM-dd");
		SicPersonaAdapter adp = new SicPersonaAdapter();
		SicMunicipioAdapter adpm = new SicMunicipioAdapter();
		SicEstadoPacienteAdapter adpa = new SicEstadoPacienteAdapter();
		SicContactoPacienteAdapter adpc=new SicContactoPacienteAdapter();
		SicTipoPatologiaAdapter adptp = new SicTipoPatologiaAdapter();
		
		SicPacienteDto dto = new SicPacienteDto();
		dto.setIdSicPaciente((obj.getIdSicPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getIdSicPaciente().toString()):"");
		dto.setNumExpediente((obj.getNumeroExpediente()!=null)?SicDataTypeFormat.toStringValue(obj.getNumeroExpediente()):"");
		dto.setDireccionPaciente((obj.getDireccionPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getDireccionPaciente()):"");
		dto.setTelefonoPaciente((obj.getTelefonoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getTelefonoPaciente()):"");
		dto.setSexoPaciente((obj.getSexoPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getSexoPaciente()):"");
		dto.setFxNacimiento((obj.getFxNacimiento()!=null) ? normalFormat.format(obj.getFxNacimiento()):normalFormat.format(new Date()));
		dto.setDocumentoIdentidad((obj.getDuiPaciente()!=null)?SicDataTypeFormat.toStringValue(obj.getDuiPaciente()):"");
		dto.setEdad((obj.getEdad()!=null)?SicDataTypeFormat.toStringValue(obj.getEdad().toString()):"");
		dto.setFkSicTipoPatologia(adptp.entityToDto(obj.getFkSicTipoPatologia()));
		dto.setFkSicPersona(adp.entityToDto(obj.getFkSicPersona()));
		dto.setFkSicMunicipio(adpm.entityToDto(obj.getFkSicMunicipio()));
		dto.setFkSicEstadoPaciente(adpa.entityToDto(obj.getFkSicEstadoPaciente()));
		if (obj.getFkSicContactoPaciente()!=null) {
			dto.setFkSicContactoPaciente(adpc.entityToDto(obj.getFkSicContactoPaciente()));
		}		
		return dto;
	}


	
}