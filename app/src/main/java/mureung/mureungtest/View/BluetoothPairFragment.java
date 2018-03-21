package mureung.mureungtest.View;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.Comunication.Bluetooth_Protocol2;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-03-19.
 */

public class BluetoothPairFragment extends Fragment implements View.OnClickListener {

    LineChart bluetooth1Chart, bluetooth2Chart;
    ArrayList<Entry> bluetooth1ArrayList,bluetooth2ArrayList;
    TextView bluetooth1Text, bluetooth2Text, bluetooth1State, bluetooth2State;
    Button bluetooth1Btn, bluetooth2Btn;
    public static Handler bluetoothTestHandler ;
    public static final int bluetooth1ChartNum = 1;
    public static final int bluetooth2ChartNum = 2;
    public static final int bluetooth1TextNum = 3;
    public static final int bluetooth2TextNum = 4;
    public static final int bluetooth1StateNum = 5;
    public static final int bluetooth2StateNum = 6;

    private int entries1Count = 0;
    private int entries2Count = 0;
    public static boolean BluetoothTestPage_FLAG = false;

    private static Timer bluetooth1Timer, bluetooth2Timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bluetoothtest,container,false);

        bluetooth1Chart = (LineChart)view.findViewById(R.id.bluetooth1Chart);
        bluetooth2Chart = (LineChart)view.findViewById(R.id.bluetooth2Chart);

        bluetooth1Text = (TextView)view.findViewById(R.id.bluetooth1Text);
        bluetooth2Text = (TextView)view.findViewById(R.id.bluetooth2Text);

        bluetooth1ArrayList = new ArrayList<Entry>();
        bluetooth2ArrayList = new ArrayList<Entry>();

        bluetooth1State = ( TextView) view.findViewById(R.id.bluetooth1State);
        bluetooth2State = ( TextView) view.findViewById(R.id.bluetooth2State);

        bluetooth1Btn = (Button)view.findViewById(R.id.bluetooth1Btn);
        bluetooth2Btn = (Button)view.findViewById(R.id.bluetooth2Btn);
        bluetooth1Btn.setOnClickListener(this);
        bluetooth2Btn.setOnClickListener(this);

        PageStr.setPageStrData(PageStr.BluetoothTest);
        Bluetooth_Protocol.PidTestFlag = true;
        Bluetooth_Protocol2.PidTestFlag = true;

        bluetoothTestHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case bluetooth1ChartNum :
                        bluetooth1ArrayList.add(new Entry(entries1Count,Float.parseFloat(String.valueOf(msg.obj))));
                        LineDataSet dataset1 = new LineDataSet(bluetooth1ArrayList,"# of Calls");
                        LineData lineData1 = new LineData(dataset1);
                        dataset1.setColor(Color.BLUE);
                        bluetooth1Chart.setData(lineData1);
                        bluetooth1Chart.animateY(1);
                        entries1Count ++;

                        break;
                    case bluetooth2ChartNum :
                        bluetooth2ArrayList.add(new Entry(entries2Count,Float.parseFloat(String.valueOf(msg.obj))));
                        LineDataSet dataset2 = new LineDataSet(bluetooth2ArrayList,"# of Calls");
                        LineData lineData2 = new LineData(dataset2);
                        dataset2.setColor(Color.RED);
                        bluetooth2Chart.setData(lineData2);
                        bluetooth2Chart.animateY(1);
                        entries2Count ++;
                        break;
                    case bluetooth1TextNum :
                        bluetooth1Text.setText(String.valueOf(msg.obj));
                        break;
                    case bluetooth2TextNum :
                        bluetooth2Text.setText(String.valueOf(msg.obj));
                        break;
                    case bluetooth1StateNum :
                        bluetooth1State.setText(String.valueOf(msg.obj));
                        break;
                    case bluetooth2StateNum :
                        bluetooth2State.setText(String.valueOf(msg.obj));
                        break;


                }

                return true;
            }
        });



        BluetoothTestPage_FLAG = true;
        return view;
    }

    public void bluetooth1TimerStart(){
        if(bluetooth1Timer == null){
            bluetooth1Timer = new Timer();
            bluetooth1Timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    String bluetoothTestPush1 = "010d";
                    bluetoothTestPush1 += "\r";
                    new Bluetooth_Protocol().write(bluetoothTestPush1.getBytes());
                }
            },0,1500);
        }

    }
    public void bluetooth2TimerStart(){
        if(bluetooth2Timer == null){
            bluetooth2Timer = new Timer();
            bluetooth2Timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    String bluetoothTestPush2 = "010d";
                    bluetoothTestPush2 += "\r";
                    new Bluetooth_Protocol2().write(bluetoothTestPush2.getBytes());
                }
            },0,1500);
        }

    }

    public void bluetoothTimerStop(){
        try {
            if(bluetooth1Timer != null){
                bluetooth1Timer.cancel();
                bluetooth1Timer = null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(bluetooth2Timer != null){
                bluetooth2Timer.cancel();
                bluetooth2Timer = null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BluetoothTestPage_FLAG = false;
        bluetoothTestHandler = null;
        Bluetooth_Protocol.PidTestFlag = false;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bluetooth1Btn :
                new Bluetooth_Protocol().bluetoothTestAutoSearchBt(getContext());
                break;
            case R.id.bluetooth2Btn :
                new Bluetooth_Protocol2().bluetoothTest2AutoSearchBt(getContext());
                break;
        }
    }
}
