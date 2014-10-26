/**
 * 
 */
package com.uesocc.sicmec.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author xtiyo
 *
 */



import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import nz.net.ultraq.web.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.jolbox.bonecp.BoneCPDataSource;


/**
 * @author pablo portillo
 * @date 31/7/2014
 */

@Configuration
@ComponentScan(value ="com.uesocc.sicmec.*")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("com.uesocc.sicmec.model.*")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class ApplicationContext extends WebMvcConfigurerAdapter {
	
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource() 
	{
		BoneCPDataSource datasource = new BoneCPDataSource();
		datasource.setDriverClass(env.getRequiredProperty("db.driver"));
		datasource.setJdbcUrl(env.getRequiredProperty("db.url"));
		datasource.setUsername(env.getRequiredProperty("db.username"));
		datasource.setPassword(env.getRequiredProperty("db.password"));
		return datasource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) 
	{
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setJpaProperties(additionalProperties());
		lef.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));
		return lef;
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) 
	{
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() 
	{
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty("hibernate.show_sql")));
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getRequiredProperty("hibernate.GenerateDdl")));
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		return hibernateJpaVendorAdapter;
	}

	Properties additionalProperties() 
	{
		return new Properties() {
			{ // Hibernate Specific:
				setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
			}
		};
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages","i18n/messages_ES", "i18n/messages_EN");
		source.setDefaultEncoding(env.getRequiredProperty("DEFAULT_ENCODING"));
		return source;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").
        addResourceLocations("/WEB-INF/resources/");
    }
	
	
//	@Bean
//	public SpringLiquibase liquibase(DataSource dataSource) {
//		SpringLiquibase springLiquibase = new SpringLiquibase();
//		springLiquibase.setDataSource(dataSource);
//		springLiquibase.setChangeLog(env.getRequiredProperty("liquidbase.changelog"));
//		return springLiquibase;
//	}

		
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.addDialect(new LayoutDialect());
		return engine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() 
	{
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding(env.getRequiredProperty("DEFAULT_ENCODING"));
		return resolver;
	}
	
	//Mail sender
	
//	@Bean
//    public JavaMailSenderImpl emailSender(){
//		
//        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
//            emailSender.setHost(env.getRequiredProperty("email.host"));
//            emailSender.setPort(Integer.parseInt(env.getRequiredProperty("email.port")));
//            emailSender.setUsername(env.getRequiredProperty("email.username"));
//            emailSender.setPassword(env.getRequiredProperty("email.pass"));
//   
//            Properties mailProps = new Properties();
//                mailProps.setProperty("mail.transport.protocol","smtp");
//                mailProps.setProperty("mail.smtp.auth","true");
//                mailProps.setProperty("mail.smtp.starttls.enable","true");
//                mailProps.setProperty("mail.debug","true");
//                emailSender.setJavaMailProperties(mailProps);
//        return emailSender;
//    }

}
