package com.target.camelvmmsync.response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * The interface Vmm services auth client.
 */
@FeignClient(value = "authServiceClient", url = "${services.oAuth.url}")
public interface AuthServiceClient {

    /**
     * Create o auth token string.
     *
     * @param headerMap the header map
     * @param grantType the grant type
     * @param username  the username
     * @param password  the password
     * @param scope     the scope
     * @return the string
     */
    @RequestMapping(method = RequestMethod.POST, path = "/auth/oauth/v2/token")
    String createOAuthToken(
            @RequestHeader Map<String, String> headerMap,
            @RequestParam(value = "grant_type") String grantType,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "scope") String scope);
}
