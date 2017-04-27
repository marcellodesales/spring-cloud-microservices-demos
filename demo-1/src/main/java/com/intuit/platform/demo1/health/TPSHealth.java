package com.intuit.platform.demo1.health;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
class TPSHealth implements HealthIndicator {
  TPSCounter counter;

  public TPSHealth() {
    if (counter == null) {
      counter = new TPSCounter();
    }
  }

  @PostConstruct
  public void viewSettings() {
    System.out.println("THIS IS HERE WITH " + counter.threshold);
  }

  @Override
  public Health health() {
    boolean health = counter.isWeak(); // perform some specific health check
    if (health) {
      return Health.down().withDetail("Too many requests", "OutofService").build();
    }
    return Health.up().build();
  }

  void updateTx() {
    if (counter == null || counter.isExpired()) {
      counter = new TPSCounter();
    }
    counter.increment();
  }
}
