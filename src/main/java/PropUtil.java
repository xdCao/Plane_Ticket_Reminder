import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 8:38 PM
 */


public class PropUtil {

    private static Properties pro = getPro();

    private static Properties getPro() {
        Properties pro = new Properties();

        try {
            pro.load(new InputStreamReader(new FileInputStream("ticket.properties"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pro;
    }

    public static Properties getInstance(){
        return pro;
    }

    public static void main(String[] args) {



        String date = pro.getProperty("date");
        String[] split = date.split(",");
        String src = pro.getProperty("src");
        String des = pro.getProperty("des");


        List<Ticket> dateList = new ArrayList<Ticket>();
        for (int i = 0; i < split.length; i++) {
            System.out.println(new Ticket(src, des, split[i]));
        }
    }

}
