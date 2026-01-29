package ch.ubique.backend.testapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.ResponseFilter;
import org.zalando.logbook.core.BodyFilters;
import org.zalando.logbook.core.BodyReplacers;
import org.zalando.logbook.core.Conditions;
import org.zalando.logbook.core.ResponseFilters;

import java.util.Set;
import java.util.stream.Stream;

@Configuration
public class TestappLoggingConfig {

    /**
     * Enable this Logbook {@link BodyFilter} if the property {@code
     * ch.ubique.backend.job.testapp.logbook.body-filter.enabled} equals {@code true} or is missing
     * (default)
     */
    @Bean
    @ConditionalOnProperty(
            value = "ch.ubique.backend.job.testapp.logbook.body-filter.enabled",
            havingValue = "true",
            matchIfMissing = true)
    BodyFilter logbookBodyFilterEnabled() {
        return Stream.of(
                        BodyFilters.replaceFormUrlEncodedProperty(
                                Set.of("subject_token", "authToken"), "XXX"))
                .reduce(BodyFilters.oauthRequest(), BodyFilter::merge);
    }

    /**
     * This represents a disabled Logbook {@link BodyFilter} if the property {@code
     * ch.ubique.backend.job.testapp.logbook.body-filter.enabled} equals {@code false}
     */
    @Bean
    @ConditionalOnProperty(
            value = "ch.ubique.backend.job.testapp.logbook.body-filter.enabled",
            havingValue = "false")
    BodyFilter logbookBodyFilterDisabled() {
        return BodyFilters.replaceFormUrlEncodedProperty(Set.of(), "XXX");
    }

    @Bean
    ResponseFilter logbookResponseFilter() {
        final var defaultBinaryFilter = ResponseFilters.replaceBody(BodyReplacers.binary());
        final var protobufFilter =
                ResponseFilters.replaceBody(
                        BodyReplacers.replaceBody(
                                Conditions.contentType("application/protobuf"),
                                "<binary protobuf>"));
        final var gzipFilter =
                ResponseFilters.replaceBody(
                        BodyReplacers.replaceBody(
                                Conditions.header(HttpHeaders.CONTENT_ENCODING, "gzip"),
                                "<binary gzipped>"));
        return ResponseFilter.merge(
                ResponseFilter.merge(defaultBinaryFilter, protobufFilter), gzipFilter);
    }
}
