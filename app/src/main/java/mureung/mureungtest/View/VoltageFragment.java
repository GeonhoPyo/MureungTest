package mureung.mureungtest.View;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.MainView;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-03-06.
 */

public class VoltageFragment extends Fragment {

    LineChart lineChart;
    public static Handler voltageHandler;
    private ArrayList<Entry> entries = new ArrayList<>();
    private int count = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voltage,container,false);
        PageStr.setPageStrData(PageStr.Voltage);
        lineChart = (LineChart)view.findViewById(R.id.chart);

        MainView.VoltageStart_FLAG = false;

        Timer voltageTimer = new Timer();
        voltageTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(MainView.Voltage_FLAG){
                    String voltagePush = "AT RV";
                    voltagePush += "\r";
                    new Bluetooth_Protocol().write(voltagePush.getBytes());
                }else {
                    cancel();
                }

            }
        },500,1000);
        try {
            if(voltageHandler == null){
                voltageHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        switch (msg.what){
                            case 1 :

                                entries.add(new Entry(count,Float.parseFloat(String.valueOf(msg.obj))));

                                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                                LineData data = new LineData(dataset);
                                dataset.setColors(ColorTemplate.COLORFUL_COLORS);


                                lineChart.setData(data);
                                lineChart.animateY(10);
                                count ++;
                                break;
                        }
                        return true;
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }




        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        voltageHandler = null;
        MainView.Voltage_FLAG = false;
    }
}
