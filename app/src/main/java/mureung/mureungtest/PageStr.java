package mureung.mureungtest;

/**
 * Created by user on 2018-01-29.
 */

public class PageStr {

    public static final String MainActivity = "MainActivity";
    public static final String Mainview = "MainView";
    public static final String PidTestView = "PidTestMainView";
    public static final String BluetoothConnect = "BluetoothConnect";
    public static final String Terminal = "Terminal";
    public static final String Voltage = "Voltage";
    public static final String BluetoothTest = "BluetoothTest";
    private static String pageStrData ;

    public static String getPageStrData() {
        return pageStrData;
    }

    public static void setPageStrData(String pageStrData) {
        PageStr.pageStrData = pageStrData;
    }
}
