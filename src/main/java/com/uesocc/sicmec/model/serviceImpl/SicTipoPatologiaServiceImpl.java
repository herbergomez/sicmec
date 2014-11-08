package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicContactoPacienteAdapter;
import com.uesocc.sicmec.model.adapter.SicTipoPatologiaAdapter;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.dto.SicTipoPatologiaDto;
import com.uesocc.sicmec.model.entity.SicContactoPaciente;
import com.uesocc.sicmec.model.entity.SicTipoPatologia;
import com.uesocc.sicmec.model.repository.SicTipoPatologiaRepository;
import com.uesocc.sicmec.model.service.SicTipoPatologiaService;
@Service
public class SicTipoPatologiaServiceImpl implements SicTipoPatologiaService {

	@Autowired
	private SicTipoPatologiaRepository sicTipoPatologiaRepository;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	@Override
	public SicTipoPatologiaDto insert(SicTipoPatologiaDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTipoPatologiaAdapter adp = new SicTipoPatologiaAdapter();
		SicTipoPatologia obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicTipoPatologiaRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicTipoPatologiaRepository.exists(id))
		{
			sicTipoPatologiaRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicTipoPatologiaDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicTipoPatologiaAdapter adp = new SicTipoPatologiaAdapter();
		
		if(sicTipoPatologiaRepository.exists(id))
		{
			return adp.entityToDto(sicTipoPatologiaRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicTipoPatologiaDto> findAll() {
		// TODO Auto-generated method stub
		SicTipoPatologiaAdapter  adp = new 	SicTipoPatologiaAdapter();
		List<SicTipoPatologia> list = sicTipoPatologiaRepository.findAll();
		List<SicTipoPatologiaDto> list_dto = new ArrayList<SicTipoPatologiaDto>();
		
		for (SicTipoPatologia sicPatologia : list) 
		{
			list_dto.add(adp.entityToDto(sicPatologia));
		}
		
		return list_dto;
	}

}
