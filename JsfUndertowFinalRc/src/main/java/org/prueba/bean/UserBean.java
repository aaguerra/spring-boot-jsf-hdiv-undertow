/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prueba.bean;

import erp.entity.PersonaEmpresaUsuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.prueba.jpa.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Angel
 */
@ManagedBean
@SessionScoped 
public class UserBean implements Serializable{
    
    @Autowired
  private UserDao userDao;
    private String nombre;
    public UserBean() {}
    
    @PostConstruct
    public void init() {
        this.nombre ="hola mundo";
        
//        
//        List<PersonaEmpresaUsuario> l = userDao.getAll();
//        for(PersonaEmpresaUsuario p : l)
//            System.out.println("persona base"+ p.getCiRuc());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
