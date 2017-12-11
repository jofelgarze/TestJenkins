/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofelgarze.testweb.rest;

import com.jofelgarze.testentites.Persona;
import com.jofelgarze.testweb.bean.PersonaBean;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Josue
 */
@Path("/persona")
public class PersonaService {
    
    @Inject
    private PersonaBean personaBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Persona> lista = personaBean.findAll();
        return Response.ok(lista).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final Persona book) {
            personaBean.create(book);
            return Response.ok().build();
    }
    
}
