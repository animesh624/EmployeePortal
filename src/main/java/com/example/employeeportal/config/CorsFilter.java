package com.example.employeeportal.config;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends GenericFilterBean {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String origin = request.getHeader("Origin");
        response.setHeader(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        response.setHeader(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true");
        response.setHeader(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "origin, content-type, accept, authorization, X-AUTH-TOKEN, Security-Token, Security-Timestamp, User-Key, token, attributionData, utility-token");

        if(request.getRequestURI().contains("/stomp")) {
            response.setHeader(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, origin);
            response.setHeader("Vary", "Origin");
        } else {
            response.setHeader(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*");
        }
        response.setHeader("attributionData", "");

        if(RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
