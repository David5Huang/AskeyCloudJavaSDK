package com.askeycloud.sdk.device;

import com.askeycloud.sdk.core.api.BaseApiUtils;
import com.askeycloud.sdk.core.api.response.BasicResponse;
import com.askeycloud.sdk.device.builder.UpdateIoTDeviceDetailBuilder;
import com.askeycloud.sdk.device.request.CreateIoTDeviceRequest;
import com.askeycloud.sdk.device.request.UpdateIoTDeviceDisplayRequest;
import com.askeycloud.sdk.device.response.*;

import retrofit2.Call;

/**
 * Created by david5_huang on 2017/7/25.
 */
public class AskeyCloudDeviceApiUtils extends BaseApiUtils {

    private static AskeyCloudDeviceApiUtils instance;
    private static AskeyCloudDeviceService deviceService;

    public static AskeyCloudDeviceApiUtils getInstance(String url){

        if(instance == null || url == null || !instance.getUrl().equals(url)){
            instance = new AskeyCloudDeviceApiUtils(url);
            deviceService = instance.createCloudService(AskeyCloudDeviceService.class);
        }

        return instance;
    }

    AskeyCloudDeviceApiUtils(String url) {
        super(url);
    }

    public AWSIoTCertResponse awsIoTCert(String apiKey, String token){
        Call<String> response = deviceService.getIoTCert(createApiHeaders(apiKey, token));
        return parseJson(doApi(response), AWSIoTCertResponse.class);
    }

    public IoTDeviceInfoResponse createIoTDevice(String apiKey, String token, CreateIoTDeviceRequest request){
        Call<String> response = deviceService.createIoTDevice(
                createApiHeaders(apiKey, token),
                convertRequestToJsonString(request));
        return parseJson(doApi(response), IoTDeviceInfoResponse.class);
    }

    public IoTDeviceInfoResponse lookupIoTDevice(String apiKey, String token, String model, String uniqueId){
        Call<String> response = deviceService.lookupIoTDeviceInfo(
                createApiHeaders(apiKey, token),
                model,
                uniqueId);
        return parseJson(doApi(response), IoTDeviceInfoResponse.class);
    }

    public UserIoTDeviceListResponse userIoTDeviceList(String apiKey, String token){

        Call<String> response = deviceService.userIoTDeviceList(createApiHeaders(apiKey, token));
        return parseJson(doApi(response), UserIoTDeviceListResponse.class);
    }

    public IoTDeviceDetailInfoResponse getIoTDeviceDetailInfo(String apiKey, String token, String deviceId){
        Call<String> response = deviceService.getDeviceDetailInfo(createApiHeaders(apiKey, token), deviceId);
        return parseJson(doApi(response), IoTDeviceDetailInfoResponse.class);
    }

    public IoTDeviceDetailInfoResponse updateIoTDeviceDetailInfo(String apiKey, String token, UpdateIoTDeviceDetailBuilder builder){
        Call<String> response = deviceService.updateDeviceDetailInfo(createApiHeaders(apiKey, token), builder.getDeviceId(), convertRequestToJsonString(builder.getUpdateIoTDeviceDetailRequest()));
        return parseJson(doApi(response), IoTDeviceDetailInfoResponse.class);
    }

    public BasicResponse updateIoTDeviceDisplayInfo(String apiKey, String token, String deviceId, String displayName){
        UpdateIoTDeviceDisplayRequest request = new UpdateIoTDeviceDisplayRequest();
        request.setDisplayname(displayName);
        Call<String> response = deviceService.updateIoTDeviceName(
                createApiHeaders(apiKey, token),
                deviceId,
                convertRequestToJsonString(request));
        return parseJson(doApi(response), BasicResponse.class);
    }

    public BasicResponse removeIoTDevice(String apiKey, String token, String deviceId){

        Call<String> response = deviceService.removeIoTDevice(
                createApiHeaders(apiKey, token),
                deviceId
        );
        return parseJson(doApi(response), BasicResponse.class);
    }

    public IoTDeviceDisplayInfoResponse getIoTDeviceDisplayInfo(String apiKey, String token, String deviceId){
        Call<String> response = deviceService.getIoTDeviceDisplayInfo(
                createApiHeaders(apiKey, token),
                deviceId
        );
        return parseJson(doApi(response), IoTDeviceDisplayInfoResponse.class);
    }

    public IoTDeviceEndpointResponse getIoTDeviceEndpoint(String apiKey, String token, String deviceId){
        Call<String> response = deviceService.getIoTDeviceEndpoint(createApiHeaders(apiKey, token), deviceId);
        return parseJson(doApi(response), IoTDeviceEndpointResponse.class);
    }

    public String updateIoTThingShadow(String apiKey, String token, String deviceId, String updateStates){
        Call<String> response = deviceService.updateIoTThingShadow(
                createApiHeaders(apiKey, token),
                deviceId,
                updateStates
        );

        String result = doApi(response);
        return result==null?"{}":result;
    }

    public String getIoTThingShadow(String apiKey, String token, String deviceId){
        Call<String> response = deviceService.getIoTThingShadow(
                createApiHeaders(apiKey, token),
                deviceId
        );

        String result = doApi(response);
        return result==null?"{}":result;
    }
}
