package com.junioroffers.domain.offer;

import com.junioroffers.domain.offer.dto.JobOfferResponseDto;
import com.junioroffers.domain.offer.dto.OfferRequestDto;
import com.junioroffers.domain.offer.dto.OfferResponseDto;
import com.junioroffers.domain.offer.exception.OfferDuplicateException;
import com.junioroffers.domain.offer.exception.OfferNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//# jako klient aplikacji chcę widzieć oferty pracy dla Junior Java Developera
//        korzystamy ze zdalnego serwera HTTP (skrypt który pobiera oferty ze stron WWW)
//        klient musi używać tokena, żeby zobaczyć oferty
//        klient może się zarejestrować
//        aktualizacja ofert w bazie danych jest co 3 godziny (wtedy odpytujemy zdalny serwer z pkt. 1)
//        oferty w bazie nie mogą się powtarzać (decyduje url oferty)
//        klient może pobrać jedną ofertę pracy poprzez unikalne Id
//        klient może pobrać wszystkie dostępne oferty kiedy jest zautoryzowany
//        jeśli klient w ciągu 60 minut robi więcej niż jedno zapytanie, to dane powinny pobierać się z cache (ponieważ pobieranie z bazy danych kosztuję pieniądze naszego klienta)
//        klient może ręcznie dodać ofertę pracy
//        każda oferta pracy ma (link do oferty, nazwę stanowiska, nazwę firmy, zarobki (mogą być widełki))
//@ExtendWith(MockitoExtension.class)
class OfferFacadeTest {

    @Test
    void it_should_get_all_offers_and_save_if_repository_is_empty() {
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.getAllOffers()).isEmpty();
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 1", " 10", "http://justyjoinit/job_offer_1"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 2", " 20", "http://justyjoinit/job_offer_2"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 3", " 30", "http://justyjoinit/another_job_offer_3"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 4", " 40", "http://justyjoinit/another_job_offer_4"));
        //when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNoExists();
//
        assertThat(result).hasSize(5);
    }

    @Test
    void should_save_only_2_offers_when_repository_4_offers_had_been_added() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferResponseDto("job offer 1", "company 1", "1000", "http://justyjoinit/job_offer_1"),
                        new JobOfferResponseDto("job offer 2", "company 2", "2000", "http://justyjoinit/job_offer_2"),
                        new JobOfferResponseDto("job offer 3", "company 3", "3000", "http://justyjoinit/job_offer_3"),
                        new JobOfferResponseDto("job offer 4", "company 4", "4000", "http://justyjoinit/job_offer_4")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 1", " 10", "http://justyjoinit/job_offer_1"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 2", " 20", "http://justyjoinit/job_offer_2"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 3", " 30", "http://justyjoinit/another_job_offer_3"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 4", " 40", "http://justyjoinit/another_job_offer_4"));
        //when
        List<OfferResponseDto> offerResponseDtos = offerFacade.fetchAllOffersAndSaveAllIfNoExists();

        assertThat(offerResponseDtos).hasSize(2);
        //then
    }

    @Test
    void should_save_all_offers() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferResponseDto("job offer 1", "company 1", "1000", "http://justyjoinit/job_offer_1"),
                        new JobOfferResponseDto("job offer 2", "company 2", "2000", "http://justyjoinit/job_offer_2"),
                        new JobOfferResponseDto("job offer 3", "company 3", "3000", "http://justyjoinit/job_offer_3"),
                        new JobOfferResponseDto("job offer 4", "company 4", "4000", "http://justyjoinit/job_offer_4")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 1", " 10", "http://justyjoinit/another_job_offer_1"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 2", " 20", "http://justyjoinit/another_job_offer_2"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 3", " 30", "http://justyjoinit/another_job_offer_3"));
        offerFacade.saveOffer(new OfferRequestDto("job offer 1", "another company 4", " 40", "http://justyjoinit/another_job_offer_4"));
        //when
        List<OfferResponseDto> offerResponseDtos = offerFacade.fetchAllOffersAndSaveAllIfNoExists();

        assertThat(offerResponseDtos).hasSize(4);
        //then
    }

    @Test
    void should_find_offer_by_id() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        //when
        OfferRequestDto offerRequestDto = new OfferRequestDto("job offer 1", "another company 1", " 10", "http://justyjoinit/another_job_offer_1");
        var offerResponseDto = offerFacade.saveOffer(offerRequestDto);
        OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());//then
        assertThat(offerById).isEqualTo(OfferResponseDto.builder()
                .id(offerResponseDto.id())
                .title(offerRequestDto.title())
                .offerUrl(offerRequestDto.offerUrl())
                .salary(offerRequestDto.salary())
                .company(offerRequestDto.company())
                .build());
    }

    @Test
    void should_throw_exception_when_id_not_found() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        //when
        String id = UUID.randomUUID().toString();
        //then

        assertThatThrownBy(() -> offerFacade.findOfferById(id))
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessageContaining(String.format("Offer with id %s not found", id));
    }
    @Test
    void it_should_throw_duplicate_exception_when_offer_exists() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        //when
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("testOffer", "company1", "5", "http://nofluffjobs/1"));
        String savedId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(savedId).id()).isEqualTo(savedId);
        assertThatThrownBy(()->offerFacade.saveOffer(new OfferRequestDto("testOffer", "company1", "5", "http://nofluffjobs/1")))
                .isInstanceOf(OfferDuplicateException.class)
                .hasMessageContaining(String.format("Offer with offerUrl %s already exists",offerResponseDto.offerUrl()));
    }
}