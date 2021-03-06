package com.askeycloud.sdk.device.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.askeycloud.sdk.core.api.response.BasicResponse;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Get user all IoT devices.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "devices"
})
public class UserIoTDeviceListResponse extends BasicResponse {

    @JsonProperty("devices")
    private List<UserIoTDevice> devices = new ArrayList<UserIoTDevice>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The index device data.<br>
     * @see UserIoTDevice
     */
    @JsonProperty("devices")
    public List<UserIoTDevice> getDevices() {
        return devices;
    }

    /**
     *
     * @param devices
     * The devices
     */
    @JsonProperty("devices")
    public void setDevices(List<UserIoTDevice> devices) {
        this.devices = devices;
    }

    public UserIoTDeviceListResponse withDevices(List<UserIoTDevice> devices) {
        this.devices = devices;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public UserIoTDeviceListResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}