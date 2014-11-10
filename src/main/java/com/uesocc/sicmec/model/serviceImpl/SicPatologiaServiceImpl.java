package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicPatologiaAdapter;
import com.uesocc.sicmec.model.dto.SicPatologiaDto;
import com.uesocc.sicmec.model.entity.SicPatologia;
import com.uesocc.sicmec.model.repository.SicPatologiaRepository;
import com.uesocc.sicmec.model.service.SicPatologiaService;
@Service
public class SicPatologiaServiceImpl implements SicPatologiaService {

	@Autowired
	private SicPatologiaRepository sicPatologiaRepository;
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	@Override
	public SicPatologiaDto insert(SicPatologiaDto entity) throws ParseException {
		// TODO Auto-generated method stub
		SicPatologiaAdapter adp = new SicPatologiaAdapter();
		SicPatologia obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicPatologiaRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicPatologiaRepository.exists(id))
		{
			sicPatologiaRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicPatologiaDto findById(Integer id) {
		// TODO Auto-generated method stub
		 SicPatologiaAdapter adp = new SicPatologiaAdapter();
			
			if(sicPatologiaRepository.exists(id))
			{
				return adp.entityToDto(sicPatologiaRepository.findOne(id));
			}
			else
			{
				return null;
			}
	}

	@Override
	public List<SicPatologiaDto> findAll() {
		// TODO Auto-generated method stub
		SicPatologiaAdapter adp = new SicPatologiaAdapter();
		List<SicPatologia> list = sicPatologiaRepository.findAll();
		List<SicPatologiaDto> list_dto = new ArrayList<SicPatologiaDto>();
		
		for (SicPatologia sicPatologia : list) 
		{
			list_dto.add(adp.entityToDto(sicPatologia));
		}
		
		return list_dto;
	}

	@Override
	public boolean validacionPatologia(String nombre) {
		// TODO Auto-generated method stub
		if (sicPatologiaRepository.findAllBynombrePatologia(nombre.trim()).isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Metodo para validar que no se repita el nombre de una patologia al editarlo
	 */
	public boolean validacionPatologiaUp(String nombre) {
		// TODO Auto-generated method stub
		if (sicPatologiaRepository.findAllBynombrePatologia(nombre.trim()).isEmpty()
				|| sicPatologiaRepository.findAllBynombrePatologia(nombre.trim()).size()==1){
			return true;
		} else {
			return false;
		}
	}

}
