package com.spring.jpa.entitygraph.enumeration;

public enum UserEntityGraph {
    USER_WITH_PHONE("user-with-phone"),
    USER_WITH_PHONE_AND_ADDRESS("user-with-phone-and-address"), // can not retrieve two collections of data in entity
    USER_WITH_PHONE_AND_CALL("user-with-phone-and-call");

    private String name;

    UserEntityGraph(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
