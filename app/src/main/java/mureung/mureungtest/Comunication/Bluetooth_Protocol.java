package mureung.mureungtest.Comunication;


import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Pattern;

import mureung.mureungtest.MainActivity;
import mureung.mureungtest.MainView;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.MakeData;
import mureung.mureungtest.Tool.SearchVINTask;
import mureung.mureungtest.Tool.Time_DataBridge;
import mureung.mureungtest.View.Terminal.TerminalView;
import mureung.mureungtest.View.VoltageFragment;

import static mureung.mureungtest.Comunication.Bypass_Stream.dataVIN;
import static mureung.mureungtest.Tool.SearchVINTask.strMaker;
import static mureung.mureungtest.Tool.SearchVINTask.strModel;
import static mureung.mureungtest.Tool.SearchVINTask.strYear;


/**
 * Created by user on 2017-06-08.
 */

public class Bluetooth_Protocol {

    public static boolean OBD_CONNECTSTATE = false;


    private static final String TAG = "Bluetooth_Protocol";

    public static BluetoothAdapter bluetoothAdapter;

    //UUID
    private static final UUID btUUID = UUID
            .fromString("00001101-0000-1000-8000-00805f9b34fb");
    //00001101-0000-1000-8000-00805f9b34fb
    //00000000-0000-1000-8000-00805F9B34FB


    //private static ArrayList<ListAdapter_OBDConnect.btList> btPairedArrayList = null;
    private static ConnectThread mConnectThread;
    private static ConnectedThread mConnectedThread;


    private static Handler dataHandler;
    private static String btAddress;

    public static int mState = 0;


    //태그 값들 지정해줘야함
    private static final int STATE_LISTEN = 1; // now listening for incoming
    private static final int STATE_CONNECTING = 2; // now initiating an outgoing
    private static final int STATE_CONNECTED = 3; // now connected to a remote
    private static final int REQUEST_ENABLE_BT = 3;
    private static final int MESSAGE_READ =3;
    private static final int CONNECT_FAIL = 4;
    public static boolean SETTING_FLAG = false;
    //public static boolean REPAIR_FLAG = false;


    public static BroadcastReceiver btReceiver;


    public static int CONNECTED_STATE = 0;

    private static boolean REPAIR_FLAG =false;

    public static boolean PidTestFlag = false;

    public static boolean BluetoothConnect = false;
    public static String obdVersion = null;
    public static String protocolData = null;
    public static String protocolDataNum = null;

    public static String pushVIN = null;

    public Bluetooth_Protocol(Activity activity , Handler handler) {
        dataHandler = handler;

    }
    public Bluetooth_Protocol() {


    }
    //블루투스 사용시 사용할 어레이 리스트, Adapter 등 초기 선언부분
    /*private void bluetoothProtocol(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btPairedArrayList = new ArrayList<ListAdapter_OBDConnect.btList>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            BluetoothLe_Protocol bluetoothLe_protocol = new BluetoothLe_Protocol(dataHandler);
        }


    }*/

    public ArrayList<BtList> enableBluetooth(){
        ArrayList<BtList> btListArrayList = new ArrayList<BtList>();
        if(getState()!=3){
            if(bluetoothAdapter!=null && !bluetoothConnected()){
                setState(STATE_LISTEN);
                //페어링된 기기
                Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
                if(pairedDevice.size()>0){
                    for(BluetoothDevice device : pairedDevice){
                        BtList btPairedList;
                        btPairedList = new BtList(device.getName(),device.getAddress());
                        btListArrayList.add(btPairedList);
                        Log.e("test","device.getName : " + device.getName() + " , device.getAddress : " + device.getAddress());
                    }

                }

            }
            return btListArrayList;
        }else {
            return null;
        }

    }




    //device 정보 가져오기 및 연결 시도
    /*public void getDeviceInfo(String address) {


        btAddress = address;
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(btAddress);
        Log.e(TAG, "Get Device Info \n" + "address : " + btAddress);

        connect(device);


    }*/


