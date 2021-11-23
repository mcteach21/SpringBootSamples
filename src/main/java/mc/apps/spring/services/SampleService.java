package mc.apps.spring.services;

import mc.apps.spring.modele.Sample;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SampleService {

    ArrayList<Sample> samples = new ArrayList();

    public SampleService() {
        samples.addAll(Arrays.asList(
                new Sample(1,"stock"),
                new Sample(2,"prepare"),
                new Sample(3,"roast")));
    }

    public List<Sample> getAll(){
        return samples;
    }

//    public void add(String title) {
//        samples.add(new Sample(samples.size()+1, title));
//    }

    public void add(Sample sample) {
        if(sample.getId()==0)
            sample.setId(maxID()+1);
        samples.add(sample);
    }

    private int maxID(){
        return samples.stream().mapToInt(s->s.getId()).max().orElse(0);
    }
}
