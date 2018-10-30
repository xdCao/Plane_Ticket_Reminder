import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.MessagingException;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 7:43 PM
 */


public class TicketJob implements Job {

    public TicketJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Properties pro = PropUtil.getInstance();

        String date = pro.getProperty("date");
        String[] split = date.split(",");
        String src = pro.getProperty("src");
        String des = pro.getProperty("des");


        List<Ticket> dateList = new ArrayList<Ticket>();
        List<Ticket> results = new ArrayList<Ticket>();
        for (int i = 0; i < split.length; i++) {
            dateList.add(new Ticket(src, des, split[i]));
        }



        CountDownLatch latch = new CountDownLatch(dateList.size());


        for (int i = 0; i < dateList.size(); i++) {
            Thread thread = new Thread(new TicketFetcher(latch,dateList.get(i),results));
            thread.run();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = "";

        for (Ticket ticket:results) {
            result = result + JSON.toJSONString(ticket)+"\r\n";
        }

        try {
            MailUtil.send_mail("xd_caohao@aliyun.com", result,"smtp.163.com",465,"xdcao011291@163.com","705083979123");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
