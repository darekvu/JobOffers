package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponseDto;

import java.util.List;

public class InMemoryFetcherTestImpl implements OfferFetchable {
    List<JobOfferResponseDto> listOfOffers;

    InMemoryFetcherTestImpl(List<JobOfferResponseDto> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    @Override
    public List<JobOfferResponseDto> fetchOffers() {
        return listOfOffers;
    }
}
