/**
 * 
 */
package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicEstadoUsuarioDto;
import com.uesocc.sicmec.model.entity.SicEstadoUsuario;

/**
 * @author xtiyo
 *
 */
public class SicEstadoUsuarioAdapter implements
		BaseAdapter<SicEstadoUsuario, SicEstadoUsuarioDto> {

	@Override
	public SicEstadoUsuario dtoToEntity(SicEstadoUsuarioDto obj)
			throws ParseException {
		// TODO Auto-generated method stub
		SicEstadoUsuario entidad = new SicEstadoUsuario();
		entidad.setIdSicEstadoUsuario((obj.getIdSicEstadoUsuario()!=null)? SicDataTypeFormat.toIntValue(obj.getIdSicEstadoUsuario()):null);
		entidad.setDescripcion((obj.getDescripcion()!=null) ? SicDataTypeFormat.toStringValue(obj.getDescripcion()):"");
		
		return entidad;
	}

	@Override
	public SicEstadoUsuarioDto entityToDto(SicEstadoUsuario obj) {
		// TODO Auto-generated method stub
		
		SicEstadoUsuarioDto dto = new SicEstadoUsuarioDto();
		dto.setIdSicEstadoUsuario((obj.getIdSicEstadoUsuario()!=null)? SicDataTypeFormat.toStringValue(obj.getIdSicEstadoUsuario().toString()):"");
		dto.setDescripcion((obj.getDescripcion()!=null) ? SicDataTypeFormat.toStringValue(obj.getDescripcion()):"");
		
		return dto;
	}

}
