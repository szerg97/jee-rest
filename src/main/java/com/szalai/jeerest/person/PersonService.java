package com.szalai.jeerest.person;

import com.szalai.jeerest.interceptor.Timed;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonService {
    private List<Person> repository;

    @PostConstruct
    public void init() {
        this.repository = new ArrayList<>();
        repository.addAll(List.of(
                new Person(1L, "Steven", 26),
                new Person(2L, "Josh", 54),
                new Person(3L, "Erica", 34),
                new Person(4L, "Monica", 21),
                new Person(5L, "Billy", 45),
                new Person(6L, "Jessey", 39)
        ));
    }

    public void addOne(@Observes AddPersonEvent addPersonEvent ){
        Person person = addPersonEvent.getPerson();
        if (repository.stream().anyMatch(p -> p.getId() == person.getId())){
            throw new IllegalStateException(String.format("Person with id [%s] already exists", person.getId()));
        }
        this.repository.add(addPersonEvent.getPerson());
    }

    public Person getOne(long id){
        return this.repository.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @Timed
    public List<Person> getAll(){
        return this.repository;
    }
}
