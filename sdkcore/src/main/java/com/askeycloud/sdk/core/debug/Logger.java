package com.askeycloud.sdk.core.debug;

/**
 * Created by david5_huang on 2017/6/6.
 */
public interface Logger {

    public static final String TAG = "askeycloudsdk";

    abstract public void v(String tag, String msg);
    abstract public void d(String tag, String msg);
    abstract public void i(String tag, String msg);
    abstract public void e(String tag, String msg);
    abstract public void w(String tag, String msg);

}
