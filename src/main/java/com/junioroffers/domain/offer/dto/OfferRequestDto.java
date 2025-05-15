package com.junioroffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(String title,String company,String salary,String offerUrl) {
}
