package mc.apps.spring.services;

import mc.apps.spring.modele.ExchangeRate;
import mc.apps.spring.repository.ForexRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ForexRatesService {

    @Autowired
    ForexRatesRepository forexRatesRepository;

//    public Flux<ExchangeRate> getAllRates() {
    public List<ExchangeRate> getAllRates() {
         //return forexRatesRepository.getAll();

        return Arrays.asList(
                new ExchangeRate(1L,"USD", 1.25, new Date(2021,11,22)),
                new ExchangeRate(1L,"USD", 1.25, new Date(2021,11,22)),
                new ExchangeRate(1L,"USD", 1.25, new Date(2021,11,22))
        );
    }
}
