package com.askeycloud.sdk.auth;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by david5_huang on 2017/6/6.
 */
public interface AskeyCloudAuthService {

    @GET("device/code")
    Call<String> getDeviceOAuthCodeUri(@Header("x-api-key") String header,
                                       @Query("appclientid") String appId,
                                       @Query("uniqueid") String uniqueId,
                                       @Query("model") String model);

    @GET()
    Call<String> getOAuthProviders(@Url String url,
                                   @Header("x-api-key") String header,
                                   @Query("appclientid") String appId);

    @GET("authprovider/uri")
    Call<String> authProvider(@Header("x-api-key") String header,
                              @Query("appclientid") String appId,
                              @Query("provider") String provider,
                              @Query("state") String state);

}
