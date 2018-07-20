package mureung.mureungtest;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import mureung.mureungtest.Communication.AcceptCallActivity;
import mureung.mureungtest.Communication.Bluetooth_Protocol;
import mureung.mureungtest.Communication.Bluetooth_Protocol3;
import mureung.mureungtest.Communication.ConnectThread;
import mureung.mureungtest.Tool.*;
import mureung.mureungtest.View.BluetoothConnect.BluetoothConnect;
import mureung.mureungtest.View.BluetoothPairFragment;
import mureung.mureungtest.View.Camera.CameraPushFragment;
import mureung.mureungtest.View.Camera.CameraPullFragment;
import mureung.mureungtest.View.Terminal.TerminalView;
import mureung.mureungtest.View.Wifi.WifiTestFragment;

import static mureung.mureungtest.Communication.Bluetooth_Protocol.PidTestFlag;

/**
 * Created by user on 2018-01-29.
 */

public class MainView extends Fragment implements View.OnClickListener {

    LinearLayout pidTestBtn,terminalButton,pidScheduleBtn, diagnosisButton,voltageButton,bluetoothPairButton, cameraPushButton,cameraPullButton;
    LinearLayout bluetoothConnect;
    LinearLayout wifiTestButton;
    ImageView bluetoothIcon;
    static Intent outIntent;
    public static Handler mainViewHandler;
    public static boolean bluetoothState;
    public final String ALLPID = "ALL_PID";
    public final String SCHEDULEPID = "SCHEDULE_PID";
    public static String PID = null;
    private int i  = 0 ;
    public static boolean Diagnosis_FLAG = false;
    public static String diagnosisVin = null;
    public static boolean Voltage_FLAG = false;
    public static boolean PidTestStart_FLAG = false;
    public static boolean TerminalStart_FLAG = false;
    public static boolean DiagnosisStart_FLAG = false;
    public static boolean VoltageStart_FLAG = false;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainview,container,false);

        pidTestBtn = (LinearLayout) view.findViewById(R.id.pidTestBtn);
        pidTestBtn.setOnClickListener(this);
        bluetoothConnect = (LinearLayout)view.findViewById(R.id.bluetoothConnect);
        bluetoothConnect.setOnClickListener(this);
        terminalButton = (LinearLayout) view.findViewById(R.id.terminalButton);
        terminalButton.setOnClickListener(this);

        pidScheduleBtn = (LinearLayout) view.findViewById(R.id.pidScheduleBtn);
        pidScheduleBtn.setOnClickListener(this);
        diagnosisButton = (LinearLayout)view.findViewById(R.id.diagnosisButton);
        diagnosisButton.setOnClickListener(this);
        voltageButton = (LinearLayout)view.findViewById(R.id.voltageButton);
        voltageButton.setOnClickListener(this);
        bluetoothIcon = (ImageView) view.findViewById(R.id.bluetoothIcon);

        bluetoothPairButton = (LinearLayout)view.findViewById(R.id.bluetoothPairButton);
        bluetoothPairButton.setOnClickListener(this);

        cameraPushButton = (LinearLayout)view.findViewById(R.id.cameraPushButton);
        cameraPushButton.setOnClickListener(this);
        cameraPullButton = (LinearLayout)view.findViewById(R.id.cameraPullButton);
        cameraPullButton.setOnClickListener(this);

        wifiTestButton = (LinearLayout)view.findViewById(R.id.wifiTestButton);
        wifiTestButton.setOnClickListener(this);

        /*emailButton = ( LinearLayout)view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);*/


        PageStr.setPageStrData(PageStr.Mainview);
        PidTestFlag = false;
        mainViewHandler = new Handler(new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case 1 :
                        bluetoothIcon.setImageDrawable(MainActivity.mainContext.getDrawable(R.drawable.obd_on));
                        break;
                    case 2 :
                        bluetoothIcon.setImageDrawable(MainActivity.mainContext.getDrawable(R.drawable.obd_off));
                        break;
                    case 3:
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        };
                        try {
                            new DialogManager(getContext()).positiveDialog("DTC 고장 코드", String.valueOf(msg.obj),"확인",onClickListener);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        break;
                }
                return true;
            }
        });
        if(bluetoothState){
            bluetoothIcon.setImageDrawable(getContext().getDrawable(R.drawable.obd_on));
        }else {
            bluetoothIcon.setImageDrawable(getContext().getDrawable(R.drawable.obd_off));
        }












        return view;
    }

    public void setObdIcon(boolean state){
        try {
            if(mainViewHandler != null){
                if(state){
                    mainViewHandler.obtainMessage(1,null).sendToTarget();
                }else{
                    mainViewHandler.obtainMessage(2,null).sendToTarget();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pidTestBtn:
                if(!PidTestStart_FLAG){
                    PidTestStart_FLAG = true;
                    Bluetooth_Protocol.SETTING_FLAG = false;
                    PID = ALLPID;
                    new Bluetooth_Protocol().pushATSetting();

                    if(MakeData.FinishLog_FLAG|| !Bluetooth_Protocol.BluetoothConnect){
                        //((MainActivity)getActivity()).mainChangeMenu(new PidTestMainView());
                    }else {
                        Toast.makeText(getContext(),"데이터를 쓰는중입니다. 기다려 주십시오. ",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.bluetoothConnect:
                ((MainActivity)getActivity()).mainChangeMenu(new BluetoothConnect());
                break;
            case R.id.terminalButton:
                if(!TerminalStart_FLAG){
                    TerminalStart_FLAG = true;
                    ((MainActivity)getActivity()).mainChangeMenu(new TerminalView());
                }

                break;

            case R.id.pidScheduleBtn :
                if(!PidTestStart_FLAG){
                    PidTestStart_FLAG = true;
                    Bluetooth_Protocol.SETTING_FLAG = false;
                    PID = SCHEDULEPID;
                    new Bluetooth_Protocol().pushATSetting();

                    if(MakeData.FinishLog_FLAG|| !Bluetooth_Protocol.BluetoothConnect){
                        //((MainActivity)getActivity()).mainChangeMenu(new PidTestMainView());
                    }else {
                        Toast.makeText(getContext(),"데이터를 쓰는중입니다. 잠시만 기다리세요.",Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.diagnosisButton :
                if(!DiagnosisStart_FLAG){
                    DiagnosisStart_FLAG = true;
                    startDiagnosis();
                }


                break;

            case R.id.voltageButton :
                if(!VoltageStart_FLAG){
                    VoltageStart_FLAG = true;
                    startVoltage();
                }

                break;

            case R.id.bluetoothPairButton :
                ((MainActivity)getActivity()).mainChangeMenu(new BluetoothPairFragment());
                new Bluetooth_Protocol().autoSearchBt(getContext(),null);
                break;

            case R.id.cameraPushButton:
                ((MainActivity)getActivity()).mainChangeMenu(new CameraPushFragment());
                break;

            case R.id.cameraPullButton :
                ((MainActivity)getActivity()).mainChangeMenu(new CameraPullFragment());
                break;

            /*case R.id.emailButton:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"pyogh.mureung@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"BluetoothErrorLog");
                email.putExtra(Intent.EXTRA_TEXT,"test");
                startActivity(email);
                break;*/

            case R.id.wifiTestButton :
                //((MainActivity)getActivity()).mainChangeMenu(new WifiTestFragment());




                Log.e("test","test 1111");
                /*if(Build.VERSION.SDK_INT >= 21){
                    Intent answerCalintent = new Intent(getContext(), AcceptCallActivity.class);
                    answerCalintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    answerCalintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(answerCalintent);
                }else {
                    Intent intent = new Intent(getContext(), AcceptCallActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                }*/
                /**
                 *
                 * 123213213
                 *
                 * */
                /*v.setX(100);
                v.setY(100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    v.callOnClick();
                }*/

                break;

        }
    }



    private void startVoltage(){

        if(!Bluetooth_Protocol.BluetoothConnect){
            Toast.makeText(getContext(),"OBD 연결 후 전압 테스트를 하십시오.",Toast.LENGTH_LONG).show();
        }else {


            if(!Voltage_FLAG){
                Toast.makeText(getContext(),"초기 설정 중 입니다 . 잠시만 기다려주십시오 .",Toast.LENGTH_LONG).show();
                final String[] pushData = {
                        "AT Z",
                        "AT SP0",
                        "AT H1",
                        "AT E1",
                        "AT L0",
                        "AT S1",
                        "0902"
                };
                Voltage_FLAG = true;

                new Bluetooth_Protocol().pushATSetting();

                /*final Timer voltageTimer = new Timer();
                Timer settingTimer = new Timer();
                settingTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(pushData.length > i){
                            String push = pushData[i];
                            push += "\r";
                            new Bluetooth_Protocol().write(push.getBytes());
                            i++;
                        }else {
                            ((MainActivity)getActivity()).mainChangeMenu(new VoltageFragment());
                            cancel();
                            i = 0;
                            voltageTimer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    if(!Voltage_FLAG){
                                        cancel();
                                    }else {
                                        String voltagePush ="AT RV";
                                        voltagePush += "\r";
                                        new Bluetooth_Protocol().write(voltagePush.getBytes());
                                    }

                                }


                            },500,1000);
                        }

                    }
                },0,1000);*/
            }else {

                try {
                    Voltage_FLAG = false;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }
    }

    private void startDiagnosis(){
        if(!Bluetooth_Protocol.BluetoothConnect){
            Toast.makeText(getContext(),"OBD 연결 후 진단을 하십시오.",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"진단 중입니다. 잠시만 기다려 주십시오.",Toast.LENGTH_LONG).show();
            /*final String[] pushData = {
                    "AT Z",
                    "AT SP0",
                    "AT H1",
                    "AT E1",
                    "AT L0",
                    "AT S1",
                    "0902",
                    "03"
            };*/
            if(!Diagnosis_FLAG){
                Diagnosis_FLAG = true;
                new Bluetooth_Protocol().pushATSetting();
            }
            /*Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(pushData.length > i){

                        String push = pushData[i];
                        push += "\r";
                        new Bluetooth_Protocol().write(push.getBytes());
                        i++;
                    }else {
                        cancel();
                        i = 0;
                    }

                }
            },0,1000);*/



            /*DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            new mureung.mureungtest.Tool.DialogManager(getContext()).positiveDialog("DTC 고장 코드","P0101","확인",onClickListener);*/
        }
    }




}
