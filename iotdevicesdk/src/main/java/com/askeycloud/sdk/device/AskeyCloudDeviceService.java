package com.askeycloud.sdk.device;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by david5_huang on 2017/7/25.
 */
public interface AskeyCloudDeviceService {
    @GET("device/{deviceid}/detail")
    Call<String> getDeviceDetailInfo(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId);

    @POST("device/{deviceid}/detail")
    Call<String> updateDeviceDetailInfo(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId, @Body String request);

    @GET("device/iotcert")
    Call<String> getIoTCert(@HeaderMap Map<String, String> headers);

    @PUT("device/create")
    Call<String> createIoTDevice(@HeaderMap Map<String, String> headers, @Body String request);


    @GET("device/list")
    Call<String> userIoTDeviceList(@HeaderMap Map<String, String> headers);


    @GET("device/lookup")
    Call<String> lookupIoTDeviceInfo(@HeaderMap Map<String, String> headers, @Query("model") String model, @Query("uniqueid") String uniqueId);

    @POST("device/{deviceid}/update")
    Call<String> updateIoTDeviceName(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId, @Body String request);

    @POST("device/{deviceid}/shadow")
    Call<String> updateIoTThingShadow(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId, @Body String request);

    @GET("device/{deviceid}/shadow")
    Call<String> getIoTThingShadow(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId);

    @GET("device/{deviceid}/endpoint")
    Call<String> getIoTDeviceEndpoint(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId);

    @GET("device/{deviceid}/basic")
    Call<String> getIoTDeviceDisplayInfo(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId);

    @DELETE("device/{deviceid}/remove")
    Call<String> removeIoTDevice(@HeaderMap Map<String, String> headers, @Path("deviceid") String deviceId);
}