    //블루투스 연결 Thread
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice ;
        Handler obdContectFragmentHandler;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        private ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            if(MainActivity.MainActivityHandler != null){
                MainActivity.MainActivityHandler.obtainMessage(1,"연결중").sendToTarget();
                BluetoothConnect = true;
            }
            BluetoothSocket secureRfComm = null;
            // 디바이스 정보를 얻어서 BluetoothSocket 생성
            btAddress = device.getAddress();
            Log.e("device"," getName : "+device.getName()+" , getBoundState : "+device.getBondState() + " , getAddress : " + device.getAddress() + " , getType :" + device.getType());

            /**
             * 2018.01.09 by.GeonHo
             * 여기서 createRfcommSocketToServiceRecord 랑
             *       createInsecureRfcommSocketToServiceRecord 분류 해야할수도
             * */
            try {
                //type 정해야겟다
                /*if(device.getType() != 2){
                    Log.e("test","createRfcommSocketToServiceRecord");
                    secureRfComm = device.createRfcommSocketToServiceRecord(btUUID);
                }else {
                    Log.e("test","createInsecureRfcommSocketToServiceRecord");
                    secureRfComm = device.createInsecureRfcommSocketToServiceRecord(btUUID);
                }*/
                secureRfComm = device.createInsecureRfcommSocketToServiceRecord(btUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmSocket = secureRfComm;
        }
        //블루투스 연결 스레드 시작
        public void run() {
            Log.e(TAG, "BEGIN mConnectThread");
            if(bluetoothAdapter != null){
                bluetoothAdapter.cancelDiscovery();
            }

            try {

                mmSocket.connect();


                Log.e(TAG, "Connect Success");
            } catch (IOException e) {
                connectionFailed(mmDevice.getAddress());

                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG,
                            "unable to close() socket during connection failure",
                            e2);
                }

                return;
            }
            synchronized (Bluetooth_Protocol.this) {
                mConnectThread = null;
            }

