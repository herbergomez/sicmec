package com.grupogd.localiza.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.grupogd.localiza.dto.LocalizaCustomerBlockedDto;
import com.grupogd.localiza.dto.LocalizaCustomerBlockedStatusDto;
import com.grupogd.localiza.dto.LocalizaCustomerDto;
import com.grupogd.localiza.dto.LocalizaLoginCounterDto;
import com.grupogd.localiza.dto.LocalizaUserCustomerDto;
import com.grupogd.localiza.dto.LocalizaUserDto;
import com.grupogd.localiza.framework.exception.database.NotFoundException;
import com.grupogd.localiza.framework.utils.EncriptUtility;
import com.grupogd.localiza.service.impl.LocalizaCustomerBlockedServiceImpl;
import com.grupogd.localiza.service.impl.LocalizaCustomerBlockedStatusServiceImpl;
import com.grupogd.localiza.service.impl.LocalizaCustomerServiceImpl;
import com.grupogd.localiza.service.impl.LocalizaLoginCounterServiceImpl;
import com.grupogd.localiza.service.impl.LocalizaUserCustomerServiceImpl;
import com.grupogd.localiza.service.impl.LocalizaUserServiceImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	LocalizaUserServiceImpl LocalizaUserServiceImpl; 
	 
	@Autowired
	LocalizaUserCustomerServiceImpl LocalizaUserCustomerServiceImpl;
	
	@Autowired
	LocalizaCustomerServiceImpl LocalizaCustomerServiceImpl;
	
	@Autowired
	LocalizaCustomerBlockedServiceImpl LocalizaCustomerBlockedServiceImpl;
	
	@Autowired
	LocalizaCustomerBlockedStatusServiceImpl LocalizaCustomerBlockedStatusServiceImpl;
		
	@Autowired
	LocalizaLoginCounterServiceImpl localizaLoginCounterServiceImpl;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatfull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar cal =Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.getTime();
		
		String username = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();
        		
        if(LocalizaUserServiceImpl.exists(username))
        {
        	Authentication auth = null;
        	LocalizaUserDto dto = LocalizaUserServiceImpl.findByuserName(username);
        	
        	try {
        		
        		LOGGER.info("Validate user fxactivation and fxoff date");
        		
				if(format.parse(dto.getFxActivation()).before(cal.getTime()) && format.parse(dto.getFxOff()).after(cal.getTime()))
				{
					LOGGER.info("User Active");
					
					if(dto.getUserPassword().equals(EncriptUtility.getStringMessageDigest(password,EncriptUtility.MD5)))
	           		{
	           			
	           			Boolean authPass = true;
	           			authPass = customerCheck(dto, authPass);
	            			if (authPass == true) 
	            			{
	            				
	            				List<GrantedAuthority> grantedAuths = new ArrayList<>();
	            				grantedAuths.add(new CustomGrantedAuthority("ROLE_"+dto.getLocalizaUserProfile().getProfileName()));
	            				auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	            				SecurityContextHolder.getContext().setAuthentication(authentication);
	            				
	            				LocalizaLoginCounterDto counter = localizaLoginCounterServiceImpl.findLoginCounterByDate(format.format(new Date()));
	            				
	            				//Login Counter Process
	            				if(counter != null)
	            				{
	            					counter.setCounterValue(String.valueOf(Integer.parseInt(counter.getCounterValue())+1));
	            				}
	            				else
	            				{
	            					counter = new LocalizaLoginCounterDto();
	            					counter.setCounterDate(format.format(new Date()));
	            					counter.setCounterValue("1");
	            				}
	            				
	            				dto.setLastLogin(formatfull.format(new Date()));
	            				LocalizaUserServiceImpl.insert(dto);
	            				localizaLoginCounterServiceImpl.insert(counter);
	            				
	            				return auth;
	            			} 
	            			else 
	            			{
	            				throw new BadCredentialsException("Usuario Bloqueado, por favor comunicarse al 2246-4827");
	            			}
	           			
	                    
	           		}
					else
	           		{
	           			throw new BadCredentialsException("Usuario o Contrase&ntilde;a Inv&aacute;lidos");
	           		}
				}
				else
				{
					throw new BadCredentialsException("Usuario inactivo");
				}
			} catch (ParseException | NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BadCredentialsException("Error en la fecha de activacion/desactivacion del usuario");
			}

           		
        }
        else
        {
        	throw new BadCredentialsException("Usuario no encontrado");
        }
        
 
	}
		

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Boolean customerCheck(LocalizaUserDto dto, Boolean auth){
		if (dto.getLocalizaUserProfile().getProfileName().equals("ADMINISTRADOR_FLOTA") == true || dto.getLocalizaUserProfile().getProfileName().equals("USUARIO_FLOTA") == true){
				LocalizaUserCustomerDto userCustomer = LocalizaUserCustomerServiceImpl.findUserCustomerBylocalizaUser(dto.getCodLocalizaUser());
				LocalizaCustomerDto customer = userCustomer.getLocalizaCustomer();
   				try {
					List<LocalizaCustomerBlockedDto> blocked = LocalizaCustomerBlockedServiceImpl.findCustomerByLocalizaCustomer_codLocalizaCustomer(Integer.parseInt(customer.getCodLocalizaCustomer()));
					if (blocked.isEmpty() == true) {
						LocalizaCustomerBlockedStatusDto status = LocalizaCustomerBlockedStatusServiceImpl.findStatusByBlockedStatus("Normal");
						LocalizaCustomerBlockedDto customerBlocked = new LocalizaCustomerBlockedDto();
						customerBlocked.setLocalizaCustomer(customer);
						customerBlocked.setLocalizaCustomerBlockedStatus(status);
						customerBlocked.setBlockedMessage("-");
						try {
							LocalizaCustomerBlockedServiceImpl.insert(customerBlocked);
						} catch (NotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						if (blocked.get(0).getLocalizaCustomerBlockedStatus().getBlockedStatus().equals("Normal") == true) {
							auth = true;
							return auth;
						} else if (blocked.get(0).getLocalizaCustomerBlockedStatus().getBlockedStatus().equals("Bloqueado") == true) {
							auth = false;
							return auth;
						} else if (blocked.get(0).getLocalizaCustomerBlockedStatus().getBlockedStatus().equals("Advertido") == true) {
							auth = true;
							return auth;
						}
					}
				} catch (NumberFormatException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return auth;
	}

}

