package mureung.mureungtest.View.Terminal;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mureung.mureungtest.R;

/**
 * Created by user on 2018-01-30.
 */

public class TerminalView_List extends ArrayAdapter {

    private TextView pushData,pullData;
    private static ViewHolder holder;
    private static ArrayList<ComunicationData> comunicationDataArrayList =null;
    public TerminalView_List(@NonNull Context context, int textViewResourceId, ArrayList<ComunicationData> comunicationDataArrayList) {
        super(context, textViewResourceId,comunicationDataArrayList);
        TerminalView_List.comunicationDataArrayList = comunicationDataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.terminalview_list, null);


        }
        initView(v);

        if(TerminalView_List.comunicationDataArrayList != null){
            String strPushData = String.valueOf(comunicationDataArrayList.get(position).pushData);
            String strPullData = String.valueOf(comunicationDataArrayList.get(position).pullData);
            if(strPushData != null && !strPushData.equals("")&& !strPushData.equals("null")){
                holder.pullData.setVisibility(View.INVISIBLE);
                holder.pushData.setVisibility(View.VISIBLE);
                holder.pushData.setText(strPushData);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.pushData.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.terminal_data_text));
                }else {
                    holder.pushData.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.terminal_data_text));
                }
            }
            if( strPullData!= null && !strPullData.equals("")&& !strPullData.equals("null")){
                holder.pushData.setVisibility(View.INVISIBLE);
                holder.pullData.setVisibility(View.VISIBLE);
                holder.pullData.setText(strPullData);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.pullData.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.terminal_data_text));
                }else {
                    holder.pullData.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.terminal_data_text));
                }
            }

        }


        return v;
    }

    private void initView(View v){
        holder = new ViewHolder();
        holder.pushData = (TextView)v.findViewById(R.id.pushData);
        holder.pullData = (TextView)v.findViewById(R.id.pullData);

        holder.pushData.setTextColor(Color.BLUE);
        holder.pullData.setTextColor(Color.RED);
        v.setTag(holder);
    }
    private static class ViewHolder {
        private TextView pushData,pullData;

    }
}
