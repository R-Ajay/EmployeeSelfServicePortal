package com.cognizant.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @GetMapping(path = "/contactSupport")
    public Mono<String> contactSupport(){
        return Mono
                .just("An error occured. Please try after sometimes or contact support team");

    }
}
