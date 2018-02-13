package mureung.mureungtest.Tool;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import mureung.mureungtest.DataBase.PIDTEST;
import mureung.mureungtest.DataBase.PIDTEST_DBHelper;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.obdVersion;
import static mureung.mureungtest.Comunication.Bypass_Stream.dataVIN;

/**
 * Created by user on 2018-01-31.
 */

public class MakeData {
    public static boolean FinishLog_FLAG ;
    public static String fileName ;

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


        fileName = "T"+new Time_DataBridge().getLogTime()+"__N"+currentValue+"__V"+vin.substring(0,3)+"__M"+carModel;


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

    }

    public String getFileName (){
        return fileName;
    }
}
