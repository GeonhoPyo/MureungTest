package mureung.mureungtest.Communication;

/**
 * Created by user on 2018-01-29.
 */

public class srcData {
    public float data;
    public boolean find_flag;
    public String DTC_code;

    public srcData(float data, boolean find_flag){
        this.data = data;
        this.find_flag = find_flag;
    }

    public srcData(String dtcCode, boolean find_flag){
        this.DTC_code = dtcCode;
        this.find_flag = find_flag;
    }
}
