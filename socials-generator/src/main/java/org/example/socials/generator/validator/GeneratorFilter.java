package org.example.socials.generator.validator;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class GeneratorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println(servletRequest.getProtocol());
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
