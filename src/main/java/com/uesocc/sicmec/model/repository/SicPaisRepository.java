package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicPais;
/**
 * @author Herber
 * @date 04/11/2014
 */
@Repository
public interface SicPaisRepository extends JpaRepository<SicPais, Integer> {

}