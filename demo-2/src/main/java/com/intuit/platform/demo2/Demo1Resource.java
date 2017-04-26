package com.intuit.platform.demo2;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://demo1")
public interface Demo1Resource {

  @RequestMapping(value = "/myname", method = RequestMethod.GET)
  String getMyName();

  @RequestMapping(value = "/appProperties", method = RequestMethod.GET)
  AppProperties getAppProperties();
}
