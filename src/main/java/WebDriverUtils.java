import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 6:53 PM
 */


public class WebDriverUtils {

    private static final String TEST = "/Users/caohao/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs";

    private static final String PROD = "/root/jipiao/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";

//    private static WebDriver instance =  getWebDriver();

    public static WebDriver getInstance() {
        return getWebDriver();
    }

    private static WebDriver getWebDriver(){
        System.setProperty("phantomjs.binary.path", PROD);
        //创建无界面浏览器对象
        DesiredCapabilities dcaps=new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        dcaps.setCapability("phantomjs.page.settings.XSSAuditingEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.webSecurityEnabled",false);
        dcaps.setCapability("phantomjs.page.settings.localToRemoteUrlAccessEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.XSSAuditingEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.loadImages",false);
        //js支持
        dcaps.setJavascriptEnabled(true);
        return new PhantomJSDriver(dcaps);
    }





}
