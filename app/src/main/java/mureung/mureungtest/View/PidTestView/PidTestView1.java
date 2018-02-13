package mureung.mureungtest.View.PidTestView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mureung.mureungtest.R;

/**
 * Created by user on 2018-01-30.
 */


public class PidTestView1 extends Fragment {

    TextView pidName00,pidName01,pidName02,pidName03,pidName04,pidName05,pidName06,pidName07,pidName08
            ,pidName09,pidName0A,pidName0B,pidName0C,pidName0D,pidName0E,pidName0F,pidName10,pidName11
            ,pidName12,pidName13,pidName14,pidName15,pidName16,pidName17,pidName18,pidName19,pidName1A
            ,pidName1B,pidName1C,pidName1D,pidName1E,pidName1F
            ,pidData00,pidData01,pidData02,pidData03,pidData04,pidData05,pidData06,pidData07,pidData08
            ,pidData09,pidData0A,pidData0B,pidData0C,pidData0D,pidData0E,pidData0F,pidData10,pidData11
            ,pidData12,pidData13,pidData14,pidData15,pidData16,pidData17,pidData18,pidData19,pidData1A
            ,pidData1B,pidData1C,pidData1D,pidData1E,pidData1F;

    public static Handler pidTestView1_nameHandler = null;
    public static Handler pidTestView1_dataHandler = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pidtestview1,container,false);
        initView(view);

        nameHandler();
        dataHandler();



        return view;
    }

    private void initView(View view){
        pidData00 = (TextView)view.findViewById(R.id.pidData00);
        pidData01 = (TextView)view.findViewById(R.id.pidData01);
        pidData02 = (TextView)view.findViewById(R.id.pidData02);
        pidData03 = (TextView)view.findViewById(R.id.pidData03);
        pidData04 = (TextView)view.findViewById(R.id.pidData04);
        pidData05 = (TextView)view.findViewById(R.id.pidData05);
        pidData06 = (TextView)view.findViewById(R.id.pidData06);
        pidData07 = (TextView)view.findViewById(R.id.pidData07);
        pidData08 = (TextView)view.findViewById(R.id.pidData08);
        pidData09 = (TextView)view.findViewById(R.id.pidData09);
        pidData0A = (TextView)view.findViewById(R.id.pidData0A);
        pidData0B = (TextView)view.findViewById(R.id.pidData0B);
        pidData0C = (TextView)view.findViewById(R.id.pidData0C);
        pidData0D = (TextView)view.findViewById(R.id.pidData0D);
        pidData0E = (TextView)view.findViewById(R.id.pidData0E);
        pidData0F = (TextView)view.findViewById(R.id.pidData0F);
        pidData10 = (TextView)view.findViewById(R.id.pidData10);
        pidData11 = (TextView)view.findViewById(R.id.pidData11);
        pidData12 = (TextView)view.findViewById(R.id.pidData12);
        pidData13 = (TextView)view.findViewById(R.id.pidData13);
        pidData14 = (TextView)view.findViewById(R.id.pidData14);
        pidData15 = (TextView)view.findViewById(R.id.pidData15);
        pidData16 = (TextView)view.findViewById(R.id.pidData16);
        pidData17 = (TextView)view.findViewById(R.id.pidData17);
        pidData18 = (TextView)view.findViewById(R.id.pidData18);
        pidData19 = (TextView)view.findViewById(R.id.pidData19);
        pidData1A = (TextView)view.findViewById(R.id.pidData1A);
        pidData1B = (TextView)view.findViewById(R.id.pidData1B);
        pidData1C = (TextView)view.findViewById(R.id.pidData1C);
        pidData1D = (TextView)view.findViewById(R.id.pidData1D);
        pidData1E = (TextView)view.findViewById(R.id.pidData1E);
        pidData1F = (TextView)view.findViewById(R.id.pidData1F);



        pidName00 = (TextView)view.findViewById(R.id.pidName00);
        pidName00.setTextColor(Color.RED);
        pidName01 = (TextView)view.findViewById(R.id.pidName01);
        pidName01.setTextColor(Color.RED);
        pidName02 = (TextView)view.findViewById(R.id.pidName02);
        pidName02.setTextColor(Color.RED);
        pidName03 = (TextView)view.findViewById(R.id.pidName03);
        pidName03.setTextColor(Color.RED);
        pidName04 = (TextView)view.findViewById(R.id.pidName04);
        pidName04.setTextColor(Color.RED);
        pidName05 = (TextView)view.findViewById(R.id.pidName05);
        pidName05.setTextColor(Color.RED);
        pidName06 = (TextView)view.findViewById(R.id.pidName06);
        pidName06.setTextColor(Color.RED);
        pidName07 = (TextView)view.findViewById(R.id.pidName07);
        pidName07.setTextColor(Color.RED);
        pidName08 = (TextView)view.findViewById(R.id.pidName08);
        pidName08.setTextColor(Color.RED);
        pidName09 = (TextView)view.findViewById(R.id.pidName09);
        pidName09.setTextColor(Color.RED);
        pidName0A = (TextView)view.findViewById(R.id.pidName0A);
        pidName0A.setTextColor(Color.RED);
        pidName0B = (TextView)view.findViewById(R.id.pidName0B);
        pidName0B.setTextColor(Color.RED);
        pidName0C = (TextView)view.findViewById(R.id.pidName0C);
        pidName0C.setTextColor(Color.RED);
        pidName0D = (TextView)view.findViewById(R.id.pidName0D);
        pidName0D.setTextColor(Color.RED);
        pidName0E = (TextView)view.findViewById(R.id.pidName0E);
        pidName0E.setTextColor(Color.RED);
        pidName0F = (TextView)view.findViewById(R.id.pidName0F);
        pidName0F.setTextColor(Color.RED);
        pidName10 = (TextView)view.findViewById(R.id.pidName10);
        pidName10.setTextColor(Color.RED);
        pidName11 = (TextView)view.findViewById(R.id.pidName11);
        pidName11.setTextColor(Color.RED);
        pidName12 = (TextView)view.findViewById(R.id.pidName12);
        pidName12.setTextColor(Color.RED);
        pidName13 = (TextView)view.findViewById(R.id.pidName13);
        pidName13.setTextColor(Color.RED);
        pidName14 = (TextView)view.findViewById(R.id.pidName14);
        pidName14.setTextColor(Color.RED);
        pidName15 = (TextView)view.findViewById(R.id.pidName15);
        pidName15.setTextColor(Color.RED);
        pidName16 = (TextView)view.findViewById(R.id.pidName16);
        pidName16.setTextColor(Color.RED);
        pidName17 = (TextView)view.findViewById(R.id.pidName17);
        pidName17.setTextColor(Color.RED);
        pidName18 = (TextView)view.findViewById(R.id.pidName18);
        pidName18.setTextColor(Color.RED);
        pidName19 = (TextView)view.findViewById(R.id.pidName19);
        pidName19.setTextColor(Color.RED);
        pidName1A = (TextView)view.findViewById(R.id.pidName1A);
        pidName1A.setTextColor(Color.RED);
        pidName1B = (TextView)view.findViewById(R.id.pidName1B);
        pidName1B.setTextColor(Color.RED);
        pidName1C = (TextView)view.findViewById(R.id.pidName1C);
        pidName1C.setTextColor(Color.RED);
        pidName1D = (TextView)view.findViewById(R.id.pidName1D);
        pidName1D.setTextColor(Color.RED);
        pidName1E = (TextView)view.findViewById(R.id.pidName1E);
        pidName1E.setTextColor(Color.RED);
        pidName1F = (TextView)view.findViewById(R.id.pidName1F);
        pidName1F.setTextColor(Color.RED);

    }

    private void nameHandler(){
        pidTestView1_nameHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName00.setTextColor(Color.GREEN);
                        }

                        break;
                    case 2:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName01.setTextColor(Color.GREEN);
                        }

                        break;
                    case 3:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName02.setTextColor(Color.GREEN);
                        }

                        break;
                    case 4:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName03.setTextColor(Color.GREEN);
                        }

                        break;
                    case 5:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName04.setTextColor(Color.GREEN);
                        }

                        break;
                    case 6:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName05.setTextColor(Color.GREEN);
                        }

                        break;
                    case 7:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName06.setTextColor(Color.GREEN);
                        }

                        break;
                    case 8:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName07.setTextColor(Color.GREEN);
                        }

                        break;
                    case 9:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName08.setTextColor(Color.GREEN);
                        }

                        break;
                    case 10:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName09.setTextColor(Color.GREEN);
                        }

                        break;
                    case 11:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 12:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 13:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 14:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 15:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 16:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName0F.setTextColor(Color.GREEN);
                        }

                        break;
                    case 17:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName10.setTextColor(Color.GREEN);
                        }

                        break;
                    case 18:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName11.setTextColor(Color.GREEN);
                        }

                        break;
                    case 19:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName12.setTextColor(Color.GREEN);
                        }

                        break;
                    case 20:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName13.setTextColor(Color.GREEN);
                        }

                        break;
                    case 21:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName14.setTextColor(Color.GREEN);
                        }

                        break;
                    case 22:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName15.setTextColor(Color.GREEN);
                        }

                        break;
                    case 23:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName16.setTextColor(Color.GREEN);
                        }

                        break;
                    case 24:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName17.setTextColor(Color.GREEN);
                        }

                        break;
                    case 25:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName18.setTextColor(Color.GREEN);
                        }

                        break;
                    case 26:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName19.setTextColor(Color.GREEN);
                        }

                        break;
                    case 27:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 28:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 29:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 30:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 31:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 32:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName1F.setTextColor(Color.GREEN);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void dataHandler(){
        pidTestView1_dataHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1: pidData00.setText(String.valueOf(msg.obj));
                        break;
                    case 2: pidData01.setText(String.valueOf(msg.obj));
                        break;
                    case 3: pidData02.setText(String.valueOf(msg.obj));
                        break;
                    case 4: pidData03.setText(String.valueOf(msg.obj));
                        break;
                    case 5: pidData04.setText(String.valueOf(msg.obj));
                        break;
                    case 6: pidData05.setText(String.valueOf(msg.obj));
                        break;
                    case 7: pidData06.setText(String.valueOf(msg.obj));
                        break;
                    case 8: pidData07.setText(String.valueOf(msg.obj));
                        break;
                    case 9: pidData08.setText(String.valueOf(msg.obj));
                        break;
                    case 10: pidData09.setText(String.valueOf(msg.obj));
                        break;
                    case 11: pidData0A.setText(String.valueOf(msg.obj));
                        break;
                    case 12: pidData0B.setText(String.valueOf(msg.obj));
                        break;
                    case 13: pidData0C.setText(String.valueOf(msg.obj));
                        break;
                    case 14: pidData0D.setText(String.valueOf(msg.obj));
                        break;
                    case 15: pidData0E.setText(String.valueOf(msg.obj));
                        break;
                    case 16: pidData0F.setText(String.valueOf(msg.obj));
                        break;
                    case 17: pidData10.setText(String.valueOf(msg.obj));
                        break;
                    case 18: pidData11.setText(String.valueOf(msg.obj));
                        break;
                    case 19: pidData12.setText(String.valueOf(msg.obj));
                        break;
                    case 20: pidData13.setText(String.valueOf(msg.obj));
                        break;
                    case 21: pidData14.setText(String.valueOf(msg.obj));
                        break;
                    case 22: pidData15.setText(String.valueOf(msg.obj));
                        break;
                    case 23: pidData16.setText(String.valueOf(msg.obj));
                        break;
                    case 24: pidData17.setText(String.valueOf(msg.obj));
                        break;
                    case 25: pidData18.setText(String.valueOf(msg.obj));
                        break;
                    case 26: pidData19.setText(String.valueOf(msg.obj));
                        break;
                    case 27: pidData1A.setText(String.valueOf(msg.obj));
                        break;
                    case 28: pidData1B.setText(String.valueOf(msg.obj));
                        break;
                    case 29: pidData1C.setText(String.valueOf(msg.obj));
                        break;
                    case 30: pidData1D.setText(String.valueOf(msg.obj));
                        break;
                    case 31: pidData1E.setText(String.valueOf(msg.obj));
                        break;
                    case 32: pidData1F.setText(String.valueOf(msg.obj));
                        break;
                }
                return true;
            }
        });
    }
}
