package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicContactoPaciente;
/**
 * @author Herber
 * @date 04/11/2014
 */
@Repository
public interface SicContactoPacienteRepository extends JpaRepository<SicContactoPaciente, Integer> {

}
