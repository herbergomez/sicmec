package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicMedicamento;

/**
 * 
 * @author kevingcfcb88
 *
 */
@Repository
public interface SicDrugRepository extends JpaRepository<SicMedicamento, Integer>{

}
