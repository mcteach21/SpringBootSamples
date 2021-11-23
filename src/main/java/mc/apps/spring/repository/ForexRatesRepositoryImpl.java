package mc.apps.spring.repository;

import mc.apps.spring.modele.ExchangeRate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class ForexRatesRepositoryImpl implements ForexRatesRepository{
    private static final Logger logger = LogManager.getLogger(ForexRatesRepositoryImpl.class);

    final String FOREX_API_URL ="http://api.exchangeratesapi.io/v1/latest?access_key=3942fdcf1e4e98add7605f388356e263";
    WebClient client = WebClient.create(FOREX_API_URL);

    @Override
    public Flux<ExchangeRate> getAll() {

        logger.info("ForexRatesRepositoryImpl getAll()..");

        Flux<ExchangeRate> todoFlux = client.get()
                .uri("")
                .retrieve()
                .bodyToFlux(ExchangeRate.class);

        todoFlux.subscribe(logger::info);

        return todoFlux;
    }
}
