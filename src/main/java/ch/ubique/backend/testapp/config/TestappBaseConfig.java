package ch.ubique.backend.testapp.config;

import ch.ubique.backend.job.annotation.EnableJobs;
import ch.ubique.backend.spring.boot.geo.jackson.UetlibJacksonGeoJtsLv95JsonMapperBuilderCustomizer;
import ch.ubique.backend.storage.spring.annotations.EnableStorages;

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
@EntityScan(basePackages = "ch.ubique.backend.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.ubique.backend.job.data.repository")
@EnableJobs(basePackages = {"ch.ubique.backend.job", "ch.ubique.backend.testapp.job"})
@EnableStorages(basePackages = "ch.ubique.backend.job.storage")
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
