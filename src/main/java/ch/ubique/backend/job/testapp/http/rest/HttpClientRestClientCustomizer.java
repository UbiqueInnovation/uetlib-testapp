package ch.ubique.backend.job.testapp.http.rest;

import org.apache.hc.client5.http.classic.HttpClient;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpClientRestClientCustomizer implements RestClientCustomizer {

    private final HttpClient httpClient;

    public HttpClientRestClientCustomizer(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void customize(final RestClient.Builder restClientBuilder) {
        restClientBuilder.requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}
