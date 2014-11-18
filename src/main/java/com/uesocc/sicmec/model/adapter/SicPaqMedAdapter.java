package com.uesocc.sicmec.model.adapter;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicPaqMedDto;
import com.uesocc.sicmec.model.entity.SicCatMedicamentos;

import java.text.ParseException;

public class SicPaqMedAdapter implements BaseAdapter<SicCatMedicamentos, SicPaqMedDto>{
	
	@Override
	public  SicCatMedicamentos dtoToEntity ( SicPaqMedDto obj ) throws ParseException 
	{
		SicCatMedicamentos paqEntity = new SicCatMedicamentos();
		paqEntity.setIdSicCatMedicamentos(  obj.getIdPaq() != null ? SicDataTypeFormat.toIntValue( obj.getIdPaq() ) : null);
		paqEntity.setNombre( obj.getName() != null ? SicDataTypeFormat.toStringValue(  obj.getName() ) : null );
		paqEntity.setDescripcion( obj.getDescription() != null ? SicDataTypeFormat.toStringValue( obj.getDescription() ) : null );
		paqEntity.setEstado( obj.getActive() != null ? SicDataTypeFormat.toStringValue( obj.getActive() ) : null );
		return paqEntity;
	}
	
	@Override
	public SicPaqMedDto entityToDto ( SicCatMedicamentos ent ) 
	{
		SicPaqMedDto paqDto = new SicPaqMedDto();
		paqDto.setIdPaq( ent.getIdSicCatMedicamentos() != null ? SicDataTypeFormat.toStringValue( ent.getIdSicCatMedicamentos().toString() ) : "" );
		paqDto.setName( ent.getNombre() != null ? SicDataTypeFormat.toStringValue( ent.getNombre() ) : "" );
		paqDto.setDescription( ent.getDescripcion() != null ? SicDataTypeFormat.toStringValue( ent.getDescripcion() ) : "" );
		paqDto.setActive( ent.getEstado() != null ? SicDataTypeFormat.toStringValue( ent.getEstado() ) : "" );
		return paqDto;		
	}

}
