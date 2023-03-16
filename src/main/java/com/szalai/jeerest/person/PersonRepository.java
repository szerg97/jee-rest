package com.szalai.jeerest.person;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonRepository {
    private final List<Person> people;

    public PersonRepository() {
        this.people = new ArrayList<>();
        people.addAll(List.of(
                new Person(1L, "Steven", 26),
                new Person(2L, "Josh", 54),
                new Person(3L, "Erica", 34),
                new Person(4L, "Monica", 21),
                new Person(5L, "Billy", 45),
                new Person(6L, "Jessey", 39)
        ));
    }

    public int add(Person person){
        this.people.add(person);
        return 1;
    }

    public Person getOne(long id){
        return this.people.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public List<Person> getAll(){
        return this.people;
    }
}
