package mureung.mureungtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.Tool.MakeData;
import mureung.mureungtest.View.BluetoothConnect.BluetoothConnect;
import mureung.mureungtest.View.PidTestView.PidTestMainView;
import mureung.mureungtest.View.Terminal.TerminalView;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.PidTestFlag;

/**
 * Created by user on 2018-01-29.
 */

public class MainView extends Fragment implements View.OnClickListener {

    LinearLayout pidTestBtn,terminalButton,emailButton;
    LinearLayout bluetoothConnect;
    ImageView bluetoothIcon;
    static Intent outIntent;
    private static Handler bluetoothIconHandler;
    public static boolean bluetoothState;
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
        bluetoothIcon = (ImageView) view.findViewById(R.id.bluetoothIcon);
        /*emailButton = ( LinearLayout)view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);*/


        PageStr.setPageStrData(PageStr.Mainview);
        PidTestFlag = false;
        bluetoothIconHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case 1 :
                        bluetoothIcon.setImageDrawable(MainActivity.mainContext.getDrawable(R.drawable.obd_on));
                        break;
                    case 2 :
                        bluetoothIcon.setImageDrawable(MainActivity.mainContext.getDrawable(R.drawable.obd_off));
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
            if(bluetoothIconHandler != null){
                if(state){
                    bluetoothIconHandler.obtainMessage(1,null).sendToTarget();
                }else{
                    bluetoothIconHandler.obtainMessage(2,null).sendToTarget();
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
                if(MakeData.FinishLog_FLAG|| !Bluetooth_Protocol.BluetoothConnect){
                    ((MainActivity)getActivity()).mainChangeMenu(new PidTestMainView());
                }else {
                    Toast.makeText(getContext(),"데이터를 쓰는중입니다. 잠시후 시도 해보십시오.",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.bluetoothConnect:
                ((MainActivity)getActivity()).mainChangeMenu(new BluetoothConnect());
                break;
            case R.id.terminalButton:
                ((MainActivity)getActivity()).mainChangeMenu(new TerminalView());
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

    private void displayMirror(){

    }
}
