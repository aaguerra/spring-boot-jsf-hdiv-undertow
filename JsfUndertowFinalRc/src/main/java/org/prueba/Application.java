package org.prueba;

import org.prueba.bean.JSFSpringBean;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.hdiv.config.Strategy;
import org.hdiv.config.annotation.EnableHdivWebSecurity;
import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.config.annotation.builders.SecurityConfigBuilder;
import org.hdiv.config.annotation.configuration.HdivWebSecurityConfigurerAdapter;
import org.hdiv.filter.ValidatorFilter;
import org.hdiv.listener.InitListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan(basePackages = {""})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        //setRegisterErrorPageFilter(false);
        return application.sources(Application.class);
    }

//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    public ServletRegistrationBean dispatchServletRegistration() {
//
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                dispatcherServlet(), "/*");
//
//        registration
//                .setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//
//        return registration;
//
//    }

    @Configuration
    @Profile("dev")
    static class ConfigureJSFContextParameters implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext servletContext)
                throws ServletException {
            
            
//
//            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
//                    ".xhtml");
            servletContext.setInitParameter(
                    "javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
                    "Development");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter(
                    "javax.faces.FACELETS_REFRESH_PERIOD", "1");
//            
//            servletContext.addListener(new org.springframework.web.context.ContextLoaderListener());
//            //servletContext.addListener(new org.hdiv.listener.InitListener());
////            servletContext.addFilter("ValidatorFilter",new org.hdiv.filter.ValidatorFilter());
//            //servletContext.addFilter("ValidatorFilter","Faces Servlet");
//            
//            //servletContext.setInitParameter("javax.faces.CONFIG_FILES", "/WEB-INF/hdiv-faces2-config.xml");
////            servletContext.addListener("org.hdiv.listener.InitListener");
////            servletContext.addFilter("ValidatorFilter","org.hdiv.filter.ValidatorFilter");
////            servletContext.addFilter("ValidatorFilter","Faces Servlet");
//
            //servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/hdiv-config.xml");
//            servletContext.setInitParameter("javax.faces.CONFIG_FILES", "/WEB-INF/hdiv-faces2-config.xml");
//            servletContext.addListener("org.hdiv.listener.InitListener");
//            servletContext.addFilter("ValidatorFilter","org.hdiv.filter.ValidatorFilter");
//            servletContext.addFilter("ValidatorFilter","Faces Servlet");
//            
//            
        }
    }
    
    @Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		ValidatorFilter validatorFilter = new ValidatorFilter();
		registrationBean.setFilter(validatorFilter);
		registrationBean.setOrder(0);
		return registrationBean;
	}

	@Bean
	public InitListener initListener() {
		return new InitListener();
	}

    @Configuration
    @Profile("production")
    static class ConfigureJSFContextParametersProd implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext servletContext)
                throws ServletException {

//            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
//                    ".xhtml");
            servletContext.setInitParameter(
                    "javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
                    "Production");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
            servletContext.setInitParameter(
                    "javax.faces.FACELETS_REFRESH_PERIOD", "-1");
                       
//            servletContext.addListener(new org.springframework.web.context.ContextLoaderListener());
//            servletContext.addListener(new org.hdiv.listener.InitListener());
//            servletContext.addFilter("ValidatorFilter",new org.hdiv.filter.ValidatorFilter());
//            servletContext.addFilter("ValidatorFilter","Faces Servlet");
//            servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/hdiv-config.xml");
            //servletContext.setInitParameter("javax.faces.CONFIG_FILES", "/WEB-INF/hdiv-faces2-config.xml");
//            servletContext.addListener("org.hdiv.listener.InitListener");
//            servletContext.addFilter("ValidatorFilter","org.hdiv.filter.ValidatorFilter");
//            servletContext.addFilter("ValidatorFilter","Faces Servlet");
            

        }
    }
    
    
    
    
    
}
