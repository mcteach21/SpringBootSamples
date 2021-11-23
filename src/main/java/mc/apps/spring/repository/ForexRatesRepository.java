package mc.apps.spring.repository;

import mc.apps.spring.modele.ExchangeRate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface ForexRatesRepository {
    Flux<ExchangeRate> getAll();
}
