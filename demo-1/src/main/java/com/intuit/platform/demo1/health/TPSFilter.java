package com.intuit.platform.demo1.health;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TPSFilter implements Filter {

  @Autowired
  private TPSHealth health;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest)request;
    String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

    // Verify the APIs
    if (path.startsWith("/myname") || path.startsWith("/app")) {
      health.counter.increment();
    }

    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
