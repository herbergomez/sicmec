package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uesocc.sicmec.model.adapter.SicPacienteAdapter;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.entity.SicPaciente;
import com.uesocc.sicmec.model.repository.SicPacienteRepository;
import com.uesocc.sicmec.model.service.SicPacienteService;

@Service
public class SicPacienteServiceImpl implements SicPacienteService  {
	
	@Autowired
	private SicPacienteRepository sicPacienteRepository;

	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicPacienteDto insert(SicPacienteDto entity) throws ParseException {
		// TODO Auto-generated method stub
		SicPacienteAdapter adp = new SicPacienteAdapter();
		SicPaciente obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicPacienteRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicPacienteRepository.exists(id))
		{
		  sicPacienteRepository.delete(id);
		   return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicPacienteDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicPacienteAdapter adp = new SicPacienteAdapter();
		
		if(sicPacienteRepository.exists(id))
		{
			return adp.entityToDto(sicPacienteRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicPacienteDto> findAll() {
		// TODO Auto-generated method stub
		SicPacienteAdapter adp = new SicPacienteAdapter();
		List<SicPaciente> list = sicPacienteRepository.findAll();
		List<SicPacienteDto> list_dto = new ArrayList<SicPacienteDto>();
		
		for (SicPaciente sicPaciente : list) 
		{
			String sexo=sicPaciente.getSexoPaciente();
			if (sexo.equals("M")){
				sicPaciente.setSexoPaciente("Masculino");
			} else {
				sicPaciente.setSexoPaciente("Femenino");
			}
			list_dto.add(adp.entityToDto(sicPaciente));
		}
		
		return list_dto;
	}

}
