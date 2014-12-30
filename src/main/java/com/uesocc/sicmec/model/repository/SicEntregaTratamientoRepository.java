package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicEntregaTratamiento;

@Repository
public interface SicEntregaTratamientoRepository extends
		JpaRepository<SicEntregaTratamiento, Integer> {


	
}
