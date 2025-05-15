package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository {
    Offer save(Offer offer);

    List<Offer> saveAll(List<Offer> offers);

    List<Offer> findAll();

    Optional<Offer> findOfferById(String id);

    boolean existsOfferByUrl(String url);
}
