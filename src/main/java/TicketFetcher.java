import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 6:51 PM
 */


public class TicketFetcher implements Runnable{

    private Ticket ticket;
    private CountDownLatch latch;
    private List<Ticket> results;

    public TicketFetcher(CountDownLatch latch, Ticket ticket, List<Ticket> results) {
        this.ticket = ticket;
        this.latch = latch;
        this.results = results;
    }

    public void run() {

        try {
            WebDriver webDriver = WebDriverUtils.getInstance();

            webDriver.manage().window().setSize(new Dimension(1920,1080));
            //设置隐性等待（作用于全局）

            webDriver.get("http://www.ctrip.com");
            Thread.sleep(2000);
            webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            webDriver.findElement(By.xpath(".//*[@id='searchBoxUl']/li[2]/b")).click();

            WebElement startCity = webDriver.findElement(By.xpath(".//*[@id='FD_StartCity']"));
            Thread.sleep(5000);
            startCity.click();
            startCity.clear();
            startCity.sendKeys(ticket.getSrc());
            WebElement desCity = webDriver.findElement(By.xpath(".//*[@id='FD_DestCity']"));
            desCity.sendKeys(ticket.getDes());
            Thread.sleep(5000);
            WebElement startTime=webDriver.findElement(By.xpath(".//*[@id='FD_StartDate']"));
            //WebElement endTime=webDriver.findElement(By.xpath(".//*[@id='FD_ReturnDate']"));
            //startCity.click();
            startTime.sendKeys(ticket.getDate());
            //desCity.click();
            Thread.sleep(5000);
            webDriver.findElement(By.xpath(".//*[@id='FD_StartSearch']")).click();
            Thread.sleep(5000);
            WebElement element = webDriver.findElement(By.xpath(".//*[@id='base_bd']/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div/div[7]/div/span"));
            String price = element.getText();
            webDriver.quit();
            ticket.setPrice(price);
            results.add(ticket);
            latch.countDown();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
