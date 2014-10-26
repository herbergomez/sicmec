/**
 * 
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.dto.SicEstadoUsuarioDto;
import com.uesocc.sicmec.model.repository.SicEstadoUsuarioRepository;
import com.uesocc.sicmec.model.service.SicEstadoUsuarioService;

/**
 * @author xtiyo
 *
 */

@Service
public class SicEstadoUsuarioServiceImpl implements SicEstadoUsuarioService {

	@Autowired
	private SicEstadoUsuarioRepository sicEstadoUsuarioRepository;
	
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
	public SicEstadoUsuarioDto insert(SicEstadoUsuarioDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findById(java.io.Serializable)
	 */
	@Override
	public SicEstadoUsuarioDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findAll()
	 */
	@Override
	public List<SicEstadoUsuarioDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.model.service.SicEstadoUsuarioService#findOneByDescripcion(java.lang.String)
	 */
	@Override
	public SicEstadoUsuarioDto findOneByDescripcion(String Descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

}
