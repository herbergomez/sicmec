package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicEntregaTratamientoAdapter;
import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.entity.SicEntregaTratamiento;
import com.uesocc.sicmec.model.repository.JdbcRepository;
import com.uesocc.sicmec.model.repository.SicEntregaTratamientoRepository;
import com.uesocc.sicmec.model.service.SicEntregaTratamientoService;

@Service
public class SicEntregaTratamientoServiceImpl implements
		SicEntregaTratamientoService {

	@Autowired
	private SicEntregaTratamientoRepository sicEntregaTratamientoRepository;
	@Autowired
	private JdbcRepository jdbcRepository;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	@Override
	public SicEntregaTratamientoDto insert(SicEntregaTratamientoDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicEntregaTratamientoAdapter adp = new SicEntregaTratamientoAdapter();
		return adp.entityToDto(sicEntregaTratamientoRepository.save(adp.dtoToEntity(entity)));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicEntregaTratamientoRepository.exists(id))
		{
			sicEntregaTratamientoRepository.delete(id);
			return true;			
		}
		else
		{
			return false;
		}
	}

	@Override
	public SicEntregaTratamientoDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicEntregaTratamientoAdapter adp = new SicEntregaTratamientoAdapter();
		if(sicEntregaTratamientoRepository.exists(id))
		{
		
			return adp.entityToDto(sicEntregaTratamientoRepository.findOne(id));			
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicEntregaTratamientoDto> findAll() {
		// TODO Auto-generated method stub
		SicEntregaTratamientoAdapter adp = new SicEntregaTratamientoAdapter();
		List<SicEntregaTratamiento> list = sicEntregaTratamientoRepository.findAll();
		List<SicEntregaTratamientoDto> list_dto = new ArrayList<SicEntregaTratamientoDto>();
		for (SicEntregaTratamiento sicEntregaTratamiento : list) 
		{
			list_dto.add(adp.entityToDto(sicEntregaTratamiento));
		}
		return list_dto;
	}

	@Override
	public List<SicEntregaTratamientoDto> findAllTreatmentDeliveryByPaciente(
			String id) {
		// TODO Auto-generated method stub
		
		return jdbcRepository.findAllTreatmentDeliveryByPaciente(id);
	}

}
