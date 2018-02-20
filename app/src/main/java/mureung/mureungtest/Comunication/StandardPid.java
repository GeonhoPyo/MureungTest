package mureung.mureungtest.Comunication;

import android.util.Log;

/**
 * Created by user on 2018-01-29.
 */

public class StandardPid {
    private static int pidCount = 0 ;
    /*public String[] standardPid = {
            "0902",
            "0100",
            "01010203",
            "01040506",
            "01070809",
            "010A0B0C",
            "010D0E0F",
            "01101112",
            "01131415",
            "01161718",
            "01191A1B",
            "011C1D1E",
            "011F",
            "0120",
            "01212223",
            "01242526",
            "01272829",
            "012A2B2C",
            "012D2E2F",
            "01303132",
            "01333435",
            "01363738",
            "01393A3B",
            "013C3D3E",
            "013F",
            "0140",
            "01414243",
            "01444546",
            "01474849",
            "014A4B4C",
            "014D4E4F",
            "01505152",
            "01535455",
            "01565758",
            "01595A5B",
            "015C5D5E",
            "015F",
            "0160",
            "01616263",
            "01646566",
            "01676869",
            "016A6B6C",
            "016D6E6F",
            "01707172",
            "01737475",
            "01767778",
            "01797A7B",
            "017C7D7E",
            "017F",
            "0180",
            "01818283",
            "01848586",
            "0187"
    };*/

    public String[] standardPid = {
            "010d4910",
            "010c112f",
            "01230d63",
            "01490c7a",
            "01112304",
            "010d4906",
            "010c1108",
            "01230d7c",
            "01490c0b",
            "01112305",
            "010d495c",
            "010c110f",
            "01230d10",
            "01490c2f",
            "01112363",
            "010d4911",
            "010c1104",
            "01230d06",
            "01490c08",
            "01112379",
            "010d490b",
            "010c1105",
            "01230d5c",
            "01490c07",
            "01490c09",
            "01112346",
            "01112333",
            "03" //27
    };


    public String startStandardPID(){
        if(pidCount >= 28){
            pidCount = 0;
        }
        String PID = standardPid[pidCount];
        PID += "\r";

        pidCount++;
        return PID;
    }

}