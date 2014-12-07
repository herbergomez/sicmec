
package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.entity.SicTratamiento;

/**
 * @author xtiyo
 *
 */
public class SicTratamientoAdapter implements
		BaseAdapter<SicTratamiento, SicTratamientoDto> {

	@Override
	public SicTratamiento dtoToEntity(SicTratamientoDto obj)
			throws ParseException {
		// TODO Auto-generated method stub
		SicCitaMedicaAdapter adp = new SicCitaMedicaAdapter();
		SicPaqMedAdapter adpp = new SicPaqMedAdapter();
		
		SicTratamiento entity = new SicTratamiento();
		entity.setIdSicTratamiento((obj.getIdSicTratamiento()!=null) ?  SicDataTypeFormat.toIntValue(obj.getIdSicTratamiento()):null);
		entity.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		entity.setPeriodisidad((obj.getPeriodisidad()!=null) ? SicDataTypeFormat.toStringValue(obj.getPeriodisidad()):"");
		entity.setDosis((obj.getDosis()!=null) ? SicDataTypeFormat.toStringValue(obj.getDosis()):"");
		entity.setFxTratamiento((obj.getFxTratamiento()!=null) ? SicDataTypeFormat.toDateTimeValue(obj.getFxTratamiento()):new Date());
		entity.setFkSicCatMedicamentos((obj.getFkSicCatMedicamentos()!=null) ? adpp.dtoToEntity(obj.getFkSicCatMedicamentos()):null);
		entity.setFkSicCitaMedica((obj.getFkSicCitaMedica()!=null) ? adp.dtoToEntity(obj.getFkSicCitaMedica()):null);
		
		return entity;
	}

	@Override
	public SicTratamientoDto entityToDto(SicTratamiento obj) {
		// TODO Auto-generated method stub
		SicCitaMedicaAdapter adp = new SicCitaMedicaAdapter();
		SicPaqMedAdapter adpp = new SicPaqMedAdapter();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicTratamientoDto dto = new SicTratamientoDto();
		dto.setIdSicTratamiento((obj.getIdSicTratamiento()!=null) ?  SicDataTypeFormat.toStringValue(obj.getIdSicTratamiento().toString()):"");
		dto.setComentario((obj.getComentario()!=null) ? SicDataTypeFormat.toStringValue(obj.getComentario()):"");
		dto.setPeriodisidad((obj.getPeriodisidad()!=null) ? SicDataTypeFormat.toStringValue(obj.getPeriodisidad()):"");
		dto.setDosis((obj.getDosis()!=null) ? SicDataTypeFormat.toStringValue(obj.getDosis()):"");
		dto.setFxTratamiento((obj.getFxTratamiento()!=null) ? format.format(obj.getFxTratamiento()):format.format(new Date()));
		dto.setFkSicCatMedicamentos((obj.getFkSicCatMedicamentos()!=null) ? adpp.entityToDto(obj.getFkSicCatMedicamentos()):null);
		dto.setFkSicCitaMedica((obj.getFkSicCitaMedica()!=null) ? adp.entityToDto(obj.getFkSicCitaMedica()):null);
		
		return dto;
	}

}
