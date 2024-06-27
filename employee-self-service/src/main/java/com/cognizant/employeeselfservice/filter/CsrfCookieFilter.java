package com.cognizant.employeeselfservice.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class CsrfCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
