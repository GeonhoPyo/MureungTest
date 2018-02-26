package mureung.mureungtest.Tool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import mureung.mureungtest.MainActivity;

/**
 * Created by user on 2018-01-31.
 */

public class SearchVINTask extends AsyncTask<Void, Void, Void> {
    // VIN 크롤링 url
    private static final String URL = "https://www.vindecoderz.com/EN/check-lookup/";

    // 크롤링을 위한 변수
    private Document doc;
    private Elements elements;

    // VIN을 저장할 변수
    private static String vin;

    // 크롤링 된 데이터를 저장할 변수
    private String strMaker;
    private String strYear;
    private String strModel;

    // 프로그레스 다이얼로그 변수
    private ProgressDialog progressDialog;
    private Context context;

    // 자동완성을 위한 EditText 변수들
    private EditText et_Model;
    private EditText et_Maker;
    private EditText et_Year;



    // 통신을 위한 객체 초기화
    public SearchVINTask(final Context context, EditText et_Model, EditText et_Maker, EditText et_Year, String vin){
        try{
            this.context = context;
            this.et_Model = et_Model;
            this.et_Maker = et_Maker;
            this.et_Year = et_Year;
            this.vin = vin;
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    protected Void doInBackground(Void... params) {
        try{
            // 크롤링을 하는동안 프로그레스 다이얼로그를 띄움
            // 크롤링 작업
            doc = Jsoup.connect(URL + vin).get();

            elements = doc.select("table").get(0).select("tr").select("td").select("h5");

            for (int i = 0; i < elements.size(); i++){
                if (elements.get(i).text().equals("Brand:")){
                    strMaker = elements.get(i + 1).text();
                }
                if (elements.get(i).text().equals("Model:")){
                    strModel = elements.get(i + 1).text();
                }
                if (elements.get(i).text().equals("Year:")){
                    strYear = elements.get(i + 1).text();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if(MainActivity.MainActivityHandler != null){
            MainActivity.MainActivityHandler.obtainMessage(1,"연결됨 " + vin + " , 제조사 : " +strMaker + " , 모델명 : " + strModel + " , 연식 : " + strYear ).sendToTarget();
        }
        new MakeData().defaultData(MainActivity.mainContext,vin,strMaker,strModel,strYear);


    }








}