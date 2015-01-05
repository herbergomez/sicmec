package com.uesocc.sicmec.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;
import com.uesocc.sicmec.model.entity.SicMedicamento;

@Repository
public interface SicAsignacionMedPaqRepository extends JpaRepository<SicAsignacionMedicamento, Integer>{

	/**
	 * Encuentra todas las conicidencias 
	 * @param i ID del paquete de medicamento
	 * @return 
	 */
	List<SicAsignacionMedicamento> findAllByfkSicCatMedicamentos_idSicCatMedicamentos(int i);
	
	/**
	 * Encuentra todos los medicamentos pertenienctes al paquete y al estado deseado
	 * @param i ID del paquete de medicamento
	 * @param ii Estado del medicamento en el paquete
	 * @return
	 */
	List<SicAsignacionMedicamento> findAllByfkSicCatMedicamentos_idSicCatMedicamentosAndEstado(int i, String ii);


	/**
	 * Encuentra todos los medicamentos que pertenecen al paquete y estan activos dentro del paquete
	 * @param id Id del paquete de medicamentos
	 * @return Lista de medicamentos del paquete
	 */

<<<<<<< HEAD
//	List<SicMedicamento> findAllDrugsOfPaq(@Param("id") int id);
=======
	List<SicMedicamento> findAllDrugsOfPaq (int id);
>>>>>>> Finalized the medicamentos module
	
	/**
	 * Encuentra todos los medicamentos que NO pertenecen al paquete y estan activos
	 * @param id Id del paquete de medicamentos
	 * @return Lista de medicamentos del paquete
	 */
			
<<<<<<< HEAD
//	List<SicMedicamento> findAllDrugsOfNotInPaq(@Param("id") int id);
=======
	List<SicMedicamento> findAllDrugsOfNotInPaq (int id);
>>>>>>> Finalized the medicamentos module
}
