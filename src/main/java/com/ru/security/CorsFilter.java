package com.ru.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	public void doFilter(ServletRequest r, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) r;
		String origin = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", origin != null && origin.equals("http://localhost:8080") ? origin : "");
		response.setHeader("Vary", "Origin");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Authorization, Content-Type, Accept, X-CSRF-TOKEN");
		response.setHeader("Access-Control-Max-Age", "3600");
		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) r).getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(r, res);
		}
	}



	public void destroy() {
	}

	public void init(FilterConfig c) throws ServletException {
	}

}
