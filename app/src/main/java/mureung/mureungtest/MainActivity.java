package mureung.mureungtest;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import mureung.mureungtest.Communication.Bluetooth_Protocol;
import mureung.mureungtest.Communication.WifiConnection;
import mureung.mureungtest.Tool.Dlog;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.Time_DataBridge;
import mureung.mureungtest.View.BluetoothPairFragment;
import mureung.mureungtest.View.PidTestView.PidTestMainView;
import mureung.mureungtest.View.VoltageFragment;

import static mureung.mureungtest.Communication.Bluetooth_Protocol.btReceiver;

public class MainActivity extends AppCompatActivity{

    TextView connectText,dataText,protocolText,protocolNumText;

    public static Handler MainActivityHandler;
    public static Context mainContext;
    BroadcastReceiver broadcastReceiver;

    public static WifiP2pManager wifiP2pManager ;
    public static String VIN = null;
    //public static WifiP2pManager.Channel channel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Log.e("test","configApState() : " + configApState());

        mainContext = getBaseContext();
        mainChangeMenu(new MainView());
        connectText = (TextView)findViewById(R.id.connectText);
        connectText.setText("연결 없음");
        dataText = (TextView)findViewById(R.id.dataText);
        dataText.setText("Data");
        protocolText = (TextView)findViewById(R.id.protocolText);
        protocolText.setText("Protocol : ");
        protocolNumText = (TextView)findViewById(R.id.protocolNumText);
        protocolNumText.setText("Protocol Num : ");


        wifiP2pManager = (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
        //channel = wifiP2pManager.initialize(this,getMainLooper(),null);


        ConnectivityManager connectivityManager = (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager !=null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null){
                Log.e("test","ConnectivityManager.TYPE_WIFI : " + ConnectivityManager.TYPE_WIFI);
                Log.e("test","ConnectivityManager.TYPE_MOBILE : " + ConnectivityManager.TYPE_MOBILE);
                Log.e("test","networkInfo : " + networkInfo.getType());
            }else {
                Log.e("test","networkInfo : null");
            }

        }

        MainActivityHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case 1 :
                        String connectState = String.valueOf(msg.obj);
                        connectText.setText(connectState);
                        break;
                    case 2 :
                        Toast.makeText(MainActivity.mainContext,String.valueOf(msg.obj),Toast.LENGTH_LONG).show();
                        break;
                    case 3 :
                            dataText.setText(String.valueOf(msg.obj));
                        break;
                    case 4 :
                        mainChangeMenu(new PidTestMainView());
                        break;
                    case 5 :
                        protocolText.setText(String.valueOf(msg.obj));
                        break;

