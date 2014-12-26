package com.uesocc.sicmec.security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal =Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.getTime();
		
		String username = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();
        		
        if(!sicUsuarioServiceImpl.validacionDenombreUsuario(username))
        {
        	Authentication auth = null;
        	SicUsuarioDto dto = sicUsuarioServiceImpl.findByNombreUsuario(username);
        	
        		
        		LOGGER.info("Validate user fxactivation and fxoff date");
        		
				try 
				{
					if(format.parse(dto.getFxActivacion()).before(cal.getTime()) && format.parse(dto.getFxDesactivacion()).after(cal.getTime()))
					{
						LOGGER.info("User Active");
						
						if(dto.getClave().equals(password))
						{
									
							List<GrantedAuthority> grantedAuths = new ArrayList<>();
							grantedAuths.add(new CustomGrantedAuthority("ROLE_"+dto.getSicRol().getNombreRol()));
							auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
							SecurityContextHolder.getContext().setAuthentication(authentication);	
							
							return auth;
						}
						else
						{
							throw new BadCredentialsException("Usuario o contrase&ntilde;a incorrecta");
						}
					}
					else
					{
						throw new BadCredentialsException("Usuario inactivo");
					}
				} 
				catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return auth;
				}
        }	
        else
        {
        	throw new BadCredentialsException("Usuario o contrase&ntilde;a incorrecta");
        }

	}


	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
		
	

}

