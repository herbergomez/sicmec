/**
 * 
 */
package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicUsuario;

/**
 * @author pablo portillo
 * @date 5/10/2014
 */
@Repository
public interface SicUsuarioRepository extends
		JpaRepository<SicUsuario, Integer> {

	/**
	 * @param nombreUsuario
	 * @return Lista de usuarios que responden a este nombre de usuario
	 */
	
	List<SicUsuario> findAllBynombreUsuario(String nombreUsuario);
	
	List<SicUsuario> findAllByfkSicEstadoUsuario_descripcion(String descripcion);
	
}
