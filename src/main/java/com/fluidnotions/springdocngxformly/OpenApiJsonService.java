package com.fluidnotions.springdocngxformly;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenApiJsonService {

    private final RestTemplate restTemplate;

    @Value("${server.port}") String port;

    //FIXME: can't seems to find a way to get the json from the OpenAPIService
    public ObjectNode getJsonObjectNode() {
        String fullUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:%s/%s".formatted(port, "/v3/api-docs"))
                .toUriString();
        var json = restTemplate.getForObject(fullUrl, ObjectNode.class);
        return json;
    }
}
