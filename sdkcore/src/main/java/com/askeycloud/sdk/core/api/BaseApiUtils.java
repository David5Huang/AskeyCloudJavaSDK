package com.askeycloud.sdk.core.api;
import com.askeycloud.sdk.core.debug.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by david5_huang on 2016/7/27.
 */
abstract public class BaseApiUtils {

    protected final String HEADER_API_KEY = "x-api-key";
    protected final String HEADER_AUTHORIZATION = "Authorization";
    protected final String HEADER_CONTENT_TYPE = "Content-type";
    protected final String API_HEADER_CONTENT_TYPE = "application/json; charset=utf-8";

    protected String url;

    protected Retrofit retrofit;

    protected ObjectMapper mapper;
    protected Logger logger;

    public BaseApiUtils(String url){
        this.url = url;

        OkHttpClient okHttpClient = configHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new StringConverter())
                .client(okHttpClient)
                .build();

        mapper = new ObjectMapper();
    }

    protected String convertRequestToJsonString(Object request) {
        String jsonString = null;
        try {
            if(request == null){
                return null;
            }
            jsonString = mapper.writeValueAsString(request);
            if(logger != null){
                logger.d(Logger.TAG, "request str: "+jsonString);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    protected String doApi(Call<String> response) {
        try {
            Response<String> apiResponse = response.execute();
            int status = apiResponse.code();
            if(status == 200){
                String rtnResponse = apiResponse.body();
                if(logger != null){
                    logger.d(Logger.TAG, "response str: "+rtnResponse);
                }
                return rtnResponse;
            }
            else{
                if(logger != null){
                    logger.e(Logger.TAG, "err status: "+status);
                }
                return handleHttpResponseStr(apiResponse.code(), apiResponse.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return handleHttpResponseStr(ApiStatus.API_JAVA_EXCEPTION, e.getMessage());
        } catch (IllegalArgumentException ie){
            ie.printStackTrace();
            return handleHttpResponseStr(ApiStatus.API_JAVA_EXCEPTION, ie.getMessage());
        }
    }

    protected <T> T parseJson(String jsonStr, Class<T> object){
        if(jsonStr == null){
            return null;
        }
        try {
            T result = mapper.readValue(jsonStr, object);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    protected <T> T createCloudService(Class<T> service){
        T cloudService = retrofit.create(service);
        return cloudService;
    }

    private OkHttpClient configHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        return okHttpClient;
    }

    protected Map<String, String> createApiHeaders(String apiKey, String token){
        Map<String, String> headers = new HashMap<>();
        headers.put(HEADER_API_KEY, apiKey);
        headers.put(HEADER_AUTHORIZATION, createV3AuthHeader(token));
        headers.put(HEADER_CONTENT_TYPE, API_HEADER_CONTENT_TYPE);
        return headers;
    }

    private String createV3AuthHeader(String authToken){
        StringBuilder builder = new StringBuilder();
        builder.append("Bearer version=3")
                .append("&tstamp=")
                .append(getUTCTimestamp())
                .append("&access_token=")
                .append(authToken);

        return builder.toString();
    }

    protected String handleHttpResponseStr(int statusCode, String errMsg){

        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("code", statusCode);
        objectNode.put("message", errMsg);

        return objectNode.toString();
    }

    private String getUTCTimestamp(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return Long.toString(calendar.getTimeInMillis()/1000L);
    }

    public String getUrl() {
        return url;
    }
}
