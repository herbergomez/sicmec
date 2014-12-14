/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicTipoExamen;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */

@Repository
public interface SicTipoExamenRepository extends JpaRepository<SicTipoExamen, Integer> 
{

	
	
}
