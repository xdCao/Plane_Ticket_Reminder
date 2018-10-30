import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 6:36 PM
 */


public class Main {


    public static void main(String[] args) throws ParseException, SchedulerException, IOException {


            Scheduler sche=StdSchedulerFactory.getDefaultScheduler();


            JobDetail job = newJob(TicketJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            CronTrigger build = newTrigger()
                    .withIdentity("trigger1")
                    .forJob(job)
                    .withSchedule(CronScheduleBuilder.cronSchedule(PropUtil.getInstance().getProperty("cron")))
                    .startNow()
                    .build();

            sche.scheduleJob(job, build);

            sche.start();

            System.in.read();

            sche.shutdown();
    }
}