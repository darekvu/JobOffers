package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponseDto;

import java.util.List;

public class OfferFacadeTestConfiguration {
    private final InMemoryFetcherTestImpl inMemoryFetcherTest;
    private final InMemoryOfferRepository offerRepository;


    public OfferFacadeTestConfiguration() {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(
                List.of(
                        new JobOfferResponseDto("title1", "company1", "3000", "test1@.com"),
                        new JobOfferResponseDto("title2", "company2", "4000", "test2@.com"),
                        new JobOfferResponseDto("title3", "company3", "5000", "test3@.com"),
                        new JobOfferResponseDto("title4", "company4", "6000", "test4@.com"),
                        new JobOfferResponseDto("title5", "company5", "7000", "test5@.com")
                )
        );
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacadeTestConfiguration(List<JobOfferResponseDto> remoteClientOffers) {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(remoteClientOffers);
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(inMemoryFetcherTest, offerRepository));
    }
}
