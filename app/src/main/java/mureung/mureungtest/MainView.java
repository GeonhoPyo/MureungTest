package mureung.mureungtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.Tool.*;
import mureung.mureungtest.View.BluetoothConnect.BluetoothConnect;
import mureung.mureungtest.View.PidTestView.PidTestMainView;
import mureung.mureungtest.View.Terminal.TerminalView;
import mureung.mureungtest.View.VoltageFragment;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.PidTestFlag;

/**
 * Created by user on 2018-01-29.
 */

public class MainView extends Fragment implements View.OnClickListener {

    LinearLayout pidTestBtn,terminalButton,pidScheduleBtn, diagnosisButton,voltageButton;
    LinearLayout bluetoothConnect;
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
        /*emailButton = ( LinearLayout)view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);*/


        PageStr.setPageStrData(PageStr.Mainview);
        PidTestFlag = false;
        mainViewHandler = new Handler(new Handler.Callback() {
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
                Bluetooth_Protocol.SETTING_FLAG = false;
                PID = ALLPID;
                new Bluetooth_Protocol().pushATSetting();

                if(MakeData.FinishLog_FLAG|| !Bluetooth_Protocol.BluetoothConnect){
                    //((MainActivity)getActivity()).mainChangeMenu(new PidTestMainView());
                }else {
                    Toast.makeText(getContext(),"데이터를 쓰는중입니다. 기다려 주십시오. ",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bluetoothConnect:
                ((MainActivity)getActivity()).mainChangeMenu(new BluetoothConnect());
                break;
            case R.id.terminalButton:
                ((MainActivity)getActivity()).mainChangeMenu(new TerminalView());
                break;

            case R.id.pidScheduleBtn :
                Bluetooth_Protocol.SETTING_FLAG = false;
                PID = SCHEDULEPID;
                new Bluetooth_Protocol().pushATSetting();

                if(MakeData.FinishLog_FLAG|| !Bluetooth_Protocol.BluetoothConnect){
                    ((MainActivity)getActivity()).mainChangeMenu(new PidTestMainView());
                }else {
                    Toast.makeText(getContext(),"데이터를 쓰는중입니다. 잠시후 시도 해보십시오.",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.diagnosisButton :
                startDiagnosis();

                break;

            case R.id.voltageButton :
                startVoltage();
                break;
            /*case R.id.emailButton:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"pyogh.mureung@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"BluetoothErrorLog");
                email.putExtra(Intent.EXTRA_TEXT,"test");
                startActivity(email);
                break;*/

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
                        "AT S1"
                };
                Voltage_FLAG = true;
                final Timer voltageTimer = new Timer();
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
                                    }
                                    String voltagePush ="AT RV";
                                    voltagePush += "\r";
                                    new Bluetooth_Protocol().write(voltagePush.getBytes());
                                }


                            },500,1000);
                        }

                    }
                },0,500);
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
            final String[] pushData = {
                    "AT Z",
                    "AT SP0",
                    "AT H1",
                    "AT E1",
                    "AT L0",
                    "AT S1",
                    "0902",
                    "03"
            };
            if(!Diagnosis_FLAG){
                Diagnosis_FLAG = true;
            }
            Timer timer = new Timer();
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
            },0,1000);



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
