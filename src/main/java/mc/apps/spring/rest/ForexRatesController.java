package mc.apps.spring.rest;

import mc.apps.spring.modele.ExchangeRate;
import mc.apps.spring.services.ForexRatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/exchangeRate")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ForexRatesController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ForexRatesService forexRatesService;
    public ForexRatesController(ForexRatesService forexRatesService) {
        this.forexRatesService = forexRatesService;
    }

    /**
     * Forex API access
     */

//    @GetMapping("/api/exchangeRate/all/")
//    public ResponseEntity<List<ExchangeRate>> GetAllRates(){
//        logger.info("Listing all rates");
//
//        List<ExchangeRate> rates = forexRatesService.getAllRates();
//
//        if (rates == null || rates.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(rates, HttpStatus.OK);
//    }

    @GetMapping("/all")
    //public Flux<ExchangeRate> getAllTodos(){
    public List<ExchangeRate> getAllTodos(){
        logger.info("RestController..getAllRates");
        return forexRatesService.getAllRates();
    }

}
