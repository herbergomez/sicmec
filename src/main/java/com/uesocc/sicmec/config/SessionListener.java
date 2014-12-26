/**
 * 
 */
package com.uesocc.sicmec.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pportillo
 * @Date 26/12/2014
 */

public class SessionListener implements HttpSessionListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
		 LOGGER.info("SESSION IS CREATED");
		 //Seg*Min*Hours (2 horas)
		 se.getSession().setMaxInactiveInterval(60*60*2);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("SESSION IS CLOSE");
		LOGGER.info("SESSION IS CLOSE");
	}

}
