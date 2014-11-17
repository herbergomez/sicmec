/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicTipoExamenDto;
import com.uesocc.sicmec.model.entity.SicTipoExamen;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
public class SicTipoExamenAdapter implements
		BaseAdapter<SicTipoExamen, SicTipoExamenDto> {

	@Override
	public SicTipoExamen dtoToEntity(SicTipoExamenDto obj)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTipoExamen entity = new SicTipoExamen();
		entity.setIdSicTipoExamen((obj.getIdSicTipoExamen()!=null) ? SicDataTypeFormat.toIntValue(obj.getIdSicTipoExamen()):null);
		entity.setDescripcion((obj.getDescripcion()!=null) ? SicDataTypeFormat.toStringValue(obj.getDescripcion()):" ");
		
		return entity;
	}

	@Override
	public SicTipoExamenDto entityToDto(SicTipoExamen obj) {
		// TODO Auto-generated method stub
		SicTipoExamenDto dto = new SicTipoExamenDto();
		dto.setIdSicTipoExamen((obj.getIdSicTipoExamen()!=null) ? SicDataTypeFormat.toStringValue(obj.getIdSicTipoExamen().toString()):"");
		dto.setDescripcion((obj.getDescripcion()!=null) ? SicDataTypeFormat.toStringValue(obj.getDescripcion()):" ");
		
		return dto;
	}

}
