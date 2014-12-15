package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;

@Repository
public interface SicAsignacionMedPaqRepository extends JpaRepository<SicAsignacionMedicamento, Integer>{

}
