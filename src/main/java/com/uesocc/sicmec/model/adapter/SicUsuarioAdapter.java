/**
 * 
 */
package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.entity.SicUsuario;

/**
 * @author xtiyo
 *
 */
public class SicUsuarioAdapter implements
		BaseAdapter<SicUsuario, SicUsuarioDto> {

	@Override
	public SicUsuario dtoToEntity(SicUsuarioDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicPersonaAdapter adp = new SicPersonaAdapter();
		SicRolAdapter adpp = new SicRolAdapter();
		SicEstadoUsuarioAdapter addp = new SicEstadoUsuarioAdapter();
		
		SicUsuario entidad = new SicUsuario();
		entidad.setIdSicUsuario((obj.getIdSicUsuario()!=null) ? SicDataTypeFormat.toIntValue(obj.getIdSicUsuario()):null);
		entidad.setNombreUsuario((obj.getNombreUsuario()!=null) ? SicDataTypeFormat.toStringValue(obj.getNombreUsuario()):"");
		entidad.setCreadoPor((obj.getCreadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		entidad.setModicadoPor((obj.getModicadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getModicadoPor()):"");
		entidad.setFxCreado((obj.getFxCreado()!=null) ?  SicDataTypeFormat.toDateTimeValue(obj.getFxCreado()):SicDataTypeFormat.toDateTimeValue(new Date().toString()));
		entidad.setFxModicado((obj.getFxModicado()!=null) ? SicDataTypeFormat.toDateTimeValue(obj.getFxModicado()):SicDataTypeFormat.toDateTimeValue(new Date().toString()));
		entidad.setFxActivacion((obj.getFxActivacion()!=null) ? SicDataTypeFormat.toDateValue(obj.getFxActivacion()): new Date());
		entidad.setFxDesactivacion((obj.getFxDesactivacion()!=null) ? SicDataTypeFormat.toDateValue(obj.getFxDesactivacion()):new Date());
		entidad.setFkSicPersona(adp.dtoToEntity(obj.getSicPersona()));
		entidad.setFkSicRol(adpp.dtoToEntity(obj.getSicRol()));
		entidad.setFkSicEstadoUsuario(addp.dtoToEntity(obj.getSicEstadoUsuario()));
		
		return entidad;
	}

	@Override
	public SicUsuarioDto entityToDto(SicUsuario obj) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat normalFormat = new SimpleDateFormat("yyyy-MM-dd");
		SicPersonaAdapter adp = new SicPersonaAdapter();
		SicRolAdapter adpp = new SicRolAdapter();
		SicEstadoUsuarioAdapter addp = new SicEstadoUsuarioAdapter();
		
		SicUsuarioDto dto = new SicUsuarioDto();
		dto.setIdSicUsuario((obj.getIdSicUsuario()!=null) ? SicDataTypeFormat.toStringValue(obj.getIdSicUsuario().toString()):"");
		dto.setNombreUsuario((obj.getNombreUsuario()!=null) ? SicDataTypeFormat.toStringValue(obj.getNombreUsuario()):"");
		dto.setCreadoPor((obj.getCreadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		dto.setModicadoPor((obj.getModicadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getModicadoPor()):"");
		dto.setFxCreado((obj.getFxCreado()!=null) ?  format.format(obj.getFxCreado()):format.format(new Date().toString()));
		dto.setFxModicado((obj.getFxModicado()!=null) ? format.format(obj.getFxModicado()):format.format(new Date().toString()));
		dto.setFxActivacion((obj.getFxActivacion()!=null) ?normalFormat.format(obj.getFxActivacion()): normalFormat.format(new Date()));
		dto.setFxDesactivacion((obj.getFxDesactivacion()!=null) ? normalFormat.format(obj.getFxDesactivacion()):normalFormat.format(new Date()));
		dto.setSicPersona(adp.entityToDto(obj.getFkSicPersona()));
		dto.setSicRol(adpp.entityToDto(obj.getFkSicRol()));
		dto.setSicEstadoUsuario(addp.entityToDto(obj.getFkSicEstadoUsuario()));
		
		return dto;
	}

}
