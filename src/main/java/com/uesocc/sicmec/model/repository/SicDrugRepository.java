package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicMedicamento;
import com.uesocc.sicmec.model.entity.SicUsuario;

/**
 * 
 * @author kevingcfcb88
 *
 */
@Repository
public interface SicDrugRepository extends JpaRepository<SicMedicamento, Integer>{

	/**
	 * Search a match for the drug name
	 * @param nombreMedicamento Drug name to search
	 * @return
	 */
	List<SicMedicamento> findAllBynombreMedicamento(String nombreMedicamento);
}