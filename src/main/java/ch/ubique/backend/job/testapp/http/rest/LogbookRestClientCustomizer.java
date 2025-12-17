package ch.ubique.backend.job.testapp.http.rest;

import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@Component
public class LogbookRestClientCustomizer implements RestClientCustomizer {

    private final LogbookClientHttpRequestInterceptor logbookInterceptor;

    public LogbookRestClientCustomizer(
            final LogbookClientHttpRequestInterceptor logbookInterceptor) {
        this.logbookInterceptor = logbookInterceptor;
    }

    @Override
    public void customize(final RestClient.Builder restClientBuilder) {
        restClientBuilder.requestInterceptor(logbookInterceptor);
    }
}
