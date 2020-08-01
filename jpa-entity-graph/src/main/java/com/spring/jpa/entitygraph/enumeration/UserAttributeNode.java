package com.spring.jpa.entitygraph.enumeration;

public enum UserAttributeNode {
    USER_WITH_PHONE_NODE("user-with-phone-node"),
    USER_WITH_PHONE_AND_CALL_NODE("user-with-phone-and-call-node");

    private String name;

    UserAttributeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
