package mureung.mureungtest.Communication;

import android.app.Instrumentation;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mureung.mureungtest.Tool.ErrorLogManager;


public class Device_Stream extends BroadcastReceiver {


    String state;

    private static String mLastState;

    @Override
    public void onReceive(Context context, Intent intent) {


        state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (mLastState != null) {
            if (state.equals(mLastState)) {
                return;
            } else {
                mLastState = state;
            }
        }
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {

            String incommingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String phone_number = PhoneNumberUtils.formatNumber(incommingNumber);

            answerPhoneHeadsethook(context,intent);


            //new RingingTask(context, phone_number).execute();
        }
        String action = intent.getAction();
        Log.e("MurengTest","Device_Straem action : " + action);
        try {
            final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if(device != null){
                Log.e("Device_Stream","device getAddress : " + device.getAddress() + " , getName : " + device.getName()
                        + " , getBondState : " + device.getBondState() + " , getType : " + device.getType()
                        + " , getUuids : " + Arrays.toString(device.getUuids()));

                int majorClass= device.getBluetoothClass().getMajorDeviceClass();
                int minorClass = device.getBluetoothClass().getDeviceClass();
                Log.e("Device_Stream","블루투스 majorClass : " + majorClass + " , minorClass : " + minorClass + " , getName : " + device.getName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        if (action != null && action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
            /*final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.e("Device_Stream","device getAddress : " + device.getAddress() + " , getName : " + device.getName()
                    + " , getBondState : " + device.getBondState() + " , getType : " + device.getType()
                    + " , getUuids : " + Arrays.toString(device.getUuids()));

            int majorClass= device.getBluetoothClass().getMajorDeviceClass();
            int minorClass = device.getBluetoothClass().getDeviceClass();*/

            /*Log.e("Device_Stream","블루투스 majorClass : " + majorClass);
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String test = simpleDateFormat.format(date);
            new ErrorLogManager().saveErrorLog(test + "   Connect majorClass :" + String.valueOf(majorClass) + " , minorClass : " +  minorClass);*/






        }


    }



    public class RingingTask extends AsyncTask<String, Void, String> {
        Context context;
        boolean isFind = false;
        String number = "";
        String name = "";

        public RingingTask(Context context, String number) {
            this.context = context;
            this.number = number;
        }


        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                String question = "";

                // 벨소리 볼륨값 저장
                /*ringingVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                // 벨소리 볼륨값 0으로 설정.
                try {
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                } catch (Exception e) {

                    e.printStackTrace();
                }*/


                // TODO
                /*if (isFind){
                    question = name + "님께서 전화를 거셨습니다. 받으시겠습니까?";
                }else{
                    question = number + "님께서 전화를 거셨습니다. 받으시겠습니까?";
                }

                callingThread = InfoCarAssistant.conversation("수신", question);
                callingThread.start();*/

                if (isFind){
                    question = name + "님께서 전화를 거셨습니다.";
                }else{
                    question = number + "님께서 전화를 거셨습니다.";
                }



                // 전화 끊는 함수
                //declineCall();

                // 전화 받는 함수.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //acceptCall(context);
                    }
                }, 0);

                //acceptCall(context);
            }
        }
    }


    String TAG = "Device_Stream";
    public void answerPhoneHeadsethook(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d(TAG, "Incoming call from: " + number);
            Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);
            buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
            try {
                context.sendOrderedBroadcast(buttonDown, "android.permission.CALL_PRIVILEGED");

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
            buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
            try {
                context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
                Log.d(TAG, "ACTION_MEDIA_BUTTON broadcasted...");
            }
            catch (Exception e) {
                Log.d(TAG, "Catch block of ACTION_MEDIA_BUTTON broadcast !");
            }




            /*Intent headSetUnPluggedintent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                headSetUnPluggedintent = new Intent(AudioManager.ACTION_HEADSET_PLUG);
            }else {
                headSetUnPluggedintent = new Intent(Intent.ACTION_HEADSET_PLUG);
            }

            headSetUnPluggedintent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY);
            headSetUnPluggedintent.putExtra("state", 1); // 0 = unplugged  1 = Headset with microphone 2 = Headset without microphone
            headSetUnPluggedintent.putExtra("name", "Headset");



            // TODO: Should we require a permission?
            try {
                context.sendOrderedBroadcast(headSetUnPluggedintent, null);
                Log.d(TAG, "ACTION_HEADSET_PLUG broadcasted ...");
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d(TAG, "Catch block of ACTION_HEADSET_PLUG broadcast");
                Log.d(TAG, "Call Answered From Catch Block !!");
            }
            Log.d(TAG, "Answered incoming call from: " + number);*/
        }
        Log.d(TAG, "Call Answered using headsethook");
    }
}
