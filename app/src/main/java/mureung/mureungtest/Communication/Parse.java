package mureung.mureungtest.Communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import mureung.mureungtest.MainActivity;

/**
 * Created by user on 2018-01-29.
 */

public class Parse {
    private static Map<String,String> dtcSortMap =null;
    public static String strVIN;

    private void initParse(){
        if(dtcSortMap == null){
            dtcSortMap = new HashMap<String,String>();
            dtcSortMap.put("0","P0");
            dtcSortMap.put("1","P1");
            dtcSortMap.put("2","P2");
            dtcSortMap.put("3","P3");
            dtcSortMap.put("4","C0");
            dtcSortMap.put("5","C1");
            dtcSortMap.put("6","C2");
            dtcSortMap.put("7","C3");
            dtcSortMap.put("8","B0");
            dtcSortMap.put("9","B1");
            dtcSortMap.put("A","B2");
            dtcSortMap.put("B","B3");
            dtcSortMap.put("C","U0");
            dtcSortMap.put("D","U1");
            dtcSortMap.put("E","U2");
            dtcSortMap.put("F","U3");
        }

    }


    public String parseVIN(String data)
    {
        int i;
        String collection = "";
        String Result="";


        try {
            for(i = 0; i<data.length()-1; i+=2) {
                String temp = data.substring(i,i+2);
                collection += (char)Integer.parseInt(temp,16);
            }
            if(collection.contains("\r")){
                StringBuilder strValueResult = new StringBuilder();
                for(int j = 0 ; j < collection.length() ; j++){
                    String checkRecevedText = collection.substring(j,j+1);
                    if(Pattern.matches("^[a-zA-Z0-9. ]*$",checkRecevedText)){
                        strValueResult.append(checkRecevedText);
                    }
                }
                collection = String.valueOf(strValueResult);

            }
            try {
                collection = collection.substring(3,collection.length());
            }catch (Exception e){
                e.printStackTrace();
            }

            Result = collection;
            MainActivity.VIN = Result;

            // TODO VIN 크롤링을 위해 VIN저장
            //new Bluetooth_Protocol().startPidData();
        }
        catch (Exception e){
            // 지원하지 않는 차량일때 데이터가 제대로 안넘온것 처리
            e.printStackTrace();

        }

        strVIN = Result;
        return Result;
    }

    /**
     * 2017.11.21 by.GeonHo
     * 진단 parse
     * */
    public ArrayList<String> parseDiagnosis(String data){
        initParse();
        ArrayList<String> dtcCode = new ArrayList<String>();
        String dtcId = data.substring(0,2);
        int dtcCount = Integer.parseInt(data.substring(2,4)); // 6
        if(dtcId.equals("43")){
            for(int i = 1 ; i <= dtcCount ; i++ ){
                String strData = null;
                String firstCode = null;
                try {
                    strData =  data.substring(i*4,(i*4)+4);
                    firstCode = dtcSortMap.get(strData.substring(0,1));
                }catch (Exception e){

                }
                if(strData != null && firstCode != null){
                    dtcCode.add(firstCode+ strData.substring(1,4));
                }

            }
        }
        return dtcCode;

    }
}
