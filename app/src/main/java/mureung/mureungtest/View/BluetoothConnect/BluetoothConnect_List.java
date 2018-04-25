package mureung.mureungtest.View.BluetoothConnect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mureung.mureungtest.Communication.BtList;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-01-29.
 */

public class BluetoothConnect_List extends ArrayAdapter {
    private ArrayList<BtList> btArrayList ;
    private TextView btName , btAddress;

    public BluetoothConnect_List(Context context, int textViewResourceId, ArrayList btArrayList) {
        super(context, textViewResourceId, btArrayList);
        this.btArrayList = btArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.bluetoothconnect_list, null);


        }

        btName = (TextView)v.findViewById(R.id.btName);
        btAddress = (TextView)v.findViewById(R.id.btAddress);

        btName.setText(btArrayList.get(position).btName);
        btAddress.setText(btArrayList.get(position).btAddress);

        return v;
    }
}
