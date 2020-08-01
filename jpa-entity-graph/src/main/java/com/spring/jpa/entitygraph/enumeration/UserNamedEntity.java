package com.spring.jpa.entitygraph.enumeration;

public enum UserNamedEntity {
    USER_WITH_PHONE_NAMED_QUERY("user-with-phone-named-query"),
    USER_WITH_PHONE_AND_CALL_NAMED_QUERY("user-with-phone-and-call-named-query"),
    USER_WITH_PHONE_NAMED_QUERY_ALL("user-with-phone-named-query-all"),
    USER_WITH_PHONE_AND_CALL_NAMED_QUERY_ALL("user-with-phone-and-call-named-query-all");

    private String name;

    UserNamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
