/**
 * 
 */
package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicEstadoUsuario;

/**
 * @author xtiyo
 *
 */
@Repository
public interface SicEstadoUsuarioRepository extends
		JpaRepository<SicEstadoUsuario, Integer> 
{
	SicEstadoUsuario findOneBydescripcion(String descripcion);	
}
