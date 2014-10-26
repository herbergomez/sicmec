package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicDepartamentoAdapter;
import com.uesocc.sicmec.model.adapter.SicMunicipioAdapter;
import com.uesocc.sicmec.model.dto.SicDepartamentoDto;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.entity.SicDepartamento;
import com.uesocc.sicmec.model.entity.SicMunicipio;
import com.uesocc.sicmec.model.repository.SicDepartamentoRepository;
import com.uesocc.sicmec.model.service.SicDepartamentoService;

@Service
public class SicDepartamentoServiceImpl implements SicDepartamentoService {

	@Autowired
	private SicDepartamentoRepository sicDepartamentoRepository;

	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicDepartamentoDto insert(SicDepartamentoDto entity)
									throws ParseException {
		// TODO Auto-generated method stub
		SicDepartamentoAdapter adp = new SicDepartamentoAdapter();
		SicDepartamento obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicDepartamentoRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicDepartamentoRepository.exists(id))
		{
		  sicDepartamentoRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicDepartamentoDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicDepartamentoAdapter adp = new SicDepartamentoAdapter();
		
		if(sicDepartamentoRepository.exists(id))
		{
			return adp.entityToDto(sicDepartamentoRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicDepartamentoDto> findAll() {
		// TODO Auto-generated method stub
		SicDepartamentoAdapter adp = new SicDepartamentoAdapter();
		List<SicDepartamento> list = sicDepartamentoRepository.findAll();
		List<SicDepartamentoDto> list_dto = new ArrayList<SicDepartamentoDto>();
		
		for (SicDepartamento sicDepartamento : list) 
		{
			list_dto.add(adp.entityToDto(sicDepartamento));
		}
		
		return list_dto;
	} 
	
	

}
