/**
 * 
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicCitaMedicaAdapter;
import com.uesocc.sicmec.model.dto.SicCitaMedicaDto;
import com.uesocc.sicmec.model.entity.SicCitaMedica;
import com.uesocc.sicmec.model.repository.SicCitaMedicaRepository;
import com.uesocc.sicmec.model.service.SicCitaMedicaService;

/**
 * @author xtiyo
 *
 */
@Service
public class SicCitaMedicaServiceImpl implements SicCitaMedicaService {

	@Autowired
	private SicCitaMedicaRepository sicCitaMedicaRepository;
	
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
	public SicCitaMedicaDto insert(SicCitaMedicaDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicCitaMedicaAdapter adp = new SicCitaMedicaAdapter();
		SicCitaMedica sic = adp.dtoToEntity(entity);
		return adp.entityToDto(sicCitaMedicaRepository.save(sic));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicCitaMedicaRepository.exists(id))
		{
			sicCitaMedicaRepository.delete(id);
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
	public SicCitaMedicaDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicCitaMedicaAdapter adp = new SicCitaMedicaAdapter();
		
		if(sicCitaMedicaRepository.exists(id))
		{
			return adp.entityToDto(sicCitaMedicaRepository.findOne(id));
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
	public List<SicCitaMedicaDto> findAll() {
		// TODO Auto-generated method stub
		SicCitaMedicaAdapter adp = new SicCitaMedicaAdapter();
		List<SicCitaMedica> list = sicCitaMedicaRepository.findAll();
		List<SicCitaMedicaDto> list_dto = new ArrayList<SicCitaMedicaDto>();
		
		for (SicCitaMedica sicCitaMedica : list) 
		{
			list_dto.add(adp.entityToDto(sicCitaMedica));
		}
		
		return list_dto;
	}

}
