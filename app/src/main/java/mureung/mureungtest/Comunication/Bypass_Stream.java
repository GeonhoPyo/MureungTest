package mureung.mureungtest.Comunication;

import android.util.Log;

import java.util.ArrayList;

import mureung.mureungtest.MainActivity;
import mureung.mureungtest.Tool.SearchVINTask;

/**
 * Created by user on 2018-01-29.
 */

public class Bypass_Stream {

    public static String strVIN;

    private static boolean DTC_CHECK = false;
    private static ArrayList<String> strDtcCode;
    public static String dataVIN;



    //Bypass로 보낼 것들
    public Bypass_Stream() {

    }

    /** Created by Shin on 2017-08-01 */
    /**
     * 2017.11.14 by.GeonHo
     * 이전 PID 3개까지 기록에 대해 미리 배열을 3개까지만 받아오는 부분에서 항상 에러가 발생
     * PID 6개 요청을 예상하여 7개까지 늘림.
     * Try Catch 문으로 자주 에러 나오는 부분 처리.
     * */

    //Bluetooth 모듈을 통해 전달되는 입력 값을 모아 Parsing 하고 결과값을 출력
    public void NewStart(String data) {
        //Log.e("S T",""+"123");
        String RequestPID = "";
        String DataField = "";
        boolean data_find_flag = false;
        boolean [] sensor_flag = {false,false,false,false,false,false,false};
        boolean NonPID = false;
        boolean Init = false;
        boolean VIN = false;
        boolean Diagnosis = false;
        String Collected_Data;
        String Result = "";
        strDtcCode = new ArrayList<String>();

        String [] ECU_ID = {"7E8","7E9","7EA","7EB","7EC","7ED","7EE","7EF"};

        int DataFieldStartPosition = 0;

        //CR 기준으로 응답 여부 체크
        if(data.contains("\r"))
        {
            data_find_flag = true;
            DataFieldStartPosition = data.indexOf("\r") + 1;
        }

        //PID CMD 부분과 DataField 부분 분리
        if(data_find_flag)
        {

            RequestPID = data.substring(0, DataFieldStartPosition - 1);
            DataField = data.substring(DataFieldStartPosition, data.length());

            //RequestPID 보고 atz 이면 Init, CMD 이면 NonPID 체크
            if(RequestPID.contains("atz") ||RequestPID.contains("ATZ"))
            {
                Init = true;
                NonPID = false;
                VIN = false;
                data_find_flag = false;
                Diagnosis = false;
            }
            else if(RequestPID.contains("0902"))
            {
                VIN = true;
                NonPID = false;
                Init = false;
                data_find_flag = false;
                Diagnosis = false;
                Result = new Parse().parseVIN(collect_data(DataField, ECU_ID[0]));
                Log.e("Bypass_Stream","0902 Result : " + Result);

                if(MainActivity.connectTextHandler != null){
                    MainActivity.connectTextHandler.obtainMessage(1,"연결됨 " + Result).sendToTarget();
                }
                new SearchVINTask(MainActivity.mainContext, null, null, null, Parse.strVIN).execute();

                dataVIN+= strVIN;

            }

            else if(RequestPID.contains("03")){
                NonPID = false;
                Init = false;
                VIN = false;
                data_find_flag = false;
                Diagnosis = true;
                ArrayList<String> dtcCode = new ArrayList<String>();
                for(int i = 0 ; i < ECU_ID.length ; i++){
                    ArrayList<String> parseArray = new ArrayList<String>();
                    String result = collect_data(DataField,ECU_ID[i]);
                    if(result != null){
                        if(result.length() != 0){
                            parseArray = new Parse().parseDiagnosis(result);
                            if(parseArray!=null){
                                dtcCode.addAll(parseArray);
                            }
                        }

                    }


                }

                //여기서 가져온 DTC Code 전체를 Event_DataBridge 에 넣으면 된다 계속
                //dtcCode = 현재 DTC 코드 리스트
                //strDtcCode = 직전 DTC 코드 리스트
                //두개를 다 보내서 비교해서 event on/off

                strDtcCode.clear();
                strDtcCode.addAll(dtcCode);
            }
            else if(!RequestPID.contains("01"))
            {
                NonPID = true;
                Init = false;
                VIN = false;
                data_find_flag = false;
                Diagnosis = false;
            }

            //Data 없는 경우 구별
            if(DataField.contains("NO DATA"))
            {
                data_find_flag = false;
            }
            else if(DataField.matches(""))
            {
                data_find_flag = false;
            }
            else if(DataField.contains("OK"))
            {
                data_find_flag = false;
                Log.e("Check CMD","OK");
            }
        }
        //PID CMD에 따른 flag 설정
        if(data_find_flag)
        {
            sensor_flag = sensor_flag_generator(RequestPID);
        }

        //ECU_ID 기준으로 데이터를 분리하여 collect_data 를 통해 Data 영역을 Parsing 한다.
        if(data_find_flag)
        {
            int i;
            for(i = 0; i<ECU_ID.length; i++)
            {
                Collected_Data = collect_data(DataField, ECU_ID[i]);
                if (!Collected_Data.matches(""))
                {
                    if(Collected_Data.substring(0,2).matches("41"))
                    {
                        int flag_position = 0;
                        int parsing_Data = 2;
                        int parsing_CMD = 2;

                        while ((parsing_Data < Collected_Data.length()) && (parsing_CMD < RequestPID.length()))
                        {

                            String CheckPID = RequestPID.substring(parsing_CMD, parsing_CMD + 2);
                            String DataRegion = Collected_Data.substring(parsing_Data, parsing_Data + 2);
                            //PID 를 통해 몇 바이트 데이터인지 결정
                            int n_bytes = check_PID(CheckPID);
                            int result[] = new int[0];
                            if (n_bytes != 0) {
                                result = new int[n_bytes];
                            }


                            //보낸 PID 와 collected_response 를 비교하여 데이터를 n_byte 만큼 얻는다.
                            if (DataRegion.equalsIgnoreCase(CheckPID))
                            {
                                try{
                                    if(!sensor_flag[flag_position])
                                    {
                                        int k;
                                        for (k = 0; k < n_bytes; k++)
                                        {
                                            parsing_Data += 2;
                                            result[k] = Integer.parseInt(Collected_Data.substring(parsing_Data, parsing_Data + 2), 16);
                                        }
                                        sensor_flag[flag_position] = true;
                                        Result = Result + new PidAlgorithm().readBypass(result, CheckPID, sensor_flag[flag_position]);
                                        parsing_Data += 2;
                                        parsing_CMD += 2;
                                        flag_position++;
                                    }
                                    else
                                    {
                                        int k;
                                        for (k = 0; k < n_bytes; k++)
                                        {
                                            parsing_Data += 2;
                                        }
                                        parsing_Data += 2;
                                        parsing_CMD += 2;
                                        flag_position++;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                if(!sensor_flag[flag_position])
                                {
                                    int k;
                                    for (k = 0; k < n_bytes; k++) {
                                        result[k] = 0;
                                    }
                                    Result = Result + new PidAlgorithm().readBypass(result, CheckPID, sensor_flag[flag_position]);
                                    parsing_CMD += 2;
                                    flag_position++;
                                }
                                else
                                {
                                    parsing_CMD += 2;
                                    flag_position++;
                                }
                            }

                        }
                    }
                    else
                    {
                        Log.e("Error","Invalid collected data"+Collected_Data);
                        //Error
                    }
                }
                else if(Collected_Data.matches(""))
                {
                    //Empty ECU ID
                }
                else
                {
                    if(!(sensor_flag[0]&sensor_flag[1]&sensor_flag[2]&sensor_flag[3]&sensor_flag[4]&sensor_flag[5]))
                        Log.e("Error","No 41 Values in data field");
                    //Error
                }
            }
        }

        //각 상황별 결과 Log 출력
        if(!data_find_flag)
        {
            if(Init)
            {
                Log.e("Bypass Test","Initialize");
            }
            else if(NonPID)
            {
                Log.e("Bypass Test","NonPID, it could be CMD");
            }
            else if(VIN)
            {
                Log.e("Bypass Test","VIN: "+Result);
            }
            else if(Diagnosis){
                Log.e("Bypass Test","Diagnosis");
            }
            else
            {
                //Log.e("Result","NonOfData");
            }
        }
        //else Log.e("Bypass Test", "" + Result);
    }

    /** Created by Shin on 2017-08-01 */
    //ECU_ID 로 구분된 한 줄의 정보에 대해 내부를 분석하여 collected_response String으로 반환한다.
    private String collect_data(String DataField, String ECU_ID){
        String[] line_division;
        int line_count;

        line_division = DataField.split("\r");
        line_count = line_division.length;

        String[][] frame_division = new String[line_count][];


        int i;
        for (i = 0; i < line_count; i++)
        {
            frame_division[i] = line_division[i].split(" ");
        }
        String frame_check;
        int bvalid = 0;
        String collected_response = "";

        int k;
        for(k = 0; k < frame_division.length; k ++)
        {
            if(frame_division[k][0].equals(ECU_ID))
            {
                /**
                 * Length = 1 ; index = 1
                 * */
                frame_check = frame_division[k][1].substring(0,1);
                if(frame_check.matches("0")) //single line
                {
                    bvalid = Integer.parseInt(frame_division[k][1].substring(1,2));
                    int m;
                    for(m=2; m < frame_division[k].length; m++)
                    {
                        collected_response += frame_division[k][m];
                        bvalid--;
                        if(bvalid == 0)
                        {
                            break;
                        }
                    }
                }

                else if(frame_check.matches("1")) //First frame of multiline
                {
                    String validbit = frame_division[k][1].substring(1,2);
                    validbit += frame_division[k][2];
                    bvalid = Integer.parseInt(validbit,16);

                    if((bvalid<7)||(bvalid>DataField.length()))
                    {
                        //Error
                        Log.e("Error","ECU ID "+ECU_ID+frame_division[k][1]+frame_division[k][2]+" is out of bound");

                        return collected_response;
                    }

                    int m;
                    for(m=3; m < frame_division[k].length; m++)
                    {
                        collected_response += frame_division[k][m];
                        bvalid--;
                    }

                }

                else if(frame_check.matches("2")) //Consecutive frame of multiline
                {
                    int m;
                    for(m=2; m < frame_division[k].length; m++)
                    {
                        collected_response += frame_division[k][m];
                        bvalid--;

                        if(bvalid == 0)
                        {
                            break;
                        }
                    }
                }

                else
                {
                    //Error
                    Log.e("Error","ECU ID "+ECU_ID+frame_division[k][1]+" is not a frame type");
                }

            }
        }
        return collected_response;
    }

    /** Created by Shin on 2017-08-01 */
    //요청 PID 갯수에 따라 sensor_flag 값 반환
    private boolean[] sensor_flag_generator(String PID) {
        boolean [] sensor_flag = {true,true,true,true,true,true,true};
        try {
            if(PID.length() > 3)
            {
                sensor_flag[0] = false;
            }
            if(PID.length() > 5)
            {
                sensor_flag[1] = false;
            }
            if(PID.length() > 7)
            {
                sensor_flag[2] = false;
            }
            if(PID.length() > 9)
            {
                sensor_flag[3] = false;
            }
            if(PID.length() > 11)
            {
                sensor_flag[4] = false;
            }
            if(PID.length() > 13)
            {
                sensor_flag[5] = false;
            }
            if(PID.length() > 15)
            {
                sensor_flag[6] = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return sensor_flag;
    }

    /** Created by Shin on 2017-08-01 */
    //PID 확인 후 해당 명령어가 몇 바이트 출력 값인지를 반환
    private int check_PID(String PID) {
        int n_bytes;

        if (PID.matches("00"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("01"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("02"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("03"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("04"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("05"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("06"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("07"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("08"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("09"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("0A"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("0B"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("0C"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("0D"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("0E"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("0F"))
        {
            n_bytes = 1;
        }

        else if (PID.matches("10"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("11"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("12"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("13"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("14"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("15"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("16"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("17"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("18"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("19"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("1A"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("1B"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("1C"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("1D"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("1E"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("1F"))
        {
            n_bytes = 2;
        }

        else if (PID.matches("20"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("21"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("22"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("23"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("24"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("25"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("26"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("27"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("28"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("29"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("2A"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("2B"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("2C"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("2D"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("2E"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("2F"))
        {
            n_bytes = 1;
        }

        else if (PID.matches("30"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("31"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("32"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("33"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("34"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("35"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("36"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("37"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("38"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("39"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("3A"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("3B"))
        {
            n_bytes = 4;
        }
        else if (PID.equalsIgnoreCase("3C"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("3D"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("3E"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("3F"))
        {
            n_bytes = 2;
        }

        else if (PID.matches("40"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("41"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("42"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("43"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("44"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("45"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("46"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("47"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("48"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("49"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("4A"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("4B"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("4C"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("4D"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("4E"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("4F"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("50"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("51"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("52"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("53"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("54"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("55"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("56"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("57"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("58"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("59"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("5A"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("5B"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("5C"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("5D"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("5E"))
        {
            n_bytes = 2;
        }
        else if (PID.equalsIgnoreCase("5F"))
        {
            n_bytes = 1;
        }

        else if (PID.matches("60"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("61"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("62"))
        {
            n_bytes = 1;
        }
        else if (PID.matches("63"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("64"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("65"))
        {
            n_bytes = 2;
        }
        else if (PID.matches("66"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("67"))
        {
            n_bytes = 3;
        }
        else if (PID.matches("68"))
        {
            n_bytes = 7;
        }
        else if (PID.matches("69"))
        {
            n_bytes = 7;
        }
        else if (PID.equalsIgnoreCase("6A"))
        {
            n_bytes = 5;
        }
        else if (PID.equalsIgnoreCase("6B"))
        {
            n_bytes = 5;
        }
        else if (PID.equalsIgnoreCase("6C"))
        {
            n_bytes = 5;
        }
        else if (PID.equalsIgnoreCase("6D"))
        {
            n_bytes = 6;
        }
        else if (PID.equalsIgnoreCase("6E"))
        {
            n_bytes = 5;
        }
        else if (PID.equalsIgnoreCase("6F"))
        {
            n_bytes = 3;
        }

        else if (PID.matches("70"))
        {
            n_bytes = 9;
        }
        else if (PID.matches("71"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("72"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("73"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("74"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("75"))
        {
            n_bytes = 7;
        }
        else if (PID.matches("76"))
        {
            n_bytes = 7;
        }
        else if (PID.matches("77"))
        {
            n_bytes = 5;
        }
        else if (PID.matches("78"))
        {
            n_bytes = 9;
        }
        else if (PID.matches("79"))
        {
            n_bytes = 9;
        }
        else if (PID.equalsIgnoreCase("7A"))
        {
            n_bytes = 7;
        }
        else if (PID.equalsIgnoreCase("7B"))
        {
            n_bytes = 7;
        }
        else if (PID.equalsIgnoreCase("7C"))
        {
            n_bytes = 9;
        }
        else if (PID.equalsIgnoreCase("7D"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("7E"))
        {
            n_bytes = 1;
        }
        else if (PID.equalsIgnoreCase("7F"))
        {
            n_bytes = 13;
        }

        else if (PID.matches("80"))
        {
            n_bytes = 4;
        }
        else if (PID.matches("81"))
        {
            n_bytes = 21;
        }
        else if (PID.matches("82"))
        {
            n_bytes = 21;
        }
        else if (PID.matches("83"))
        {
            n_bytes = 5;
        }



        else
        {
            n_bytes = 0;
            Log.e("Error",""+PID+" is not a serviced PID");
            //Error
        }
        return n_bytes;
    }






}