            //연결 스레드 run 부분을 다 통과하게 된다면 연결 후 통신 스레드 시작
            connected(mmSocket);
        }
        private void cancel() {
            try {
                mmSocket.close();
                if(MainActivity.MainActivityHandler != null){
                    MainActivity.MainActivityHandler.obtainMessage(1,"연결끊김").sendToTarget();
                    MakeData.fileName = null;
                    BluetoothConnect = false;
                    PidTestFlag = false;
                }
                new MainView().setObdIcon(false);
                MainView.bluetoothState = false;
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }




    //블루투스 연결 후 통신을 위한 Thread
    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Bypass_Stream bypass_stream;
        int responsTime[] = new int[2];
        final int writeTime = 0;
        final int readTime = 1;

        final int ATZ_SETTING = 1;
        final int ATSP0_SETTING = 2;
        final int ATH1_SETTING = 3;
        final int VIN_SETIING = 4;
        final int ATECHO_SETTING = 5;
        final int ATLINEFEED_SETTING = 6;
        final int ATSPACE_SETTING = 7;
        final int ATDPN_SETTING = 8;
        final int ATDP_SETTING = 9;

        private boolean ATZ_FLAG = false;
        private boolean ATSP0_FLAG = false;
        private boolean ATH1_FLAG = false;
        private boolean VIN_FLAG = false;
        private boolean ATECHO_FLAG = false;
        private boolean ATLINEFEED_FLAG = false;
        private boolean ATSPACE_FLAG = false;
        private boolean ATDP_FLAG = false;
        private boolean ATDPN_FLAG = false;

       /* private String[] protocol = {"3","6","1","2","4","5","7","8","9","A","B","C","0"};
        private int protocolCount = 0;
        private boolean Protocol_FLAG=false;
        private boolean ProtocolCheck_FLAG = false;
        private int saveProtocolCount = 0;*/

        private boolean NoData_FLAG = false;

        private int NoDataCount = 0;
        private int i = 0;
        private boolean Diagnosis_Push_FLAG = false;



        //통신에 필요한 Socket 을 선언 및 접속 하는 부분 = 블루투스 송수신 접속
        private ConnectedThread(BluetoothSocket socket) {

            Log.e(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            Log.e("ConnectedThread","mmSocket "+mmSocket);
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
            bypass_stream = new Bypass_Stream();
            CONNECTED_STATE = 1;
            if(MainActivity.MainActivityHandler != null){
                MainActivity.MainActivityHandler.obtainMessage(1,"연결됨").sendToTarget();
            }
            new MainView().setObdIcon(true);
            MainView.bluetoothState = true;

        }

        //블루투스 데이터 수신을 스레드로 항상 수신대기함
        public void run() {
            Log.e(TAG, "BEGIN mConnectedThread");

            byte[] readBuffer = new byte[1024];
            int bytes = 0;
            String received_text = "";

            while (true) {

                try {

                    //String 으로 변환
                    bytes = mmInStream.read(readBuffer,0,readBuffer.length);
                    final String strBuffer = new String(readBuffer,0,bytes,"UTF-8");
                    Log.e("bluetooth_protocol","strBuffer : "+strBuffer);
                    received_text += strBuffer;

                    if(!SETTING_FLAG){

                        if(received_text.contains(">")){
                            if(!received_text.matches("^[a-zA-Z0-9. >\r]*$")){
                                StringBuilder strRecivedText = new StringBuilder();
                                for(int i = 0 ; i < received_text.length() ; i ++){

                                    String checkText = received_text.substring(i,i+1);
                                    if(Pattern.matches("^[a-zA-Z0-9. >\r]*$",checkText)){
                                        strRecivedText.append(checkText);
                                    }
                                }
                                received_text = String.valueOf(strRecivedText);
                            }

                            /*strLog = (received_text + "  \r");
                            new ErrorLogManager().saveErrorLog(strLog);*/
                            if(ATZ_FLAG){
                                ATZ_FLAG = false;

                                btSetting(ATSP0_SETTING);
                                if(MainActivity.MainActivityHandler !=null){
                                    //MainActivity.MainActivityHandler.obtainMessage(2,received_text).sendToTarget();
                                    received_text = received_text.replace("\r","");
                                    obdVersion = received_text;
                                }
                                received_text = "";

                            }
                            else if(ATSP0_FLAG){
                                ATSP0_FLAG = false;
                                btSetting(ATECHO_SETTING);
                                received_text = "";
                            }
                            else if(ATECHO_FLAG){
                                ATECHO_FLAG = false;
                                btSetting(ATSPACE_SETTING);
                                received_text = "";
                            }else if(received_text.contains("S1")&&ATSPACE_FLAG){
                                ATSPACE_FLAG = false;
                                btSetting(ATLINEFEED_SETTING);
                                received_text = "";
                            }else if(received_text.contains("L0")&&ATLINEFEED_FLAG){
                                btSetting(ATH1_SETTING);
                                received_text = "";
                            }else if(received_text.contains("H1")&& ATH1_FLAG){
                                btSetting(VIN_SETIING);
                                received_text = "";
                            }else if(received_text.contains("0902")&& VIN_FLAG){
                                if(received_text.contains("SEARCHING")){
                                    received_text = received_text.replace("SEARCHING...\r","");
                                }
                                if(received_text.contains("\r")){
                                    StringBuilder strValueResult = new StringBuilder();
                                    for(int i = 0 ; i < received_text.length() ; i++){
                                        String checkRecevedText = received_text.substring(i,i+1);
                                        String result = null;
                                        if(Pattern.matches("^[a-zA-Z0-9. >\r]*$",checkRecevedText)){
                                            strValueResult.append(checkRecevedText);
                                        }
                                    }
                                    received_text = String.valueOf(strValueResult);

                                }
                                if(dataVIN != null){
                                    dataVIN = null;
                                }
                                dataVIN = received_text;
                                try {
                                    bypass_stream.NewStart(received_text);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                btSetting(ATDPN_SETTING);
                                received_text = "";
                            }else if(received_text.contains("DPN")&&ATDPN_FLAG){
                                protocolDataNum = null;
                                protocolDataNum = received_text;
                                btSetting(ATDP_SETTING);
                                received_text = "";

                            }else if(received_text.contains("DP")&&ATDP_FLAG){
                                protocolData = null;
                                protocolData = received_text;
                                if(MainActivity.MainActivityHandler != null){
                                    MainActivity.MainActivityHandler.obtainMessage(5,"Protocol Data : "+protocolData).sendToTarget();
                                    MainActivity.MainActivityHandler.obtainMessage(6,"Protocol Num : " + protocolDataNum).sendToTarget();
                                }
                                try {
                                    new SearchVINTask(MainActivity.mainContext, null, null, null, Parse.strVIN).execute();

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                Log.e("test","test MainView.Voltage_FLAG : " + MainView.Voltage_FLAG + " , MainView.Diagnosis_FLAG : " + MainView.Diagnosis_FLAG +
                                        " , MainView.PIDTestStart_FLAG : " + MainView.PidTestStart_FLAG);
                                if(!MainView.Voltage_FLAG&&!MainView.Diagnosis_FLAG){
                                    if(MainView.PidTestStart_FLAG){
                                        new MakeData().defaultData(MainActivity.mainContext,Parse.strVIN,strMaker,strModel,strYear);
                                    }

                                }


                                if(MainView.Voltage_FLAG){
                                    if(MainActivity.MainActivityHandler != null){
                                        MainActivity.MainActivityHandler.obtainMessage(8,null).sendToTarget();
                                    }
                                }else if(MainView.Diagnosis_FLAG){
                                    String push = "03";
                                    push += "\r";
                                    new Bluetooth_Protocol().write(push.getBytes());
                                    MainView.DiagnosisStart_FLAG = false;
                                }
                                received_text = "";
                                SETTING_FLAG = true;
                            }





                        }


                    }else if(SETTING_FLAG){
                        if(strBuffer.contains(">"))
                        {

                            OBD_CONNECTSTATE = true;
                            //Response Time

                            if(!received_text.matches("^[a-zA-Z0-9. >\r]*$")){
                                StringBuilder strRecivedText = new StringBuilder();
                                for(int i = 0 ; i < received_text.length() ; i ++){

                                    String checkText = received_text.substring(i,i+1);
                                    if(Pattern.matches("^[a-zA-Z0-9. >\r]*$",checkText)){
                                        strRecivedText.append(checkText);
                                    }
                                }
                                received_text = String.valueOf(strRecivedText);
                            }

                            if(PageStr.getPageStrData().equals(PageStr.Terminal)){
                                new TerminalView().setListData(null,received_text);
                                String logPullData = received_text.replace("\r","");
                                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime()," PULL DATA : " + logPullData);
                                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime(),"----------------------------------------------------------");
                            }

                            if(MainActivity.MainActivityHandler != null){
                                MainActivity.MainActivityHandler.obtainMessage(3,received_text).sendToTarget();
                            }
                            if(received_text.contains("03")){
                                if(received_text.contains("SEARCHING")){
                                    received_text = received_text.replace("SEARCHING...\r","");
                                }
                                if(received_text.contains("NO")){
                                    if(MainView.mainViewHandler!=null){
                                        MainView.mainViewHandler.obtainMessage(3,"NO DATA").sendToTarget();
                                    }
                                }else {
                                    bypass_stream.NewStart(received_text);
                                }

                                MainView.Diagnosis_FLAG = false;
                                received_text = "";
                            }


                            if(received_text.contains("AT RV")){
                                new MakeData().voltageData(new Time_DataBridge().getRealTime(),pushVIN,received_text);
                            }

                            if(received_text.contains("NO DATA")){
                                //Log.e("ConnectedThread","NoDataCount : " + NoDataCount);
                                NoDataCount +=1;
                                if(NoDataCount > 56){
                                    //연결재시도
                                    NoDataCount = 0;
                                    NoData_FLAG= true;
                                    String pushSP0 = "AT SP0";
                                    pushSP0 += "\r";
                                    //Log.e("ConnectedThread","pushSP0 : " + pushSP0);
                                    write(pushSP0.getBytes());
                                    /*try {
                                        //new Bluetooth_Protocol().cancel();
                                        if(!ReconnectFlag){
                                            ReconnectFlag = true;
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    new Bluetooth_Protocol().connect(reConnectDevice);
                                                    reConnectDevice = null;
                                                }
                                            },1000);

                                        }else {
                                            if(Status_DataBridge.getMainContext()!=null){
                                                if(MainActivity.toastHandler != null){

                                                    MainActivity.toastHandler.obtainMessage(1,Status_DataBridge.getMainContext().getString(R.string.bluetooth_disconnect_msg)).sendToTarget();
                                                }
                                                ((MainActivity)Status_DataBridge.getMainContext()).mainChangeMenu(new MainFragment());


                                            }
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }*/
                                }
                            }else {
                                NoDataCount = 0;
                            }

                            if(NoData_FLAG){
                                //여기서 한번은 sp0 으로 search 하고
                                //그다음 더미데이터 보내고 데이터 잘나오는지 확인하고
                                //그다음 저장되어있던 AT Protocol 을 다시 설정하고 시작
                                if(received_text.contains("SP0")){
                                    String pushData = "010c";
                                    pushData += "\r";
                                    //Log.e("ConnectedThread","pushData : " + pushData);
                                    write(pushData.getBytes());
                                    NoData_FLAG = false;
                                }/*else if(received_text.contains("010c")){

                                    String pushProtocol = "AT SP"+protocol[saveProtocolCount];
                                    pushProtocol += "\r";
                                    //Log.e("ConnectedThread","pushProtocol : " + pushProtocol);
                                    write(pushProtocol.getBytes());

                                }*/
                                received_text = "";
                            }else {
                                try {
                                    bypass_stream.NewStart(received_text);
                                }catch (Exception e){

                                }

                                if(PidTestFlag){
                                    pushPID();
                                }
                            }




                            //Data TextView 로 보냄
                            if(dataHandler != null) {
                                dataHandler.obtainMessage(MESSAGE_READ, bytes, 0, received_text)
                                        .sendToTarget();
                            }
                            /*if(PageStr.getPageStrData().equals(PageStr.Terminal)){
                                received_text.replace("\r","");
                                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime()," PULL DATA : " + received_text);
                            }*/
                            received_text = "";

                        }
                    }
                } catch (IOException e) {
                    connectionLost(btAddress);
                    e.printStackTrace();
                    if(MainActivity.MainActivityHandler != null){
                        MainActivity.MainActivityHandler.obtainMessage(1,"연결끊김").sendToTarget();
                        MakeData.fileName = null;
                        BluetoothConnect = false;
                        PidTestFlag = false;
                    }
                    new MainView().setObdIcon(false);
                    MainView.bluetoothState = false;

                    break;
                }

            }
        }

        public void pushSettingATCommand(){
            SETTING_FLAG = false;
            if(!ATZ_FLAG){
                btSetting(ATZ_SETTING);
                ATZ_FLAG = true;
            }
        }

        public void pushPID(){
            //여기서 유저 정보 없으면 못하게 막아라
            if(MainView.PID != null){
                if(MainView.PID.contains(new MainView().ALLPID)){
                    write(new StandardPid().startAllPid().getBytes());
                }else if(MainView.PID.contains(new MainView().SCHEDULEPID)){
                    write(new StandardPid().startSchedulePid().getBytes());
                }
            }



        }

        //블루투스 연결 후 값 전송 부분
        void write(byte[] buffer) {
            try {
                //Response Time
                /*long now = System.currentTimeMillis();
                Date date = new Date(now);
                responsTime[writeTime] = Integer.parseInt(new SimpleDateFormat("ssSSS").format(date));*/
                mmOutStream.write(buffer);
            } catch (IOException e) {
                Log.e(TAG, "Exception during writeBT", e);
            }
        }

        //블루투스 연결을 끊기 위한 부분
        public void cancle() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }


    }

    public void pushATSetting(){
        try {
            mConnectedThread.pushSettingATCommand();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 1 ~ 12
     * */
    private void setObdProtocol(String protocol){
        String pushData = null;
        pushData = "AT SP"+protocol;
        pushData+= "\r";
        write(pushData.getBytes());
    }

    /*public void startPidData(){
        String startPid = "0100";
        startPid +="\r";
        write(startPid.getBytes());
    }*/

    public void checkPage(String PID){
        switch (PageStr.getPageStrData()){
            case PageStr.PidTestView :
                if(PID.equals(new MainView().ALLPID)){
                    Log.e("test","test 1111");
                    PidTestFlag = true;
                    write(new StandardPid().startAllPid().getBytes());
                    break;
                }else if(PID.equals(new MainView().SCHEDULEPID)){
                    Log.e("test","test 2222");
                    PidTestFlag = true;
                    write(new StandardPid().startSchedulePid().getBytes());
                    break;
                }

        }
    }

    //현재 상태 setting
    public static synchronized void setState(int state) {
        mState = state;
    }

    //현재 상태 액티비티에서 가져오기
    public synchronized int getState() {
        return mState;
    }


    //블루투스 기기 연결 시작
    private synchronized void start() {

        if (mConnectThread == null) {

        } else {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread == null) {

        } else {
            mConnectedThread.cancle();
            mConnectedThread = null;
        }

    }

    //블루투스 기기 연결 시도
    private synchronized void connect(BluetoothDevice device) {
        Log.e(TAG, "connect to: " + device);

        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancle();
            mConnectedThread = null;
        }

        mConnectThread = new ConnectThread(device);
        mConnectThread.start();


        setState(STATE_CONNECTING);
    }


    //블루투스 연결 완료
    private synchronized void connected(BluetoothSocket socket ) {
        Log.e(TAG, "connected");
        REPAIR_FLAG = false;
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancle();
            mConnectedThread = null;
        }


        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        setState(STATE_CONNECTED);
    }

    /*public synchronized void stop() {
        Log.e(TAG, "stop");
        if (mConnectThread != null) {
            mConnectThread.cancle();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancle();
            mConnectedThread = null;
        }

        setState(0);
    }*/

    // 값을 쓰는 부분(보내는 부분)
    public void write(byte[] out) {
        ConnectedThread r;
        //Log.e("test","write out : " + new String(out,0,out.length) );
        synchronized (this) {
            if (mState != STATE_CONNECTED){
                return;
            }
            r = mConnectedThread;
        }

        r.write(out);
    }


    // 연결 실패했을때
    private void connectionFailed(String address) {
        SETTING_FLAG = false;
        setState(STATE_LISTEN);
        if(mConnectedThread != null){

            mConnectedThread.VIN_FLAG = false;
            mConnectedThread.ATH1_FLAG = false;
            mConnectedThread.ATSP0_FLAG = false;
            mConnectedThread.ATZ_FLAG = false;
            mConnectedThread.ATECHO_FLAG = false;
            mConnectedThread.ATLINEFEED_FLAG = false;
            mConnectedThread.ATSPACE_FLAG = false;
        }

        rePairBT(address,REPAIR_FLAG);
    }




    // 연결을 잃었을 때
    private void connectionLost(String address) {


        setState(STATE_LISTEN);
        if(dataHandler != null){
            dataHandler.obtainMessage(CONNECT_FAIL,0,0,"연결 끊김")
                    .sendToTarget();
        }
        if(mConnectThread != null){
            mConnectThread.interrupt();
        }
        if(mConnectedThread != null){
            mConnectedThread.interrupt();
        }

        /**
         * 2018.01.12 by.GeonHo
         * 연결 잃엇을때 다시 재 접속
         * */


    }

    /**
     *
     *
     * 블루투스 자동연결 리시버로 해결가능성 있어 보임
     * 블루투스가 켜져있을경우 리시버를 이용해 블루투스 상태 파싱해서 가져온뒤
     * 연결되었던적 있던 OBD device.address를 가져와서 실행
     *
     *
     * */

    public void connectDevice(final BluetoothDevice device){
        Log.e("Bluetooth_Protocol","connectDevice");


        connect(device);

    }

    public void bluetoothEnable (){
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.enable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void autoSearchBt(Context context, final String address){
        Log.e("autoSearchBt"," isDiscovering : "+bluetoothAdapter.isDiscovering());

        Log.e("Bluetooth_Protocol","autoSearchBt");
        if(!bluetoothAdapter.isEnabled()){
            bluetoothAdapter.enable();
        }
        if(bluetoothAdapter.startDiscovery()){
        }
        btReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    Log.e("autoSearchBt","onReceive getName :  "+device.getName()+"   , getAddress : "+device.getAddress());
                    if(device.getAddress().equals(address)){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            connect(device);
                            try{
                                context.unregisterReceiver(btReceiver);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            btReceiver = null;
                        }
                    }

                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(btReceiver, filter);


    }


    public void cancle(){
        if(mConnectedThread != null){
            Log.e("Bluetooth_Protocol"," cancle ");
            mConnectedThread.cancle();
        }

    }


    //블루투스가 완전히 연결 되었는지 아닌지 ture,false 의 값을 반환한다
    private boolean bluetoothConnected(){
        return getState() == 3;
    }

    //블루투스 초기 세팅을 위한 함수
    private void btSetting(int settingNum){
        switch (settingNum){
            case 1 :
                String strATZ = "AT Z";
                strATZ += "\r";
                write(strATZ.getBytes());
                mConnectedThread.ATZ_FLAG = true;
                break;
            case 2 :
                String strATSP0 = "AT SP0";
                strATSP0 += "\r";
                write(strATSP0.getBytes());
                mConnectedThread.ATSP0_FLAG = true;
                break;
            case 3 :
                String strATH1 = "AT H1";
                strATH1 += "\r";
                write(strATH1.getBytes());
                mConnectedThread.ATH1_FLAG = true;
                break;

            case 4 :
                String strVIN = "0902";
                strVIN += "\r";
                write(strVIN.getBytes());
                mConnectedThread.VIN_FLAG = true;
                break;
            case 5 :
                String strEcho = "AT E1";
                strEcho += "\r";
                write(strEcho.getBytes());
                mConnectedThread.ATECHO_FLAG = true;
                break;
            case 6 :
                String strLineFeed = "AT L0";
                strLineFeed += "\r";
                write(strLineFeed.getBytes());
                mConnectedThread.ATLINEFEED_FLAG = true;
                break;

            case 7 :
                String strSpace = "AT S1";
                strSpace += "\r";
                write(strSpace.getBytes());
                mConnectedThread.ATSPACE_FLAG = true;
                break;

            case 8 :
                String strProtocolNum = "AT DPN";
                strProtocolNum += "\r";
                write(strProtocolNum.getBytes());
                mConnectedThread.ATDPN_FLAG = true;

            case 9 :
                String strDisplayProtocol = "AT DP";
                strDisplayProtocol += "\r";
                write(strDisplayProtocol.getBytes());
                mConnectedThread.ATDP_FLAG = true;

        }

    }

    //등록해제
    private void rePairBT(final String address, boolean reconnetFlag){
        Log.e("Bluetooth_Protocol","rePairBT in ");
        if(mConnectThread != null){
            mConnectThread.interrupt();
        }


        try {
            Class<?> btDeviceInstance = Class.forName(BluetoothDevice.class.getCanonicalName());
            Method removeBondMethod = btDeviceInstance.getMethod("removeBond");
            //btAddress 현재 클릭한 주소값
            final BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
            removeBondMethod.invoke(device);
            if(!reconnetFlag){

                connect(device);
                REPAIR_FLAG = true;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }



}