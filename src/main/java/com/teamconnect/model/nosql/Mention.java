package com.teamconnect.model.nosql;

import java.util.HashSet;
import java.util.Set;

public class Mention {
    private String id;
    private Set<String> users = new HashSet<>();

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
