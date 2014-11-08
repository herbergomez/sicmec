package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicDepartamento;

/**
 * @author Herber
 * @date 17/10/2014
 */
@Repository
public interface SicDepartamentoRepository extends JpaRepository<SicDepartamento, Integer> {

	/**
	* @param Id de Pais
	* @return Lista de departamentos que responden a este pais
	*/
	List <SicDepartamento> findAllByfkSicPais_idSicPais(int id);
}
