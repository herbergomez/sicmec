/**
 * 
 */
package com.uesocc.sicmec.config;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.context.embedded.MultiPartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * @author pportillo
 *
 */
@Configuration
public class MultipartConfig {

	public MultipartConfig(){
		
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(500000000);
	    return multipartResolver;
	}
	
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultiPartConfigFactory factory = new MultiPartConfigFactory();
		factory.setMaxFileSize("512KB");
		factory.setMaxRequestSize("512KB");
		return factory.createMultipartConfig();
	}
}
