package com.askeycloud.sdk.device.test;

import com.askeycloud.sdk.core.api.ApiStatus;
import com.askeycloud.sdk.device.AskeyCloudDeviceApiUtils;
import com.askeycloud.sdk.device.response.UserIoTDeviceListResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by david5_huang on 2017/7/25.
 */
public class UserIoTDeviceListTest {

    @Test
    public void userIoTDeviceListSuccess(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .userIoTDeviceList(TestConst.TEST_API_KEY, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(response.getDevices());
    }

    @Test
    public void userIoTDeviceListNullApiKey(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .userIoTDeviceList(null, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
        Assert.assertNotNull(response.getMessage());
    }

    @Test
    public void userIoTDeviceListNullToken(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .userIoTDeviceList(TestConst.TEST_API_KEY, null);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(403));
        Assert.assertNotNull(response.getMessage());
    }

    @Test
    public void userIoTDeviceListApiOutVersion(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_EARLY_VERSION_DEVICE_URL)
                .userIoTDeviceList(TestConst.TEST_API_KEY, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(400005));
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getAddtionMessage());
    }

    @Test
    public void userIoTDeviceListWrongToken(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .userIoTDeviceList(TestConst.TEST_API_KEY, TestConst.TEST_REFRESH_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(403));
        Assert.assertNotNull(response.getMessage());
    }

    @Test
    public void userIoTDeviceListWrongApiUrl(){
        UserIoTDeviceListResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .userIoTDeviceList(TestConst.TEST_API_KEY, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCode());
    }
}
