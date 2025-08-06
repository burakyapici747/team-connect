package com.teamconnect.api.output;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ResponseWrapper<T>(
    HttpStatus status,
    String message,
    T data
) {
    public static <T> ResponseEntity<ResponseWrapper<T>> ok(T data) {
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, null, data));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(T data, String message) {
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, message, data));
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> created(T data) {
        return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.CREATED, null, data), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> created(T data, String message) {
        return new ResponseEntity<>(new ResponseWrapper<>(HttpStatus.CREATED, message, data), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}