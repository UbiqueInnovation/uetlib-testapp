package ch.uetlib.testapp.config;

import ch.uetlib.geo.spring.boot.jackson.UetlibJacksonGeoJtsLv95JsonMapperBuilderCustomizer;
import ch.uetlib.job.annotation.EnableJobs;
import ch.uetlib.storage.spring.boot.annotations.EnableStorages;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;

@Configuration
@EntityScan(basePackages = "ch.uetlib.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.uetlib.job.data.repository")
@EnableJobs(basePackages = "ch.uetlib.job")
@EnableStorages(basePackages = "ch.uetlib.job.storage")
public class TestappBaseConfig {

    @Bean
    JsonMapperBuilderCustomizer jacksonGeoJtsLv95JsonMapperBuilderCustomizer() {
        return new UetlibJacksonGeoJtsLv95JsonMapperBuilderCustomizer();
    }

    @Bean
    ResourceBundleMessageSource messageSource() {
        final var source = new ResourceBundleMessageSource();
        source.setDefaultEncoding("UTF-8");
        source.setBasenames("i18n/messages");
        source.setDefaultLocale(Locale.ENGLISH);
        return source;
    }

    @Bean
    CloseableHttpClient httpClient() {
        return HttpClients.createSystem();
    }
}
