package mureung.mureungtest.Communication;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * Created by user on 2017-06-08.
 */

public class Bluetooth_Protocol3 {

    public static boolean OBD_CONNECTSTATE = false;


    private static final String TAG = "Bluetooth_Protocol";

    public static BluetoothAdapter bluetoothAdapter;

    //UUID
    private static final UUID btUUID = UUID
            .fromString("00001101-0000-1000-8000-00805f9b34fb");



    private static ConnectThread mConnectThread;
    private static ConnectedThread mConnectedThread;



    public static int mState = 0;


    //태그 값들 지정해줘야함
    private static final int STATE_LISTEN = 1; // now listening for incoming
    private static final int STATE_CONNECTING = 2; // now initiating an outgoing
    private static final int STATE_CONNECTED = 3; // now connected to a remote
    public static boolean SETTING_FLAG = false;

    public static BroadcastReceiver btReceiver;


    public static int CONNECTED_STATE = 0;


    private static int noDataCount = 0;
    private static int goodDataCount= 0;

    public static boolean Diagnosis_FLAG = false;

    private static boolean Cancel_FLAG = false;
    private static boolean ReConnect_FLAG = false;

    public static boolean MainDestroy_FLAG = false;

    public static boolean AutoSearch_FLAG = false;

    public static ArrayList<BluetoothDevice> scanBtList ;







    public Bluetooth_Protocol3() {
        bluetoothProtocol();

    }
    //블루투스 사용시 사용할 어레이 리스트, Adapter 등 초기 선언부분
    private void bluetoothProtocol(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();




    }



    //블루투스 연결 Thread
    public class ConnectThread extends Thread {
        private BluetoothSocket mSocket = null;
        private BluetoothDevice mmDevice ;