                    case 6 :
                        protocolNumText.setText(String.valueOf(msg.obj));
                        break;
                    case 7 :
                        final Toast toast = Toast.makeText(MainActivity.mainContext,String.valueOf(msg.obj),Toast.LENGTH_SHORT);
                        toast.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        },700);

                        break;
                    case 8 :
                        mainChangeMenu(new VoltageFragment());
                        break;
                }


                return true;
            }
        });

        checkPermission(this);

        /*BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.startDiscovery();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    Log.e("MainActivity","test onReceive getName :  "+device.getName()+"   , getAddress : "+device.getAddress());


                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(broadcastReceiver, filter);*/

        //this.registerReceiver(headsetReceiver,headsetReceiverIntentFilter);


        /*Dlog.e("WifiConnection Test Start");
        new WifiConnection().startWifiScan(getBaseContext());*/


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(btReceiver != null){
            getApplicationContext().unregisterReceiver(btReceiver);
        }
    }

    public void mainChangeMenu(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        switch (PageStr.getPageStrData()){
            case PageStr.Mainview :
                new DialogManager(this).positiveNegativeDialog("앱 종료", "앱을 종료하시겠습니까? ",
                        "확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(Process.myPid());

                            }
                        }, "취소",null);
                break;
            case PageStr.PidTestView :
                mainChangeMenu(new MainView());
                if(MainView.PID.contains(new MainView().ALLPID)){
                    String data = "------------------------------------------------------------------------------ \r\n";
                    new ErrorLogManager().saveErrorLog("DATA",String.valueOf(data+new Time_DataBridge().getDateTime()+"\r\n"+data+ "VIN : "+VIN+"\r\n"+data + Bluetooth_Protocol.saveData));
                }
                MainView.PID = null;

                break;
            case PageStr.BluetoothConnect:
                mainChangeMenu(new MainView());
                break;
            case PageStr.Terminal:
                mainChangeMenu(new MainView());
                break;
            case PageStr.Voltage :
                mainChangeMenu(new MainView());
                break;
            case PageStr.BluetoothTest :
                new BluetoothPairFragment().bluetoothTimerStop();
                Bluetooth_Protocol.PidTestFlag = false;
                mainChangeMenu(new MainView());
                break;
            case PageStr.CameraPushTest:
                mainChangeMenu(new MainView());
                break;
            case PageStr.CameraPullTest:
                mainChangeMenu(new MainView());
                break;
            case PageStr.WifiTest :
                mainChangeMenu(new MainView());
                break;
        }
    }

    public static boolean isPermission(Context context, String strPermission){
        return ActivityCompat.checkSelfPermission(context, strPermission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    !isPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    !isPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    !isPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    !isPermission(context, Manifest.permission.CAMERA) ||
                    !isPermission(context, Manifest.permission.RECORD_AUDIO) ||

                    !isPermission(context, Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.RECORD_AUDIO) ||

                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                }
                ActivityCompat.requestPermissions((Activity)context,new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,       // 위치 서비스(정확한 위치 판별 | GPS, Wi-Fi 또는 데이터 사용)
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,     // 위치 서비스(대략적인 위치 판별 | Wi-Fi 또는 데이터 사용)
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,      // 사진, 미디어, 파일 읽기
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,     // 사진, 미디어, 파일 쓰기
                                android.Manifest.permission.CAMERA,                     // 사진 찍기, 비디오 녹화
                                android.Manifest.permission.RECORD_AUDIO,               // 오디오 녹음
                                android.Manifest.permission.CALL_PHONE,                 // 전화 걸기, 관리
                                android.Manifest.permission.MODIFY_AUDIO_SETTINGS},
                        1000);
            }
            //다른앱으로 그리기 권한은 설정창으로 이동해서 사용자가 직접 입력을 해야 바꿀수있음
            if(Settings.canDrawOverlays(context)){
                if(context != null){

                }

            }else{
                if(context != null){

                    Uri uri = Uri.fromParts("package",context.getPackageName(),null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,uri);
                    context.startActivity(intent);
                }

            }
        }

    }


    public class BluetoothThread extends Thread{
        private Context context;
        private String obdSN;
        public BluetoothThread(Context context, String obdSN){

            this.context = context;
            this.obdSN = obdSN;

        }
        @Override
        public void run() {
            super.run();


            /*if(obdSN!=null){
                if(!obdSN.equals("")&&!obdSN.equals("null")){
                    //자동연결 시도
                    if(!FirstAutoSearchFlag){
                        Dlog.i(" auto Connect");
                        try {
                            new Bluetooth_Protocol().autoSearchBt(context,obdSN);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }


                }
            }*/
        }
    }


    /**
     * 2018.04.04 by.GeonHo
     * 핫스팟 자동으로 켜기 부분
     * configApState() 키면 자동으로 켜진다.
     * */

    private boolean isApOn(){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        try{


            Method method = wifiManager.getClass().getDeclaredMethod("isWifiApEnabled");
            method.setAccessible(true);
            Log.e("test","(Boolean) method.invoke(wifiManager) : " + (Boolean) method.invoke(wifiManager));
            return (Boolean) method.invoke(wifiManager);

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean configApState(){
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiConfiguration wifiConfiguration = null;
        try{
            if(isApOn()){
                //wifiManager.setWifiEnabled(false);
            }
            Method method = wifiManager.getClass().getDeclaredMethod("setWifiApEnabled",WifiConfiguration.class,boolean.class);
            method.invoke(wifiManager,wifiConfiguration,!isApOn());
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
