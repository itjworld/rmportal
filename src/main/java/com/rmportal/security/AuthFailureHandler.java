package com.rmportal.security;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.rmportal.enums.Credential;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		JSONObject jo= new JSONObject();
		jo.put("status",HttpServletResponse.SC_UNAUTHORIZED);
		if(exception instanceof BadCredentialsException) {
			jo.put("message",Credential.BAD.name());
		}else if(exception instanceof LockedException) {
			jo.put("message",Credential.LOCKED.name());
		}else if(exception instanceof DisabledException) {
			jo.put("message","User are not active!");
		}else if(exception instanceof AccountExpiredException) {
			jo.put("message",Credential.EXPIRED.name());
		}else if(exception instanceof CredentialsExpiredException) {
			jo.put("message",Credential.CREDENTIAL_EXPIRED.name());
		}else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	
		}
		response.getWriter().print(jo);
		response.getWriter().flush();
		
	}
}
