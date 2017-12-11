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
import javax.transaction.Transactional;

/**
 *
 * @author Josue
 */
@Stateless
@Transactional(Transactional.TxType.SUPPORTS)
public class PersonaBean {
    
    @PersistenceContext
    private EntityManager em;

    public List<Persona> findAll() {
        List<Persona> lista = 
            em.createNamedQuery(Persona.Query.FIND_ALL, Persona.class)
                .getResultList();
        return lista;
    }

    public void create(final Persona persona) {
       em.persist(persona);
    }
    
}
