package com.intuit.platform.gateway;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RequestMappingZuulFilter extends ZuulFilter {

  private static Log logger = LogFactory.getLog(RequestMappingZuulFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest req = ctx.getRequest();
    logger.info(req.getMethod() + " REQUEST TO THIS RESOURCE " + req.getRequestURI());
    return null;
  }
}
