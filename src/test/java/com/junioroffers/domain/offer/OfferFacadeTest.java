package com.junioroffers.domain.offer;

import static org.junit.jupiter.api.Assertions.*;

/*
# jako klient aplikacji chcę widzieć oferty pracy dla Junior Java Developera
1. korzystamy ze zdalnego serwera HTTP (skrypt który pobiera oferty ze stron WWW)
2. klient musi używać tokena, żeby zobaczyć oferty
3. klient może się zarejestrować
4. aktualizacja ofert w bazie danych jest co 3 godziny (wtedy odpytujemy zdalny serwer z pkt. 1)
5. oferty w bazie nie mogą się powtarzać (decyduje url oferty)
6. klient może pobrać jedną ofertę pracy poprzez unikalne Id
7. klient może pobrać wszystkie dostępne oferty kiedy jest zautoryzowany
8. jeśli klient w ciągu 60 minut robi więcej niż jedno zapytanie, to dane powinny pobierać się z cache
(ponieważ pobieranie z bazy danych kosztuję pieniądze naszego klienta)
9. klient może ręcznie dodać ofertę pracy
10. każda oferta pracy ma (link do oferty, nazwę stanowiska, nazwę firmy, zarobki (mogą być widełki))

 */
class OfferFacadeTest {

//
//    public void getAllOffers(){
//        return "success";
//    }
}