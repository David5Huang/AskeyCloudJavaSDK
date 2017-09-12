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
 * Get OAuth code.
 * In SDK, this api is using in user authorization.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "device_token_uri",
        "rest_confirm_uri",
        "expires_in",
        "interval"
})
public class DeviceOAuthCodeResponse extends BasicResponse {

    @JsonProperty("device_token_uri")
    private String deviceTokenUri;
    @JsonProperty("rest_confirm_uri")
    private String restConfirmUri;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("interval")
    private Integer interval;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("device_token_uri")
    public String getDeviceTokenUri() {
        return deviceTokenUri;
    }

    @JsonProperty("device_token_uri")
    public void setDeviceTokenUri(String deviceTokenUri) {
        this.deviceTokenUri = deviceTokenUri;
    }

    @JsonProperty("rest_confirm_uri")
    public String getRestConfirmUri() {
        return restConfirmUri;
    }

    @JsonProperty("rest_confirm_uri")
    public void setRestConfirmUri(String restConfirmUri) {
        this.restConfirmUri = restConfirmUri;
    }

    @JsonProperty("expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonProperty("interval")
    public Integer getInterval() {
        return interval;
    }

    @JsonProperty("interval")
    public void setInterval(Integer interval) {
        this.interval = interval;
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