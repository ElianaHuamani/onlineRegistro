package com.nisum.onlineRegistro.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("ServletPath:"+httpRequest.getServletPath());//     /v1/api/client
        System.out.println("RequestURL:"+httpRequest.getRequestURL());//      http://localhost:8080/v1/api/client
        System.out.println("_csrf_header:"+httpRequest.getHeader("_csrf_header")); //

        System.out.println(httpRequest.getRequestURL().substring(httpRequest.getRequestURL().indexOf("/v1/api") + "/v1/api".length())); //      /client
        response.getOutputStream().print("filtering ");
        chain.doFilter(request, response);

    }
}
