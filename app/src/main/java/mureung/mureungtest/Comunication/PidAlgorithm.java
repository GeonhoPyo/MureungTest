package mureung.mureungtest.Comunication;

import android.os.Handler;
import android.util.Log;

import mureung.mureungtest.PageStr;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.MakeData;
import mureung.mureungtest.View.PidTestView.PidTestView1;
import mureung.mureungtest.View.PidTestView.PidTestView2;
import mureung.mureungtest.View.PidTestView.PidTestView3;
import mureung.mureungtest.View.PidTestView.PidTestView4;

/**
 * Created by user on 2018-01-29.
 */

public class PidAlgorithm {

    private static float distance = 0 ;
    private static String preTime = null ;
    private static float startRpm = 0;
    private static SrcFindFlag  srcFindFlag = new SrcFindFlag(
            false,false, false, false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false);

    public static SrcFindFlag getSrcFindFlag() {
        return srcFindFlag;
    }

    public String readBypass(int data[], String PID, boolean find_flag) {

        float Result = 0;



        if (PID.matches("00")) //PID check 00~20
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","00 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                Result = 0;
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(1,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC00 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(1,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"00 / "+String.valueOf(find_flag)+" / PID CHECK ( 00~ 20) / Result : " + Result);
                }
            }




            new srcData(Result, find_flag);

        }
        else if (PID.matches("01")) //DTC가 해체된 이후의 상태를 모니터링 함
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","01 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                Result = 0;
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(2,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC01 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(2,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"01 / "+String.valueOf(find_flag)+" / DTC가 해체된 이후의 상태를 모니터링 함 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("02")) //DTC 고정
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                Log.e("test","02 : data1 : " + data1 + " ,  data2 : " + data2 );
                Result = 0;
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(3,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC02 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(3,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"02 / "+String.valueOf(find_flag)+" / DTC 고정 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }


        else if (PID.matches("03")) //연료 시스템 상태
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                Log.e("test","03 : data1 : " + data1 + " ,  data2 : " + data2 );
                Result = 0;
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(4,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC03 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(4,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"03 / "+String.valueOf(find_flag)+" / 연료 시스템 상태 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }
        else if (PID.matches("04")) // EngineLoad
        {
            if(find_flag)
            {
                Result = 100 * data[0] / 255;
                SRCREC_DataBridge.setSrc04(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(5,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC04 = find_flag;

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(5,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"04 / "+String.valueOf(find_flag)+" / EngineLoad / Result : " + Result);
                }
            }
            new srcData(Result, find_flag);

        }

        else if (PID.matches("05")) // EngineCoolantTemp
        {
            if(find_flag)
            {
                Result = data[0] - 40;
                SRCREC_DataBridge.setSrc05(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(6,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC05 = find_flag;

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(6,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"05 / "+String.valueOf(find_flag)+" / EngineCoolantTemp / Result : " + Result);
                }
            }

        }

        else if (PID.matches("06")) // FuelTrimB1S
        {
            if(find_flag)
            {
                Result = (float)(((data[0]/1.28)-100)/100);
                SRCREC_DataBridge.setSrc06(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(7,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC06 = find_flag;
            new srcData(Result, find_flag);

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(7,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"06 / "+String.valueOf(find_flag)+" / FuelTrimB1S / Result : " + Result);
                }
            }
        }
        else if (PID.matches("07")) // FuelTrimB1L
        {
            if(find_flag)
            {
                Result = (float)(((data[0]/1.28)-100)/100);
                SRCREC_DataBridge.setSrc07(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(8,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC07 = find_flag;
            new srcData(Result, find_flag);
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(8,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"07 / "+String.valueOf(find_flag)+" / FuelTrimB1L / Result : " + Result);
                }
            }

        }
        else if (PID.matches("08")) // FuelTrimB2S
        {
            if(find_flag)
            {
                Result = (float)(((data[0]/1.28)-100)/100);
                SRCREC_DataBridge.setSrc08(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(9,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC08 = find_flag;
            new srcData(Result, find_flag);

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(9,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"08 / "+String.valueOf(find_flag)+" / FuelTrimB2S / Result : " + Result);
                }
            }
        }

        else if (PID.matches("09")) // FuelTrimB2L
        {
            if(find_flag)
            {
                Result = (float)(((data[0]/1.28)-100)/100);
                SRCREC_DataBridge.setSrc09(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(10,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC09 = find_flag;
            new srcData(Result, find_flag);
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(10,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"09 / "+String.valueOf(find_flag)+" / FuelTrimB2L / Result : " + Result);
                }
            }
        }


        else if (PID.equalsIgnoreCase("0A")) //연료 압력( 게이지 압력 )
        {
            if(find_flag)
            {

                Result = 3 * data[0];
                SRCREC_DataBridge.setSrc0A(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(11,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC0A = find_flag;

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(11,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0A / "+String.valueOf(find_flag)+" / 연료 압력( 게이지 압력 ) / Result : " + Result);
                }
            }
            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("0B")) // IntakePress
        {
            if(find_flag)
            {
                Result = data[0];
                SRCREC_DataBridge.setSrc0B((int)Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(12,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC0B = find_flag;
            new srcData(Result, find_flag);

            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(12,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0B / "+String.valueOf(find_flag)+" / IntakePress / Result : " + Result);
                }
            }

        }

        else if (PID.equalsIgnoreCase("0C")) //RPM
        {
            if(find_flag)
            {
                Result = (256 * data[0] + data[1])/4;
                SRCREC_DataBridge.setSrc0C(Result);
                startRpm = Result;
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(13,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC0C = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(13,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0C / "+String.valueOf(find_flag)+" / RPM / Result : " + Result);
                }
            }

        }
        else if (PID.equalsIgnoreCase("0D")) //Speed
        {
            if(find_flag)
            {
                Result = data[0];

                SRCREC_DataBridge.setSrc0D((int)Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(14,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC0D = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(14,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0D / "+String.valueOf(find_flag)+" / Speed / Result : " + Result);
                }
            }

        }

        else if (PID.equalsIgnoreCase("0E")) // 타이밍 진각
        {
            if(find_flag)
            {

                Result = (data[0]/2)-64;
                SRCREC_DataBridge.setSrc0E(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(15,Result).sendToTarget();
                }

            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC0E = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(15,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0E / "+String.valueOf(find_flag)+" / 타이밍 진각 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("0F")) // IntakeAirTemp
        {
            if(find_flag)
            {
                Result = data[0] - 40;
                SRCREC_DataBridge.setSrc0F(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(16,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }

            srcFindFlag.SRC0F = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(16,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"0F / "+String.valueOf(find_flag)+" / IntakeAirTemp / Result : " + Result);
                }
            }
        }


        else if (PID.matches("10")) // MAF
        {
            if(find_flag)
            {
                Result = (256 * data[0] + data[1]) / 100;
                SRCREC_DataBridge.setSrc10(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(17,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC10 = find_flag;

            new srcData(Result, find_flag);
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(17,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"10 / "+String.valueOf(find_flag)+" / MAF / Result : " + Result);
                }
            }


        }

        else if (PID.matches("11")) //TPS
        {
            if(find_flag)
            {
                Result = 100 * data[0] / 255;
                SRCREC_DataBridge.setSrc11(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(18,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC11 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(18,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"11 / "+String.valueOf(find_flag)+" / TPS / Result : " + Result);
                }
            }

        }


        else if (PID.matches("12")) // 지령된 2차 대기 상태
        {
            if(find_flag)
            {

                Result = (data[0]/2)-64;
                Log.e("test"," 지령된 2차 대기 상태 : " + data[0]);
                //SRCREC_DataBridge.setSrc12(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(19,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC12 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(19,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"12 / "+String.valueOf(find_flag)+" / 지령된 2차 대기 상태 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("13")) // 산소 센서 유무
        {
            if(find_flag)
            {

                Result = (data[0]/2)-64;
                Log.e("test"," 산소 센서 유무 : " + data[0]);
                //SRCREC_DataBridge.setSrc13(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(20,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC13 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(20,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"13 / "+String.valueOf(find_flag)+" / 산소 센서 유무 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("14")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc14(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(21,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC14 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(21,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"14 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("15")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc15(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(22,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC15 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(22,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"15 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("16")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc16(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(23,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC16 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(23,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"16 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("17")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc17(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(24,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC17 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(24,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"17 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("18")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc18(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(25,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC18 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(25,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"18 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("19")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc19(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(26,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC19 = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(26,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"19 / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1A")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc1A(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(27,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1A = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(27,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1A / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1B")) // 산소 센서 1 A : 전압 , B : 단기 연료 트림
        {
            if(find_flag)
            {

                Result = (data[0]/200);

                SRCREC_DataBridge.setSrc1B(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(28,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1B = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(28,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1B / "+String.valueOf(find_flag)+" / 산소 센서 1 A : 전압 , B : 단기 연료 트림 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1C")) // 이 차가 따르는 OBD 기준
        {
            if(find_flag)
            {

                Result = (data[0]/200);
                Log.e("test","이 차가 따르는 OBD 기준  1C : " + data[0]);
                //SRCREC_DataBridge.setSrc1B(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(29,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1C = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(29,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1C / "+String.valueOf(find_flag)+" / 이 차가 따르는 OBD 기준 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1D")) // 산소 센서 유무
        {
            if(find_flag)
            {

                Result = (data[0]/200);
                Log.e("test","산소 센서 유무  1D : " + data[0]);
                //SRCREC_DataBridge.setSrc1B(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(30,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1D = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(30,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1D / "+String.valueOf(find_flag)+" / 산소 센서 유무 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1E")) // 보조 입력 상태
        {
            if(find_flag)
            {

                Result = (data[0]/200);
                Log.e("test","보조 입력 상태  1E : " + data[0]);
                //SRCREC_DataBridge.setSrc1B(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(31,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1E = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(31,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1E / "+String.valueOf(find_flag)+" / 보조 입력 상태 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.equalsIgnoreCase("1F")) // 엔진 시동 이후의 운전시간
        {
            if(find_flag)
            {

                Result = (256*data[0])+data[1];

                SRCREC_DataBridge.setSrc1F(Result);
                if(PidTestView1.pidTestView1_dataHandler !=null){
                    PidTestView1.pidTestView1_dataHandler.obtainMessage(32,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC1F = find_flag;
            if(PidTestView1.pidTestView1_nameHandler != null){
                PidTestView1.pidTestView1_nameHandler.obtainMessage(32,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"1F / "+String.valueOf(find_flag)+" / 엔진 시동 이후의 운전시간 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("20")) //PID check 21~40
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","20 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                Result = 0;
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(33,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC20 = find_flag;
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(33,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"20 / "+String.valueOf(find_flag)+" / PID check 21~40 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("21")) // 오작동 표시 등이 켜진 상태에서의 주행거리
        {
            if(find_flag)
            {

                Result = (256*data[0])+data[1];

                SRCREC_DataBridge.setSrc21(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(34,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC21 = find_flag;
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(34,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"21 / "+String.valueOf(find_flag)+" / 오작동 표시 등이 켜진 상태에서의 주행거리 / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("22")) //RPS
        {
            if(find_flag)
            {
                Result =(float) 0.079 * ((256 * data[0]) + data[1]);
                SRCREC_DataBridge.setSrc22(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(35,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC22 = find_flag;
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(35,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"22 / "+String.valueOf(find_flag)+" / RPS / Result : " + Result);
                }
            }

            new srcData(Result, find_flag);

        }

        else if (PID.matches("23")) // 연료 레일 게이지 압력 ( 디젤 또는 가솔린 직접 분사)
        {
            if(find_flag)
            {

                Result = 10*((256*data[0])+data[1]);

                SRCREC_DataBridge.setSrc23(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(36,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC23 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(36,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"23 / "+String.valueOf(find_flag)+" / 연료 레일 게이지 압력 ( 디젤 또는 가솔린 직접 분사) / Result : " + Result);
                }
            }
        }

        else if (PID.matches("24")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc24(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(37,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC24 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(38,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"24 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("25")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc25(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(38,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC25 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(38,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"25 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("26")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc26(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(39,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC26 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(39,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"26 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("27")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc27(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(40,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC27 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(40,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"27 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }
        else if (PID.matches("28")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc28(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(41,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC28 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(41,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"28 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }
        else if (PID.matches("29")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc29(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(42,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC29 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(42,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"29 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("2A")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc2A(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(43,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC2A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(43,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2A / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("2B")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압
        {
            if(find_flag)
            {

                Result = ((float)0.0001220703125*((256*data[2])+data[3]));
                SRCREC_DataBridge.setSrc2B(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(44,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC24 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(44,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2B / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전압 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("2C")) // 명령된 EGR
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc2C(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(45,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC2C = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(45,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2C / "+String.valueOf(find_flag)+" / 명령된 EGR / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("2D")) // EGR 오류
        {
            if(find_flag)
            {

                Result = (float) ((data[0]/1.28)-100);
                SRCREC_DataBridge.setSrc2D(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(46,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC2D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(46,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2D / "+String.valueOf(find_flag)+" / EGR 오류 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("2E")) // 증발식 퍼지 명령
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc2E(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(47,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC2E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(47,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2E / "+String.valueOf(find_flag)+" / 증발식 퍼지 명령 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("2F")) // FuelLevel
        {
            if(find_flag)
            {
                Result = 100 * data[0] / 255;
                SRCREC_DataBridge.setSrc2F(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(48,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC2F = find_flag;
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(48,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"2F / "+String.valueOf(find_flag)+" / FuelLevel / Result : " + Result);
                }
            }

        }

        else if (PID.matches("30")) // 코드 정리가 끝난 후 워밍업
        {
            if(find_flag)
            {

                Result = (float) data[0];
                SRCREC_DataBridge.setSrc30(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(49,Result).sendToTarget();
                }

            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC30 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(49,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"30 / "+String.valueOf(find_flag)+" / 코드 정리가 끝난 후 워밍업 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("31")) // 코드 삭제 후 이동거리
        {
            if(find_flag)
            {

                Result = (float) ((256*data[0]) + data[1]);
                SRCREC_DataBridge.setSrc31(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(50,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC31 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(50,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"31 / "+String.valueOf(find_flag)+" / 코드 삭제 후 이동거리 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("32")) // Evap. 시스템 증기압
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0]) + data[1])/4);
                SRCREC_DataBridge.setSrc32(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(51,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC32 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(51,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"32 / "+String.valueOf(find_flag)+" / Evap. 시스템 증기압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("33")) // AbsolutePress
        {
            if(find_flag)
            {
                Result = data[0];
                SRCREC_DataBridge.setSrc33((int)Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(52,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC33 = find_flag;
            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(52,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"33 / "+String.valueOf(find_flag)+" / AbsolutePress / Result : " + Result);
                }
            }
        }

        else if (PID.matches("34")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc34(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(53,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC34 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(53,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"34 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("35")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc35(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(54,Result).sendToTarget();
                }

            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC35 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(54,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"35 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("36")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc36(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(55,Result).sendToTarget();
                }

            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC36 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(55,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"36 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("37")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc37(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(56,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC37 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(56,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"37 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("38")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc38(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(57,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC38 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(57,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"38 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("39")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc39(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(58,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC39 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(58,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"39 / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("3A")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc3A(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(59,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(59,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3A / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("3B")) // 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류
        {
            if(find_flag)
            {

                Result = (float) (((256*data[2])+data[3])/256)-128;
                SRCREC_DataBridge.setSrc3B(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(60,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3B = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(60,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3B / "+String.valueOf(find_flag)+" / 산소 센서 1 AB : 연료 - 공기 동등한 비율 , CD : 전류 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("3C")) // 촉매 온도 : 뱅크 1. 센터 1
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/10)-40;
                SRCREC_DataBridge.setSrc3C(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(61,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3C = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(61,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3C / "+String.valueOf(find_flag)+" / 촉매 온도 : 뱅크 1. 센터 1 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("3D")) // 촉매 온도 : 뱅크 2. 센터 1
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/10)-40;
                SRCREC_DataBridge.setSrc3D(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(62,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(62,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3D / "+String.valueOf(find_flag)+" / 촉매 온도 : 뱅크 2. 센터 1 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("3E")) // 촉매 온도 : 뱅크 1. 센터 2
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/10)-40;
                SRCREC_DataBridge.setSrc3E(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(63,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(63,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3E / "+String.valueOf(find_flag)+" / 촉매 온도 : 뱅크 1. 센터 2 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("3F")) // 촉매 온도 : 뱅크 2. 센터 2
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/10)-40;
                SRCREC_DataBridge.setSrc3F(Result);
                if(PidTestView2.pidTestView2_dataHandler !=null){
                    PidTestView2.pidTestView2_dataHandler.obtainMessage(64,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC3F = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView2.pidTestView2_nameHandler != null){
                PidTestView2.pidTestView2_nameHandler.obtainMessage(64,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"3F / "+String.valueOf(find_flag)+" / 촉매 온도 : 뱅크 2. 센터 2 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("40")) //PID check 41~60
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","40 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(65,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC40 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(65,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"40 / "+String.valueOf(find_flag)+" / PID check 41~60 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("41")) // 이 드라이브 사이클 상태 모니터링
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","41 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                Result = 0;
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(66,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC41 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(66,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"41 / "+String.valueOf(find_flag)+" / 이 드라이브 사이클 상태 모니터링 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("42")) // 제어 모듈 전압
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/1000);
                SRCREC_DataBridge.setSrc42(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(67,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC42 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(67,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"42 / "+String.valueOf(find_flag)+" / 제어 모듈 전압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("43")) // 절대 하중 값
        {
            if(find_flag)
            {

                Result = (float) 100/255*((256*data[0])+data[1]);
                SRCREC_DataBridge.setSrc43(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(68,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC43 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(68,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"43 / "+String.valueOf(find_flag)+" / 절대 하중 값 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("44")) // 공연비
        {
            if(find_flag)
            {

                Result = (float) (2/65536)*((256*data[0])+data[1]);
                SRCREC_DataBridge.setSrc44(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(69,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC44 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(69,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"44 / "+String.valueOf(find_flag)+" / 공연비 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("45")) // 상대 스로틀 위치
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc45(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(70,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC45 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(70,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"45 / "+String.valueOf(find_flag)+" / 상대 스로틀 위치 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("46")) // AmbientAirTemp
        {
            if(find_flag)
            {
                Result = data[0] - 40;
                SRCREC_DataBridge.setSrc46(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(71,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC46 = find_flag;
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(71,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"46 / "+String.valueOf(find_flag)+" / AmbientAirTemp / Result : " + Result);
                }
            }

        }

        else if (PID.matches("47")) // 절대 스로틀 위치 B
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc47(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(72,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC47 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(72,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"47 / "+String.valueOf(find_flag)+" / 절대 스로틀 위치 B / Result : " + Result);
                }
            }
        }

        else if (PID.matches("48")) // 절대 스로틀 위치 C
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc48(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(73,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC48 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(73,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"48 / "+String.valueOf(find_flag)+" / 절대 스로틀 위치 C / Result : " + Result);
                }
            }
        }
        else if (PID.matches("49")) //APS
        {
            if(find_flag)
            {
                Result = 100 * data[0] / 255;
                SRCREC_DataBridge.setSrc49(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(74,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC49 = find_flag;



            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(74,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"49 / "+String.valueOf(find_flag)+" / 가속 페달 위치 D (APS) / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4A")) // 가속 페달 위치 E
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc4A(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(75,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(75,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4A / "+String.valueOf(find_flag)+" / 가속 페달 위치 E / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4B")) // 가속 페달 위치 F
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc4B(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(76,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4B = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(76,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4B / "+String.valueOf(find_flag)+" / 가속 페달 위치 F / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4C")) // 지령된 스로틀 액추에이터
        {
            if(find_flag)
            {

                Result = data[0];
                SRCREC_DataBridge.setSrc4B(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(77,Result).sendToTarget();
                }
                Log.e("test","지령된 스로틀 액추에이터 4C : " + data[0]);
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4C = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(77,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4C / "+String.valueOf(find_flag)+" / 지령된 스로틀 액추에이터 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4D")) // MIL ON 으로 실행되는 시간
        {
            if(find_flag)
            {

                Result = (float) ((256*data[0])+data[1]);
                SRCREC_DataBridge.setSrc4D(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(78,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(78,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4D / "+String.valueOf(find_flag)+" / MIL ON 으로 실행되는 시간 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4E")) // 문제 코드가 삭제 된 이후의 시간
        {
            if(find_flag)
            {

                Result = (float) ((256*data[0])+data[1]);
                SRCREC_DataBridge.setSrc4E(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(79,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(79,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4E / "+String.valueOf(find_flag)+" / 문제 코드가 삭제 된 이후의 시간 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("4F")) // 공연비, 산소센서 전압, 산소센서전류,  흡기 매니폴드 절대 압력의 최대값
        {
            if(find_flag)
            {

                Log.e("test","  공연비,"+data[0]+"  산소센서 전압,"+data[1]+"  산소센서전류,"+data[2]+"  흡기 매니폴드 절대 압력의 최대값"+data[3]);

                /*Result = (float) ((256*data[0])+data[1]);
                SRCREC_DataBridge.setSrc4E(Result);*/
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(80,data[0]).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC4F = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(80,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"4F / "+String.valueOf(find_flag)+" / 공연비, 산소센서 전압, 산소센서전류,  흡기 매니폴드 절대 압력의 최대값 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("50")) // 질량 공기 유량 센서의 공기 유량 최대 값
        {
            if(find_flag)
            {

                Result = (float) (data[0]*10);
                SRCREC_DataBridge.setSrc50(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(81,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC50 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(81,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"50 / "+String.valueOf(find_flag)+" / 질량 공기 유량 센서의 공기 유량 최대 값 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("51")) // 연료 유형
        {
            if(find_flag)
            {

                Result = (float) (data[0]);
                SRCREC_DataBridge.setSrc51(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(82,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC51 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(82,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"51 / "+String.valueOf(find_flag)+" / 연료 유형 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("52")) // 에탄올 연료 %
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc52(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(83,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC52 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(83,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"52 / "+String.valueOf(find_flag)+" / 에탄올 연료 % / Result : " + Result);
                }
            }
        }

        else if (PID.matches("53")) // 절대 Evap 시스템 증기압
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])/200);
                SRCREC_DataBridge.setSrc53(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(84,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC53 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(84,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"53 / "+String.valueOf(find_flag)+" / 절대 Evap 시스템 증기압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("54")) // 증기 시스템 증기압
        {
            if(find_flag)
            {

                Result = (float) (((256*data[0])+data[1])-32767);
                SRCREC_DataBridge.setSrc54(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(85,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC54 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(85,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"54 / "+String.valueOf(find_flag)+" / 증기 시스템 증기압 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("55")) // 단기 2차 산소 센서 트림 . A : 제 1차 뱅크 , B : 제 3 뱅크
        {
            if(find_flag)
            {

                Result = (float) ((100/(128*data[0]))-100);
                SRCREC_DataBridge.setSrc55(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(86,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC55 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(86,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"55 / "+String.valueOf(find_flag)+" / 단기 2차 산소 센서 트림 . A : 제 1차 뱅크 , B : 제 3 뱅크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("56")) // 장기 2차 산소 센서 트림 . A : 제 1차 뱅크 , B : 제 3 뱅크
        {
            if(find_flag)
            {

                Result = (float) ((100/(128*data[0]))-100);
                SRCREC_DataBridge.setSrc56(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(87,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC56 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(87,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"56 / "+String.valueOf(find_flag)+" / 장기 2차 산소 센서 트림 . A : 제 1차 뱅크 , B : 제 3 뱅크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("57")) // 단기 2차 산소 센서 트림 . A : 제 2차 뱅크 , B : 제 4 뱅크
        {
            if(find_flag)
            {

                Result = (float) ((100/(128*data[0]))-100);
                SRCREC_DataBridge.setSrc57(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(88,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC57 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(88,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"57 / "+String.valueOf(find_flag)+" / 단기 2차 산소 센서 트림 . A : 제 2차 뱅크 , B : 제 4 뱅크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("58")) // 장기 2차 산소 센서 트림 . A : 제 2차 뱅크 , B : 제 4 뱅크
        {
            if(find_flag)
            {

                Result = (float) ((100/(128*data[0]))-100);
                SRCREC_DataBridge.setSrc58(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(89,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC58 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(89,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"58 / "+String.valueOf(find_flag)+" / 장기 2차 산소 센서 트림 . A : 제 2차 뱅크 , B : 제 4 뱅크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("59")) // 연료 레일 절대 압력
        {
            if(find_flag)
            {

                Result = (float) ((10*((256*data[0])+data[1])));
                SRCREC_DataBridge.setSrc59(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(90,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC59 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(90,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"59 / "+String.valueOf(find_flag)+" / 연료 레일 절대 압력 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("5A")) // 상대 가속 페달 위치
        {
            if(find_flag)
            {

                Result = (float) (data[0]/2.55);
                SRCREC_DataBridge.setSrc5A(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(91,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(91,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5A / "+String.valueOf(find_flag)+" / 상대 가속 페달 위치 / Result : " + Result);
                }
            }
        }




        else if (PID.equalsIgnoreCase("5B")) // HybridBatteryT
        {
            if(find_flag)
            {
                Result = 100 * data[0] /255;
                SRCREC_DataBridge.setSrc5B(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(92,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5B = find_flag;
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(92,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5B / "+String.valueOf(find_flag)+" / HybridBatteryT / Result : " + Result);
                }
            }

        }

        else if (PID.equalsIgnoreCase("5C")) // EngineOilTemp
        {
            if(find_flag)
            {
                Result = data[0] - 40;
                SRCREC_DataBridge.setSrc5C(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(93,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5C = find_flag;
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(93,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5C / "+String.valueOf(find_flag)+" / EngineOilTemp / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("5D")) // 연료 분사 타이밍
        {
            if(find_flag)
            {

                Result = (float) (256*data[0])+data[1];
                SRCREC_DataBridge.setSrc5D(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(94,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(94,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5D / "+String.valueOf(find_flag)+" / 연료 분사 타이밍 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("5E")) // 엔진 연료 비율
        {
            if(find_flag)
            {

                Result = (float) ((256*data[0])+data[1])/20;
                SRCREC_DataBridge.setSrc5E(Result);
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(95,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(95,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5E / "+String.valueOf(find_flag)+" / 엔진 연료 비율 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("5F")) // 차량이 설계된 배출 요구 사항
        {
            if(find_flag)
            {

                Log.e("test"," 차량이 설계된 배출 요구 사항  5F : " + data[0]);

                /*Result = (float) ((256*data[0])+data[1])/20;
                SRCREC_DataBridge.setSrc5E(Result);*/
                if(PidTestView3.pidTestView3_dataHandler !=null){
                    PidTestView3.pidTestView3_dataHandler.obtainMessage(96,data[0]).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC5F = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView3.pidTestView3_nameHandler != null){
                PidTestView3.pidTestView3_nameHandler.obtainMessage(96,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"5F / "+String.valueOf(find_flag)+" / 차량이 설계된 배출 요구 사항 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("60")) //PID check 61 ~ 80
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","60 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(97,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC60 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(97,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"60 / "+String.valueOf(find_flag)+" / PID check 61 ~ 80 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("61")) // 운전자 요구 동력 - 토크 퍼센트
        {
            if(find_flag)
            {

                Result = (float) (data[0]-125);
                SRCREC_DataBridge.setSrc61(Result);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(98,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC61 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(98,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"61 / "+String.valueOf(find_flag)+" / 운전자 요구 동력 - 토크 퍼센트 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("62")) // 실제 엔진 - 퍼센트 토크
        {
            if(find_flag)
            {

                Result = (float) (data[0]-125);
                SRCREC_DataBridge.setSrc62(Result);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(99,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC62 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(99,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"62 / "+String.valueOf(find_flag)+" / 실제 엔진 - 퍼센트 토크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("63")) // 엔진 기준 토크
        {
            if(find_flag)
            {

                Result = (float) (256*data[0])+data[1];
                SRCREC_DataBridge.setSrc63(Result);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(100,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC63 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(100,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"63 / "+String.valueOf(find_flag)+" / 엔진 기준 토크 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("64")) //엔진 토크 데이터
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                Log.e("test","엔진 토크 데이터 : Idle : " + data1 + " ,  엔진 포인트 1 : " + data2 + " ,  엔진 포인트 2 : " + data3 + " ,  엔진 포인트 3 : " + data4+ " ,  엔진 포인트 4 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(101,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC64 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(101,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"64 / "+String.valueOf(find_flag)+" / 엔진 토크 데이터 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("65")) //  보조 입 / 출력
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                Log.e("test","보조 입 / 출력 : data1 : " + data1 + " ,  data2 : " + data2);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(102,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC65 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(102,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"65 / "+String.valueOf(find_flag)+" / 보조 입_출력 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("66")) //  질량 공기 유량 센서
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                Log.e("test","질량 공기 유량 센서 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(103,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC66 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(103,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"66 / "+String.valueOf(find_flag)+" / 질량 공기 유량 센서 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("67")) //  엔진 냉각수 온도
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                Log.e("test","엔진 냉각수 온도 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(104,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC67 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(104,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"67 / "+String.valueOf(find_flag)+" / 엔진 냉각수 온도 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("68")) //  흡기 온도 센서
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                Log.e("test","흡기 온도 센서 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(105,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC68 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(105,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"68 / "+String.valueOf(find_flag)+" / 흡기 온도 센서 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("69")) //  지령 EGR 및 EGR 오류
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                Log.e("test","지령 EGR 및 EGR 오류 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(106,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC69 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(106,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"69 / "+String.valueOf(find_flag)+" / 지령 EGR 및 EGR 오류 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("6A")) //  지령 된 디젤 흡입 공기 흐름 제어 및 상대 흡입 공기 흐름 위치
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                Log.e("test","지령 된 디젤 흡입 공기 흐름 제어 및 상대 흡입 공기 흐름 위치 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(107,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(107,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6A / "+String.valueOf(find_flag)+" / 지령 된 디젤 흡입 공기 흐름 제어 및 상대 흡입 공기 흐름 위치 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("6B")) //  배기 가스 재순환 온도
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                Log.e("test"," 배기 가스 재순환 온도 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(108,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6B = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(108,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6B / "+String.valueOf(find_flag)+" / 배기 가스 재순환 온도 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("6C")) //  지령 된 스로틀 액추에이터 제어 및 상대 스로틀 위치
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                Log.e("test"," 지령 된 스로틀 액추에이터 제어 및 상대 스로틀 위치 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(109,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6C = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(109,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6C / "+String.valueOf(find_flag)+" / 지령 된 스로틀 액추에이터 제어 및 상대 스로틀 위치 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("6D")) //  연료 압력 제어 시스템
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                Log.e("test"," 연료 압력 제어 시스템 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(110,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(110,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6D / "+String.valueOf(find_flag)+" / 연료 압력 제어 시스템 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("6E")) //  분사 압력 제어 시스템
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test"," 분사 압력 제어 시스템 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(111,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(111,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6E / "+String.valueOf(find_flag)+" / 분사 압력 제어 시스템 / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("6F")) //  터보 차저 압축기 입구 압력
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];

                Log.e("test"," 터보 차저 압축기 입구 압력 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(112,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC6F = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(112,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"6F / "+String.valueOf(find_flag)+" / 터보 차저 압축기 입구 압력 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("70")) //  부스트 압력 제어
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];

                Log.e("test"," 부스트 압력 제어 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6
                        + " ,  data7 : " + data7+ " ,  data8 : " + data8+ " ,  data9 : " + data9);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(113,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC70 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(113,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"70 / "+String.valueOf(find_flag)+" / 부스트 압력 제어 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("71")) //  지오 메트리 터보
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test"," 지오 메트리 터보 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(114,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC71 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(114,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"71 / "+String.valueOf(find_flag)+" / 지오 메트리 터보 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("72")) //  웨이스트 게이트 제어
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test"," 웨이스트 게이트 제어 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(115,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC72 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(115,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"72 / "+String.valueOf(find_flag)+" / 웨이스트 게이트 제어 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("73")) //  배기 압력
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test"," 배기 압력 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(116,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC73 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(116,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"73 / "+String.valueOf(find_flag)+" / 배기 압력 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("74")) //  터보 충전기 RPM
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test"," 터보 충전기 RPM : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(117,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC74 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(117,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"74 / "+String.valueOf(find_flag)+" / 터보 충전기 RPM / Result : " + Result);
                }
            }
        }

        else if (PID.matches("75")) //  터보 차저 온도 75
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];

                Log.e("test"," 터보 차저 온도 75  : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(118,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC75 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(118,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"75 / "+String.valueOf(find_flag)+" /터보 차저 온도 75  / Result : " + Result);
                }
            }
        }

        else if (PID.matches("76")) //  터보 차저 온도
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];

                Log.e("test"," 터보 차저 온도 76 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5+ " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(119,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC76 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(119,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"76 / "+String.valueOf(find_flag)+" / 터보 차저 온도 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("77")) //  충전 공기 냉각기 온도 (CACT)
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test","  충전 공기 냉각기 온도 (CACT) : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(120,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC77 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(120,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"77 / "+String.valueOf(find_flag)+" /  충전 공기 냉각기 온도 (CACT) / Result : " + Result);
                }
            }
        }

        else if (PID.matches("78")) //  배기 가스 온도 (EGT) 뱅크 1
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];

                Log.e("test","   배기 가스 온도 (EGT) 뱅크 1 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7+ " ,  data8 : " + data8+ " ,  data9 : " + data9);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(121,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC78 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(121,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"78 / "+String.valueOf(find_flag)+" / 배기 가스 온도 (EGT) 뱅크 1 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("79")) //  배기 가스 온도 (EGT) 뱅크 2
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];

                Log.e("test","   배기 가스 온도 (EGT) 뱅크 2 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7+ " ,  data8 : " + data8+ " ,  data9 : " + data9);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(122,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC79 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(122,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"79 / "+String.valueOf(find_flag)+" / 배기 가스 온도 (EGT) 뱅크 2 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("7A")) //  디젤 미립자 필터 (DPF) 7A
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];

                Log.e("test","   디젤 미립자 필터 (DPF) 7A : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(123,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7A = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(123,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7A / "+String.valueOf(find_flag)+" / 디젤 미립자 필터 (DPF) 7A / Result : " + Result);
                }
            }
        }
        else if (PID.equalsIgnoreCase("7B")) //  디젤 미립자 필터 (DPF) 7B
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];

                Log.e("test","   디젤 미립자 필터 (DPF) 7B : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(124,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7B = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(124,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7B / "+String.valueOf(find_flag)+" / 디젤 미립자 필터 (DPF) 7B / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("7C")) //  디젤 미립자 필터 (DPF) 온도
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];

                Log.e("test","    디젤 미립자 필터 (DPF) 온도 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7+ " ,  data8 : " + data8+ " ,  data9 : " + data9);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(125,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7C = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(125,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7C / "+String.valueOf(find_flag)+" / 디젤 미립자 필터 (DPF) 온도 / Result : " + Result);
                }
            }
        }


        else if (PID.equalsIgnoreCase("7D")) //  NOx NTE 제어 영역 상태
        {
            if(find_flag)
            {
                int data1 = data[0];

                Log.e("test","    NOx NTE 제어 영역 상태 : data1 : " + data1 );
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(126,Result).sendToTarget();
                }

            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7D = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(126,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7D / "+String.valueOf(find_flag)+" /  NOx NTE 제어 영역 상태 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("7E")) //  PM NTE 제어 영역 상태
        {
            if(find_flag)
            {
                int data1 = data[0];

                Log.e("test","    PM NTE 제어 영역 상태 : data1 : " + data1 );
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(127,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7E = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(127,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7E / "+String.valueOf(find_flag)+" / PM NTE 제어 영역 상태 / Result : " + Result);
                }
            }
        }

        else if (PID.equalsIgnoreCase("7F")) //  엔진 작동 시간
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];
                int data10 = data[9];
                int data11 = data[10];
                int data12 = data[11];
                int data13 = data[12];

                Log.e("test","    엔진 작동 시간 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5
                        + " ,  data6 : " + data6+ " ,  data7 : " + data7+ " ,  data8 : " + data8+ " ,  data9 : " + data9
                        + " ,  data10 : " + data10+ " ,  data11 : " + data11+ " ,  data12 : " + data12+ " ,  data13 : " + data13);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(128,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC7F = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(128,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"7F / "+String.valueOf(find_flag)+" /  엔진 작동 시간 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("80")) //PID check 81 ~ A0
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                Log.e("test","80 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(129,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC80 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(129,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"80 / "+String.valueOf(find_flag)+" / PID check 81 ~ A0 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("81")) //보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 81
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];
                int data10 = data[9];
                int data11 = data[10];
                int data12 = data[11];
                int data13 = data[12];
                int data14 = data[13];
                int data15 = data[14];
                int data16 = data[15];
                int data17 = data[16];
                int data18 = data[17];
                int data19 = data[18];
                int data20 = data[19];
                int data21 = data[20];
                Log.e("test","보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 81 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4
                        + " ,  data5 : " + data5 + " ,  data6 : " + data6 + " ,  data7 : " + data7+ " ,  data8 : " + data8 + " ,  data9 : " + data9 + " ,  data10 : " + data10
                        + " ,  data11 : " + data11 + " ,  data12 : " + data12 + " ,  data13 : " + data13+ " ,  data14 : " + data14 + " ,  data15 : " + data15 + " ,  data16 : " + data16
                        + " ,  data17 : " + data17 + " ,  data18 : " + data18 + " ,  data19 : " + data19+ " ,  data20 : " + data20 + " ,  data21 : " + data21);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(130,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC81 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(130,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"81 / "+String.valueOf(find_flag)+" / 보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 81 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("82")) //보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 82
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];
                int data6 = data[5];
                int data7 = data[6];
                int data8 = data[7];
                int data9 = data[8];
                int data10 = data[9];
                int data11 = data[10];
                int data12 = data[11];
                int data13 = data[12];
                int data14 = data[13];
                int data15 = data[14];
                int data16 = data[15];
                int data17 = data[16];
                int data18 = data[17];
                int data19 = data[18];
                int data20 = data[19];
                int data21 = data[20];
                Log.e("test","보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 82 : data1 : " + data1 + " ,  data2 : " + data2 + " ,  data3 : " + data3 + " ,  data4 : " + data4
                        + " ,  data5 : " + data5 + " ,  data6 : " + data6 + " ,  data7 : " + data7+ " ,  data8 : " + data8 + " ,  data9 : " + data9 + " ,  data10 : " + data10
                        + " ,  data11 : " + data11 + " ,  data12 : " + data12 + " ,  data13 : " + data13+ " ,  data14 : " + data14 + " ,  data15 : " + data15 + " ,  data16 : " + data16
                        + " ,  data17 : " + data17 + " ,  data18 : " + data18 + " ,  data19 : " + data19+ " ,  data20 : " + data20 + " ,  data21 : " + data21);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(131,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC82 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(131,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"82 / "+String.valueOf(find_flag)+" / 보조 방출 제어 장치 (AECD) 의 엔진 가동 시간 82 / Result : " + Result);
                }
            }
        }

        else if (PID.matches("83")) //  NOx 센서
        {
            if(find_flag)
            {
                int data1 = data[0];
                int data2 = data[1];
                int data3 = data[2];
                int data4 = data[3];
                int data5 = data[4];

                Log.e("test","    NOx 센서 : data1 : " + data1 + " ,  data2 : " + data2+ " ,  data3 : " + data3
                        + " ,  data4 : " + data4+ " ,  data4 : " + data4+ " ,  data5 : " + data5);
                if(PidTestView4.pidTestView4_dataHandler !=null){
                    PidTestView4.pidTestView4_dataHandler.obtainMessage(132,Result).sendToTarget();
                }
            }
            else
            {
                Result = 0;
            }
            srcFindFlag.SRC83 = find_flag;


            new srcData(Result, find_flag);
            if(PidTestView4.pidTestView4_nameHandler != null){
                PidTestView4.pidTestView4_nameHandler.obtainMessage(132,find_flag).sendToTarget();
                if(MakeData.fileName!=null&& PageStr.getPageStrData().equals(PageStr.PidTestView)){
                    new ErrorLogManager().saveErrorLog(MakeData.fileName,"83 / "+String.valueOf(find_flag)+" / NOx 센서 / Result : " + Result);
                }
            }
        }




        else
        {

            Log.e("Error",""+PID+" is not aError serviced PID");
        }





        return null;
    }

}
