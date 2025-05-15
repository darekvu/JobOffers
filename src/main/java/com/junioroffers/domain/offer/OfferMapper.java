package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponseDto;
import com.junioroffers.domain.offer.dto.OfferDto;
import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import com.junioroffers.domain.offer.model.Offer;

public class OfferMapper {

    public static OfferDto mapToOfferDto(Offer offer) {
        return OfferDto.builder()
                .title(offer.getTitle())
                .company(offer.getCompany())
                .salary(offer.getSalary())
                .offerUrl(offer.getUrl())
                .build();
    }

    public static Offer mapToOffer(OfferDto offerDto) {
        return Offer.builder()
                .title(offerDto.title())
                .company(offerDto.company())
                .salary(offerDto.salary())
                .url(offerDto.offerUrl())
                .build();
    }

    public static Offer mapFromRequestDtoToOffer(OfferRequestDto offerRequest) {
        return Offer.builder()
                .title(offerRequest.title())
                .company(offerRequest.company())
                .salary(offerRequest.salary())
                .url(offerRequest.offerUrl())
                .build();
    }

    public static OfferResponseDto mapFromOfferToOfferResponseDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .company(offer.getCompany())
                .salary(offer.getSalary())
                .offerUrl(offer.getUrl())
                .build();
    }

    public static Offer mapFromJobOfferResponseToOffer(JobOfferResponseDto jobOfferResponseDto) {
        return Offer.builder()
                .title(jobOfferResponseDto.title())
                .company(jobOfferResponseDto.company())
                .salary(jobOfferResponseDto.salary())
                .url(jobOfferResponseDto.offerUrl())
                .build();
    }


}
