package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.exception.OfferDuplicateException;
import com.junioroffers.domain.offer.model.Offer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOfferRepository implements OfferRepository {
    Map<String, Offer> db = new ConcurrentHashMap<>();

    @Override
    public Offer save(Offer entity) {
        if (db.values()
                .stream()
                .anyMatch(offer -> offer.getUrl().equals(entity.getUrl()))) {
            throw new OfferDuplicateException(String.format("Offer with offerUrl %s already exists", entity.getUrl()));
        }
        String id = UUID.randomUUID().toString();

        Offer offer = Offer.builder()
                .id(id)
                .title(entity.getTitle())
                .company(entity.getCompany())
                .salary(entity.getSalary())
                .url(entity.getUrl())
                .build();
        db.put(id, offer);
        return offer;
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<Offer> findAll() {
        return db.values().stream()
                .toList();
    }

    @Override
    public Optional<Offer> findOfferById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existsOfferByUrl(String url) {
        long count = db.values()
                .stream()
                .filter(offer -> offer.getUrl().equals(url))
                .count();
        return count == 1;
    }

}
