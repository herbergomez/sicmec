/**
 * 
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicTratamientoAdapter;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.entity.SicTratamiento;
import com.uesocc.sicmec.model.repository.SicTratamientoRepository;
import com.uesocc.sicmec.model.service.SicTratamientoService;

/**
 * @author xtiyo
 *
 */
@Service
public class SicTratamientoServiceImpl implements SicTratamientoService {

	@Autowired
	private SicTratamientoRepository sicTratamientoRepository;
	
	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#setupService()
	 */
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#insert(java.lang.Object)
	 */
	@Override
	public SicTratamientoDto insert(SicTratamientoDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		SicTratamiento obj = adp.dtoToEntity(entity);
		return adp.entityToDto(sicTratamientoRepository.save(obj));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicTratamientoRepository.exists(id))
		{
			sicTratamientoRepository.delete(id);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findById(java.io.Serializable)
	 */
	@Override
	public SicTratamientoDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		if(sicTratamientoRepository.exists(id))
		{
			return adp.entityToDto(sicTratamientoRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findAll()
	 */
	@Override
	public List<SicTratamientoDto> findAll() {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		List<SicTratamiento> list = sicTratamientoRepository.findAll();
		List<SicTratamientoDto> list_dto = new ArrayList<SicTratamientoDto>();
		
		for (SicTratamiento sicTratamiento : list) 
		{
			list_dto.add(adp.entityToDto(sicTratamiento));
		}
		
		return list_dto;
	}

	@Override
	public List<SicTratamientoDto> findAllBySicPaciente(int pac) {
		// TODO Auto-generated method stub
		
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		List<SicTratamiento> list = sicTratamientoRepository.findAllBySicPaciente(pac);
		List<SicTratamientoDto> list_dto = new ArrayList<SicTratamientoDto>();
		
		for (SicTratamiento sicTratamiento : list) 
		{
			list_dto.add(adp.entityToDto(sicTratamiento));
		}
		
		return list_dto;
	}

}
