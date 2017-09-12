package com.askeycloud.sdk.core.test;

import com.askeycloud.sdk.core.api.StringConverter;
import com.askeycloud.sdk.core.api.response.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by david5_huang on 2017/8/28.
 */
public class BaseApiUtilsTest {

    @Test
    public void convertJsonStringTest(){
        UnitTestApiUtils apiUtils = new UnitTestApiUtils(TestConst.TEST_URL);
        BasicResponse response = new BasicResponse();
        response.setCode(200);
        response.setMessage("Unit test success response");

        String jsonStr = apiUtils.convertJsonStrTest(response);

        Assert.assertNotNull("Json String", jsonStr);
        System.out.println(jsonStr);
    }

    @Test
    public void convertJsonStringUnSupportType(){
        UnitTestApiUtils apiUtils = new UnitTestApiUtils(TestConst.TEST_URL);

        String jsonStr = apiUtils.convertJsonStrTest(new StringConverter());
        Assert.assertEquals(true, jsonStr == null);
    }

    @Test
    public void convertJsonStringInsertNull(){
        UnitTestApiUtils apiUtils = new UnitTestApiUtils(TestConst.TEST_URL);
        String jsonStr = apiUtils.convertJsonStrTest(null);
        Assert.assertEquals(true, jsonStr == null);
    }

    @Test
    public void doApiSuccessTest(){
        UnitTestApiUtils apiUtils = new UnitTestApiUtils(TestConst.TEST_URL);

        Call<String> response = mock(Call.class);
        try {
            when(response.execute()).then(MockResponse.getSuccessMockResponse(TestConst.SUCCESS_MSG));
            String result = apiUtils.doApi(response);
            Assert.assertEquals(result, TestConst.SUCCESS_MSG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doApiErrTest(){
        UnitTestApiUtils apiUtils = new UnitTestApiUtils(TestConst.TEST_URL);

        Call<String> repsonse = mock(Call.class);
        try {
            when(repsonse.execute())
                    .then(MockResponse.getErrorMockResponse(
                            TestConst.CODE_403,
                            TestConst.ERR_FORBIDDEN));
            String result = apiUtils.doApi(repsonse);

            Assert.assertNotNull("Error message result", result);

            ObjectMapper mapper = new ObjectMapper();
            BasicResponse basicResponse = mapper.readValue(result, BasicResponse.class);

            Assert.assertNotNull("Error response json object", basicResponse);
            Assert.assertEquals("Mock error status", basicResponse.getCode(), new Integer(TestConst.CODE_403));
            Assert.assertEquals("Mock error message", basicResponse.getMessage(), TestConst.ERR_FORBIDDEN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
