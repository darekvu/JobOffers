package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.exception.OfferDuplicateException;
import com.junioroffers.domain.offer.exception.OfferSavingException;
import com.junioroffers.domain.offer.model.Offer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OfferService {
    private final OfferFetchable offerFetcher;
    private final OfferRepository offerRepository;

    public List<Offer> fetchAllOffersAndSaveIfNotExists() {

        List<Offer> jobOffers = fetchOffers();
        final List<Offer> offers = filterNotExistingOffers(jobOffers);
        try {
            return offerRepository.saveAll(offers);
        } catch (OfferDuplicateException duplicateException) {
            throw new OfferSavingException(duplicateException.getMessage(), jobOffers);
        }

    }

    public List<Offer> fetchOffers() {
        return offerFetcher.fetchOffers()
                .stream()
                .map(OfferMapper::mapFromJobOfferResponseToOffer)
                .toList();
    }

    public List<Offer> filterNotExistingOffers(List<Offer> jobOffers) {
        return jobOffers.stream()
                .filter(offer -> !offer.getUrl().isEmpty())
                .filter(offer -> !offerRepository.existsOfferByUrl(offer.getUrl()))
                .toList();


    }

}
