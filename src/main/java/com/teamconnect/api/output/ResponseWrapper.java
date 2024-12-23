package com.teamconnect.api.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(T data) {
        return ResponseEntity.ok(
                ResponseWrapper.<T>builder()
                        .status(HttpStatus.OK)
                        .data(data)
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(T data, String message) {
        return ResponseEntity.ok(
                ResponseWrapper.<T>builder()
                        .status(HttpStatus.OK)
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> created(T data) {
        return new ResponseEntity<>(
                ResponseWrapper.<T>builder()
                        .status(HttpStatus.CREATED)
                        .data(data)
                        .build(),
                HttpStatus.CREATED
        );
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> created(T data, String message) {
        return new ResponseEntity<>(
                ResponseWrapper.<T>builder()
                        .status(HttpStatus.CREATED)
                        .message(message)
                        .data(data)
                        .build(),
                HttpStatus.CREATED
        );
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}