package com.junioroffers.domain.offer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OfferDuplicateException extends RuntimeException {
    public OfferDuplicateException(String message) {
        super(message);
    }
}
