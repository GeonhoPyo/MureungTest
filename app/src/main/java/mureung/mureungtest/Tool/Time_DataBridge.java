package mureung.mureungtest.Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2018-01-31.
 */

public class Time_DataBridge {
    public String getRealTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }
    public String getDateTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    public String getLogTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd(HH-mm-ss)");
        return simpleDateFormat.format(date);
    }
    public String getTerminalTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        return simpleDateFormat.format(date);
    }
}
