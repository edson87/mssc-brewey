package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerId(UUID beerId);

    /*BeerDto saveNewBeer(BeerDto beerDto);*/
}
