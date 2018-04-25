package mureung.mureungtest.Communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import mureung.mureungtest.MainActivity;

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

            //answerPhoneHeadsethook(context,intent);
            new RingingTask(context, phone_number).execute();
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
            isFind = false;

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                String question = "";



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
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //acceptCall(context);
                    }
                }, 0);*/

                Log.e("test","startIntent");

                /*Intent intent = new Intent(Intent.ACTION_ANSWER);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                        | Intent.FLAG_DEBUG_LOG_RESOLUTION
                        | Intent.FLAG_FROM_BACKGROUND
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.sendOrderedBroadcast(intent,null);*/

                Intent intent = new Intent(context, AcceptCallActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(intent);
                Log.e("test","stopIntent");

                //acceptCall(context);
            }
        }
    }
    /*String TAG = "Device_Stream";
    public void answerPhoneHeadsethook(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d(TAG, "Incoming call from: " + number);
            Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
            buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
            try {
                context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
                Log.d(TAG, "ACTION_MEDIA_BUTTON broadcasted...");
            }
            catch (Exception e) {
                Log.d(TAG, "Catch block of ACTION_MEDIA_BUTTON broadcast !");
            }

            Intent headSetUnPluggedintent = new Intent(Intent.ACTION_HEADSET_PLUG);
            headSetUnPluggedintent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY);
            headSetUnPluggedintent.putExtra("state", 1); // 0 = unplugged  1 = Headset with microphone 2 = Headset without microphone
            headSetUnPluggedintent.putExtra("name", "Headset");
            // TODO: Should we require a permission?
            try {
                context.sendOrderedBroadcast(headSetUnPluggedintent, "android.intent.action.HEADSET_PLUG");
                Log.d(TAG, "ACTION_HEADSET_PLUG broadcasted ...");
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d(TAG, "Catch block of ACTION_HEADSET_PLUG broadcast");
                Log.d(TAG, "Call Answered From Catch Block !!");
            }
            Log.d(TAG, "Answered incoming call from: " + number);
        }
        Log.d(TAG, "Call Answered using headsethook");
    }*/
}
