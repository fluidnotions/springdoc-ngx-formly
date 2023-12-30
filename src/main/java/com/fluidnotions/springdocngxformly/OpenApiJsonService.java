package com.fluidnotions.springdocngxformly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
        import org.springframework.web.client.RestTemplate;
        import org.springframework.stereotype.Service;
        import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OpenApiJsonService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${server.port}") String port;

    public ObjectNode getJsonObjectNode() {
        String fullUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:%s/%s".formatted(port, "/v3/api-docs"))
                .toUriString();
        var json = restTemplate.getForObject(fullUrl, String.class);
        return objectMapper.convertValue(json, ObjectNode.class);
    }
}
