package mureung.mureungtest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import java.util.regex.Pattern;

import mureung.mureungtest.Tool.ErrorLogManager;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.btReceiver;

public class MainActivity extends AppCompatActivity {

    TextView connectText,dataText;
    public static Handler connectTextHandler;
    public static Context mainContext;

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

        connectTextHandler = new Handler(new Handler.Callback() {
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
                }


                return true;
            }
        });

        checkPermission(this);


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
                break;
            case PageStr.BluetoothConnect:
                mainChangeMenu(new MainView());
                break;
            case PageStr.Terminal:
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

}