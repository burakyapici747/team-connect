package com.teamconnect.dto;

import com.teamconnect.model.sql.User;

import java.util.Collection;

public record PageableDto<T> (
    Collection<T> elements,
    boolean hasNext,
    int totalPages,
    boolean hastPrevious
){}
