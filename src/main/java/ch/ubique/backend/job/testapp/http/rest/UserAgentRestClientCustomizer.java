package ch.ubique.backend.job.testapp.http.rest;

import static org.springframework.http.HttpHeaders.USER_AGENT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserAgentRestClientCustomizer implements RestClientCustomizer {

    private final String defaultUserAgent;

    public UserAgentRestClientCustomizer(
            @Value("${ch.ubique.backend.job.testapp.external-api.default-user-agent}")
                    final String defaultUserAgent) {
        this.defaultUserAgent = defaultUserAgent;
    }

    @Override
    public void customize(final RestClient.Builder restClientBuilder) {
        restClientBuilder.defaultHeader(USER_AGENT, defaultUserAgent);
    }
}
