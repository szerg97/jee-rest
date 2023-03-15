package com.szalai.jeerest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/hello-world")
public class HelloResource {

    private final Map<Language, List<String>> animals = new HashMap<>();

    public HelloResource() {
        animals.put(Language.EN, List.of(
                "dog",
                "cat",
                "penguin",
                "lion"
        ));
        animals.put(Language.HU, List.of(
                "kutya",
                "cica",
                "pingvin",
                "oroszlán"
        ));
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAll() {
        return animals.get(Language.EN);
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOne(
            @PathParam(value = "id") int id,
            @QueryParam(value = "language") String language) {
        if (language != null){
            if (language.equals("en")){
                return animals.get(Language.EN).get(id);
            }
            else if (language.equals("hu")){
                return animals.get(Language.HU).get(id);
            }
        }
        return animals.get(Language.EN).get(id);
    }
}