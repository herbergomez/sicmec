package com.uesocc.sicmec.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicAuditoria;

@Repository
public interface SicAuditoriaRepository extends JpaRepository<SicAuditoria, Integer>
{

	@Query(value="SELECT s FROM SicAuditoria s WHERE s.fkSicUsuario.idSicUsuario = (:user) AND s.fxAuditoria BETWEEN (:from) AND (:to) ORDER BY s.fxAuditoria DESC")
	List<SicAuditoria> findAllByDateAndUser(@Param("user") int user,@Param("from") Date from,@Param("to") Date to);
	
}
