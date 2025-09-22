package org.example.calendar.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
public class MyContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, null, locale);
    }

    public String getMessage(String code, String[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, locale);
    }

    public String getMessage(String code, String[] args, Locale locale) {
        String s = code;
        try {
            s = applicationContext.getMessage(code, args, locale);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error("can't find code[{}]:{}", code, e.getMessage());
            }
        }
        return s;
    }
}
