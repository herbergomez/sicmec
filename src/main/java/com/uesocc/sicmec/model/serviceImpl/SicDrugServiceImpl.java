package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uesocc.sicmec.model.adapter.SicDrugAdapter;
import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.entity.SicMedicamento;
import com.uesocc.sicmec.model.repository.SicDrugRepository;
import com.uesocc.sicmec.model.service.SicDrugService;

@Service
public class SicDrugServiceImpl implements SicDrugService{
	
	@Autowired
	private SicDrugRepository sicDrugRepository;

	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicDrugDto insert(SicDrugDto entity) throws ParseException {
		SicDrugAdapter drugAdapter = new SicDrugAdapter();
		SicMedicamento sicDrug = drugAdapter.dtoToEntity(entity);		
		return drugAdapter.entityToDto(sicDrugRepository.save(sicDrug));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if ( sicDrugRepository.exists(id) ) {
			sicDrugRepository.delete(id);
			return true;
		} else {
			return false;
		}		
	}

	@Override
	public SicDrugDto findById(Integer id) {
		SicDrugAdapter drugAdapter = new SicDrugAdapter();
		if ( sicDrugRepository.exists(id) ) {
			return drugAdapter.entityToDto( sicDrugRepository.findOne(id) );
		} else {
			return null;
		}
	}

	@Override
	public List<SicDrugDto> findAll() {
		
		SicDrugAdapter drugAdapter = new SicDrugAdapter();
		List<SicMedicamento> drugList = sicDrugRepository.findAll();
		List<SicDrugDto> drugListDto = new ArrayList<SicDrugDto>();		
		
		//Going throught the result
		for ( SicMedicamento oneDrug : drugList ) {			
			drugListDto.add(drugAdapter.entityToDto(oneDrug));
		}
		// TODO Auto-generated method stub
		return drugListDto;
	}
	
	/**
	 * Check if the name of the drug already exist on the DB
	 * @param sName The name to check if exist
	 * @return true if NOT exist, false is ALREADY exist
	 */
	public boolean nameExist( String sName ) {
		//Check if exist the drug name
		if( sicDrugRepository.findAllBynombreMedicamento(sName).isEmpty() ){
			return true;
		}
		else {
			return false;
		}
	}
	

}
