package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.model.Offer;

import java.util.List;

public interface OfferRepository {
    Offer save(Offer offer);
    List<Offer>
}
