import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: buku.ch
 * @Date: 2018/10/30 7:22 PM
 */


public class Ticket implements Serializable {

    private String src;
    private String des;
    private String date;
    private String price;

    public Ticket(String src, String des, String date) {
        this.src = src;
        this.des = des;
        this.date = date;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
