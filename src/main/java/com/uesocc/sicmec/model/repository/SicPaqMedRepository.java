package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicCatMedicamentos;

@Repository
public interface SicPaqMedRepository extends JpaRepository<SicCatMedicamentos, Integer>{

}
