package com.example.demo;

package com.service.generatexls.service;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestTemplateGetJson {

    private final RestTemplate restTemplate;
    @Value("${Url}")
    private String url;
    @Value("${Key}")
    private String key;
    @Value("${Header}")
    private String header;

    public RestTemplateGetJson(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public JSONPObject getJson(String end, String begin) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(header, key);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<JSONPObject> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<JSONPObject>() {
                },
                end,
                begin


        );
        return response.getBody();
    }
}