        public ConnectThread(BluetoothDevice device,boolean secureFlag) {
            Log.e("Bluetooth_Protocol","ConnectThread In");
            if(device != null){
                mSocket = null;


                mmDevice = device;
                BluetoothSocket secureRfComm = null;
                // 디바이스 정보를 얻어서 BluetoothSocket 생성

                Log.e("device"," getName : "+device.getName()+" , getBoundState : "+device.getBondState() + " , getAddress : " + device.getAddress() + " , getType :" + device.getType());

                try {
                    //secureRfComm = device.createInsecureRfcommSocketToServiceRecord(btUUID);
                    //device.setPin(String.valueOf("1234").getBytes());
                    if(secureFlag){
                        secureRfComm = device.createRfcommSocketToServiceRecord(btUUID);
                    }else {
                        secureRfComm = device.createInsecureRfcommSocketToServiceRecord(btUUID);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                mSocket = secureRfComm;
            }

        }
        //블루투스 연결 스레드 시작
        public void run() {
            Log.e(TAG, "BEGIN mConnectThread");

            try {

                mSocket.connect();


                Log.e(TAG, "Connect Success");

                if(bluetoothAdapter != null){
                    bluetoothAdapter.cancelDiscovery();
                }
            } catch (Exception e) {

                try {
                    if(mSocket != null){
                        mSocket.close();
                        mSocket = null;
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                connectionFailed(mmDevice.getAddress());
                e.printStackTrace();




                /**
                 * 블루투스 연결 시도중 차량이 끊길경우 이때 끊기게 해줘야함 아니면 끊어지질 않음
                 * 근데 다시 차량이 연결되는경우는 큰일남
                 * */
                return;
            }
            synchronized (Bluetooth_Protocol3.this) {
                mConnectThread = null;
            }

            //연결 스레드 run 부분을 다 통과하게 된다면 연결 후 통신 스레드 시작
            connected(mSocket);

        }
        public void cancel() {
            try {
                if(mSocket != null){
                    mSocket.close();
                }
                Log.e("Bluetooth_Protocol","cancel Success");
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }


    }

    public void connectSocketClose(){
        new ConnectThread(null,false).cancel();
    }

    public void connectedThread(BluetoothSocket socket){
        connected(socket);
    }




    //블루투스 연결 후 통신을 위한 Thread
    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Bypass_Stream bypass_stream;

        final String ATZ_SETTING = "ATZ";
        final String ATSP0_SETTING = "ATSP0";
        final String ATH1_SETTING = "ATH1";
        final String VIN_SETIING = "VIN";
        final String ATECHO_SETTING = "ATECHO";
        final String ATLINEFEED_SETTING = "ATLINEFEED";
        final String ATSPACE_SETTING = "ATSPACE";
        final String ATLOWPOWER_SETTING = "ATLOWPOWER";
        final String ATPP_SETTING = "ATPP" ;
        final String ATCV_SETTING = "ATCV";
        final String ATCV12_SETTING = "ATCV12";
        final String ATRV_SETTING ="ATRV";



        private boolean ATZ_FLAG = false;
        private boolean ATSP0_FLAG = false;
        private boolean ATH1_FLAG = false;
        private boolean VIN_FLAG = false;
        private boolean ATECHO_FLAG = false;
        private boolean ATLINEFEED_FLAG = false;
        private boolean ATSPACE_FLAG = false;
        private boolean ATLOWPOWER_FLAG = false;
        private boolean ATPP_FLAG = false;
        private boolean ATCV_FLAG = false;
        private boolean ATCV12_FLAG = false;
        private boolean ATRV_FLAG = false;


        private boolean resetProtocol = false;
        private int resetProtocolCount = 0;

        private boolean Thread_FLAG = false;

        private float checkPreRpm = 0;


        //통신에 필요한 Socket 을 선언 및 접속 하는 부분 = 블루투스 송수신 접속
        public ConnectedThread(BluetoothSocket socket) {
            Log.e(TAG, "create ConnectedThread");
            mmSocket = socket;

            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            //Log.e("ConnectedThread","mSocket "+mSocket);
            try {
                if(socket != null){
                    tmpIn = socket.getInputStream();
                    tmpOut = socket.getOutputStream();
                }

            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
            bypass_stream = new Bypass_Stream();
            CONNECTED_STATE = 1;

            Thread_FLAG = true;

            Cancel_FLAG = false;
            AutoSearch_FLAG = false;
            //ENGINE_ON_FLAG = true;
            scanBtList = null;
            MainDestroy_FLAG = false;


        }

        //블루투스 데이터 수신을 스레드로 항상 수신대기함
        public void run() {
            Log.e(TAG, "BEGIN mConnectedThread");

            byte[] readBuffer = new byte[1024];
            int bytes = 0;
            String received_text = "";
            if(!ATZ_FLAG){
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                btSetting(ATZ_SETTING);
                ATZ_FLAG = true;
            }
            while (Thread_FLAG) {

                try {
                    bytes = mmInStream.read(readBuffer,0,readBuffer.length);
                    final String strBuffer = new String(readBuffer,0,bytes,"UTF-8");
                    //Log.e("bluetooth_protocol","strBuffer : "+strBuffer);
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
                            //Log.e("ConnectedThread","SETTING_FLAG = false , received_text : " + received_text);
                            //Log.e("bluetooth_protocol","received_text : " + received_text);
                            if(ATZ_FLAG){
                                Log.e("bluetooth_protocol","received_text : " + received_text);
                                ATZ_FLAG = false;
                                btSetting(ATSP0_SETTING);
                                received_text = "";
                            }else if(ATSP0_FLAG){
                                ATSP0_FLAG = false;
                                btSetting(ATECHO_SETTING);
                                received_text = "";
                            }else if(ATECHO_FLAG){
                                ATECHO_FLAG = false;
                                btSetting(ATSPACE_SETTING);
                                received_text = "";
                            }else if(received_text.contains("S1")&&ATSPACE_FLAG){
                                ATSPACE_FLAG = false;
                                btSetting(ATLINEFEED_SETTING);
                                received_text = "";
                            }else if(received_text.contains("L0")&&ATLINEFEED_FLAG){
                                ATLINEFEED_FLAG = false;
                                btSetting(ATH1_SETTING);
                                received_text = "";
                            }else if(received_text.contains("H1")&& ATH1_FLAG){
                                ATH1_FLAG = false;
                                btSetting(ATPP_SETTING);
                                received_text = "";
                            }else if(received_text.contains("PP")&& ATPP_FLAG){
                                ATPP_FLAG = false;
                                btSetting(ATCV_SETTING);
                                received_text = "";
                            }else if(received_text.contains("CV")&&ATCV_FLAG){
                                ATCV_FLAG = false;
                                btSetting(ATRV_SETTING);
                                received_text = "";
                            }else if(received_text.contains("RV")&&ATRV_FLAG){
                                //AT RV12.3V
                                //Log.e("LogoutFLAG","received_text: " + received_text);
                                received_text = received_text.replace("AT","");
                                received_text = received_text.replace("RV","");
                                received_text = received_text.replace(" ","");
                                received_text = received_text.replace("V","");
                                received_text = received_text.replace(">","");
                                int battery = 0;
                                try {
                                    battery = Integer.parseInt(String.valueOf((int)(Float.parseFloat(received_text)*100)));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }


                                ATRV_FLAG = false;
                                btSetting(VIN_SETIING);
                                received_text = "";
                            }else if(received_text.contains("0902")){

                                Log.e("Bluetooth_Protocol","received_text contains 0902 : " + received_text);
                                VIN_FLAG = false;
                                if(received_text.contains("SEARCHING")){
                                    received_text = received_text.replace(".\r","");
                                    received_text = received_text.replace(".","");
                                    received_text = received_text.replace("SEARCHING","");
                                }
                                if(received_text.contains("\r")){
                                    StringBuilder strValueResult = new StringBuilder();
                                    for(int i = 0 ; i < received_text.length() ; i++){
                                        String checkRecevedText = received_text.substring(i,i+1);
                                        if(Pattern.matches("^[a-zA-Z0-9. >\r]*$",checkRecevedText)){
                                            strValueResult.append(checkRecevedText);
                                        }
                                    }
                                    received_text = String.valueOf(strValueResult);

                                }
                                try {
                                    //Log.e("Bluetooth_Protocol","bypass_Stream.NewStart(received_text) : " + received_text);
                                    bypass_stream.NewStart(received_text);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                received_text = "";
                            }

                            if(Cancel_FLAG){
                                /*if(!ATCV12_FLAG){
                                    btSetting(ATCV12_SETTING);
                                    received_text = "";
                                }else if(ATCV12_FLAG&&!ATLOWPOWER_FLAG){
                                    btSetting(ATLOWPOWER_SETTING);
                                    received_text = "";
                                }else if(ATCV12_FLAG && ATLOWPOWER_FLAG){
                                    new Bluetooth_DataBridge().connected_Finish();
                                    cancel();
                                    ATCV12_FLAG = false;
                                    ATLOWPOWER_FLAG = false;
                                    if(ReConnect_FLAG){
                                        reconnectBluetooth();
                                    }
                                }*/

                                /*new Bluetooth_DataBridge().connected_Finish();
                                cancel();
                                ATLOWPOWER_FLAG = false;
                                if(ReConnect_FLAG){
                                    reconnectBluetooth();
                                }*/
                                if(!ATLOWPOWER_FLAG){
                                    btSetting(ATLOWPOWER_SETTING);
                                    cancel();
                                    received_text = "";

                                }
                                /*else if(ATLOWPOWER_FLAG){

                                    cancel();
                                    Log.e("LogoutFLAG","LogoutFLAG 2222");
                                    ATLOWPOWER_FLAG = false;
                                    if(ReConnect_FLAG){
                                        reconnectBluetooth();
                                    }
                                }*/
                            }

                        }

                    }else if(SETTING_FLAG){
                        //스케줄로 받아온 값들을 여기서 처리
                        if(received_text.contains(">"))
                        {
                            OBD_CONNECTSTATE = true;
                            try {
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
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try {
                                if(received_text.contains("SEARCHING")){
                                    received_text = received_text.replace(".\r","");
                                    received_text = received_text.replace(".","");
                                    received_text = received_text.replace("SEARCHING","");
                                }
                                if(received_text.contains("UNABLE")){
                                    received_text = received_text.replace("UNABLE","");
                                    received_text = received_text.replace("TO","");
                                    received_text = received_text.replace("CONNECT","");
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            //Log.e("ConnectedThread","received_text : " + received_text);

                            //Check No Data . Reset Protocol
                            try {
                                checkResetProtocolOBD(received_text);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try {
                                bypass_stream.NewStart(received_text);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            if(mmSocket.isConnected()&&!resetProtocol){
                                if(MainDestroy_FLAG){
                                    if(received_text.contains("AT")&&received_text.contains("CV")&&received_text.contains("1200")){
                                        String strLP = "AT LP";
                                        strLP+="\r";
                                        write(strLP.getBytes());
                                    }else {
                                        String strCV12 = "AT CV 1200";
                                        strCV12 += "\r";
                                        write(strCV12.getBytes());
                                    }

                                }else {

                                    if(received_text.contains("AT")&&received_text.contains("RV")){
                                        received_text = received_text.replace("AT","");
                                        received_text = received_text.replace("RV","");
                                        received_text = received_text.replace(" ","");
                                        received_text = received_text.replace("V","");
                                        received_text = received_text.replace(">","");


                                    }
                                    pushPID();
                                }


                            }



                            //Log.e("LogoutFLAG","received_text : " + received_text);

                            received_text = "";

                        }
                    }
                } catch (IOException e) {
                    Thread_FLAG = false;
                    Log.e("ConnectedThread","catch!!!!");
                    //Log.e("ConnectedThread","Device_Stream.ConnectedCar_FLAG : " + Device_Stream.ConnectedCar_FLAG);


                    connectionLost();
                    e.printStackTrace();

                    break;
                }

            }
        }

        private void checkResetProtocolOBD(String received_text){
            if(received_text !=null){

                if(received_text.contains("NO")&&received_text.contains("DATA")){

                    noDataCount++;

                    if(noDataCount > 14){
                        if(resetProtocolCount >=2 ){
                            if(ReConnect_FLAG){
                                lowPowerCancel();

                                ReConnect_FLAG = false;
                            }else {
                                ReConnect_FLAG = true;
                                resetProtocolCount = 0;
                                lowPowerCancel();

                            }


                        }else {
                            String resetATZ = "ATZ";
                            resetATZ += "\r";
                            write(resetATZ.getBytes());
                            resetProtocol = true;
                        }
                        noDataCount = 0;
                    }
                }else {
                    noDataCount = 0;
                    goodDataCount ++;
                    if(goodDataCount >15){
                        goodDataCount = 0;
                        resetProtocolCount = 0;
                    }
                }

                if(resetProtocol){
                    if(received_text.contains("Z")){
                        String resetATSP0 = "AT SP0";
                        resetATSP0 += "\r";
                        write(resetATSP0.getBytes());

                    }else if(received_text.contains("SP0")){
                        String resetATH1 = "AT H1";
                        resetATH1 += "\r";
                        write(resetATH1.getBytes());
                    }else if(received_text.contains("H1")){
                        resetProtocolCount ++;
                        resetProtocol = false;
                    }
                }
            }


        }



        public void pushPID(){
            //여기서 유저 정보 없으면 못하게 막아라
            if(!Diagnosis_FLAG){
                String standardPID = new StandardPid().startAllPid();
                write(standardPID.getBytes());


            }else {
                String diagnosisData = new StandardPid().startSchedulePid();
                if(diagnosisData != null){
                    write(diagnosisData.getBytes());
                    Diagnosis_FLAG = false;
                }
            }



        }



        //블루투스 연결 후 값 전송 부분
        void write(byte[] buffer) {
            try {
                //Response Time
                /*long now = System.currentTimeMillis();
                Date date = new Date(now);
                responsTime[writeTime] = Integer.parseInt(new SimpleDateFormat("ss:SSS").format(date));*/

                //Log.e("write","byte to string  out : " + new String(buffer,0,buffer.length));
                if(mmSocket.isConnected()){
                    mmOutStream.write(buffer);
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Exception during writeBT", e);
            }
        }

        //블루투스 연결을 끊기 위한 부분
        private void cancel() {
            try {
                Thread_FLAG = false;
                if(mmSocket != null){
                    mmSocket.close();
                    Log.e("Bluetooth_Protocol","cancel mmSocket.close");
                }
                setState(STATE_LISTEN);
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }


    }


    //현재 상태 setting
    public static synchronized void setState(int state) {
        Log.e("Bluetooth_Protocol","setState  : " + state);


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
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

    }


    //블루투스 기기 연결 시도
    private synchronized void connect(final  BluetoothDevice device, final boolean secureFlag) {
        /*Log.e("LogoutFLAG","LogoutFLAG 1111");
        AgainFlag = false;
        StartFlag = false;
        FindFlag = false;


        Log.e(TAG, " connect() connect to: " + device);

        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        mConnectThread = new ConnectThread(device);
        mConnectThread.start();

        setState(STATE_CONNECTING);*/
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {


                Log.e(TAG, " connect() connect to: " + device);

                if (mState == STATE_CONNECTING) {
                    if (mConnectThread != null) {
                        mConnectThread.cancel();
                        mConnectThread = null;
                    }
                }

                if (mConnectedThread != null) {
                    mConnectedThread.cancel();
                    mConnectedThread = null;
                }

                mConnectThread = new ConnectThread(device,secureFlag);
                mConnectThread.start();

                setState(STATE_CONNECTING);
            }
        },10);





    }



    //블루투스 연결 완료
    private synchronized void connected(final BluetoothSocket socket ) {
        Log.e(TAG, "connected");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }



        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        setState(STATE_CONNECTED);







    }

    // 값을 쓰는 부분(보내는 부분)
    public void write(byte[] out) {

        ConnectedThread r;
        synchronized (this) {
            if (mState != STATE_CONNECTED){
                return;
            }
            r = mConnectedThread;
        }

        r.write(out);
    }

    public void initSetting(){
        try {
            if(mConnectedThread != null){
                mConnectedThread.ATZ_FLAG = true;
                SETTING_FLAG = false;
                String pushInit = "ATZ";
                pushInit += "\r";
                write(pushInit.getBytes());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

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


    }




    // 연결을 잃었을 때
    private void connectionLost() {
        // 앱종료시 tts가 null이 되기때문에 null 체크.
        /*if (MainActivity.getTTS() != null){
            MainActivity.speak(Status_DataBridge.getMainContext().getString(R.string.TTS_stopDriving));
        }*/




        setState(STATE_LISTEN);
        if(mConnectThread != null){
            mConnectThread.interrupt();
        }
        if(mConnectedThread != null){
            mConnectedThread.Thread_FLAG = false;
            mConnectedThread.interrupt();
        }



    }

    public void connectDevice(final BluetoothDevice device){
        Log.e("Bluetooth_Protocol","connectDevice");

        boolean findFlag = false;
        Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bluetoothDevice : pairedDevice){
            if(device.getAddress().equals(bluetoothDevice.getAddress())){
                findFlag = true;
                break;
            }

        }
        if (findFlag){
            connect(device,false);
        }else {
            connect(device,true);
        }




    }

    public void bluetoothEnable (){
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.enable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void initAutoFlag (){
        AutoSearch_FLAG = false;
    }

   /* private static boolean dataObdConnect_FLAG = false;
    public static boolean Scanning_FLAG = false;
    public void autoSearchBt(Context context, final String address){
        Log.e("Bluetooth_Protocol","autoSearchBt init FLAG : " + AutoSearch_FLAG);

        Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();



        if(scanBtList == null){
            scanBtList = new ArrayList<BluetoothDevice>();
            bondedBtList = new ArrayList<BluetoothDevice>();
            try {
                if(pairedDevice != null){
                    if(pairedDevice.size() != 0){
                        bondedBtList.addAll(pairedDevice);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            if(!AutoSearch_FLAG){
                if(pairedDevice.size() == 0){
                    AutoSearch_FLAG = true;
                }else if(pairedDevice.size()>0){
                    for(BluetoothDevice device : pairedDevice){

                        Log.e("Bluetooth_Protocol","autoSearchBt device.getNama : " + device.getName() + " getBondState : " + device.getBondState() +  " , Address : " +device.getAddress() + " OBD SN : " + USERINFO_DataBridge.getObdSN());


                        *//*if(device.getAddress().equals(USERINFO_DataBridge.getObdSN())||device.getName().contains("OBD")||device.getName().contains("obd")||device.getName().contains("viecar")){

                            scanBtList.add(device);


                        }*//*

                    }

                }
            }
        }


        if(scanBtList != null){
            if(scanBtList.isEmpty()){
                AutoSearch_FLAG = true;
                Scan_FLAG = false;
            }else {
                AutoSearch_FLAG = false;
                Scan_FLAG = true;
            }
        }else {
            AutoSearch_FLAG = true;
        }

        Log.e("Bluetooth_Protocol","AutoSearchBluetooth : " + AutoSearch_FLAG);
        if(!AutoSearch_FLAG){
            try {
                bluetoothAdapter.cancelDiscovery();
            }catch (Exception e){
                e.printStackTrace();
            }
            //AutoSearch_FLAG = true;
            if(scanBtList.size()== 0){
                AutoSearch_FLAG = true;
            }
            if(!dataObdConnect_FLAG){
                for(int i = 0 ; i < scanBtList.size() ; i ++ ){
                    BluetoothDevice device = scanBtList.get(i);
                    if(USERINFO_DataBridge.getObdSN().equals( device.getAddress())){
                        BluetoothDevice removeDevice = scanBtList.remove(i);
                        Log.e("Bluetooth_Protocol","removeDevice i : " + i + " , name : " + removeDevice.getName() + " ,address : " + removeDevice.getAddress());
                        connect(device,false);

                        break;
                    }
                    if(i  == scanBtList.size()-1){
                        for(int j = 0 ; j <  scanBtList.size() ; j++){
                            BluetoothDevice bluetoothDevice = scanBtList.get(j);

                            if(scanBtList.size() != 0){
                                BluetoothDevice removeDevice = scanBtList.remove(j);
                                Log.e("Bluetooth_Protocol","removeDevice i : " + i + " , name : " + removeDevice.getName() + " ,address : " + removeDevice.getAddress());
                                connect(bluetoothDevice,false);
                                break;

                            }else {
                                AutoSearch_FLAG = true;
                            }

                        }
                    }
                }

                dataObdConnect_FLAG = true;
            }else {
                for(int i = 0 ; i <  scanBtList.size() ; i++){
                    BluetoothDevice device = scanBtList.get(i);

                    if(scanBtList.size() != 0){
                        BluetoothDevice removeDevice = scanBtList.remove(i);
                        Log.e("Bluetooth_Protocol","removeDevice i : " + i + " , name : " + removeDevice.getName() + " ,address : " + removeDevice.getAddress());
                        connect(device,false);
                        break;

                    }else {
                        AutoSearch_FLAG = true;
                    }

                }
            }


        }else {
            Log.e("Bluetooth_Protocol","Bluetooth Scan Start");
            *//**
     * 현재 등록된 디바이스 연결 시도 끝나고 스캔하면 스캔 검색이안됨 검색되도 ConnectThread 에서 연결시도가 안됨
     *
     * *//*

            new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  Bluetooth Scan Start");
            if(bluetoothAdapter.startDiscovery()){
            }
            new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver BEFORE" );
            Scanning_FLAG = true;
            btReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(final Context context, Intent intent) {
                    //Log.e("onReceive"," StartFlag : " + StartFlag  + " , AgainFlag : " + AgainFlag + " , FindFlag : " + FindFlag);
                    String action = intent.getAction();
                    if(BluetoothDevice.ACTION_FOUND.equals(action)){
                        FirstAutoSearchFlag = true;
                        final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Log.e("autoSearchBt","onReceive getName :  "+device.getName()+"   , getAddress : "+device.getAddress());
                        try {
                            new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver Bluetooth Device : " + device.getName()
                                    + " , getAddress : " + device.getAddress() + " , getBondState : " + device.getBondState() + " , getBluetoothClass : " + device.getBluetoothClass());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(device.getName()!=null){

                            if(device.getName().contains("OBD")||device.getName().contains("obd")||device.getName().contains("Viecar")||device.getName().contains("VIECAR")||device.getName().contains("innocar")){
                                //new BluetoothLe_Protocol().connect(address);
                                FindFlag = true;
                                try {
                                    bluetoothAdapter.cancelDiscovery();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver device.getName().contains(OBD) connect device : " + device.getName() );
                                checkScanBondDevice(device);
                                connect(device,true);
                                Scanning_FLAG = false;
                                try{
                                    if(btReceiver!=null){
                                        context.unregisterReceiver(btReceiver);
                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                btReceiver = null;

                            }else {
                                if(address != null){
                                    if(device.getAddress().equals(address) && !address.equals("")&&!address.equals("null")){

                                        //new BluetoothLe_Protocol().connect(address);
                                        try {
                                            bluetoothAdapter.cancelDiscovery();
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        FindFlag = true;
                                        Scanning_FLAG = false;
                                        checkScanBondDevice(device);
                                        connect(device,true);
                                        new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver device.getAddress().equals(address) && !address.equals()&&!address.equals(null) connect device : " + device.getName() );
                                        try{
                                            if(btReceiver!=null){
                                                context.unregisterReceiver(btReceiver);
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                        btReceiver = null;
                                    }
                                }
                            }
                        }

                        new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver  StartFlag : " + StartFlag + " , AgainFlag : " + AgainFlag  );
                        if(!StartFlag){
                            if(!AgainFlag){
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        Log.e("Bluetooth_Protocol","re Scan Start!!!! findFLag : " + FindFlag);
                                        if(!FindFlag){
                                            new ErrorLogManager().saveDeviceErrorLog(testUserinfo,new Time_DataBridge().getRealTime()+"  "+"Bluetooth_Protocol autoSearchBt  BroadcastReceiver btReceiver  new Handler().postDelayed(new Runnable()  " );
                                            try {
                                                bluetoothAdapter.cancelDiscovery();
                                                bluetoothAdapter.startDiscovery();
                                                AgainFlag = true;
                                                try {
                                                    if(btReceiver !=null){
                                                        context.unregisterReceiver(btReceiver);
                                                    }

                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }

                                                btReceiver = null;

                                                startReceiver(context);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                                },15000);
                            }
                            StartFlag = true;
                        }

                    }
                }
            };

            startReceiver(context);
        }






    }*/

    public void autoSearchBt(){
        Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevice){
            Log.e("Bluetooth_Protocol","autoSearchBt device : " + device.getName() + " , device.getAddress : " + device.getAddress());
            connect(device,false);
        }
    }



    /*private void checkScanBondDevice(BluetoothDevice device){
        Log.e("Bluetooth_Protocol","checkScanBondDevice init!!!  ");
        if(device!=null){
            Log.e("Bluetooth_Protocol","checkScanBondDevice device : " + device.getName() + " , address : " + device.getAddress());
        }
        for(BluetoothDevice boundedDevice : bondedBtList){

            if (device != null && device.getAddress().equals(boundedDevice.getAddress())) {

                Class<?> btDeviceInstance = null;
                try {
                    btDeviceInstance = Class.forName(BluetoothDevice.class.getCanonicalName());
                    Method removeBoundMethod = btDeviceInstance.getMethod("removeBond");
                    removeBoundMethod.invoke(device);
                    Log.e("Bluetooth_Protocol", "checkScanBondDevice removed device : " + device.getName() + " , address : " + device.getAddress()
                            + " , boundedDevice.getName : " + boundedDevice.getName() + "boundedDevice address : " + boundedDevice.getAddress());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    private void startReceiver(Context context){
        try {
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            context.getApplicationContext().registerReceiver(btReceiver, filter);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void cancel (){
        try {
            //btSetting(mConnectedThread.ATLOWPOWER_SETTING);
            if(mConnectedThread !=null){
                mConnectedThread.cancel();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void connectedThreadStop(){
        if(mConnectedThread!=null){
            mConnectedThread.Thread_FLAG = false;
        }

    }*/


    public void lowPowerCancel(){
        SETTING_FLAG = false;
        try {
            if(!Cancel_FLAG){
                Cancel_FLAG = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //블루투스가 완전히 연결 되었는지 아닌지 ture,false 의 값을 반환한다
    private boolean bluetoothConnected(){
        return getState() == 3;
    }

    //블루투스 초기 세팅을 위한 함수
    private void btSetting(String settingNum){
        switch (settingNum){
            case "ATZ" :
                String strATZ = "ATZ";
                strATZ += "\r";
                write(strATZ.getBytes());
                mConnectedThread.ATZ_FLAG = true;
                break;
            case "ATSP0" :
                String strATSP0 = "AT SP0";
                strATSP0 += "\r";
                write(strATSP0.getBytes());
                mConnectedThread.ATSP0_FLAG = true;
                break;
            case "ATH1" :
                String strATH1 = "AT H1";
                strATH1 += "\r";
                write(strATH1.getBytes());
                mConnectedThread.ATH1_FLAG = true;
                break;

            case "VIN" :
                String strVIN = "0902";
                strVIN += "\r";
                write(strVIN.getBytes());
                mConnectedThread.VIN_FLAG = true;
                break;
            case "ATECHO" :
                String strEcho = "AT E1";
                strEcho += "\r";
                write(strEcho.getBytes());
                mConnectedThread.ATECHO_FLAG = true;
                break;
            case "ATLINEFEED" :
                String strLineFeed = "AT L0";
                strLineFeed += "\r";
                write(strLineFeed.getBytes());
                mConnectedThread.ATLINEFEED_FLAG = true;
                break;

            case "ATSPACE" :
                String strSpace = "AT S1";
                strSpace += "\r";
                write(strSpace.getBytes());
                mConnectedThread.ATSPACE_FLAG = true;
                break;

            case "ATLOWPOWER" :
                String strLowPower = "AT LP";
                strLowPower += "\r";
                write(strLowPower.getBytes());

                mConnectedThread.ATLOWPOWER_FLAG = true;
                break;
            case "ATPP" :
                //PP 파라미터 Default 에서 IgnMon Input 조건 추가.  ( b2 - true )
                String strPP = "AT PP 0E SV BE";
                strPP += "\r";
                write(strPP.getBytes());
                mConnectedThread.ATPP_FLAG = true;
                break;
            case "ATCV" :
                String strCV = "AT CV 0000";
                strCV += "\r";
                write(strCV.getBytes());
                mConnectedThread.ATCV_FLAG = true;
                break;
            case "ATCV12" :
                String strCV12 = "AT CV 1200";
                strCV12 += "\r";
                write(strCV12.getBytes());
                mConnectedThread.ATCV12_FLAG = true;
                break;

            case "ATRV" :
                String strRV = "AT RV";
                strRV += "\r";
                write(strRV.getBytes());
                mConnectedThread.ATRV_FLAG = true;
                break;



        }

    }

    /**
     * 2018.02.26
     * 통신프로토콜 설정 오류 나면 블루투스 끊고 다시 받는 부분
     * */

    /*public void reconnectBluetooth (){
        try {

            Log.e("Bluetooth_Protocol","reconnectBluetooth");
            Context context = Status_DataBridge.getMainContext();
            String obdSN = USERINFO_DataBridge.getObdSN();
            if(context!=null && obdSN != null){
                new Bluetooth_Protocol().autoSearchBt(context,obdSN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void reconnectToastMessage(){
        try {
            Log.e("Bluetooth_Protocol","reconnectToastMessage");
            if(HandlerManager.toastHandler !=null){
                HandlerManager.toastHandler.obtainMessage(2,null).sendToTarget();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/







}