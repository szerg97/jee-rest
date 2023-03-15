package com.szalai.jeerest.person;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("person")
public class PersonResource {

    private final PersonRepository repository;

    @Inject
    public PersonResource(PersonRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll(){
        return repository.getAll();
    }
}
