package com.wcs.live.jparest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestResponseExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({ResponseStatusException.class})
    @ResponseBody
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        if (e.getStatus().is4xxClientError()) log.warn("4xx", e);
        else if (e.getStatus().is5xxServerError()) log.error("5xx", e);
        return new ResponseEntity<>(e, e.getResponseHeaders(), e.getStatus());
    }
}
