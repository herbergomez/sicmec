package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicMunicipio;
/**
 * @author Herber
 * @date 16/10/2014
 */
@Repository
public interface SicMunicipioRepository extends 
							JpaRepository<SicMunicipio, Integer> {


/**
* @param Id de Departamento
* @return Lista de municipios que responden a este departamento
*/
List <SicMunicipio> findAllByfkSicDepartamento_idSicDepartamento(int id);
}
