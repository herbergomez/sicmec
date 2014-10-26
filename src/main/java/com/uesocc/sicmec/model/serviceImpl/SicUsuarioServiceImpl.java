/**
 * 
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicUsuarioAdapter;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.entity.SicUsuario;
import com.uesocc.sicmec.model.repository.SicUsuarioRepository;
import com.uesocc.sicmec.model.service.SicUsuarioService;

/**
 * @author pablo portillo
 * @date 5/10/2014
 */

@Service
public class SicUsuarioServiceImpl implements SicUsuarioService {

	@Autowired
	private SicUsuarioRepository sicUsuarioRepository;
	
	/* (non-Javadoc)
	 * @see com.uesocc.framework.general.BaseService#setupService()
	 */
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.uesocc.framework.general.BaseService#insert(java.lang.Object)
	 */
	@Override
	public SicUsuarioDto insert(SicUsuarioDto entity) throws ParseException 
	{
		// TODO Auto-generated method stub
		SicUsuarioAdapter adp = new SicUsuarioAdapter();
		SicUsuario obj = adp.dtoToEntity(entity);
		
		return adp.entityToDto(this.sicUsuarioRepository.save(obj));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) 
	{
		// TODO Auto-generated method stub
		if(sicUsuarioRepository.exists(id))
		{
			sicUsuarioRepository.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.uesocc.framework.general.BaseService#findById(java.io.Serializable)
	 */
	@Override
	public SicUsuarioDto findById(Integer id) 
	{
		// TODO Auto-generated method stub
		SicUsuarioAdapter adp = new SicUsuarioAdapter();
		
		if(sicUsuarioRepository.exists(id))
		{
			return adp.entityToDto(sicUsuarioRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.uesocc.framework.general.BaseService#findAll()
	 */
	@Override
	public List<SicUsuarioDto> findAll() 
	{
		// TODO Auto-generated method stub
		SicUsuarioAdapter adp = new SicUsuarioAdapter();
		List<SicUsuario> list = sicUsuarioRepository.findAll();
		List<SicUsuarioDto> list_dto = new ArrayList<SicUsuarioDto>();
		
		for (SicUsuario sicUsuario : list) 
		{
			list_dto.add(adp.entityToDto(sicUsuario));
		}
		
		return list_dto;
	}

	@Override
	public boolean validacionDenombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		
		if(sicUsuarioRepository.findAllBynombreUsuario(nombreUsuario).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
