package com.target.camelvmmsync.response;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final String KEY = "key";
    private final OAuthTokenProvider authTokenProvider;
    private final List<String> skipList = List.of("authServiceClient");
    @Value("${services.service.key}")
    public String key;

    public FeignClientInterceptor(@Lazy OAuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (headers.containsKey(HttpHeaders.AUTHORIZATION))
            return;
        final Target<?> target = template.feignTarget();
        if (target != null && (skipList.contains(target.type().getSimpleName()) || skipList.contains(target.name()))) {
            return;
        }
        if (target != null && target.url().toUpperCase().contains("target.com".toUpperCase()))
            template.query(KEY, key);
        template.header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN_TYPE + authTokenProvider.createOAuthToken());


    }
}
