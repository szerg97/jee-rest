package com.szalai.jeerest.person;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private final PersonService personService;
    private final Event<AddPersonEvent> addPersonEvent;

    @Inject
    public PersonResource(PersonService personService, Event<AddPersonEvent> addPersonEvent) {
        this.personService = personService;
        this.addPersonEvent = addPersonEvent;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response
                .ok()
                .entity(new ResponseEntity("Returned all persons", personService.getAll()))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(
            @PathParam(value = "id") int id
    ){
        return Response
                .ok()
                .entity(new ResponseEntity("Returned one person", personService.getOne(id)))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(
            Person person
    ){
        try{
            addPersonEvent.fire(new AddPersonEvent(person));
            return Response
                    .ok()
                    .entity(new ResponseEntity("Person successfully added", 1))
                    .status(Response.Status.CREATED)
                    .build();
        }
        catch (IllegalStateException e){
            return Response
                    .ok()
                    .entity(new ResponseEntity("Couldn't add person", -1))
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
