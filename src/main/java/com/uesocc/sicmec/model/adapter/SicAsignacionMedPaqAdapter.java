package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicAsignacionMedPaqDto;
import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;

public class SicAsignacionMedPaqAdapter implements BaseAdapter<SicAsignacionMedicamento, SicAsignacionMedPaqDto>{
	
	@Override
	public SicAsignacionMedicamento dtoToEntity (SicAsignacionMedPaqDto obj) throws ParseException
	{
		SicDrugAdapter drugAdp = new SicDrugAdapter();
		SicPaqMedAdapter paqAdp = new SicPaqMedAdapter();
		SicAsignacionMedicamento medEntity = new SicAsignacionMedicamento();
		
		medEntity.setIdSicAsignacionMedicamento( obj.getIdAsignacionMedPaq() != null ? SicDataTypeFormat.toIntValue( obj.getIdAsignacionMedPaq() ) : null );
		medEntity.setFkSicMedicamento( drugAdp.dtoToEntity(obj.getIdMedicamento()) );
		medEntity.setFkSicCatMedicamentos(paqAdp.dtoToEntity(obj.getIdMedPaq()));
		
		return medEntity;
		
	}
	
	@Override
	public SicAsignacionMedPaqDto entityToDto ( SicAsignacionMedicamento obj )
	{
		SicDrugAdapter drugAdp = new SicDrugAdapter();
		SicPaqMedAdapter paqAdp = new SicPaqMedAdapter();
		SicAsignacionMedPaqDto medDto = new SicAsignacionMedPaqDto();
		
		medDto.setIdAsignacionMedPaq( obj.getIdSicAsignacionMedicamento() != null ? SicDataTypeFormat.toStringValue( obj.getIdSicAsignacionMedicamento().toString() ) : null);
		medDto.setIdMedicamento(drugAdp.entityToDto(obj.getFkSicMedicamento()));
		medDto.setIdMedPaq(paqAdp.entityToDto(obj.getFkSicCatMedicamentos()));
		
		return medDto;
		
	}

}
