package com.askeycloud.sdk.device.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Create IoT device API request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "device"
})
public class CreateIoTDeviceRequest {

    @JsonProperty("device")
    private Device device;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(Device device) {
        this.device = device;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "model",
            "uniqueid",
            "displayname",
            "detail"
    })
    public class Device {

        @JsonProperty("model")
        private String model;
        @JsonProperty("uniqueid")
        private String uniqueid;
        @JsonProperty("displayname")
        private String displayname;
        @JsonProperty("detail")
        private Detail detail;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        @JsonProperty("displayname")
        public String getDisplayname() {
            return displayname;
        }

        @JsonProperty("displayname")
        public void setDisplayname(String displayname) {
            this.displayname = displayname;
        }

        @JsonProperty("detail")
        public Detail getDetail() {
            return detail;
        }

        @JsonProperty("detail")
        public void setDetail(Detail detail) {
            this.detail = detail;
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



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({

    })
    public class Detail {

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

}