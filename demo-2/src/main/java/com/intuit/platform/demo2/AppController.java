package com.intuit.platform.demo2;

import java.util.HashMap;
import java.util.Map;

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
  private Demo1Resource demo1App;

  @Autowired
  private AppProperties myProperties;

  @RequestMapping(value = "/myname", method = RequestMethod.GET)
  public ResponseEntity<String> serveFeatureProperties() {
    return new ResponseEntity<>(this.appName + " talking to " + this.demo1App.getMyName(),
        HttpStatus.OK);
  }

  @RequestMapping(value = "/appProperties", method = RequestMethod.GET)
  public ResponseEntity<Map<String, AppProperties>> appProperties() {
    Map<String, AppProperties> allProps = new HashMap<>();
    allProps.put("demo1", demo1App.getAppProperties());
    allProps.put("demo2", this.myProperties);
    return new ResponseEntity<>(allProps, HttpStatus.OK);
  }
}
