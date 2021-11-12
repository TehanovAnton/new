package com.example.carrentservice.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class SignInFilter implements Filter {

    private static String[] exceptUris = {
            "user/session/new",
            "user/new"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Boolean session_on = (Boolean) session.getAttribute("SESSION_ON");

        if (exceptUri(req.getRequestURI()) && !session_on) {
            req.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean exceptUri(String uri) {
        return Arrays.asList(exceptUris).contains(uri);
    }
}
