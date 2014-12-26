/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicCitaMedicaDto;
import com.uesocc.sicmec.model.entity.SicCitaMedica;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public class SicCitaMedicaAdapter implements BaseAdapter<SicCitaMedica, SicCitaMedicaDto>{

	

	@Override
	public SicCitaMedica dtoToEntity(SicCitaMedicaDto obj)
			throws ParseException 
	{
		// TODO Auto-generated method stub
		SicCitaMedica entity = new SicCitaMedica();
		SicPacienteAdapter adp = new SicPacienteAdapter();
		SicUsuarioAdapter adpp = new SicUsuarioAdapter();
		
		entity.setIdSicCitaMedica((obj.getIdSicCitaMedica()!=null) ? SicDataTypeFormat.toIntValue(obj.getIdSicCitaMedica()):null);
		entity.setDiagnostico((obj.getDiagnostico()!=null) ? SicDataTypeFormat.toStringValue(obj.getDiagnostico()):"");
		entity.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		entity.setSignosSintomas((obj.getSignosSintomas()!=null) ? SicDataTypeFormat.toStringValue(obj.getSignosSintomas()):"");
		entity.setEstatura((obj.getEstatura()!=null) ? SicDataTypeFormat.toBigDecimalValue(obj.getEstatura()):null);
		entity.setPeso((obj.getPeso()!=null) ? SicDataTypeFormat.toBigDecimalValue(obj.getPeso()):null);
		entity.setFxCitaMedica((obj.getFxCitaMedica()!=null) ? SicDataTypeFormat.toDateTimeValue(obj.getFxCitaMedica()):new Date());
		entity.setFkSicPaciente((obj.getFkSicPaciente()!=null) ? adp.dtoToEntity(obj.getFkSicPaciente()):null);
		entity.setFkSicUsuario((obj.getFkSicUsuario()!=null) ? adpp.dtoToEntity(obj.getFkSicUsuario()):null);
		
		return entity;
	}

	@Override
	public SicCitaMedicaDto entityToDto(SicCitaMedica obj) 
	{
		// TODO Auto-generated method stub
		SicCitaMedicaDto dto = new SicCitaMedicaDto();
		SicPacienteAdapter adp = new SicPacienteAdapter();
		SicUsuarioAdapter adpp = new SicUsuarioAdapter();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		dto.setIdSicCitaMedica((obj.getIdSicCitaMedica()!=null) ? SicDataTypeFormat.toStringValue(obj.getIdSicCitaMedica().toString()):"");
		dto.setDiagnostico((obj.getDiagnostico()!=null) ? SicDataTypeFormat.toStringValue(obj.getDiagnostico()):"");
		dto.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		dto.setSignosSintomas((obj.getSignosSintomas()!=null) ? SicDataTypeFormat.toStringValue(obj.getSignosSintomas()):"");
		dto.setEstatura((obj.getEstatura()!=null) ? (obj.getEstatura().toString()):"");
		dto.setPeso((obj.getPeso()!=null) ? (obj.getPeso().toString()):"");
		dto.setFxCitaMedica((obj.getFxCitaMedica()!=null) ? format.format(obj.getFxCitaMedica()):format.format(new Date()));
		dto.setFkSicPaciente((obj.getFkSicPaciente()!=null) ? adp.entityToDto(obj.getFkSicPaciente()):null);
		dto.setFkSicUsuario((obj.getFkSicUsuario()!=null) ? adpp.entityToDto(obj.getFkSicUsuario()):null);
		return dto;
	}

}
