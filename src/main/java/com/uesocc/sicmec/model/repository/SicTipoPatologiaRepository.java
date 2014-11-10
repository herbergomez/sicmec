package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicTipoPatologia;
/**
 * @author Herber
 * @date 08/11/2014
 */
@Repository
public interface SicTipoPatologiaRepository extends JpaRepository<SicTipoPatologia, Integer> {

	List <SicTipoPatologia> findAllBynombreTipoPatologia(String nombre);
}
