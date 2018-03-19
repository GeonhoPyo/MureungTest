package mureung.mureungtest;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import mureung.mureungtest.View.BluetoothPairFragment;
import mureung.mureungtest.View.PidTestView.PidTestMainView;
import mureung.mureungtest.View.VoltageFragment;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.btReceiver;

public class MainActivity extends AppCompatActivity{

    TextView connectText,dataText,protocolText,protocolNumText;

    public static Handler MainActivityHandler;
    public static Context mainContext;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = getBaseContext();
        mainChangeMenu(new MainView());
        connectText = findViewById(R.id.connectText);
        connectText.setText("연결 없음");
        dataText = findViewById(R.id.dataText);
        dataText.setText("Data");
        protocolText = findViewById(R.id.protocolText);
        protocolText.setText("Protocol : ");
        protocolNumText = findViewById(R.id.protocolNumText);
        protocolNumText.setText("Protocol Num : ");


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
                    !isPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    !isPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                }
                ActivityCompat.requestPermissions((Activity)context,new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }

            if(Settings.canDrawOverlays(context)){
                if(context != null){
                    Log.e("test","다른 앱 위에 그리기 권한  "+Settings.canDrawOverlays(context));
                }

            }else{
                if(context != null){
                    Log.e("test","다른 앱 위에 그리기 권한  "+Settings.canDrawOverlays(context));
                    Uri uri = Uri.fromParts("package",context.getPackageName(),null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

}
