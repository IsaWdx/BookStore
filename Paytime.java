package fudandb;
import java.text.*;
import java.util.Date;
/**
 * Created by lenovo on 2015/5/12.
 */
public class Paytime {
    public Paytime()
    {};
    public static String getPaytime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public static String getDay() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

}
