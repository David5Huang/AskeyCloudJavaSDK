package com.askeycloud.sdk.device.response;

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
 * Get IoT Device register display(name).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deviceid",
        "displayname",
        "model",
        "uniqueid"
})
public class IoTDeviceDisplayInfoResponse extends BasicResponse {

    @JsonProperty("deviceid")
    private String deviceid;
    @JsonProperty("displayname")
    private String displayname;
    @JsonProperty("model")
    private String model;
    @JsonProperty("uniqueid")
    private String uniqueid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("deviceid")
    public String getDeviceid() {
        return deviceid;
    }

    @JsonProperty("deviceid")
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    @JsonProperty("displayname")
    public String getDisplayname() {
        return displayname;
    }

    @JsonProperty("displayname")
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("uniqueid")
    public String getUniqueid() {
        return uniqueid;
    }

    @JsonProperty("uniqueid")
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
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