package com.askeycloud.sdk.auth.test;

import com.askeycloud.sdk.auth.AskeyCloudAuthApiUtils;
import com.askeycloud.sdk.auth.response.DeviceOAuthCodeResponse;
import com.askeycloud.sdk.auth.response.OAuthProvider;
import com.askeycloud.sdk.auth.test.order.Order;
import com.askeycloud.sdk.auth.test.order.OrderedRunner;
import com.askeycloud.sdk.core.api.ApiStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by david5_huang on 2017/8/24.
 */
@RunWith(OrderedRunner.class)
public class DeviceAuthTest implements TestConst {

    @Test()
    @Order(order = 100)
    public void testGetDeviceOAuthCodeUri(){
        DeviceOAuthCodeResponse deviceOAuthCodeResponse = AskeyCloudAuthApiUtils
                .getInstance(API_OAUTH_URL)
                .getDeviceOAuthCodeUri(
                        TEST_DEVICE_API_KEY,
                        TEST_DEVICE_APPID,
                        TEST_DEVICE_UNIQUEID,
                        TEST_DEVICE_MODEL);

        Assert.assertNotNull(deviceOAuthCodeResponse);
        Assert.assertEquals(deviceOAuthCodeResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));

        Assert.assertNotNull(deviceOAuthCodeResponse.getRestConfirmUri());
        System.out.println(deviceOAuthCodeResponse.getRestConfirmUri());

        Assert.assertNotNull(deviceOAuthCodeResponse.getDeviceTokenUri());
        System.out.println(deviceOAuthCodeResponse.getDeviceTokenUri());

        Assert.assertNotNull(deviceOAuthCodeResponse.getExpiresIn());
        Assert.assertNotNull(deviceOAuthCodeResponse.getInterval());

        testGetDeviceOAuthProviders(deviceOAuthCodeResponse.getRestConfirmUri());
    }

    public void testGetDeviceOAuthProviders(String confirmUri){

        OAuthProvider[] providers = AskeyCloudAuthApiUtils
                .getInstance(API_OAUTH_URL)
                .getDeviceOAuthProviders(confirmUri, TEST_DEVICE_API_KEY, TEST_DEVICE_APPID);


        Assert.assertNotNull(providers);
        Assert.assertEquals(true, providers.length > 0);
        for(int i=0;i<providers.length;i++){
            OAuthProvider provider = providers[i];
            Assert.assertNotNull(provider.getProvider());
            System.out.println(provider.getProvider());

            Assert.assertNotNull(provider.getAuthorizeUri());
            System.out.println(provider.getAuthorizeUri());
        }
    }
}
