package mc.apps.spring.rest;

import mc.apps.spring.modele.Sample;
import mc.apps.spring.services.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SamplesRestController {
    private static Logger logger = LoggerFactory.getLogger(SamplesRestController.class);

    final SampleService sampleService;
    public SamplesRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/samples/list")
    public List<Sample> listDelayed(){

        return sampleService.getAll().stream()
                .map(s->{
                    try {
                        Thread.sleep(500);
                    }catch(InterruptedException e){}
                    return s;
                }).collect(Collectors.toList());
    }

//    @PostMapping("/samples/new")
//    public List<Sample> add(String sample){
//        sampleService.add(sample);
//        return sampleService.getAll();
//    }
    @PostMapping("/samples/add")
    public List<Sample> add(Sample item){
        sampleService.add(item);
        return sampleService.getAll();
    }
}
