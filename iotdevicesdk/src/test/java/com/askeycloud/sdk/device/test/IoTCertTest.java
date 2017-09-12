package com.askeycloud.sdk.device.test;

import com.askeycloud.sdk.core.api.ApiStatus;
import com.askeycloud.sdk.device.AskeyCloudDeviceApiUtils;
import com.askeycloud.sdk.device.response.AWSIoTCertResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by david5_huang on 2017/7/25.
 */
public class IoTCertTest {

    @Test
    public void getIoTCertSuccess(){
        AWSIoTCertResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .awsIoTCert(TestConst.TEST_API_KEY, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull("IoT cert response", response);
        Assert.assertNotNull("pk", response.getPrivateKey());
        Assert.assertNotNull("cert", response.getCertificatePem());

        System.out.println("pk: "+response.getPrivateKey());
        System.out.println("cert: "+response.getCertificatePem());
    }

    @Test
    public void getIoTCertTokenError(){
        AWSIoTCertResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .awsIoTCert(TestConst.TEST_API_KEY, TestConst.TEST_REFRESH_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(403));
        Assert.assertNotNull("message", response.getMessage());
    }

    @Test
    public void getIoTCertNullApiKey(){
        AWSIoTCertResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .awsIoTCert(null, TestConst.TEST_ACCESS_TOKEN);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
        Assert.assertNotNull("message", response.getMessage());
    }

    @Test
    public void getIoTCertNullToken(){
        AWSIoTCertResponse response = AskeyCloudDeviceApiUtils
                .getInstance(TestConst.API_DEVICE_URL)
                .awsIoTCert(TestConst.TEST_API_KEY, null);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(403));
        Assert.assertNotNull("message", response.getMessage());
    }
}
