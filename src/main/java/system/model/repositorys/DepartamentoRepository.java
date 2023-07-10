/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.model.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.entity.Departamento;

/**
 *
 * @author eric
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
