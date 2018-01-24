package com.rmportal.security;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final String X_RM_CALL = "x-www-form-rm-call";
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		
		if ("TRUE".equalsIgnoreCase(request.getHeader(X_RM_CALL))) {
			response.setStatus(HttpServletResponse.SC_OK);
			JSONObject jo= new JSONObject();
			jo.put("ROLES", authentication.getAuthorities().stream().map(x->x.getAuthority()).collect(Collectors.toList()));
			jo.put("USER", authentication.getName());
			jo.put("status",HttpServletResponse.SC_OK);
			//response.setHeader("access-control-expose-headers","ROLES");
			//response.setHeader("access-control-expose-headers","USER");
			//response.setHeader("ROLES", authentication.getAuthorities().stream().map(x->x.getAuthority()).collect(Collectors.joining(",")));
			//response.setHeader("USER", authentication.getName());
			response.addHeader("X-AUTH-TOKEN", "TOken");
			Cookie cookie = new Cookie("XSRF-TOKEN", "ashish");
			response.addCookie(cookie);
			cookie.setPath("/");
			response.getWriter().print(jo);
			response.getWriter().flush();
		} else {
			 clearAuthenticationAttributes(request);
		}
       
    }
}
