package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicMunicipioAdapter;
import com.uesocc.sicmec.model.adapter.SicPacienteAdapter;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.entity.SicMunicipio;
import com.uesocc.sicmec.model.entity.SicPaciente;
import com.uesocc.sicmec.model.repository.SicMunicipioRepository;
import com.uesocc.sicmec.model.service.SicMunicipioService;

@Service
public class SicMunicipioServiceImpl implements SicMunicipioService{

	@Autowired
	private SicMunicipioRepository sicMunicipioRepository; 
	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicMunicipioDto insert(SicMunicipioDto entity) throws ParseException {
		// TODO Auto-generated method stub
		SicMunicipioAdapter adp = new SicMunicipioAdapter();
		SicMunicipio obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicMunicipioRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicMunicipioRepository.exists(id))
		{
		  sicMunicipioRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicMunicipioDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicMunicipioAdapter adp = new SicMunicipioAdapter();
		
		if(sicMunicipioRepository.exists(id))
		{
			return adp.entityToDto(sicMunicipioRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicMunicipioDto> findAll() {
		// TODO Auto-generated method stub
		SicMunicipioAdapter adp = new SicMunicipioAdapter();
		List<SicMunicipio> list = sicMunicipioRepository.findAll();
		List<SicMunicipioDto> list_dto = new ArrayList<SicMunicipioDto>();
		
		for (SicMunicipio sicMunicipio : list) 
		{
			list_dto.add(adp.entityToDto(sicMunicipio));
		}
		
		return list_dto;
	}

	@Override
	public List<SicMunicipioDto> getMunicipiosPorDepartamento(int idDepartamento) {
		// TODO Auto-generated method stub
		SicMunicipioAdapter adp = new SicMunicipioAdapter();
		List<SicMunicipio> list = sicMunicipioRepository.findAllByfkSicDepartamento_idSicDepartamento(idDepartamento);
		List<SicMunicipioDto> list_dto = new ArrayList<SicMunicipioDto>();
		
		for (SicMunicipio sicMunicipio : list) 
		{
			list_dto.add(adp.entityToDto(sicMunicipio));
		}
		
		return list_dto;
	}
}
