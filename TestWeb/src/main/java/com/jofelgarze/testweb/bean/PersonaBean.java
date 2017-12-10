/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofelgarze.testweb.bean;

import com.jofelgarze.testentites.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Josue
 */
@Stateless
public class PersonaBean {
    
    @PersistenceContext
    private EntityManager em;

    public List<Persona> findAll() {
        return em.createNamedQuery(Persona.Query.FIND_ALL, Persona.class).getResultList();
    }

    public void create(final Persona persona) {
       em.persist(persona);
    }
    
}
