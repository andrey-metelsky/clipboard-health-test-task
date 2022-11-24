package com.clipboardhealth.automation.client;

import static com.clipboardhealth.automation.environment.ConfigurationManager.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    public static final int TIME_WAIT_SECONDS = 30;
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static String browser = configuration().browser();
    private static DriverFactory instance = new DriverFactory();

    ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() {

        @Override
        protected RemoteWebDriver initialValue() {
            Target target = Target.valueOf(configuration().target().toUpperCase());
            RemoteWebDriver webDriver;

            switch (target) {
                case LOCAL:
                    webDriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
                    break;
                case REMOTE:
                    webDriver = createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return webDriver;
        }

        private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
            RemoteWebDriver remoteWebDriver = null;
            try {
                String gridURL = String.format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());

                remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
            } catch (java.net.MalformedURLException e) {
                logger.error("Grid URL is invalid or Grid is not available");
                logger.error(String.format("Browser: %s", capability.getBrowserName()), e);
            } catch (IllegalArgumentException e) {
                logger.error(String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
            }

            return remoteWebDriver;
        }
    };

    enum Target {
        LOCAL, REMOTE
    }

    public static DriverFactory getInstance() {
        return instance;
    }

    public RemoteWebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}

