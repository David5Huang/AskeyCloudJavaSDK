package com.askeycloud.sdk.device.builder;


import com.askeycloud.sdk.device.request.UpdateIoTDeviceDetailRequest;

import java.util.HashMap;

/**
 * Update IoT device detail support builder.
 */

public class UpdateIoTDeviceDetailBuilder {

    private HashMap<String, Object> defaultDetails;
    private String deviceId;

    /**
     * @param deviceId The IoT device register ID in AskeyCloud service.
     * @param defaultDetails The IoT device exist detail data. Can input null.
     */
    public UpdateIoTDeviceDetailBuilder(String deviceId, HashMap<String, Object> defaultDetails){
        this.defaultDetails = defaultDetails;
        if(this.defaultDetails == null){
            this.defaultDetails = new HashMap<>();
        }
        this.deviceId = deviceId;
    }

    protected UpdateIoTDeviceDetailRequest genRequest(){
        UpdateIoTDeviceDetailRequest request = new UpdateIoTDeviceDetailRequest();
        request.setDeviceid(deviceId);
        if(!defaultDetails.isEmpty()){
            request.setAdditionalProperty("detail", defaultDetails);
        }
        return request;
    }

    public void addUpdateDetail(String key, String value){
        defaultDetails.put(key, value);
    }

    public void removeDetail(String key){
        defaultDetails.remove(key);
    }

    /**
     * @return
     * Generate API request class to update device detail.
     */
    public UpdateIoTDeviceDetailRequest getUpdateIoTDeviceDetailRequest() {
        return genRequest();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
