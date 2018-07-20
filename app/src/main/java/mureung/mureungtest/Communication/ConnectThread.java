package mureung.mureungtest.Communication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;


public class ConnectThread extends Thread {
    private static final UUID btUUID = UUID
            .fromString("00001101-0000-1000-8000-00805f9b34fb");
    private BluetoothSocket mSocket = null;
    private BluetoothDevice mmDevice ;
    private BluetoothAdapter bluetoothAdapter;


    public ConnectThread(BluetoothDevice device,boolean secureFlag) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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
        Log.e("Bluetooth_Protocol", "BEGIN mConnectThread");

        try {
            mSocket.connect();


            Log.e("Bluetooth_Protocol", "Connect Success");

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

            e.printStackTrace();





            return;
        }

        new Bluetooth_Protocol3().connectedThread(mSocket);
        //연결 스레드 run 부분을 다 통과하게 된다면 연결 후 통신 스레드 시작


    }
    public void cancel() {
        try {
            if(mSocket != null){
                mSocket.close();
            }
            Log.e("Bluetooth_Protocol","cancel Success");
        } catch (IOException e) {
            Log.e("Bluetooth_Protocol", "close() of connect socket failed", e);
        }
    }


}
