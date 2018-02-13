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


public class PidTestView2 extends Fragment {

    TextView pidName20, pidName21, pidName22, pidName23, pidName24, pidName25, pidName26, pidName27, pidName28
            , pidName29, pidName2A, pidName2B, pidName2C, pidName2D, pidName2E, pidName2F, pidName30, pidName31
            , pidName32, pidName33,pidName34, pidName35, pidName36, pidName37, pidName38, pidName39, pidName3A
            , pidName3B, pidName3C, pidName3D, pidName3E, pidName3F
            , pidData20, pidData21, pidData22, pidData23, pidData24, pidData25, pidData26, pidData27, pidData28
            , pidData29, pidData2A, pidData2B, pidData2C, pidData2D, pidData2E, pidData2F, pidData30, pidData31
            , pidData32, pidData33, pidData34, pidData35, pidData36, pidData37, pidData38, pidData39, pidData3A
            , pidData3B, pidData3C, pidData3D, pidData3E, pidData3F;

    public static Handler pidTestView2_nameHandler = null;
    public static Handler pidTestView2_dataHandler = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pidtestview2,container,false);
        initView(view);

        nameHandler();
        dataHandler();



        return view;
    }

    private void initView(View view){
        pidData20 = (TextView)view.findViewById(R.id.pidData20);
        pidData21 = (TextView)view.findViewById(R.id.pidData21);
        pidData22 = (TextView)view.findViewById(R.id.pidData22);
        pidData23 = (TextView)view.findViewById(R.id.pidData23);
        pidData24 = (TextView)view.findViewById(R.id.pidData24);
        pidData25 = (TextView)view.findViewById(R.id.pidData25);
        pidData26 = (TextView)view.findViewById(R.id.pidData26);
        pidData27 = (TextView)view.findViewById(R.id.pidData27);
        pidData28 = (TextView)view.findViewById(R.id.pidData28);
        pidData29 = (TextView)view.findViewById(R.id.pidData29);
        pidData2A = (TextView)view.findViewById(R.id.pidData2A);
        pidData2B = (TextView)view.findViewById(R.id.pidData2B);
        pidData2C = (TextView)view.findViewById(R.id.pidData2C);
        pidData2D = (TextView)view.findViewById(R.id.pidData2D);
        pidData2E = (TextView)view.findViewById(R.id.pidData2E);
        pidData2F = (TextView)view.findViewById(R.id.pidData2F);
        pidData30 = (TextView)view.findViewById(R.id.pidData30);
        pidData31 = (TextView)view.findViewById(R.id.pidData31);
        pidData32 = (TextView)view.findViewById(R.id.pidData32);
        pidData33 = (TextView)view.findViewById(R.id.pidData33);
        pidData34 = (TextView)view.findViewById(R.id.pidData34);
        pidData35 = (TextView)view.findViewById(R.id.pidData35);
        pidData36 = (TextView)view.findViewById(R.id.pidData36);
        pidData37 = (TextView)view.findViewById(R.id.pidData37);
        pidData38 = (TextView)view.findViewById(R.id.pidData38);
        pidData39 = (TextView)view.findViewById(R.id.pidData39);
        pidData3A = (TextView)view.findViewById(R.id.pidData3A);
        pidData3B = (TextView)view.findViewById(R.id.pidData3B);
        pidData3C = (TextView)view.findViewById(R.id.pidData3C);
        pidData3D = (TextView)view.findViewById(R.id.pidData3D);
        pidData3E = (TextView)view.findViewById(R.id.pidData3E);
        pidData3F = (TextView)view.findViewById(R.id.pidData3F);



        pidName20 = (TextView)view.findViewById(R.id.pidName20);
        pidName20.setTextColor(Color.RED);
        pidName21 = (TextView)view.findViewById(R.id.pidName21);
        pidName21.setTextColor(Color.RED);
        pidName22 = (TextView)view.findViewById(R.id.pidName22);
        pidName22.setTextColor(Color.RED);
        pidName23 = (TextView)view.findViewById(R.id.pidName23);
        pidName23.setTextColor(Color.RED);
        pidName24 = (TextView)view.findViewById(R.id.pidName24);
        pidName24.setTextColor(Color.RED);
        pidName25 = (TextView)view.findViewById(R.id.pidName25);
        pidName25.setTextColor(Color.RED);
        pidName26 = (TextView)view.findViewById(R.id.pidName26);
        pidName26.setTextColor(Color.RED);
        pidName27 = (TextView)view.findViewById(R.id.pidName27);
        pidName27.setTextColor(Color.RED);
        pidName28 = (TextView)view.findViewById(R.id.pidName28);
        pidName28.setTextColor(Color.RED);
        pidName29 = (TextView)view.findViewById(R.id.pidName29);
        pidName29.setTextColor(Color.RED);
        pidName2A = (TextView)view.findViewById(R.id.pidName2A);
        pidName2A.setTextColor(Color.RED);
        pidName2B = (TextView)view.findViewById(R.id.pidName2B);
        pidName2B.setTextColor(Color.RED);
        pidName2C = (TextView)view.findViewById(R.id.pidName2C);
        pidName2C.setTextColor(Color.RED);
        pidName2D = (TextView)view.findViewById(R.id.pidName2D);
        pidName2D.setTextColor(Color.RED);
        pidName2E = (TextView)view.findViewById(R.id.pidName2E);
        pidName2E.setTextColor(Color.RED);
        pidName2F = (TextView)view.findViewById(R.id.pidName2F);
        pidName2F.setTextColor(Color.RED);
        pidName30 = (TextView)view.findViewById(R.id.pidName30);
        pidName30.setTextColor(Color.RED);
        pidName31 = (TextView)view.findViewById(R.id.pidName31);
        pidName31.setTextColor(Color.RED);
        pidName32 = (TextView)view.findViewById(R.id.pidName32);
        pidName32.setTextColor(Color.RED);
        pidName33 = (TextView)view.findViewById(R.id.pidName33);
        pidName33.setTextColor(Color.RED);
        pidName34 = (TextView)view.findViewById(R.id.pidName34);
        pidName34.setTextColor(Color.RED);
        pidName35 = (TextView)view.findViewById(R.id.pidName35);
        pidName35.setTextColor(Color.RED);
        pidName36 = (TextView)view.findViewById(R.id.pidName36);
        pidName36.setTextColor(Color.RED);
        pidName37 = (TextView)view.findViewById(R.id.pidName37);
        pidName37.setTextColor(Color.RED);
        pidName38 = (TextView)view.findViewById(R.id.pidName38);
        pidName38.setTextColor(Color.RED);
        pidName39 = (TextView)view.findViewById(R.id.pidName39);
        pidName39.setTextColor(Color.RED);
        pidName3A = (TextView)view.findViewById(R.id.pidName3A);
        pidName3A.setTextColor(Color.RED);
        pidName3B = (TextView)view.findViewById(R.id.pidName3B);
        pidName3B.setTextColor(Color.RED);
        pidName3C = (TextView)view.findViewById(R.id.pidName3C);
        pidName3C.setTextColor(Color.RED);
        pidName3D = (TextView)view.findViewById(R.id.pidName3D);
        pidName3D.setTextColor(Color.RED);
        pidName3E = (TextView)view.findViewById(R.id.pidName3E);
        pidName3E.setTextColor(Color.RED);
        pidName3F = (TextView)view.findViewById(R.id.pidName3F);
        pidName3F.setTextColor(Color.RED);

    }

    private void nameHandler(){
        pidTestView2_nameHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 33:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName20.setTextColor(Color.GREEN);
                        }

                        break;
                    case 34:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName21.setTextColor(Color.GREEN);
                        }

                        break;
                    case 35:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName22.setTextColor(Color.GREEN);
                        }

                        break;
                    case 36:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName23.setTextColor(Color.GREEN);
                        }

                        break;
                    case 37:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName24.setTextColor(Color.GREEN);
                        }

                        break;
                    case 38:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName25.setTextColor(Color.GREEN);
                        }

                        break;
                    case 39:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName26.setTextColor(Color.GREEN);
                        }

                        break;
                    case 40:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName27.setTextColor(Color.GREEN);
                        }

                        break;
                    case 41:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName28.setTextColor(Color.GREEN);
                        }

                        break;
                    case 42:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName29.setTextColor(Color.GREEN);
                        }

                        break;
                    case 43:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 44:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 45:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 46:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 47:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 48:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName2F.setTextColor(Color.GREEN);
                        }

                        break;
                    case 49:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName30.setTextColor(Color.GREEN);
                        }

                        break;
                    case 50:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName31.setTextColor(Color.GREEN);
                        }

                        break;
                    case 51:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName32.setTextColor(Color.GREEN);
                        }

                        break;
                    case 52:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName33.setTextColor(Color.GREEN);
                        }

                        break;
                    case 53:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName34.setTextColor(Color.GREEN);
                        }

                        break;
                    case 54:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName35.setTextColor(Color.GREEN);
                        }

                        break;
                    case 55:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName36.setTextColor(Color.GREEN);
                        }

                        break;
                    case 56:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName37.setTextColor(Color.GREEN);
                        }

                        break;
                    case 57:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName38.setTextColor(Color.GREEN);
                        }

                        break;
                    case 58:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName39.setTextColor(Color.GREEN);
                        }

                        break;
                    case 59:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 60:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 61:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 62:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 63:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 64:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName3F.setTextColor(Color.GREEN);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void dataHandler(){
        pidTestView2_dataHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 33: pidData20.setText(String.valueOf(msg.obj));
                        break;
                    case 34: pidData21.setText(String.valueOf(msg.obj));
                        break;
                    case 35: pidData22.setText(String.valueOf(msg.obj));
                        break;
                    case 36: pidData23.setText(String.valueOf(msg.obj));
                        break;
                    case 37: pidData24.setText(String.valueOf(msg.obj));
                        break;
                    case 38: pidData25.setText(String.valueOf(msg.obj));
                        break;
                    case 39: pidData26.setText(String.valueOf(msg.obj));
                        break;
                    case 40: pidData27.setText(String.valueOf(msg.obj));
                        break;
                    case 41: pidData28.setText(String.valueOf(msg.obj));
                        break;
                    case 42: pidData29.setText(String.valueOf(msg.obj));
                        break;
                    case 43: pidData2A.setText(String.valueOf(msg.obj));
                        break;
                    case 44: pidData2B.setText(String.valueOf(msg.obj));
                        break;
                    case 45: pidData2C.setText(String.valueOf(msg.obj));
                        break;
                    case 46: pidData2D.setText(String.valueOf(msg.obj));
                        break;
                    case 47: pidData2E.setText(String.valueOf(msg.obj));
                        break;
                    case 48: pidData2F.setText(String.valueOf(msg.obj));
                        break;
                    case 49: pidData30.setText(String.valueOf(msg.obj));
                        break;
                    case 50: pidData31.setText(String.valueOf(msg.obj));
                        break;
                    case 51: pidData32.setText(String.valueOf(msg.obj));
                        break;
                    case 52: pidData33.setText(String.valueOf(msg.obj));
                        break;
                    case 53: pidData34.setText(String.valueOf(msg.obj));
                        break;
                    case 54: pidData35.setText(String.valueOf(msg.obj));
                        break;
                    case 55: pidData36.setText(String.valueOf(msg.obj));
                        break;
                    case 56: pidData37.setText(String.valueOf(msg.obj));
                        break;
                    case 57: pidData38.setText(String.valueOf(msg.obj));
                        break;
                    case 58: pidData39.setText(String.valueOf(msg.obj));
                        break;
                    case 59: pidData3A.setText(String.valueOf(msg.obj));
                        break;
                    case 60: pidData3B.setText(String.valueOf(msg.obj));
                        break;
                    case 61: pidData3C.setText(String.valueOf(msg.obj));
                        break;
                    case 62: pidData3D.setText(String.valueOf(msg.obj));
                        break;
                    case 63: pidData3E.setText(String.valueOf(msg.obj));
                        break;
                    case 64: pidData3F.setText(String.valueOf(msg.obj));
                        break;
                }
                return true;
            }
        });
    }
}
