package mureung.mureungtest.View.BluetoothConnect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import mureung.mureungtest.Communication.BLEGattAttributes;
import mureung.mureungtest.Communication.BluetoothTest;
import mureung.mureungtest.Communication.Bluetooth_Camera_Protocol;
import mureung.mureungtest.Communication.Bluetooth_Protocol;
import mureung.mureungtest.Communication.BtList;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;
import mureung.mureungtest.Tool.Dlog;

import static android.content.Context.BIND_AUTO_CREATE;
import static mureung.mureungtest.Communication.Bluetooth_Protocol.bluetoothAdapter;

/**
 * Created by user on 2018-01-29.
 */

public class BluetoothConnect extends Fragment implements AdapterView.OnItemClickListener {

    ListView btListView;
    Button searchBtn;
    BluetoothConnect_List btListAdapter;
    ArrayList<BtList> btArrayList = new ArrayList<BtList>();
    BroadcastReceiver btReceiver = null;
    WifiP2pManager wifiP2pManager ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bluetoothconnect,container,false);
        PageStr.setPageStrData(PageStr.BluetoothConnect);



        searchBtn = (Button)view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btFindDevice();
            }
        });
        /*MainActivity.wifiP2pManager.discoverPeers(MainActivity.channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.e("BluetoothConnect","onSuccess");
            }

            @Override
            public void onFailure(int reason) {
                Log.e("BluetoothConnect","onFailure");
            }
        });

        BroadcastReceiver wifiP2PReceiver = new BroadcastReceiver() {
            @Override    public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                    int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == -1) return;
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        Log.e("wifiP2PReceiver", "WIFI_P2P_STATE_ENABLED");
                        requestPeers();            } else {
                        Log.i("wifiP2PReceiver", "WIFI_P2P_STATE_DISABLED");            }
                } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                    Log.i("wifiP2PReceiver", "WIFI_P2P_PEERS_CHANGED_ACTION");            //do request peer
                    requestPeers();
                    NetworkInfo netInfo = (NetworkInfo) intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);            if (netInfo != null && netInfo.isConnected()) {
                        Log.i("wifiP2PReceiver", "device connected:: " + netInfo.isConnected());


                        MainActivity.wifiP2pManager.requestConnectionInfo(MainActivity.channel, new WifiP2pManager.ConnectionInfoListener() {
                            @Override
                            public void onConnectionInfoAvailable(WifiP2pInfo info) {
                                //wifiP2pManager = info;
                            }

                        });
                    }
                } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                    Log.i("wifiP2PReceiver", "WIFI_P2P_CONNECTION_CHANGED_ACTION");
                } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                    Log.i("wifiP2PReceiver", "WIFI_P2P_THIS_DEVICE_CHANGED_ACTION");        }
            }
        };
*/

        btListView = (ListView)view.findViewById(R.id.btListView);
        btArrayList = new Bluetooth_Camera_Protocol().enableBluetooth();
        btListAdapter = new BluetoothConnect_List(getActivity(),R.layout.bluetoothconnect_list, btArrayList);
        btListView.setAdapter(btListAdapter);
        btListView.setOnItemClickListener(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();







        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            if(btReceiver != null){
                getContext().unregisterReceiver(btReceiver);
                btReceiver = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String address = btArrayList.get(position).btAddress;
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
        //new Bluetooth_Camera_Protocol().connectDevice(device);
        new Bluetooth_Protocol().connectDevice(device);
        //new BluetoothTest().connect(device.getAddress());
        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String data = "010c\r";
                Dlog.e("push!");
                new BluetoothTest().writeText(data.getBytes());

            }
        },5000,1000);*/
        /*mDeviceAddress = address;
        Intent gattServiceIntent = new Intent(getContext(), BluetoothTest.class);
        getContext().bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);*/

    }

    public void btFindDevice(){
        if(!bluetoothAdapter.isEnabled()){
            bluetoothAdapter.enable();
        }

        if(btReceiver != null){
            getContext().unregisterReceiver(btReceiver);
        }

        bluetoothAdapter.startDiscovery();

        btListAdapter.clear();
        //기기 검색한 뒤 ListAdapter 로 기기들 리스트화
        btReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //검색되는 기기들을 브로드캐스트를 통해 가져옴
                String action = intent.getAction();
                BtList btList = null;
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(device.getName() == null){

                        btList = new BtList("알수 없음", device.getAddress());
                    }else {
                        btList = new BtList(device.getName(), device.getAddress());
                    }
                    if(btArrayList == null){
                        btArrayList = new ArrayList<BtList>();
                    }
                    btArrayList.add(btList);

                }
                if(getContext() != null){
                    btListAdapter = new BluetoothConnect_List(getContext(),R.layout.bluetoothconnect_list, btArrayList);
                    btListView.setAdapter(btListAdapter);
                }
            }

        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getContext().registerReceiver(btReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(btReceiver != null){
            getActivity().unregisterReceiver(btReceiver);
        }
    }


    public BluetoothGattCharacteristic characteristicTx = null;
    private BluetoothTest mBluetoothLeService;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDevice = null;
    private String mDeviceAddress;

    private boolean flag = true;
    private boolean connState = false;
    private boolean scanFlag = false;

    private byte[] data = new byte[3];
    private static final int REQUEST_ENABLE_BT = 1;
    private static final long SCAN_PERIOD = 2000;

    final private static char[] hexArray = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            Dlog.e("test 1111");
            mBluetoothLeService = ((BluetoothTest.LocalBinder) service)
                    .getService();

            if (!mBluetoothLeService.initialize()) {
                Dlog.e("Unable to initialize Bluetooth");

            }

            new BluetoothTest().connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Dlog.e("test 2222");
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_DISCONNECTED.equals(action)) {
                Toast.makeText(getContext(), "Disconnected",
                        Toast.LENGTH_SHORT).show();
                setButtonDisable();
            } else if (mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_SERVICES_DISCOVERED
                    .equals(action)) {
                Toast.makeText(getContext(), "Connected",
                        Toast.LENGTH_SHORT).show();

                getGattService(mBluetoothLeService.getSupportedGattService());
            } else if (mureung.mureungtest.Communication.BluetoothTest.ACTION_DATA_AVAILABLE.equals(action)) {
                data = intent.getByteArrayExtra(mureung.mureungtest.Communication.BluetoothTest.EXTRA_DATA);

                readAnalogInValue(data);
            } else if (mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_RSSI.equals(action)) {
                //displayData(intent.getStringExtra(mureung.mureungtest.Communication.BluetoothTest.EXTRA_DATA));
            }
        }
    };

    private void readAnalogInValue(byte[] data) {
        for (int i = 0; i < data.length; i += 3) {
            if (data[i] == 0x0A) {
                Dlog.e("test 1111");
            } else if (data[i] == 0x0B) {
                int Value;

                Value = ((data[i + 1] << 8) & 0x0000ff00)
                        | (data[i + 2] & 0x000000ff);

                Dlog.e("Value : " + Value);
            }
        }
    }

    private void setButtonEnable() {
        flag = true;
        connState = true;
    }

    private void setButtonDisable() {
        flag = false;
        connState = false;
    }

    private void startReadRssi() {
        new Thread() {
            public void run() {

                while (flag) {
                    mBluetoothLeService.readRssi();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    private void getGattService(BluetoothGattService gattService) {
        if (gattService == null)
            return;

        setButtonEnable();
        startReadRssi();

        characteristicTx = gattService
                .getCharacteristic(mureung.mureungtest.Communication.BluetoothTest.UUID_BLE_SHIELD_TX);

        BluetoothGattCharacteristic characteristicRx = gattService
                .getCharacteristic(mureung.mureungtest.Communication.BluetoothTest.UUID_BLE_SHIELD_RX);
        mBluetoothLeService.setCharacteristicNotification(characteristicRx,
                true);
        mBluetoothLeService.readCharacteristic(characteristicRx);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_CONNECTED);
        intentFilter.addAction(mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(mureung.mureungtest.Communication.BluetoothTest.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(mureung.mureungtest.Communication.BluetoothTest.ACTION_GATT_RSSI);

        return intentFilter;
    }

    private void scanLeDevice() {
        new Thread() {

            @Override
            public void run() {
                mBluetoothAdapter.startLeScan(mLeScanCallback);

                try {
                    Thread.sleep(SCAN_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mBluetoothAdapter.stopLeScan(mLeScanCallback);
            }
        }.start();
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi,
                             final byte[] scanRecord) {

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    byte[] serviceUuidBytes = new byte[16];
                    String serviceUuid = "";
                    for (int i = 32, j = 0; i >= 17; i--, j++) {
                        serviceUuidBytes[j] = scanRecord[i];
                    }
                    serviceUuid = bytesToHex(serviceUuidBytes);
                    if (stringToUuidString(serviceUuid).equals(
                            BLEGattAttributes.BLE_SHIELD_SERVICE
                                    .toUpperCase(Locale.ENGLISH))) {
                        mDevice = device;
                    }
                }
            });
        }
    };

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String stringToUuidString(String uuid) {
        StringBuffer newString = new StringBuffer();
        newString.append(uuid.toUpperCase(Locale.ENGLISH).substring(0, 8));
        newString.append("-");
        newString.append(uuid.toUpperCase(Locale.ENGLISH).substring(8, 12));
        newString.append("-");
        newString.append(uuid.toUpperCase(Locale.ENGLISH).substring(12, 16));
        newString.append("-");
        newString.append(uuid.toUpperCase(Locale.ENGLISH).substring(16, 20));
        newString.append("-");
        newString.append(uuid.toUpperCase(Locale.ENGLISH).substring(20, 32));

        return newString.toString();
    }

    /*@Override
    protected void onStop() {
        super.onStop();

        flag = false;

        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mServiceConnection != null)
            unbindService(mServiceConnection);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT
                && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/




}
