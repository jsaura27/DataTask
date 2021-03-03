import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataClass {

    public Date date = new Date();
    public int hdd;
    public int kWh;
    public List<String> messages = new ArrayList<>();

    public DataClass(Date date, int hdd, int kWh) {
        this.date = date;
        this.hdd = hdd;
        this.kWh = kWh;
    }

    public DataClass() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public int getkWh() {
        return kWh;
    }

    public void setkWh(int kWh) {
        this.kWh = kWh;
    }

    public void addMessage(String message){
        messages.add(message);
    }
}
