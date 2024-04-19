package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.model.Offer;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOfferRepositoryTestImpl implements OfferRepository {
    Map<Integer, Offer> inMemoryDatabase = new HashMap<>();

    @Override
    public Offer save(Offer offer) {
        return inMemoryDatabase.put(offer.id(), offer);
    }
}
