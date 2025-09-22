package org.example.calendar.spring.config;

import jakarta.validation.MessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class CustomMessageSourceConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 預設語系
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.TRADITIONAL_CHINESE);
        return slr;
    }

    @Bean
    public MessageInterpolator getMessageInterpolator(MessageSource messageSource) {
        MessageSourceResourceBundleLocator resourceBundleLocator = new MessageSourceResourceBundleLocator(messageSource);
        ResourceBundleMessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator(resourceBundleLocator);
        return new RecursiveLocaleContextMessageInterpolator(messageInterpolator);
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageInterpolator messageInterpolator) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setMessageInterpolator(messageInterpolator);
        return bean;
    }
}
