package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import com.junioroffers.domain.offer.exception.OfferNotFoundException;
import com.junioroffers.domain.offer.model.Offer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private OfferRepository repository;
    private OfferService offerService;

    public List<OfferResponseDto> getAllOffers() {
        return repository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferResponseDto)
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        return repository.findOfferById(id)
                .map(OfferMapper::mapFromOfferToOfferResponseDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public OfferResponseDto saveOffer(OfferRequestDto requestDto) {
        final Offer offer = OfferMapper.mapFromRequestDtoToOffer(requestDto);
        final Offer savedOffer = repository.save(offer);
        return OfferMapper.mapFromOfferToOfferResponseDto(savedOffer);
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNoExists() {
        return offerService.fetchAllOffersAndSaveIfNotExists()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferResponseDto)
                .toList();
    }


}
