package com.askeycloud.sdk.device.test;

import com.askeycloud.sdk.core.api.ApiStatus;
import com.askeycloud.sdk.core.api.response.BasicResponse;
import com.askeycloud.sdk.device.AskeyCloudDeviceApiUtils;
import com.askeycloud.sdk.device.builder.ActiveDeviceRequestBuilder;
import com.askeycloud.sdk.device.builder.UpdateIoTDeviceDetailBuilder;
import com.askeycloud.sdk.device.response.*;
import com.askeycloud.sdk.device.test.order.Order;
import com.askeycloud.sdk.device.test.order.OrderedRunner;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by david5_huang on 2017/7/26.
 */
@RunWith(OrderedRunner.class)
public class IoTDeviceApiTest implements TestConst {

    private static String uniqueID;
    private static String deviceID;

    private static HashMap<String, Object> orgDetails;

    @BeforeClass
    public static void createUniqueID(){
        if(uniqueID == null){
            uniqueID = UUID.randomUUID().toString();
            System.out.println("UUID: "+uniqueID);
        }
    }

    @Test
    @Order(order = 100)
    public void createIoTDeviceSuccess(){

        ActiveDeviceRequestBuilder builder = new ActiveDeviceRequestBuilder(
                TestConst.TEST_DEVICE_MODEL,
                uniqueID,
                TestConst.TEST_DISPLAYNAME
        );
        builder.putDeviceDetail(TEST_DETAIL_KEY, TEST_DETAIL_VALUE);

        IoTDeviceInfoResponse response = AskeyCloudDeviceApiUtils.getInstance(TestConst.API_DEVICE_URL)
                .createIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, builder.getActiveDeviceRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(response.getDeviceid());
        deviceID = response.getDeviceid();
    }

