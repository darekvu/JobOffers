package com.junioroffers.domain.offer.exception;

import com.junioroffers.domain.offer.model.Offer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OfferSavingException extends RuntimeException {

    public OfferSavingException(String message, List<Offer> offers) {
        super(message);
    }
}
