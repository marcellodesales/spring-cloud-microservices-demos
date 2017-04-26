package com.intuit.platform.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

  @Value("${spring.application.name}")
  private String appName;

  @Autowired
  private AppProperties myProperties;

  @RequestMapping(value = "/myname", method = RequestMethod.GET)
  public ResponseEntity<String> serveFeatureProperties() {
    return new ResponseEntity<>(this.appName, HttpStatus.OK);
  }

  @RequestMapping(value = "/appProperties", method = RequestMethod.GET)
  public ResponseEntity<AppProperties> appProperties() {
    return new ResponseEntity<>(this.myProperties, HttpStatus.OK);
  }
}
