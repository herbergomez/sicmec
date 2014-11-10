package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicPatologia;
/**
 * @author Herber
 * @date 09/11/2014
 */
@Repository
public interface SicPatologiaRepository extends JpaRepository<SicPatologia, Integer> {

	/**
	* @param nombre
	* @return Lista de patologias que se corresponden con este nombre
	*/
	List <SicPatologia> findAllBynombrePatologia(String nombre);
}
