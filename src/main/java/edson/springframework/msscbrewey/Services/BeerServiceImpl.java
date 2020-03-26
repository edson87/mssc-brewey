package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerId(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Taquiña")
                .beerStyle("Golden Beer")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo impl - would add a real impl to update beer

    }

    @Override
    public void deleteById(UUID beerId) {
        //todo impl - would you add real impl to delete de beer
        log.debug("Deleting a beer...");
    }
}
