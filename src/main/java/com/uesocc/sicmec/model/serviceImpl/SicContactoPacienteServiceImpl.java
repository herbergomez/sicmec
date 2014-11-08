package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicContactoPacienteAdapter;
import com.uesocc.sicmec.model.adapter.SicPaisAdapter;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.dto.SicPaisDto;
import com.uesocc.sicmec.model.entity.SicContactoPaciente;
import com.uesocc.sicmec.model.entity.SicPais;
import com.uesocc.sicmec.model.repository.SicContactoPacienteRepository;
import com.uesocc.sicmec.model.repository.SicPaisRepository;
import com.uesocc.sicmec.model.service.SicContactoPacienteService;
@Service
public class SicContactoPacienteServiceImpl implements
		SicContactoPacienteService {

	@Autowired
	private SicContactoPacienteRepository sicContactoPacienteRepository;
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	@Override
	public SicContactoPacienteDto insert(SicContactoPacienteDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicContactoPacienteAdapter adp = new SicContactoPacienteAdapter();
		SicContactoPaciente obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicContactoPacienteRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicContactoPacienteRepository.exists(id))
		{
			sicContactoPacienteRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicContactoPacienteDto findById(Integer id) {
		// TODO Auto-generated method stub
        SicContactoPacienteAdapter adp = new SicContactoPacienteAdapter();
		
		if(sicContactoPacienteRepository.exists(id))
		{
			return adp.entityToDto(sicContactoPacienteRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicContactoPacienteDto> findAll() {
		// TODO Auto-generated method stub
		SicContactoPacienteAdapter  adp = new SicContactoPacienteAdapter();
		List<SicContactoPaciente> list = sicContactoPacienteRepository.findAll();
		List<SicContactoPacienteDto> list_dto = new ArrayList<SicContactoPacienteDto>();
		
		for (SicContactoPaciente sicContacto : list) 
		{
			list_dto.add(adp.entityToDto(sicContacto));
		}
		
		return list_dto;
	}

}
