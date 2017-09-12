package com.askeycloud.sdk.auth.test;

import com.askeycloud.sdk.auth.AskeyCloudAuthApiUtils;
import com.askeycloud.sdk.auth.response.OAuthProviderResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by david5_huang on 2017/6/6.
 */
public class AuthServiceTest {

    @Test
    public void testAuthProviderSuccess(){
        OAuthProviderResponse response =
                AskeyCloudAuthApiUtils.getInstance(TestConst.API_OAUTH_URL)
                        .authProvider(
                                TestConst.TEST_API_KEY,
                                TestConst.TEST_APPLICATION_ID,
                                TestConst.ACCOUNT_PROVIDER,
                                null);

        Assert.assertNotNull(response);

        System.out.print("Output uri: "+response.getUri());

        Assert.assertNotNull("Provider uri", response.getUri());
    }

    @Test
    public void testAuthProviderUrlError(){

        OAuthProviderResponse response =
                AskeyCloudAuthApiUtils.getInstance(TestConst.API_OAUTH_ERR_URL)
                        .authProvider(
                                TestConst.TEST_API_KEY,
                                TestConst.TEST_APPLICATION_ID,
                                TestConst.ACCOUNT_PROVIDER,
                                null);

        Assert.assertNotNull(response);
        Assert.assertNotNull("status", response.getCode());
        Assert.assertNotNull("message", response.getMessage());

        System.out.println("status: "+response.getCode());
        System.out.println("message: "+response.getMessage());
    }

}
