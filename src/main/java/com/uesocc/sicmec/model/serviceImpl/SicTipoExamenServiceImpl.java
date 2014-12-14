/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uesocc.sicmec.model.adapter.SicTipoExamenAdapter;
import com.uesocc.sicmec.model.dto.SicTipoExamenDto;
import com.uesocc.sicmec.model.entity.SicTipoExamen;
import com.uesocc.sicmec.model.repository.SicTipoExamenRepository;
import com.uesocc.sicmec.model.service.SicTipoExamenService;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
@Service
public class SicTipoExamenServiceImpl implements SicTipoExamenService {

	@Autowired
	private SicTipoExamenRepository SicTipoExamenRepository;
	
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
	public SicTipoExamenDto insert(SicTipoExamenDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTipoExamenAdapter adp = new SicTipoExamenAdapter();
		SicTipoExamen tipoExm = adp.dtoToEntity(entity);
		
		return adp.entityToDto(SicTipoExamenRepository.save(tipoExm));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(SicTipoExamenRepository.exists(id))
		{
			SicTipoExamenRepository.delete(id);
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
	public SicTipoExamenDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicTipoExamenAdapter adp = new SicTipoExamenAdapter();
		
		if(SicTipoExamenRepository.exists(id))
		{
			
			return adp.entityToDto(SicTipoExamenRepository.findOne(id));
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
	public List<SicTipoExamenDto> findAll() {
		// TODO Auto-generated method stub
		List<SicTipoExamen> list = SicTipoExamenRepository.findAll();
		SicTipoExamenAdapter adp = new SicTipoExamenAdapter();
		List<SicTipoExamenDto> list_dto = new ArrayList<SicTipoExamenDto>();
		
		for (SicTipoExamen sicTipoExamen : list) 
		{
			list_dto.add(adp.entityToDto(sicTipoExamen));
		}
		return list_dto;
	}

}
