/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prueba;

import org.hdiv.config.Strategy;
import org.hdiv.config.annotation.EnableHdivWebSecurity;
import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.config.annotation.builders.SecurityConfigBuilder;
import org.hdiv.config.annotation.configuration.HdivWebSecurityConfigurerAdapter;
import org.hdiv.filter.ValidatorFilter;
import org.hdiv.listener.InitListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Angel
 */
@Configuration
@EnableHdivWebSecurity
public class HdivSecurityConfig
        extends HdivWebSecurityConfigurerAdapter {

    @Override
    public void addExclusions(ExclusionRegistry registry) {

        // Static content
        registry.addUrlExclusions("/webjars/.*").method("GET");
        // It's not possible to exclude static by pattern, like /resources/.*
        registry.addUrlExclusions(".*.js").method("GET");
        registry.addUrlExclusions(".*.css").method("GET");
        registry.addUrlExclusions(".*.png").method("GET");
        registry.addUrlExclusions(".*.jpg").method("GET");
        registry.addUrlExclusions(".*.woff").method("GET");
        registry.addUrlExclusions(".*.ttf").method("GET");
        registry.addUrlExclusions(".*.svg").method("GET");
        registry.addUrlExclusions(".*.ico").method("GET");

        // Excluded URLs
        registry.addUrlExclusions("/").method("GET");

        // It's possible to autodetect actuator endpoints and configure as them as exclusion?
        // Actuator filters
        registry.addUrlExclusions("/health");
        registry.addUrlExclusions("/beans").method("GET");
        registry.addUrlExclusions("/trace").method("GET");
        registry.addUrlExclusions("/configprops").method("GET");
        registry.addUrlExclusions("/info").method("GET");
        registry.addUrlExclusions("/dump").method("GET");
        registry.addUrlExclusions("/autoconfig").method("GET");
        registry.addUrlExclusions("/metrics", "/metrics/.*").method("GET");
        registry.addUrlExclusions("/env", "/env/.*").method("GET");
        registry.addUrlExclusions("/mappings").method("GET");

        // Spring Security
        registry.addParamExclusions("_csrf");

        registry.addUrlExclusions("/index.jsf").method("GET");
        registry.addUrlExclusions("/index.xhtml").method("GET");
        
         registry.addParamExclusions("javax.faces.*");
    }

//    @Override
//    public void configureEditableValidation(ValidationConfigurer validationConfigurer) {
//
//        // Enable default rules for all URLs.
//        validationConfigurer.addValidation(".*");
//    }

//        extends HdivWebSecurityConfigurerAdapter {
//
    @Override
    public void configure(SecurityConfigBuilder builder) {
        
        // Configuration options
        builder.cookiesConfidentiality(false)
                .cookiesIntegrity(false).confidentiality(false);
        
        builder
            .sessionExpired()
                .homePage("/").loginPage("/index.xhtml");
        builder.errorPage("/error.xhtml");
        
//        builder.maxPagesPerSession(5);
//        //nombre aleatorio por usuario dle parametro hadiv
       builder.randomName(true);
//        
//        builder.debugMode(false); 
//        
//        // no se cree otro ben e un ajax dialog o pop over
//        builder.reuseExistingPageInAjaxRequest(true);
//        
//        // todos lso errores se redirigen a la pagina de error configurada en hdiv
        builder.showErrorPageOnEditableValidation(true);
//        
        builder.validateUrlsWithoutParams(false);
    }
//        @Override
//    public void addRules(RuleRegistry registry) {
//
//        registry.addRule("customValidation").acceptedPattern("^[a-zA-Z0-9@.\\-_]*$");
//    }
//
//    @Override
//    public void configureEditableValidation(ValidationConfigurer validationConfigurer) {
//
//        validationConfigurer.addValidation(".*").rules("customValidation");
//    }
}
