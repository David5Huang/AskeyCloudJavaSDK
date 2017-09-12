package com.askeycloud.sdk.device.builder;

import com.askeycloud.sdk.device.request.CreateIoTDeviceRequest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create IoT device support builder.<br>
 * Don't manual create device, if authorize via device oauth.
 */
public class ActiveDeviceRequestBuilder  {

    protected CreateIoTDeviceRequest createIoTDeviceRequest;
    protected HashMap<String, String> deviceDetails;

    /**
     * @param model The IoT device model name by every project.
     * @param uniqueId The IoT device unique ID to register at AskeyCloud service.
     * @param displayName The IoT device display.
     */
    public ActiveDeviceRequestBuilder(
            String model,
            String uniqueId,
            String displayName){
        createIoTDeviceRequest = new CreateIoTDeviceRequest();
        CreateIoTDeviceRequest.Device device = genDevice(model, uniqueId, displayName);
        CreateIoTDeviceRequest.Detail detail = new CreateIoTDeviceRequest().new Detail();
        device.setDetail(detail);
        createIoTDeviceRequest.setDevice(device);
    }

    private CreateIoTDeviceRequest.Device genDevice(String model,
                                                 String uniqueid,
                                                 String displayName){
        CreateIoTDeviceRequest.Device device = new CreateIoTDeviceRequest().new Device();
        device.setModel(model);
        device.setUniqueid(uniqueid);
        device.setDisplayname(displayName);
        return device;
    }

    public void putDeviceDetail(String key, String value){
        if(deviceDetails == null){
            deviceDetails = new HashMap<>();
        }
        deviceDetails.put(key, value);
    }

    private void setDeviceDetail(HashMap<String, String> details){
        ArrayList<String> keys = new ArrayList<>(details.keySet());
        for(int i=0;i<keys.size();i++){
            createIoTDeviceRequest.getDevice().getDetail().setAdditionalProperty(keys.get(i), details.get(keys.get(i)));
        }
    }

    public CreateIoTDeviceRequest getActiveDeviceRequest() {
        if(deviceDetails != null){
            setDeviceDetail(deviceDetails);
        }
        return createIoTDeviceRequest;
    }

}
