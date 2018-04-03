package mureung.mureungtest.Comunication;


import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.vision.face.FaceDetector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import mureung.mureungtest.MainActivity;
import mureung.mureungtest.MainView;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.MakeData;
import mureung.mureungtest.Tool.SearchVINTask;
import mureung.mureungtest.Tool.Time_DataBridge;
import mureung.mureungtest.View.BluetoothPairFragment;
import mureung.mureungtest.View.Camera.CameraData;
import mureung.mureungtest.View.Camera.CameraPullFragment;
import mureung.mureungtest.View.Camera.CameraPushFragment;
import mureung.mureungtest.View.Terminal.TerminalView;

import static mureung.mureungtest.Comunication.Bypass_Stream.dataVIN;
import static mureung.mureungtest.Tool.SearchVINTask.strMaker;
import static mureung.mureungtest.Tool.SearchVINTask.strModel;
import static mureung.mureungtest.Tool.SearchVINTask.strYear;


/**
 * Created by user on 2017-06-08.
 */

public class Bluetooth_Camera_Protocol {

    public static boolean OBD_CONNECTSTATE = false;


    private static final String TAG = "Bluetooth_Protocol";

    public static BluetoothAdapter bluetoothAdapter;

    //UUID
    private static final UUID btUUID = UUID
            .fromString("00001101-0000-1000-8000-00805f9b34fb");
    //00001101-0000-1000-8000-00805f9b34fb
    //00000000-0000-1000-8000-00805F9B34FB

    //private static ArrayList<ListAdapter_OBDConnect.btList> btPairedArrayList = null;
    private static CameraAcceptThread mCameraAcceptThread;
    private static CameraConnectThread mCameraConnectThread;
    private static CameraConnectedThread mCameraConnectedThread;

    private static Handler dataHandler;

    public static int mState = 0;

    //태그 값들 지정해줘야함
    private static final int STATE_LISTEN = 1; // now listening for incoming
    private static final int STATE_CONNECTING = 2; // now initiating an outgoing
    private static final int STATE_CONNECTED = 3; // now connected to a remote
    private static final int REQUEST_ENABLE_BT = 3;
    private static final int MESSAGE_READ =3;
    private static final int CONNECT_FAIL = 4;

    public static boolean PushCameraDataReady_FLAG =false;
    public static BroadcastReceiver btReceiver;
    public static int CONNECTED_STATE = 0;

    public Bluetooth_Camera_Protocol(Activity activity , Handler handler) {
        dataHandler = handler;

    }
    public Bluetooth_Camera_Protocol() {


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
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter!=null){
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

        bluetoothCameraConnect(device);


    }*/

    private class CameraAcceptThread extends Thread {
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;