    @Test
    @Order(order = 101)
    public void createIoTDeviceUniqueIDExist(){
        ActiveDeviceRequestBuilder builder = new ActiveDeviceRequestBuilder(
                TestConst.TEST_DEVICE_MODEL,
                uniqueID,
                TestConst.TEST_DISPLAYNAME
        );
        IoTDeviceInfoResponse response = AskeyCloudDeviceApiUtils.getInstance(TestConst.API_DEVICE_URL)
                .createIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, builder.getActiveDeviceRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(400001));
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getAddtionMessage());
    }

    @Test
    @Order(order = 200)
    public void lookupIoTDevice(){
        IoTDeviceInfoResponse infoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .lookupIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, TEST_DEVICE_MODEL, uniqueID);
        Assert.assertNotNull(infoResponse);
        Assert.assertEquals(infoResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(infoResponse.getDeviceid());
        Assert.assertEquals(infoResponse.getDeviceid(), deviceID);
    }

    @Test
    @Order(order = 201)
    public void lookupIoTDeviceWrongUniqueID(){
        String wrongId = new StringBuffer(uniqueID).reverse().toString();
        IoTDeviceInfoResponse infoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .lookupIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, TEST_DEVICE_MODEL, wrongId);
        Assert.assertNotNull(infoResponse);
        System.out.println(infoResponse.getCode());
        System.out.println(infoResponse.getMessage());
        System.out.println(infoResponse.getAddtionMessage());
    }

    @Test
    @Order(order = 202)
    public void lookupIoTDeviceNullUniqueID(){
        IoTDeviceInfoResponse infoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .lookupIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, TEST_DEVICE_MODEL, null);
        Assert.assertNotNull(infoResponse);
        Assert.assertEquals(infoResponse.getCode(), new Integer(400005));
        Assert.assertNotNull(infoResponse.getMessage());
    }

    @Test
    @Order(order = 300)
    public void getIoTDeviceDetailInfo(){
        Assert.assertNotNull(deviceID);
        IoTDeviceDetailInfoResponse detailInfoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceDetailInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID);

        Assert.assertNotNull(detailInfoResponse);
        Assert.assertEquals(detailInfoResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(detailInfoResponse.getDeviceid());
        Assert.assertNotNull(detailInfoResponse.getDetail());

        IoTDeviceDetailInfoResponse.Detail detail = detailInfoResponse.getDetail();
        orgDetails = (HashMap<String, Object>) detail.getAdditionalProperties();

        Assert.assertNotNull(detail.getAdditionalProperties().get(TEST_DETAIL_KEY));
        Assert.assertEquals(detail.getAdditionalProperties().get(TEST_DETAIL_KEY), TEST_DETAIL_VALUE);
    }

    @Test
    @Order(order = 301)
    public void getIoTDeviceDetailInfoDeviceIDNull(){
        IoTDeviceDetailInfoResponse detailInfoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceDetailInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, null);
        Assert.assertNotNull(detailInfoResponse);
        Assert.assertEquals(detailInfoResponse.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
    }

    @Test
    @Order(order = 310)
    public void updateIoTDeviceDetail(){
        Assert.assertNotNull(orgDetails);
        Assert.assertNotNull(deviceID);

        UpdateIoTDeviceDetailBuilder detailBuilder = new UpdateIoTDeviceDetailBuilder(deviceID, orgDetails);
        detailBuilder.addUpdateDetail(TEST_EXT_DETAIL_KEY, TEST_EXT_DETAIL_VALUE);
        IoTDeviceDetailInfoResponse detailInfoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .updateIoTDeviceDetailInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, detailBuilder);

        Assert.assertNotNull(detailInfoResponse);
        Assert.assertEquals(detailInfoResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(detailInfoResponse.getDetail());

        IoTDeviceDetailInfoResponse.Detail detail = detailInfoResponse.getDetail();

        Assert.assertNotNull(detail.getAdditionalProperties().get(TEST_DETAIL_KEY));
        Assert.assertEquals(detail.getAdditionalProperties().get(TEST_DETAIL_KEY), TEST_DETAIL_VALUE);

        Assert.assertNotNull(detail.getAdditionalProperties().get(TEST_EXT_DETAIL_KEY));
        Assert.assertEquals(detail.getAdditionalProperties().get(TEST_EXT_DETAIL_KEY), TEST_EXT_DETAIL_VALUE);
    }

    @Test
    @Order(order = 320)
    public void updateIoTDeviceDisplayName(){
        Assert.assertNotNull(deviceID);

        BasicResponse response = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .updateIoTDeviceDisplayInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID, TEST_NEW_DISPLAYNAME);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_SUCCESS));
    }

    @Test
    @Order(order = 321)
    public void updateIoTDeviceDisplayDeviceIDNull(){
        BasicResponse response = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .updateIoTDeviceDisplayInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, null, TEST_NEW_DISPLAYNAME);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
    }

    @Test
    @Order(order = 322)
    public void updateIoTDeviceDisplayNewNameNull(){
        BasicResponse response = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .updateIoTDeviceDisplayInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID, null);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(400005));
    }

    @Test
    @Order(order = 400)
    public void getIoTDeviceDisplay(){
        Assert.assertNotNull(deviceID);
        Assert.assertNotNull(uniqueID);
        IoTDeviceDisplayInfoResponse displayInfoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceDisplayInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID);

        Assert.assertNotNull(displayInfoResponse);
        Assert.assertEquals(displayInfoResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));

        Assert.assertNotNull(displayInfoResponse.getModel());
        Assert.assertNotNull(displayInfoResponse.getDisplayname());
        Assert.assertNotNull(displayInfoResponse.getUniqueid());

        Assert.assertEquals(displayInfoResponse.getModel(), TEST_DEVICE_MODEL);
        Assert.assertEquals(displayInfoResponse.getUniqueid(), uniqueID);
        Assert.assertEquals(displayInfoResponse.getDisplayname(), TEST_NEW_DISPLAYNAME);
    }

    @Test
    @Order(order = 401)
    public void getIoTDeviceDisplayDeviceIDNull(){
        IoTDeviceDisplayInfoResponse displayInfoResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceDisplayInfo(TEST_API_KEY, TEST_ACCESS_TOKEN, null);

        Assert.assertNotNull(displayInfoResponse);
        Assert.assertEquals(displayInfoResponse.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
    }

    @Test
    @Order(order = 500)
    public void getIoTDeviceEndpoint(){
        Assert.assertNotNull(deviceID);
        IoTDeviceEndpointResponse endpointResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceEndpoint(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID);

        Assert.assertNotNull(endpointResponse);
        Assert.assertEquals(endpointResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(endpointResponse.getDeviceid());
        Assert.assertNotNull(endpointResponse.getIotThingname());
        Assert.assertNotNull(endpointResponse.getRestEndpoint());
        Assert.assertNotNull(endpointResponse.getShadowTopic());

        System.out.println(endpointResponse.getIotThingname());
    }

    @Test
    @Order(order = 501)
    public void getIoTDeviceEndpointDeviceIDNull(){
        IoTDeviceEndpointResponse endpointResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .getIoTDeviceEndpoint(TEST_API_KEY, TEST_ACCESS_TOKEN, null);

        Assert.assertNotNull(endpointResponse);
        Assert.assertEquals(endpointResponse.getCode(), new Integer(ApiStatus.API_JAVA_EXCEPTION));
        Assert.assertNotNull(endpointResponse.getMessage());
    }

    @Test
    @Order(order = 600)
    public void listUserIoTDevice(){
        UserIoTDeviceListResponse listResponse = AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .userIoTDeviceList(TEST_API_KEY, TEST_ACCESS_TOKEN);

        Assert.assertNotNull(listResponse);
        Assert.assertEquals(listResponse.getCode(), new Integer(ApiStatus.API_SUCCESS));
        Assert.assertNotNull(listResponse.getDevices());
        Assert.assertEquals(listResponse.getDevices().size() > 0, true);
    }

    @Test
    @Order(order = 900)
    public void deleteIoTDeviceWrongDeviceID(){
        BasicResponse response =  AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .removeIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, uniqueID);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(400006));
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getAddtionMessage());
        Assert.assertEquals(response.getAddtionMessage().length() > 0, true);
    }

    @Test
    @Order(order = 901)
    public void deleteIoTDevice(){
        Assert.assertNotNull(deviceID);
        BasicResponse response =  AskeyCloudDeviceApiUtils.getInstance(API_DEVICE_URL)
                .removeIoTDevice(TEST_API_KEY, TEST_ACCESS_TOKEN, deviceID);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCode(), new Integer(ApiStatus.API_SUCCESS));
    }
}
