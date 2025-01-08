package com.teamconnect.model.nosql;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MessageMention {
    private Set<String> users = new HashSet<>();
}
