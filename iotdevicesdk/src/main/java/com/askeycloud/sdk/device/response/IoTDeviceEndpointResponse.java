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
 * Get IoT device data used to MQTT connection.<br>
 * The parameter detail can see {@link IoTDeviceInfoResponse}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deviceid",
        "gri",
        "iot-thingname",
        "rest-endpoint",
        "shadow-topic"
})
public class IoTDeviceEndpointResponse extends BasicResponse {

    @JsonProperty("deviceid")
    private String deviceid;
    @JsonProperty("gri")
    private String gri;
    @JsonProperty("iot-thingname")
    private String iotThingname;
    @JsonProperty("rest-endpoint")
    private String restEndpoint;
    @JsonProperty("shadow-topic")
    private String shadowTopic;
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

    @JsonProperty("gri")
    public String getGri() {
        return gri;
    }

    @JsonProperty("gri")
    public void setGri(String gri) {
        this.gri = gri;
    }

    @JsonProperty("iot-thingname")
    public String getIotThingname() {
        return iotThingname;
    }

    @JsonProperty("iot-thingname")
    public void setIotThingname(String iotThingname) {
        this.iotThingname = iotThingname;
    }

    @JsonProperty("rest-endpoint")
    public String getRestEndpoint() {
        return restEndpoint;
    }

    @JsonProperty("rest-endpoint")
    public void setRestEndpoint(String restEndpoint) {
        this.restEndpoint = restEndpoint;
    }

    @JsonProperty("shadow-topic")
    public String getShadowTopic() {
        return shadowTopic;
    }

    @JsonProperty("shadow-topic")
    public void setShadowTopic(String shadowTopic) {
        this.shadowTopic = shadowTopic;
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