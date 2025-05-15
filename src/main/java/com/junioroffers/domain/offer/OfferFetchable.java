package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponseDto;

import java.util.List;

public interface OfferFetchable {
    List<JobOfferResponseDto> fetchOffers();
}


