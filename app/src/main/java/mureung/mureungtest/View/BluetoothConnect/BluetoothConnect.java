package mureung.mureungtest.View.BluetoothConnect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.Comunication.BtList;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

import static mureung.mureungtest.Comunication.Bluetooth_Protocol.bluetoothAdapter;

/**
 * Created by user on 2018-01-29.
 */

public class BluetoothConnect extends Fragment implements AdapterView.OnItemClickListener {

    ListView btListView;
    Button searchBtn;
    BluetoothConnect_List btListAdapter;
    ArrayList<BtList> btArrayList = new ArrayList<BtList>();
    BroadcastReceiver btReceiver = null;


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


        btListView = (ListView)view.findViewById(R.id.btListView);
        btListAdapter = new BluetoothConnect_List(getActivity(),R.layout.bluetoothconnect_list, btArrayList);
        btListView.setAdapter(btListAdapter);
        btListView.setOnItemClickListener(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();







        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String address = btArrayList.get(position).btAddress;
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
        new Bluetooth_Protocol().connectDevice(device);
    }

    public void btFindDevice(){
        if(!bluetoothAdapter.isEnabled()){
            bluetoothAdapter.enable();
        }

        if(btReceiver != null){
            getContext().unregisterReceiver(btReceiver);
        }

        if(bluetoothAdapter.cancelDiscovery()){
            bluetoothAdapter.startDiscovery();
        }

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
}
