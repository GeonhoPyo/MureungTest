package mureung.mureungtest.DataBase.SRCREC;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class SRCREC_DBHelper extends SQLiteOpenHelper {
    private final String TAG = "SRCREC_DBHelper";


    public SRCREC_DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    //DB를 새로 생성하는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SRCREC (_id INTEGER PRIMARY KEY AUTOINCREMENT, srcValue INTEGER, userSN INTEGER, realTime TEXT, srcLatitude REAL, srcLongitude REAL," +
                " srcSpeed REAL, srcRPM REAL, srcAPS REAL, srcTPS REAL, srcRPS REAL, srcMAF REAL, srcFuelLevel REAL, " +
                "srcTorque REAL, srcEngineLoad REAL, srcFuelTrimB1S REAL,srcFuelTrimB2S REAL, srcIntakePress REAL, srcEngineCoolantTemp REAL, srcEngineOilTemp REAL," +
                " srcFuelTrimB1L REAL,srcFuelTrimB2L REAL, srcAmbientAirTemp REAL, srcAbsolutePress REAL, srcHybridBatteryT REAL, srcDPF REAL, srcDPFTemp REAL, " +
                "srcIntakeAirTemp REAL, srcEGT1 REAL, srcEGT2 REAL, srcUploadTime TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //DB에 데이터 추가
    //사용자 구분 userSN, DRVREC와 같은 운행묶음으로 데이터를 추가
    //SRCREC는 1초에 한번 데이터를 기록
    public void SRCREC_insert(int srcValue, int userSN, String realTime, double srcLatitude, double srcLongitude, float srcSpeed, float srcRPM, float srcAPS,
                              float srcTPS, float srcRPS, float srcMAF, float srcFuelLevel, float srcTorque, float srcEngineLoad,
                              float srcFuelTrimB1S,float srcFuelTrimB2S, float srcIntakePress, float srcEngineCoolantTemp, float srcEngineOilTemp,
                              float srcFuelTrimB1L,float srcFuelTrimB2L,
                              float srcAmbientAirTemp, float srcAbsolutePress, float srcHybridBatteryT, float srcDPF, float srcDPFTemp, float srcIntakeAirTemp,
                              float srcEGT1, float srcEGT2, String srcUploadTime) {
        //Log.e("SRCREC_DBHelper","SRCREC_insert  userSN : "+ userSN + " , srcValue : " +srcValue + ", srcRPM : " + srcRPM);
        if(srcSpeed >= 254){
            srcSpeed = 0;
        }
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SRCREC VALUES (null, " + srcValue +  ", " + userSN + ", " +"'"+ realTime +"'"+ ", " + srcLatitude + ", " + srcLongitude + ", " + srcSpeed + ", "
                + srcRPM + ", " + srcAPS + ", " + srcTPS + ", " + srcRPS + ", " + srcMAF + ", " + srcFuelLevel + ", " + srcTorque +", " + srcEngineLoad +", "
                + srcFuelTrimB1S + ", " + srcFuelTrimB2S + ", "+ srcIntakePress +", " + srcEngineCoolantTemp +", " + srcEngineOilTemp +", " + srcFuelTrimB1L +", " + srcFuelTrimB2L +", "
                + srcAmbientAirTemp +", " + srcAbsolutePress +", " + srcHybridBatteryT +", " + srcDPF + ", "
                + srcDPFTemp +", " + srcIntakeAirTemp +", " + srcEGT1 +", " + srcEGT2 +", "+"'"+ srcUploadTime +"'"+");");

        db.close();

    }
    public void SRCREC_insert(int userSN ,ArrayList<SRCREC> srcrecArrayList) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            for(int i = 0 ; i < srcrecArrayList.size() ; i ++){
                SRCREC srcrec = srcrecArrayList.get(i);
                db.execSQL("INSERT INTO SRCREC VALUES (null, " + srcrec.srcValue + ", " + userSN + ", " + "'" + srcrec.realTime + "'" + ", "
                        + srcrec.srcLatitude + ", " + srcrec.srcLongitude + ", " + srcrec.srcSpeed + ", " + srcrec.srcRPM + ", " + srcrec.srcAPS + ", " + srcrec.srcTPS + ", "
                        + srcrec.srcRPS + ", " + srcrec.srcMAF + ", " + srcrec.srcFuelLevel  + ", " + srcrec.srcTorque + ", " + srcrec.srcEngineLoad + ", "
                        + srcrec.srcFuelTrimB1S + ", "+ srcrec.srcFuelTrimB2S + ", " + srcrec.srcIntakePress + ", " + srcrec.srcEngineCoolantTemp + ", "
                        + srcrec.srcEngineOilTemp + ", " + srcrec.srcFuelTrimB1L + ", "+ srcrec.srcFuelTrimB2L + ", "
                        + srcrec.srcAmbientAirTemp + ", " + srcrec.srcAbsolutePress + ", " + srcrec.srcHybridBatteryT + ", " + srcrec.srcDPF + ", "
                        + srcrec.srcDPFTemp + ", " + srcrec.srcIntakeAirTemp + ", " + srcrec.srcEGT1 + ", " + srcrec.srcEGT2 + ", " + "'" + srcrec.srcUploadTime + "'" + ");");

            }
            db.setTransactionSuccessful();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }


    }

    public void setPreSrcSpeed(int userSN,String currentTime){
        SQLiteDatabase db = getWritableDatabase();
        for(int i = 0 ; i < 2 ; i++){
            String time = String.valueOf(Long.parseLong(currentTime)-i);
            db.execSQL("UPDATE SRCREC SET srcSpeed = " + 0 + " WHERE userSN = " + userSN + " AND realTime = " +"'"+ time+"'");
        }
        db.close();
    }

    public ArrayList<SRCREC> arrayUserSNSRCREC(int userSN){
        Log.e(TAG,"arrayUserSNSRCREC");
        ArrayList<SRCREC> srcrecArrayList = new ArrayList<SRCREC>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor SRCREC_Cursor = db.rawQuery("SELECT * FROM SRCREC WHERE userSN = " + userSN +" ORDER BY _id ",null);
        srcrecArrayList = getCursorArrayList(SRCREC_Cursor,srcrecArrayList);
        db.close();
        return srcrecArrayList;
    }

    //DB 조회
    //사용자 구분 userSN 와 운행묶음으로 DataBase 조회후 전체 기록 가져오기
    public ArrayList<SRCREC> arraySRCREC(int srcValue , int userSN){
        Log.e(TAG,"arraySRCREC");
        ArrayList<SRCREC> srcrecArrayList = new ArrayList<SRCREC>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor SRCREC_Cursor = db.rawQuery("SELECT * FROM SRCREC WHERE srcValue = " + srcValue +" AND userSN = " + userSN +" ORDER BY _id ",null);
        srcrecArrayList = getCursorArrayList(SRCREC_Cursor,srcrecArrayList);
        db.close();
        return srcrecArrayList;
    }

    private ArrayList<SRCREC> getCursorArrayList(Cursor SRCREC_Cursor, ArrayList<SRCREC> srcrecArrayList){

        while(SRCREC_Cursor.moveToNext()){
            SRCREC srcRec = new SRCREC(SRCREC_Cursor.getInt(0),SRCREC_Cursor.getInt(1),SRCREC_Cursor.getInt(2),
                    SRCREC_Cursor.getString(3),SRCREC_Cursor.getDouble(4),SRCREC_Cursor.getDouble(5),SRCREC_Cursor.getFloat(6),
                    SRCREC_Cursor.getFloat(7), SRCREC_Cursor.getFloat(8),SRCREC_Cursor.getFloat(9),SRCREC_Cursor.getFloat(10),
                    SRCREC_Cursor.getFloat(11),SRCREC_Cursor.getFloat(12),SRCREC_Cursor.getFloat(13),SRCREC_Cursor.getFloat(14),
                    SRCREC_Cursor.getFloat(15),SRCREC_Cursor.getFloat(16),SRCREC_Cursor.getFloat(17),SRCREC_Cursor.getFloat(18),
                    SRCREC_Cursor.getFloat(19),SRCREC_Cursor.getFloat(20), SRCREC_Cursor.getFloat(21),SRCREC_Cursor.getFloat(22),
                    SRCREC_Cursor.getFloat(23),SRCREC_Cursor.getFloat(24),SRCREC_Cursor.getFloat(25),SRCREC_Cursor.getFloat(26),
                    SRCREC_Cursor.getFloat(27),SRCREC_Cursor.getFloat(28),SRCREC_Cursor.getFloat(29),SRCREC_Cursor.getString(30));
            srcrecArrayList.add(srcRec);
        }
        SRCREC_Cursor.close();
        return srcrecArrayList;
    }



    /**
     * 2017.11.01 by.GeonHo
     * 시간값을 비교하는 메서드를 이용해 eventStartTime < realTime < eventFinishTime 이라면 ArrayList 에 저장하고 값을 반환함
     * */
    public ArrayList<SRCREC> eventTimeSrcData(String eventStartTime, String eventFinishTime){
        SQLiteDatabase db = getReadableDatabase();
        /**
         * 전체 운행중 eventStartTime < realTime < eventFinishTime 데이터 가져옴
         * */
        Cursor SRCREC_Cursor = db.rawQuery("SELECT * FROM SRCREC WHERE realTime BETWEEN "+"'"+eventStartTime+"'"+" AND " +"'"+eventFinishTime +"'" , null);
        ArrayList<SRCREC> srcrecs = new ArrayList<SRCREC>();
        srcrecs = getCursorArrayList(SRCREC_Cursor,srcrecs);
        db.close();
        return srcrecs;
    }


    /**
     * 2017.12.14 by.GeonHo
     * SRCREC DB 서버
     * */


    //서버로 업로드를 성공하게되면 uploadTime 을 update 한다
    public void srcrecUploadTimeUpdate(int userSN, int srcValue , String srcUploadTime){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SRCREC SET srcUploadTime = " +"'"+ srcUploadTime +"'"+ " WHERE userSN = " + userSN + " AND srcValue = " + srcValue);
        db.close();

    }

    public ArrayList<Integer> getSrcValueList(int userSN){

        ArrayList<Integer> srcrecArrayList = new ArrayList<Integer>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor SRCREC_Cursor = db.rawQuery("SELECT srcValue FROM SRCREC WHERE  userSN = " + userSN +" AND srcUploadTime = 'null' "+" GROUP BY srcValue",null);
        while(SRCREC_Cursor.moveToNext()){
            srcrecArrayList.add(SRCREC_Cursor.getInt(0));
        }
        SRCREC_Cursor.close();
        db.close();
        return srcrecArrayList;
    }

    public void locationDelete(int indexNum){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SRCREC SET srcLatitude = 0 , srcLongitude = 0 WHERE _id = " +indexNum );
        db.close();

    }

    public SRCREC getSrcrecData(int userSN , int _id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor SRCREC_Cursor = db.rawQuery("SELECT * FROM SRCREC WHERE userSN = "+userSN+" AND _id = " + _id, null);
        ArrayList<SRCREC> srcrecs = new ArrayList<SRCREC>();
        srcrecs = getCursorArrayList(SRCREC_Cursor,srcrecs);
        db.close();
        return srcrecs.get(0);
    }




    //SRCREC DB 파일 존재 여부
    public boolean checkSrcRecDBFile(){
        File file = new File("/data/data/mureung.obdproject/databases/SRCREC.db");

        return file.exists();
    }




}
