package com.askeycloud.sdk.auth.response;

import java.util.HashMap;
import java.util.Map;

import com.askeycloud.sdk.core.api.response.BasicResponse;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Get registered OAuth providers.
 * OAuth providers must like facebook, google+, etc..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "provider",
        "authorize_uri"
})
public class OAuthProvider extends BasicResponse {

    @JsonProperty("provider")
    private String provider;
    @JsonProperty("authorize_uri")
    private String authorizeUri;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("authorize_uri")
    public String getAuthorizeUri() {
        return authorizeUri;
    }

    @JsonProperty("authorize_uri")
    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}