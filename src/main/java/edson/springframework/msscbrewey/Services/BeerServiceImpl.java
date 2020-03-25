package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerId(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Taquiña")
                .beerStyle("Golden Beer")
                .build();
    }

   /* @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }*/
}
