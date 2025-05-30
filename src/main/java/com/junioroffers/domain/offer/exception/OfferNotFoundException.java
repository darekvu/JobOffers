package com.junioroffers.domain.offer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String offerId) {
        super(String.format("Offer with id %s not found", offerId));
    }
}
