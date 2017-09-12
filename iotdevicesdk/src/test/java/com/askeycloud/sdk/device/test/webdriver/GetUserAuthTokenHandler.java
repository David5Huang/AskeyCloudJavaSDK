package com.askeycloud.sdk.device.test.webdriver;

import com.askeycloud.sdk.auth.AskeyCloudAuthApiUtils;
import com.askeycloud.sdk.auth.response.OAuthProviderResponse;
import com.askeycloud.sdk.device.test.TestConst;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by david5_huang on 2017/7/28.
 */
public class GetUserAuthTokenHandler {
    private static ChromeDriverService chromeDriverService;
    private WebDriver driver;

    @BeforeClass
    public static void createWebPageService() throws IOException {
        chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("chromedriver"))
                .usingAnyFreePort()
                .build();
        chromeDriverService.start();
    }

    public void createDriver(String url) throws MalformedURLException {
        URL serviceUrl = new URL(url);
        driver = new RemoteWebDriver(chromeDriverService.getUrl(), DesiredCapabilities.chrome());

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        AuthEventHandler eventHandler = new AuthEventHandler();
        eventFiringWebDriver.register(eventHandler);


        eventFiringWebDriver.get(url);
//        driver.navigate().to(serviceUrl);

        while (true){

        }
    }

    public void getAuthToken(){
        OAuthProviderResponse response =
                AskeyCloudAuthApiUtils.getInstance(TestConst.API_OAUTH_URL)
                        .authProvider(
                                TestConst.TEST_API_KEY,
                                TestConst.TEST_APPLICATION_ID,
                                TestConst.ACCOUNT_PROVIDER,
                                null);

        try {
            createDriver(response.getUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
