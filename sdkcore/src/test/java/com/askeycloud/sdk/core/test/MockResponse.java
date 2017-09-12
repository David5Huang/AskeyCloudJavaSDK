package com.askeycloud.sdk.core.test;

import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import retrofit2.Response;

/**
 * Created by david5_huang on 2017/8/30.
 */
public class MockResponse {

    public static Answer<Response<String>> getSuccessMockResponse(final String successMsg){
        Answer<Response<String>> answer = new Answer<Response<String>>() {
            @Override
            public Response<String> answer(InvocationOnMock invocation) throws Throwable {
                return Response.success(successMsg);
            }
        };
        return answer;
    }

    public static Answer<Response<String>> getErrorMockResponse(final int code, final String errMsg){
        Answer<Response<String>> answer = new Answer<Response<String>>() {
            @Override
            public Response<String> answer(InvocationOnMock invocation) throws Throwable {
                ResponseBody errBody = ResponseBody.create(null, errMsg);
                okhttp3.Response errRsp = new okhttp3.Response.Builder()
                        .code(code)
                        .message(errMsg)
                        .protocol(Protocol.HTTP_1_1)
                        .request(new Request.Builder().url("http://localhost/").build())
                        .build();
                return Response.error(errBody, errRsp);
            }
        };
        return answer;
    }

}
