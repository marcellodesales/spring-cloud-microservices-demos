package com.intuit.platform.reactorweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

  @Autowired
  private MyReactiveLibrary theLib;

  @GetMapping("/hello/{who}")
  public Mono<String> hello(@PathVariable String who) {
    return Mono
        .just(who)
        .map(w -> "Welcome " + w + "!");
  }

  @GetMapping("/hello/delay/{who}")
  public Mono<String> hello2(@PathVariable String who) {
    return theLib.withDelay("Hello delayed: " + who, 5);
  }

  @PostMapping("heymister")
  public Flux<String> hey(@RequestBody Flux<Entry> body) {
    return Mono.just("Hey Mister ")
        .concatWith(body.flatMap(entry -> Flux.fromArray(entry.getName().split(" "))
            .map(String::toUpperCase).take(1).concatWith(Mono.just(" you well?"))));
  }
}
