package com.szalai.jeerest.person;

public class AddPersonEvent {
    private final Person person;

    public AddPersonEvent(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
