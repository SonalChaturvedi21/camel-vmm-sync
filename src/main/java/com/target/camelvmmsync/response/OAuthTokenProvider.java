package com.target.camelvmmsync.response;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Component
public class OAuthTokenProvider {

    public static final String BEARER = "Bearer ";
    private final AuthServiceClient authServiceClient;
    /**
     * The Key.
     */
    @Value("${services.service.key}")
    public String key;
    private String token;
    private Long expireTime;
    @Value("${services.oAuth.basic}")
    private String oAuthBasic;
    @Value("${services.oAuth.user}")
    private String oAuthUser;
    @Value("${services.oAuth.password}")
    private String oAuthPassword;
    @Value("${services.oAuth.url}")
    private String oAuthUrl;
    @Value("${services.oAuth.grant_type}")
    private String oAuthGrantType;
    @Value("${services.oAuth.scope}")
    private String oAuthScope;

    /**
     * Instantiates a new Vmm services config.
     *
     * @param authServiceClient the vmm services auth client
     */
    public OAuthTokenProvider(@Lazy AuthServiceClient authServiceClient) {

        this.authServiceClient = authServiceClient;
    }

    /**
     * Create o auth token string.
     *
     * @return the string
     */
    public String createOAuthToken() {
        if (isRenewOAuthTokenNeeded()) {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            headersMap.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headersMap.put(HttpHeaders.AUTHORIZATION, oAuthBasic);

            String response =
                    authServiceClient.createOAuthToken(
                            headersMap,
                            oAuthGrantType.trim(),
                            oAuthUser.trim(),
                            // URLEncoder.encode(oAuthPassword.trim(), StandardCharsets.UTF_8),
                            oAuthPassword.trim(),
                            oAuthScope.trim());

            Object oAuthResponseBody =
                    com.jayway.jsonpath.Configuration.defaultConfiguration().jsonProvider().parse(response);
            token = JsonPath.read(oAuthResponseBody, "$.access_token");
            expireTime =
                    Instant.now().getEpochSecond()
                            + Long.parseLong(JsonPath.read(oAuthResponseBody, "$.expires_in").toString());
        }
        return token;
    }

    /**
     * Is renew o auth token needed boolean.
     *
     * @return the boolean
     */
    public Boolean isRenewOAuthTokenNeeded() {
        boolean isRenewTokenNeeded = false;
        if (StringUtils.isEmpty(token) || Instant.now().getEpochSecond() > expireTime) {
            isRenewTokenNeeded = true;
        }
        return isRenewTokenNeeded;
    }

    /**
     * Gets header map.
     *
     * @return the header map
     */
    public Map<String, String> getHeaderMap() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put(HttpHeaders.AUTHORIZATION, BEARER + createOAuthToken());
        return headers;
    }
}
