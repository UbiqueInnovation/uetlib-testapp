package ch.ubique.uetlib.testapp.config;

import ch.ubique.uetlib.job.annotation.EnableJobs;
import ch.ubique.uetlib.spring.boot.geo.jackson.UetlibJacksonGeoJtsLv95JsonMapperBuilderCustomizer;
import ch.ubique.uetlib.storage.spring.annotations.EnableStorages;
import java.util.Locale;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ch.ubique.uetlib.job.model.entity")
@EnableJpaRepositories(basePackages = "ch.ubique.uetlib.job.data.repository")
@EnableJobs(basePackages = "ch.ubique.uetlib.job")
@EnableStorages(basePackages = "ch.ubique.uetlib.job.storage")
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
