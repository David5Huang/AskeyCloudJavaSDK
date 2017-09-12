package com.askeycloud.sdk.core.test;

import com.askeycloud.sdk.core.api.BaseApiUtils;
import retrofit2.Call;

/**
 * Created by david5_huang on 2017/8/28.
 */
public class UnitTestApiUtils extends BaseApiUtils {
    public UnitTestApiUtils(String url) {
        super(url);
    }

    public String convertJsonStrTest(Object request){
        return convertRequestToJsonString(request);
    }

    @Override
    public String doApi(Call<String> response) {
        return super.doApi(response);
    }
}
