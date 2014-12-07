/**
 * 
 */
package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicCitaMedica;

/**
 * @author xtiyo
 *
 */
@Repository
public interface SicCitaMedicaRepository extends
		JpaRepository<SicCitaMedica, Integer> {

}
