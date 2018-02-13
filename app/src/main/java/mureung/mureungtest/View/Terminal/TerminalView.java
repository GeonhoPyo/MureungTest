package mureung.mureungtest.View.Terminal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.Time_DataBridge;

/**
 * Created by user on 2018-01-30.
 */

public class TerminalView extends Fragment implements View.OnClickListener {

    EditText pushConnectEdit;
    Button pushConnectBtn;
    ListView terminalList;
    TerminalView_List terminalAdapter;
    private static ArrayList<ComunicationData> comunicationDataArrayList ;
    private static Handler dataHanlder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terminal,container,false);
        PageStr.setPageStrData(PageStr.Terminal);
        comunicationDataArrayList = new ArrayList<ComunicationData>();
        pushConnectEdit = (EditText)view.findViewById(R.id.pushConnectEdit);
        pushConnectBtn = (Button)view.findViewById(R.id.pushConnectBtn);
        pushConnectBtn.setOnClickListener(this);
        terminalList = (ListView) view.findViewById(R.id.terminalList);

        terminalList.setDivider(null);
        terminalAdapter = new TerminalView_List(getContext(),R.layout.terminalview_list,comunicationDataArrayList);
        terminalList.setAdapter(terminalAdapter);

        dataHanlder = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1 :
                        comunicationDataArrayList.add(new ComunicationData(String.valueOf(msg.obj),null));
                        terminalAdapter = new TerminalView_List(getContext(),R.layout.terminalview_list,comunicationDataArrayList);
                        terminalAdapter.notifyDataSetChanged();
                        terminalList.setAdapter(terminalAdapter);
                        terminalList.setSelection(comunicationDataArrayList.size()-1);
                        break;
                    case 2 :
                        comunicationDataArrayList.add(new ComunicationData(null,String.valueOf(msg.obj)));
                        terminalAdapter = new TerminalView_List(getContext(),R.layout.terminalview_list,comunicationDataArrayList);
                        terminalAdapter.notifyDataSetChanged();
                        terminalList.setAdapter(terminalAdapter);
                        terminalList.setSelection(comunicationDataArrayList.size()-1);
                        break;
                }

                return true;
            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pushConnectBtn:

                String getStr = String.valueOf(pushConnectEdit.getText());
                String pushData = getStr+"\r";
                new Bluetooth_Protocol().write(pushData.getBytes());
                setListData(getStr,null);

                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime(),"----------------------------------------------------------");
                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime()," Time : " + new Time_DataBridge().getDateTime());
                new ErrorLogManager().saveErrorLog("TerminalRec"+new Time_DataBridge().getTerminalTime()," PUSH DATA : " + getStr);
                break;
        }
    }
    public void setListData(String pushData , String pullData){
        if(pushData != null && pullData == null){
            dataHanlder.obtainMessage(1,pushData).sendToTarget();
        }else if(pushData == null && pullData != null){
            dataHanlder.obtainMessage(2,pullData).sendToTarget();
        }
    }

}
