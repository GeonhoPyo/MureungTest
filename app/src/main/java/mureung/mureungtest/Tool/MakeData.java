package mureung.mureungtest.Tool;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.regex.Pattern;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.DataBase.PIDTEST;
import mureung.mureungtest.DataBase.PIDTEST_DBHelper;
import mureung.mureungtest.MainActivity;
import mureung.mureungtest.MainView;
import mureung.mureungtest.View.VoltageFragment;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.obdVersion;
import static mureung.mureungtest.Comunication.Bypass_Stream.dataVIN;

/**
 * Created by user on 2018-01-31.
 */

public class MakeData {
    public static boolean FinishLog_FLAG ;
    public static String fileName ;
    public static String voltageFileName ;

    public void defaultData(Context context, String vin, String carMaker, String carModel , String carYear){

        PIDTEST_DBHelper pidtest_dbHelper = new PIDTEST_DBHelper(context,"PIDTEST.db",null,1);
        String currentValue = String.valueOf(pidtest_dbHelper.getCurrentValue()+1);
        //Log.e("defaultData","currentValue : " + currentValue);
        pidtest_dbHelper.pidTestInsert(new PIDTEST(0,currentValue,new Time_DataBridge().getRealTime()),new Time_DataBridge().getRealTime());
        pidtest_dbHelper.close();

        String dataDate = new Time_DataBridge().getDateTime();

        //vin
        //maker
        //model
        //year

        //obd 정보
        //스마트폰 정보


        String brand = Build.BRAND;
        String model = Build.MODEL;
        String version = String.valueOf(Build.VERSION.SDK_INT);

        if(carModel != null){
            carModel = carModel.replace("/","-");
            carModel = carModel.replace(" ","");
        }


        String vinSubString = null;
        try {
            if(vin != null){
                vinSubString = vin.substring(0,3);
            }

        }catch (Exception e){
            vinSubString = "NULL";
            e.printStackTrace();
        }

        fileName = "T"+new Time_DataBridge().getLogTime()+"__N"+currentValue+"__V"+vinSubString+"__M"+carModel;


        new ErrorLogManager().saveErrorLog(fileName,"//----------------------------------------");
        if(MainView.PID != null){
            new ErrorLogManager().saveErrorLog(fileName,"//-------------"+MainView.PID+"-----------");
        }
        /*new ErrorLogManager().saveErrorLog(fileName,"//----------------------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"통신 프로토콜 표 ----");
        new ErrorLogManager().saveErrorLog(fileName,"0. Auto");
        new ErrorLogManager().saveErrorLog(fileName,"1. SAE J1850 PWM (41.6 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"2. SAE J1850 VPW (10.4 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"3. ISO 9141-2 (5 baud init, 10.4 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"4. ISO 14230-4 KWP (5 baud init, 10.4 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"5. ISO 14230-4 KWP (fast init, 10.4 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"6. ISO 15765-4 CAN (11 bit ID , 500 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"7. ISO 15765-4 CAN (29 bit ID , 500 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"8. ISO 15765-4 CAN (11 bit ID , 250 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"9. ISO 15765-4 CAN (29 bit ID , 250 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"A. SAE J1939 CAN (29 bit ID , 250 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"B. USER1 CAN (11 bit ID , 125 kbaud)");
        new ErrorLogManager().saveErrorLog(fileName,"C. USER2 CAN (11 bit ID , 50 kbaud)");*/

        new ErrorLogManager().saveErrorLog(fileName,"------------------");
        if(Bluetooth_Protocol.protocolDataNum!=null){
            new ErrorLogManager().saveErrorLog(fileName,"통신 프로토콜 Number : " + Bluetooth_Protocol.protocolDataNum);
        }
        if(Bluetooth_Protocol.protocolData!=null){
            new ErrorLogManager().saveErrorLog(fileName,"통신 프로토콜 : " + Bluetooth_Protocol.protocolData);
        }


        new ErrorLogManager().saveErrorLog(fileName,"//----------------------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"차수 : " + currentValue);
        new ErrorLogManager().saveErrorLog(fileName,"시간 정보 ----------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"시간 : " + dataDate);
        new ErrorLogManager().saveErrorLog(fileName,"차량 정보 ----------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"VIN Parsing : " + dataVIN);

        dataVIN = null;
        new ErrorLogManager().saveErrorLog(fileName,"VIN : " + vin);
        new ErrorLogManager().saveErrorLog(fileName,"제조사 : " + carMaker);
        new ErrorLogManager().saveErrorLog(fileName,"모델명 : " + carModel);
        new ErrorLogManager().saveErrorLog(fileName,"연식 : " + carYear);
        new ErrorLogManager().saveErrorLog(fileName,"OBD 정보 ----------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"OBD 버전 정보 : " + obdVersion);
        new ErrorLogManager().saveErrorLog(fileName,"스마트폰 정보 ----------------------------");
        new ErrorLogManager().saveErrorLog(fileName,"스마트폰 제조사 : " + brand);
        new ErrorLogManager().saveErrorLog(fileName,"스마트폰 모델명 : " + model);
        switch (version){
            case "19" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (KitKat)");
                break;
            case "20" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (KitKat Wear)");
                break;
            case "21" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (Lollipop)");
                break;
            case "22" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (Lollipop)");
                break;
            case "23" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (MarshMallow)");
                break;
            case "24" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (Nougat)");
                break;
            case "25" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (Nougat)");
                break;
            case "26" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (Oreo)");
                break;
            case "27" :new ErrorLogManager().saveErrorLog(fileName,"스마트폰 안드로이드 버전 : " + version +" (27)");
                break;
        }
        new ErrorLogManager().saveErrorLog(fileName,"");
        new ErrorLogManager().saveErrorLog(fileName,"내용 ******************************");


        obdVersion = null;



        FinishLog_FLAG = true;


        if(MainView.PID !=null){
            if(MainActivity.MainActivityHandler !=null){
                MainActivity.MainActivityHandler.obtainMessage(4,null).sendToTarget();
            }
        }


    }

    //diagnosisFileName : VIN + 날짜 + 시간
    public void diagnosisData(String time ,String vin, String diagnosisData){
        if(vin == null){
            vin = "NULL";
        }
        String diagnosisFileName = time + "_" + vin;
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"//----------------------------------------");
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"------------------Diagnosis---------------");
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"------------------------------------------");
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"시간 : " + time);
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"------------------------------------------");
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"VIN : " + vin);
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"------------------------------------------");
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"DTC : " + diagnosisData);
        new ErrorLogManager().saveErrorLog(diagnosisFileName,"----------------------------------------//");
    }

    public void voltageData(String time, String vin, String voltageData){
        if(!voltageData.matches("^[0-9.]*$")){
            StringBuilder strRecivedText = new StringBuilder();
            for(int i = 0 ; i < voltageData.length() ; i ++){

                String checkText = voltageData.substring(i,i+1);
                if(Pattern.matches("^[0-9. >\r]*$",checkText)){
                    strRecivedText.append(checkText);
                }
            }
            voltageData = String.valueOf(strRecivedText);
        }
        String data = voltageData.replace("AT","");
        data = data.replace("RV","");
        data = data.replace("V","");
        data = data.replace(">","");
        if(vin == null){
            vin = "NULL";
        }
        String FileName = "Voltage_"+time+"_"+vin;
        if(voltageFileName == null){
            voltageFileName = FileName;
            new ErrorLogManager().saveErrorLog(voltageFileName,"//----------------------------------------");
            new ErrorLogManager().saveErrorLog(voltageFileName,"----------------Voltage Test--------------");
            new ErrorLogManager().saveErrorLog(voltageFileName,"------------------------------------------");
            new ErrorLogManager().saveErrorLog(voltageFileName,"시간 : " + time);
            new ErrorLogManager().saveErrorLog(voltageFileName,"------------------------------------------");
            new ErrorLogManager().saveErrorLog(voltageFileName,"VIN : " + vin);
            new ErrorLogManager().saveErrorLog(voltageFileName,"------------------------------------------");
            new ErrorLogManager().saveErrorLog(voltageFileName,"전압 : " + data);
        }else {
            new ErrorLogManager().saveErrorLog(voltageFileName,"전압 : " + data);
            if(MainActivity.MainActivityHandler != null){
                MainActivity.MainActivityHandler.obtainMessage(7,"전압 : " + data).sendToTarget();
            }
        }

        if(data != null){
            Log.e("test","test 1111");
            if(VoltageFragment.voltageHandler != null){
                Log.e("test","test 2222");
                VoltageFragment.voltageHandler.obtainMessage(1,data).sendToTarget();
            }
        }


    }

    public String getFileName (){
        return fileName;
    }
}
