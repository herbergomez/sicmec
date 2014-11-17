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
import com.uesocc.sicmec.model.dto.SicExamenDto;
import com.uesocc.sicmec.model.entity.SicExamen;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public class SicExamenAdapter implements BaseAdapter<SicExamen, SicExamenDto> {

	@Override
	public SicExamen dtoToEntity(SicExamenDto obj) throws ParseException {
		// TODO Auto-generated method stub
		SicExamen entity = new SicExamen();
		SicTipoExamenAdapter adp = new SicTipoExamenAdapter();
		SicCitaMedicaAdapter adpp = new SicCitaMedicaAdapter();
		
		entity.setIdSicExamen((obj.getIdSicExamen()!=null) ? SicDataTypeFormat.toIntValue(obj.getIdSicExamen()):null);
		entity.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		entity.setResultado((obj.getResultado()!=null) ? SicDataTypeFormat.toStringValue(obj.getResultado()):"");
		entity.setCreadoPor((obj.getCreadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		entity.setModifcadoPor((obj.getModifcadoPor()!=null)  ? SicDataTypeFormat.toStringValue(obj.getModifcadoPor()):"");
		entity.setFxCreado((obj.getFxCreado()!=null) ?  SicDataTypeFormat.toDateTimeValue(obj.getFxCreado()):new Date());
		entity.setFxModificado((obj.getFxModificado()!=null) ?  SicDataTypeFormat.toDateTimeValue(obj.getFxModificado()):new Date());
		entity.setFkSicTipoExamen(adp.dtoToEntity(obj.getFkSicTipoExamen()));
		entity.setFkSicCitaMedica(adpp.dtoToEntity(obj.getFkSicCitaMedica()));
		
		return entity;
	}

	@Override
	public SicExamenDto entityToDto(SicExamen obj) {
		// TODO Auto-generated method stub
		SicExamenDto entity = new SicExamenDto();
		SicTipoExamenAdapter adp = new SicTipoExamenAdapter();
		SicCitaMedicaAdapter adpp = new SicCitaMedicaAdapter();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		entity.setIdSicExamen((obj.getIdSicExamen()!=null) ? SicDataTypeFormat.toStringValue(obj.getIdSicExamen().toString()):"");
		entity.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		entity.setResultado((obj.getResultado()!=null) ? SicDataTypeFormat.toStringValue(obj.getResultado()):"");
		entity.setCreadoPor((obj.getCreadoPor()!=null) ? SicDataTypeFormat.toStringValue(obj.getCreadoPor()):"");
		entity.setModifcadoPor((obj.getModifcadoPor()!=null)  ? SicDataTypeFormat.toStringValue(obj.getModifcadoPor()):"");
		entity.setFxCreado((obj.getFxCreado()!=null) ?  format.format(obj.getFxCreado()):format.format(new Date()));
		entity.setFxModificado((obj.getFxModificado()!=null) ?  format.format(obj.getFxModificado()):format.format(new Date()));
		entity.setFkSicTipoExamen(adp.entityToDto(obj.getFkSicTipoExamen()));
		entity.setFkSicCitaMedica(adpp.entityToDto(obj.getFkSicCitaMedica()));
		
		return entity;
	}

}
