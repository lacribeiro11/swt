package fhcampuswien.ac.at.stepdefinitions;

import org.apache.commons.lang3.SystemUtils;

/**
 * This class is responsible to select the correct chromedrive for the specific OS
 */
final class BrowserUtil {
    private BrowserUtil() {
    }

    static void SetDriverProperties() {
        final String pathTemplate = "src/test/resources/%s/chromedriver";
        String chromeDriverPath;
        if (SystemUtils.IS_OS_WINDOWS) {
            chromeDriverPath = String.format(pathTemplate, "windows");
        } else if (SystemUtils.IS_OS_MAC) {
            chromeDriverPath = String.format(pathTemplate, "mac");
        } else {
            // We believe, that there are only these 3 operating systems.
            chromeDriverPath = String.format(pathTemplate, "linux");
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }
}
