package org.prueba.jpa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import erp.entity.PersonaEmpresaUsuario;
import erp.entity.PersonaEmpresaUsuario;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import javax.persistence.Query;
/**
 *
 * @author Angel
 */
@Repository
@Transactional
public class UserDao {
    
      @SuppressWarnings("unchecked")
  public List<PersonaEmpresaUsuario> getAll() {
    //return entityManager.createQuery("SELECT p FROM PersonaEmpresaUsuario p WHERE p.ciRuc = '0923746663'").getResultList();
    Query queryByName = entityManager.createNamedQuery("PersonaEmpresaUsuario.findByCiRuc");
            queryByName.setParameter("ciRuc","0923746663");
    return queryByName.getResultList();
  }
    
  
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
}
