package controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, 
			             ServletResponse resp, 
			             FilterChain filterChain)
			throws IOException, ServletException {
		
		var httpReq = (HttpServletRequest) req;
		var httpResp = (HttpServletResponse) resp;
		System.out.println("Filtro: " + httpReq.getRequestURI());
		
		boolean userIsLogged = 
				httpReq.getSession().getAttribute("user_logged") != null;
		
		boolean isPublicResource = 
				httpReq.getRequestURI().endsWith(".css") || 
				httpReq.getRequestURI().endsWith(".js");
		
		boolean isLogin = httpReq.getRequestURI().contains("login");
		
		if (userIsLogged || isLogin || isPublicResource) {
			filterChain.doFilter(httpReq, httpResp);
		} else {
			httpResp.sendRedirect("/facebook/login.jsp");
		}
	}
}
