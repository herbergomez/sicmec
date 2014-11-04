package com.uesocc.sicmec.model.adapter;

import java.text.ParseException;

import com.uesocc.sicmec.framework.general.BaseAdapter;
import com.uesocc.sicmec.framework.general.SicDataTypeFormat;
import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.entity.SicMedicamento;

public class SicDrugAdapter implements BaseAdapter<SicMedicamento, SicDrugDto>{

	@Override
	public SicMedicamento dtoToEntity(SicDrugDto obj) throws ParseException {
		SicMedicamento drugEntity = new SicMedicamento();
		drugEntity.setIdSicMedicamento( obj.getIdDrug() != null ? SicDataTypeFormat.toIntValue( obj.getIdDrug() ) : null);
		drugEntity.setNombreMedicamento( obj.getDrugName() != null ? SicDataTypeFormat.toStringValue( obj.getDrugName() ) : null);
		drugEntity.setEstadoMedicamento( obj.getEstado() != null ? SicDataTypeFormat.toStringValue( obj.getEstado() ) : null );
		drugEntity.setDescripcionMedicamento( obj.getDrugDescription() != null ? SicDataTypeFormat.toStringValue( obj.getDrugDescription() ) : null );
		return drugEntity;
	}

	@Override
	public SicDrugDto entityToDto(SicMedicamento obj) {
		SicDrugDto drugDto = new SicDrugDto();
		drugDto.setIdDrug( obj.getIdSicMedicamento() != null ? SicDataTypeFormat.toStringValue( obj.getIdSicMedicamento().toString() ) : "" );
		drugDto.setDrugName( obj.getNombreMedicamento() != null ? SicDataTypeFormat.toStringValue( obj.getNombreMedicamento() ) : "" );
		drugDto.setEstado( obj.getEstadoMedicamento() != null ? SicDataTypeFormat.toStringValue( obj.getEstadoMedicamento().toString() ) : "" );
		drugDto.setDrugDescription( obj.getDescripcionMedicamento() != null ? SicDataTypeFormat.toStringValue( obj.getDescripcionMedicamento() ) : "" );
		return drugDto;
	}
	

}
