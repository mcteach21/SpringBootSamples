package mc.apps.spring;

import mc.apps.spring.interceptors.SampleInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(WebConfig.class);

    private final SampleInterceptor sampleInterceptor;
    public WebConfig(SampleInterceptor sampleInterceptor) {
        this.sampleInterceptor = sampleInterceptor;
    }


    /**
     * Locale Resolver!
     * determine locale courante utilisée
     * @return
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver(); //..paramètres régionaux actuels en fonction de la session
        slr.setDefaultLocale(Locale.FRANCE);
        return slr;
    }

    /**
     * Locale Change Interceptor!
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        // intercepte changment Locale en fonction de la valeur du paramètre lang (request param)
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");

        logger.info("LocaleChangeInterceptor..");
        return lci;
    }
    // Messages Resources (i18n)
    @Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
        // Read i18n/messages_xxx.properties file.
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("addInterceptors..");
        registry.addInterceptor(sampleInterceptor)
//                .addPathPatterns("/**")
                .excludePathPatterns("/webjars/**","/images/**","/css/**");

        registry.addInterceptor(localeChangeInterceptor());
    }



}
