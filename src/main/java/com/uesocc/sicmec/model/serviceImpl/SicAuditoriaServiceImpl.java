package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicAuditoriaAdapter;
import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
import com.uesocc.sicmec.model.entity.SicAuditoria;
import com.uesocc.sicmec.model.repository.SicAuditoriaRepository;
import com.uesocc.sicmec.model.service.SicAuditoriaService;

@Service
public class SicAuditoriaServiceImpl implements SicAuditoriaService
{
	@Autowired
	private SicAuditoriaRepository sicAuditoriaRepository;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicAuditoriaDto insert(SicAuditoriaDto entity) throws ParseException 
	{
		// TODO Auto-generated method stub
		SicAuditoriaAdapter adp = new SicAuditoriaAdapter();
		SicAuditoria obj = adp.dtoToEntity(entity);
		return adp.entityToDto(sicAuditoriaRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SicAuditoriaDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SicAuditoriaDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SicAuditoriaDto> findAllByDateAndUser(int user, Date from, Date to) {
		// TODO Auto-generated method stub
		SicAuditoriaAdapter adp = new SicAuditoriaAdapter();
		List<SicAuditoria> list = sicAuditoriaRepository.findAllByDateAndUser(user, from, to);
		List<SicAuditoriaDto> list_dto = new ArrayList<SicAuditoriaDto>();
		for (SicAuditoria sicAuditoria : list) 
		{
			list_dto.add(adp.entityToDto(sicAuditoria));
		}
		
		return list_dto;
	}
	

}
