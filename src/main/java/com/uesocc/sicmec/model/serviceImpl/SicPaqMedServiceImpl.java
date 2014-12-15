package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uesocc.sicmec.model.adapter.SicPaqMedAdapter;
import com.uesocc.sicmec.model.dto.SicPaqMedDto;
import com.uesocc.sicmec.model.entity.SicCatMedicamentos;
import com.uesocc.sicmec.model.repository.SicPaqMedRepository;
import com.uesocc.sicmec.model.service.SicPaqMedService;

@Service
public class SicPaqMedServiceImpl implements SicPaqMedService{
	
	@Autowired
	private SicPaqMedRepository sicPaqMedRepository;

	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicPaqMedDto insert(SicPaqMedDto entity) throws ParseException {
		SicPaqMedAdapter paqMedAdapter = new SicPaqMedAdapter();
		SicCatMedicamentos paqMed = paqMedAdapter.dtoToEntity(entity);
		return paqMedAdapter.entityToDto( sicPaqMedRepository.save(paqMed) );
	}

	@Override
	public Boolean delete(Integer id) {
		if ( sicPaqMedRepository.exists(id) ) 
		{
			sicPaqMedRepository.delete(id);
			return true;
		} 
		else 
		{
			return false;
		}
	}

	@Override
	public SicPaqMedDto findById(Integer id) {
		SicPaqMedAdapter paqMedAdapter = new SicPaqMedAdapter();
		if ( sicPaqMedRepository.exists(id) ) 
		{
			return paqMedAdapter.entityToDto( sicPaqMedRepository.findOne(id) );
		} 
		else 
		{
			return null;
		}		
	}

	@Override
	public List<SicPaqMedDto> findAll() {
		
		SicPaqMedAdapter paqMedAdapter = new SicPaqMedAdapter();
		List<SicCatMedicamentos> paqMed = sicPaqMedRepository.findAll();
		List<SicPaqMedDto> paqMedDto = new ArrayList<SicPaqMedDto>();
		
		for( SicCatMedicamentos onePaq : paqMed )
		{
			paqMedDto.add( paqMedAdapter.entityToDto(onePaq) );
		}
		
		return paqMedDto;
	}


}
