package com.szalai.jeerest.person;

public class ResponseEntity {
    private final String message;
    private final Object data;

    public ResponseEntity(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