        public CameraAcceptThread() {
            BluetoothServerSocket tmp = null;

            // Create a new listening server socket
            try {
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("BluetoothChat", btUUID);
            } catch (IOException e) {
                Log.e(TAG, "listen() failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            setName("AcceptThread");
            BluetoothSocket socket = null;

            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "accept() failed", e);
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (Bluetooth_Camera_Protocol.this) {
                        switch (mState) {
                            case STATE_LISTEN:
                            case STATE_CONNECTING:
                                // Situation normal. Start the connected thread.
                                connected(socket);
                                break;
                            case STATE_CONNECTED:
                                // Either not ready or already connected. Terminate new socket.
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "Could not close unwanted socket", e);
                                }
                                break;
                        }
                    }
                }
            }
        }

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }


    //블루투스 연결 Thread
    private class CameraConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice ;
        Handler obdContectFragmentHandler;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        private CameraConnectThread(BluetoothDevice device) {
            mmDevice = device;
            if(MainActivity.MainActivityHandler != null){
                MainActivity.MainActivityHandler.obtainMessage(1,"연결중").sendToTarget();

            }
            if(BluetoothPairFragment.bluetoothTestHandler != null){
                BluetoothPairFragment.bluetoothTestHandler.obtainMessage(BluetoothPairFragment.bluetooth1StateNum,"연결중");
            }
            BluetoothSocket secureRfComm = null;
            // 디바이스 정보를 얻어서 BluetoothSocket 생성

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
                //secureRfComm = device.createInsecureRfcommSocketToServiceRecord(btUUID);
                secureRfComm = device.createRfcommSocketToServiceRecord(btUUID);

            } catch (IOException e) {
                e.printStackTrace();
            }
            mmSocket = secureRfComm;
        }
        //블루투스 연결 스레드 시작
        public void run() {
            Log.e(TAG, "BEGIN mCameraConnectThread");
            if(bluetoothAdapter != null){
                bluetoothAdapter.cancelDiscovery();
            }

            try {

                mmSocket.connect();


                Log.e(TAG, "Connect Success");
            } catch (IOException e) {
                connectionFailed();

                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG,
                            "unable to close() socket during connection failure",
                            e2);
                }
                Bluetooth_Camera_Protocol.this.start();
                return;
            }
            synchronized (Bluetooth_Camera_Protocol.this) {
                mCameraConnectThread = null;
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
                }
                if(BluetoothPairFragment.bluetoothTestHandler != null){
                    BluetoothPairFragment.bluetoothTestHandler.obtainMessage(BluetoothPairFragment.bluetooth1StateNum,"연결끊김");
                }
                new MainView().setObdIcon(false);
                MainView.bluetoothState = false;
            } catch (IOException e) {
                Log.e(TAG, "close() of bluetoothCameraConnect socket failed", e);
            }
        }
    }




    //블루투스 연결 후 통신을 위한 Thread
    public class CameraConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;




        //통신에 필요한 Socket 을 선언 및 접속 하는 부분 = 블루투스 송수신 접속
        private CameraConnectedThread(BluetoothSocket socket) {

            Log.e(TAG, "create CameraConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            Log.e("CameraConnectedThread","mmSocket "+mmSocket);
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;


        }

        //블루투스 데이터 수신을 스레드로 항상 수신대기함
        public void run() {
            Log.e(TAG, "BEGIN mCameraConnectedThread");
            byte[] readBuffer = new byte[1024];
            byte[] testBuffer = null;
            int bytes = 0;
            String received_text = "";
            int addByte = 0;
            int dataLength = 0;

            if(MainActivity.MainActivityHandler != null){
                MainActivity.MainActivityHandler.obtainMessage(1,"연결 됨").sendToTarget();

            }
            while (true) {

                try {

                    if(PageStr.getPageStrData().equals(PageStr.CameraPullTest)){
                        bytes = mmInStream.read(readBuffer,0,readBuffer.length);
                        String strBuffer = new String(readBuffer,0,bytes,"UTF-8");
                        int intLength = 0;

                        //여기선 read
                        /*bytes = mmInStream.read(readBuffer,0,readBuffer.length);

                        //Log.e("test","test bytes : " + bytes);
                        String strBuffer = new String(readBuffer,0,bytes,"UTF-8");
                        int intLength = 0;

                        received_text += strBuffer;
                        received_text = received_text.replace("\r","");
                        if(received_text.contains("</length>")){
                            String[] data = received_text.split("<length>");
                            data = data[1].split("</length>");
                            String strLength = data[0];
                            if(!strLength.matches("^[0-9]*$")){
                                StringBuilder strRecivedText = new StringBuilder();
                                for(int i = 0 ; i < strLength.length() ; i ++){

                                    String checkText = strLength.substring(i,i+1);
                                    if(Pattern.matches("^[0-9]*$",checkText)){
                                        strRecivedText.append(checkText);
                                    }
                                }
                                strLength = String.valueOf(strRecivedText);
                            }
                            Log.e("Bluetooth_Camera","length : " + strLength);
                            received_text = received_text.replace("<length>","");
                            received_text = received_text.replace("</length>","");
                            received_text = received_text.replace(strLength,"");
                        }

                        if(received_text.contains("</body>")){
                            String[] data = received_text.split("</body>");
                            data = data[0].split("<body>");
                            String strBody = data[1];
                            Bitmap bitmap = BitmapFactory.decodeByteArray(strBody.getBytes(),0,strBody.getBytes().length);
                            new CameraPullFragment().setCameraImage(bitmap);
                            received_text = received_text.replace("<body>","");
                            received_text = received_text.replace("</body>","");
                            received_text = received_text.replace(strBody,"");
                        }*/












                        /*if(strBuffer.contains("length/")){
                            strBuffer.replace("length/","");
                            int length = Integer.parseInt(strBuffer);
                            Log.e("test","test length : " + length);
                        }*/

                        //Log.e("bluetooth_protocol","strBuffer : "+strBuffer);

                        if(strBuffer.contains("<length>")){
                            //Log.e("test","strBuffer : " + strBuffer );
                            String[] spTest = strBuffer.split("<length>");
                            //Log.e("test","spTest : " + String.valueOf(spTest[1]));
                            String spTest2= spTest[1];
                            String[] spTest3 = spTest2.split("</length>");
                            String strLength = spTest3[0];
                            //Log.e("Bluetooth_Camera","length : " + strLength);




                            if(strLength != null){
                                strLength = strLength.replace("<length>","");
                                strLength = strLength.replace("</length>","");
                            }

                            try{
                                if(!strLength.matches("^[0-9]*$")){
                                    StringBuilder strRecivedText = new StringBuilder();
                                    for(int i = 0 ; i < strLength.length() ; i ++){

                                        String checkText = strLength.substring(i,i+1);
                                        if(Pattern.matches("^[0-9]*$",checkText)){
                                            strRecivedText.append(checkText);
                                        }
                                    }
                                    strLength = String.valueOf(strRecivedText);
                                }
                                intLength = Integer.parseInt(String.valueOf(strLength));
                                dataLength = intLength +1;
                                testBuffer = new byte[dataLength+5];
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            //Log.e("Bluetooth_Camera","int length : " + String.valueOf(intLength));
                            strBuffer = "";
                            addByte = 0;
                        }else {

                            if(testBuffer != null){
                                try {
                                    //Log.e("Bluetooth_Camera","addByte : " + addByte + " , bytes : " + bytes);
                                    System.arraycopy(readBuffer,0,testBuffer,addByte,bytes);
                                    addByte += bytes;
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                //Log.e("test","test addByte : " + addByte  + " , dataLength : " + dataLength);

                                try {
                                    if(addByte == dataLength){
                                        //Log.e("test","test 111");
                                        /*Log.e("test","test 1111 ");
                                        ByteArrayOutputStream outstr = new ByteArrayOutputStream();
                                        Log.e("test","test 2222 ");
                                        //Rect rect = new Rect(0,0,1920,1080);
                                        Log.e("test","test 3333 ");
                                        YuvImage yuvImage = new YuvImage(testBuffer, ImageFormat.NV21,1920,1080,null);
                                        Log.e("test","test 4444 testBuffer.length : " + testBuffer.length);

                                        yuvImage.compressToJpeg(new Rect(0,0,yuvImage.getWidth(),yuvImage.getHeight()),100,outstr);
                                        Log.e("test","test 5555 ");
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(outstr.toByteArray(),0,outstr.size());
                                        Log.e("test","test 6666 ");
                                        new CameraPullFragment().setCameraImage(bitmap);*/

                                        Bitmap bitmap = BitmapFactory.decodeByteArray(testBuffer,0,testBuffer.length);
                                        try {
                                            if(bitmap!= null){
                                                //Log.e("test","bitmap.getWidth() : " + bitmap.getWidth() + " , bitmap.getHeight() : " + bitmap.getHeight());
                                            }

                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }


                                        /*try {
                                            FaceDetector.Face[] faces = new FaceDetector.Face[2];
                                            FaceDetector detector = new FaceDetector(bitmap.getWidth(),bitmap.getHeight(),faces.length);
                                            int numFaces=detector.findFaces(bitmap,faces);
                                            Log.e("FaceDetector","numFaces :  "+numFaces);
                                            if(faces.length!=0){
                                                for(FaceDetector.Face face: faces){
                                                    PointF midPoint = new PointF();
                                                    face.getMidPoint(midPoint);
                                                    float eyesDistance = face.eyesDistance();
                                                    Log.e("FaceDetector","eyesDistance :  " + eyesDistance);
                                                }
                                            }



                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }*/

                                        new CameraPullFragment().setCameraImage(bitmap);


                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        }










                        /*byte[] encodeByte = Base64.decode(strBuffer,Base64.DEFAULT);
                        InputStream inputStream = new ByteArrayInputStream(encodeByte);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);*/

                       /* Log.e("test","test 2222 ");
                        ByteArrayOutputStream outstr = new ByteArrayOutputStream();
                        Log.e("test","test 3333 ");
                        Rect rect = new Rect(0,0,1920,1080);
                        Log.e("test","test 4444 ");
                        YuvImage yuvImage = new YuvImage(readBuffer, ImageFormat.NV21,1920,1080,null);
                        Log.e("test","test 5555 ");
                        yuvImage.compressToJpeg(rect,100,outstr);
                        Log.e("test","test 6666 ");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(outstr.toByteArray(),0,outstr.size());
                        Log.e("test","test 7777 ");

                        new CameraPullFragment().setCameraImage(bitmap);*/

                        /*final String strBuffer = new String(readBuffer,0,bytes,"UTF-8");
                        Log.e("bluetooth_protocol","strBuffer : "+strBuffer);
                        received_text += strBuffer;

                        if(received_text.contains(">")){


                            received_text = "";
                        }*/



                    }else if(PageStr.getPageStrData().equals(PageStr.CameraPushTest)){

                        //여기서 화면 데이터 write
                        PushCameraDataReady_FLAG = true;
                    }
                    //String 으로 변환


                } catch (IOException e) {
                    connectionLost();
                    e.printStackTrace();

                    break;
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
                connectionLost();
            }
        }

        //블루투스 연결을 끊기 위한 부분
        public void cancle() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of bluetoothCameraConnect socket failed", e);
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



        if (mCameraConnectThread != null) {
            mCameraConnectThread.cancel();
            mCameraConnectThread = null;
        }

        if (mCameraConnectedThread != null) {
            mCameraConnectedThread.cancle();
            mCameraConnectedThread = null;
        }

        if(mCameraAcceptThread == null){
            mCameraAcceptThread = new CameraAcceptThread();
            mCameraAcceptThread.start();
        }

    }

    //블루투스 기기 연결 시도
    private synchronized void bluetoothCameraConnect(BluetoothDevice device) {
        Log.e(TAG, "bluetoothCameraConnect to: " + device);

        if (mState == STATE_CONNECTING) {
            if (mCameraConnectThread != null) {
                mCameraConnectThread.cancel();
                mCameraConnectThread = null;
            }
        }

        if (mCameraConnectedThread != null) {
            mCameraConnectedThread.cancle();
            mCameraConnectedThread = null;
        }

        mCameraConnectThread = new CameraConnectThread(device);
        mCameraConnectThread.start();


        setState(STATE_CONNECTING);
    }


    //블루투스 연결 완료
    private synchronized void connected(BluetoothSocket socket ) {
        Log.e(TAG, "connected");
        if (mCameraConnectThread != null) {
            mCameraConnectThread.cancel();
            mCameraConnectThread = null;
        }

        if (mCameraConnectedThread != null) {
            mCameraConnectedThread.cancle();
            mCameraConnectedThread = null;
        }


        mCameraConnectedThread = new CameraConnectedThread(socket);
        mCameraConnectedThread.start();

        setState(STATE_CONNECTED);
    }

    /*public synchronized void stop() {
        Log.e(TAG, "stop");
        if (mCameraConnectThread != null) {
            mCameraConnectThread.cancle();
            mCameraConnectThread = null;
        }
        if (mCameraConnectedThread != null) {
            mCameraConnectedThread.cancle();
            mCameraConnectedThread = null;
        }

        setState(0);
    }*/

    // 값을 쓰는 부분(보내는 부분)
    public void write(byte[] out) {
        CameraConnectedThread r;
        //Log.e("test","write out : " + new String(out,0,out.length) );
        synchronized (this) {
            if (mState != STATE_CONNECTED){
                return;
            }
            r = mCameraConnectedThread;
        }

        r.write(out);
    }


    // 연결 실패했을때
    private void connectionFailed() {
        setState(STATE_LISTEN);
        PushCameraDataReady_FLAG = false;
        if(mCameraConnectedThread != null){

        }


    }




    // 연결을 잃었을 때
    private void connectionLost() {

        PushCameraDataReady_FLAG = false;
        setState(STATE_LISTEN);
        if(dataHandler != null){
            dataHandler.obtainMessage(CONNECT_FAIL,0,0,"연결 끊김").sendToTarget();
        }
        if(BluetoothPairFragment.bluetoothTestHandler != null){
            BluetoothPairFragment.bluetoothTestHandler.obtainMessage(BluetoothPairFragment.bluetooth1StateNum,"연결끊김");
        }
        if(mCameraConnectThread != null){
            mCameraConnectThread.interrupt();
        }
        if(mCameraConnectedThread != null){
            mCameraConnectedThread.interrupt();
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


        bluetoothCameraConnect(device);

    }

    public void bluetoothEnable (){
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.enable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*public void autoSearchBt(Context context, final String address){
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
                            bluetoothCameraConnect(device);
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


    }*/

    public void cancle(){
        if(mCameraConnectedThread != null){
            Log.e("Bluetooth_Protocol"," cancle ");
            mCameraConnectedThread.cancle();
        }

    }


    //블루투스가 완전히 연결 되었는지 아닌지 ture,false 의 값을 반환한다
    private boolean bluetoothConnected(){
        return getState() == 3;
    }





}