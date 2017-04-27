package com.intuit.platform.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class Demo1ClientService {

  @Autowired
  private Demo1Resource demo1App;

  @HystrixCommand(fallbackMethod = "onError")
  public AppProperties getAppProperties() {
    return demo1App.getAppProperties();
  }

  /**
   * @return The default properties when the app is unreachable.
   */
  public AppProperties onError() {
    AppProperties defaultProps = new AppProperties();
    defaultProps.setDescription("App 1 is UNREACHABLE");
    defaultProps.setPid(0);
    return defaultProps;
  }

  @HystrixCommand(fallbackMethod = "onErrorAppName")
  public String getAppName() {
    return demo1App.getMyName();
  }

  /**
   * @return The default properties when the app is unreachable.
   */
  public String onErrorAppName() {
    return "Name Unavailable";
  }
}
