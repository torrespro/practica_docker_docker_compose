package es.codeurjc.mastercloudapps.planner.clients;

import es.codeurjc.mastercloudapps.planner.models.LandscapeResponse;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TopoClient {

//    private static final String TOPO_HOST = "toposervice";
//    private static final int TOPO_PORT = 8181;

    @Value("${app.client.topo.host:localhost}")
    private String host;

    @Value("${app.client.topo.port:8181}")
    private Integer port;

    @Async
    public CompletableFuture<String> getLandscape(String city) {

        UriComponents uriComponents =
            UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .port(port)
                .path("/api/topographicdetails/{city}")
                .buildAndExpand(city).encode();
        
        RestTemplate restTemplate = new RestTemplate();

//        String url = "http://"+TOPO_HOST+":"+TOPO_PORT+"/api/topographicdetails/" + city;

        LandscapeResponse response = restTemplate.getForObject(uriComponents.toUriString(), LandscapeResponse.class);
        
        return CompletableFuture.completedFuture(response.getLandscape());
    }
}
