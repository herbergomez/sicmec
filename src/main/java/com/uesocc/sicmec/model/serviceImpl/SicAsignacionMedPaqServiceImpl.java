package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicAsignacionMedPaqAdapter;
import com.uesocc.sicmec.model.adapter.SicMunicipioAdapter;
import com.uesocc.sicmec.model.dto.SicAsignacionMedPaqDto;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;
import com.uesocc.sicmec.model.entity.SicMunicipio;
import com.uesocc.sicmec.model.repository.SicAsignacionMedPaqRepository;
import com.uesocc.sicmec.model.service.SicAsignacionMedPaqService;

@Service
public class SicAsignacionMedPaqServiceImpl implements SicAsignacionMedPaqService{

	@Autowired
	private SicAsignacionMedPaqRepository sicAsignacionMedPaq;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicAsignacionMedPaqDto insert(SicAsignacionMedPaqDto entity)
			throws ParseException {
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		SicAsignacionMedicamento obj = adp.dtoToEntity(entity);
		return adp.entityToDto(this.sicAsignacionMedPaq.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		if (this.sicAsignacionMedPaq.exists(id))
		{
			this.sicAsignacionMedPaq.delete(id);
			return true;
		}
		else
		{
			return false;
		}		
	}

	@Override
	public SicAsignacionMedPaqDto findById(Integer id) {
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		
		if (this.sicAsignacionMedPaq.exists(id))
		{
			return adp.entityToDto(this.sicAsignacionMedPaq.findOne(id));
		}
		else
		{
			return null;
		}		
	}

	@Override
	public List<SicAsignacionMedPaqDto> findAll() {
		// TODO Auto-generated method stub
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		List<SicAsignacionMedicamento> list = this.sicAsignacionMedPaq.findAll();
		List<SicAsignacionMedPaqDto> list_dto = new ArrayList<SicAsignacionMedPaqDto>();
		
		for (SicAsignacionMedicamento med : list) 
		{
			list_dto.add(adp.entityToDto(med));
		}
		
		return list_dto;
	}

}
