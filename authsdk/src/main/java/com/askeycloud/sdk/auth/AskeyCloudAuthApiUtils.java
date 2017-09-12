package com.askeycloud.sdk.auth;

import com.askeycloud.sdk.auth.response.DeviceOAuthCodeResponse;
import com.askeycloud.sdk.auth.response.OAuthProvider;
import com.askeycloud.sdk.auth.response.OAuthProviderResponse;
import com.askeycloud.sdk.core.api.BaseApiUtils;
import retrofit2.Call;


/**
 * Created by david5_huang on 2016/12/12.
 */
public class AskeyCloudAuthApiUtils extends BaseApiUtils {

    private static AskeyCloudAuthApiUtils instance;
    private static AskeyCloudAuthService authService;

    AskeyCloudAuthApiUtils(String url) {
        super(url);
    }

    public static AskeyCloudAuthApiUtils getInstance(String url){
        if(instance == null || url == null || !instance.getUrl().equals(url)){
            instance = new AskeyCloudAuthApiUtils(url);
            authService = instance.createCloudService(AskeyCloudAuthService.class);
        }

        return instance;
    }

    public DeviceOAuthCodeResponse getDeviceOAuthCodeUri(String header, String appId, String uniqueId, String model){
        Call<String> response = authService.getDeviceOAuthCodeUri(header, appId, uniqueId, model);
        return parseJson(doApi(response), DeviceOAuthCodeResponse.class);
    }

    public OAuthProvider[] getDeviceOAuthProviders(String url, String header, String appId){
        Call<String> response = authService.getOAuthProviders(url, header, appId);
        return parseJson(doApi(response), OAuthProvider[].class);
    }

    public OAuthProviderResponse authProvider(String authHeader, String appId, String provider, String state){
        Call<String> response = authService.authProvider(authHeader, appId, provider, state);
        String apiResponse = doApi(response);
        return parseJson(apiResponse, OAuthProviderResponse.class);
    }

}
