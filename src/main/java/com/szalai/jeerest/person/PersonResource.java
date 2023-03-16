package com.szalai.jeerest.person;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private final PersonRepository repository;

    @Inject
    public PersonResource(PersonRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response
                .ok()
                .entity(new ResponseEntity("Returned all persons", repository.getAll()))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(
            Person person
    ){
        return Response
                .ok()
                .entity(new ResponseEntity("Person successfully added", repository.add(person)))
                .build();
    }
}
