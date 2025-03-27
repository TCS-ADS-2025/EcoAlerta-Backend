package com.ecoalerta.app.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private HttpStatus status;
    private final LocalDateTime timestamp = LocalDateTime
            .parse(LocalDateTime.now()
                    .format(DateTimeFormatter
                            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    private String message;
    private List<String> errorMessages;

    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestErrorMessage(HttpStatus status, List<String> errorMessages) {
        this.status = status;
        this.errorMessages = errorMessages;
    }
}