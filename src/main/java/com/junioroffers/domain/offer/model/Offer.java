package com.junioroffers.domain.offer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Offer {
    private String id;
    private String title;
    private String company;
    private String salary;
    private String url;
}
