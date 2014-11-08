package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicDepartamentoAdapter;
import com.uesocc.sicmec.model.adapter.SicPaisAdapter;
import com.uesocc.sicmec.model.dto.SicDepartamentoDto;
import com.uesocc.sicmec.model.dto.SicPaisDto;
import com.uesocc.sicmec.model.entity.SicDepartamento;
import com.uesocc.sicmec.model.entity.SicPais;
import com.uesocc.sicmec.model.repository.SicDepartamentoRepository;
import com.uesocc.sicmec.model.repository.SicPaisRepository;
import com.uesocc.sicmec.model.service.SicPaisService;
@Service
public class SicPaisServiceImpl implements SicPaisService {

	@Autowired
	private SicPaisRepository sicPaisRepository;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	@Override
	public SicPaisDto insert(SicPaisDto entity) throws ParseException {
		// TODO Auto-generated method stub
		SicPaisAdapter adp = new SicPaisAdapter();
		SicPais obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicPaisRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicPaisRepository.exists(id))
		{
		  sicPaisRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicPaisDto findById(Integer id) {
        SicPaisAdapter adp = new SicPaisAdapter();
		
		if(sicPaisRepository.exists(id))
		{
			return adp.entityToDto(sicPaisRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicPaisDto> findAll() {
		// TODO Auto-generated method stub
		SicPaisAdapter adp = new SicPaisAdapter();
		List<SicPais> list = sicPaisRepository.findAll();
		List<SicPaisDto> list_dto = new ArrayList<SicPaisDto>();
		
		for (SicPais sicPais : list) 
		{
			list_dto.add(adp.entityToDto(sicPais));
		}
		
		return list_dto;
	}

}
