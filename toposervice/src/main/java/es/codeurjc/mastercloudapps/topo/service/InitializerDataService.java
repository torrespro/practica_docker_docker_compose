package es.codeurjc.mastercloudapps.topo.service;

import es.codeurjc.mastercloudapps.topo.model.City;
import es.codeurjc.mastercloudapps.topo.repository.CityRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InitializerDataService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RetryTemplate retryTemplate;

    @PostConstruct
    public void init() {

        retryTemplate.execute(context -> {

            this.cityRepository.deleteAll();

            Flux<City> cities = Flux.just(
                new City("Madrid", "Flat"),
                new City("Barcelona", "Flat"),
                new City("Jaca", "Mountain"),
                new City("Andorra", "Mountain"),
                new City("Valencia", "Flat"),
                new City("Sevilla", "Mountain"),
                new City("Zaragoza", "Flat"),
                new City("Málaga", "Mountain"),
                new City("Murcia", "Flat"),
                new City("Palma", "Mountain"),
                new City("Bilbao", "Flat"),
                new City("Alicante", "Mountain"),
                new City("Córdoba", "Flat"),
                new City("Valladolid", "Mountain"),
                new City("Vigo", "Flat"),
                new City("Gijón", "Mountain"),
                new City("Vitoria", "Flat")
            );

            cities.flatMap(this.cityRepository::save).blockLast();
            return null;
        });
    }

}
