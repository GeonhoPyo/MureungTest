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


public class PidTestView4 extends Fragment {

    TextView pidName60, pidName61, pidName62, pidName63, pidName64, pidName65, pidName66, pidName67, pidName68
            , pidName69, pidName6A, pidName6B, pidName6C, pidName6D, pidName6E, pidName6F, pidName70, pidName71
            , pidName72, pidName73, pidName74, pidName75, pidName76, pidName77, pidName78, pidName79, pidName7A
            , pidName7B, pidName7C, pidName7D, pidName7E, pidName7F
            , pidName80, pidName81, pidName82, pidName83
            , pidData60, pidData61, pidData62, pidData63, pidData64, pidData65, pidData66, pidData67, pidData68
            , pidData69, pidData6A, pidData6B, pidData6C, pidData6D, pidData6E, pidData6F, pidData70, pidData71
            , pidData72, pidData73, pidData74, pidData75, pidData76, pidData77, pidData78, pidData79, pidData7A
            , pidData7B, pidData7C, pidData7D, pidData7E, pidData7F
            , pidData80, pidData81, pidData82, pidData83;

    public static Handler pidTestView4_nameHandler = null;
    public static Handler pidTestView4_dataHandler = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pidtestview4,container,false);
        initView(view);

        nameHandler();
        dataHandler();



        return view;
    }

    private void initView(View view){
        pidData60 = (TextView)view.findViewById(R.id.pidData60);
        pidData61 = (TextView)view.findViewById(R.id.pidData61);
        pidData62 = (TextView)view.findViewById(R.id.pidData62);
        pidData63 = (TextView)view.findViewById(R.id.pidData63);
        pidData64 = (TextView)view.findViewById(R.id.pidData64);
        pidData65 = (TextView)view.findViewById(R.id.pidData65);
        pidData66 = (TextView)view.findViewById(R.id.pidData66);
        pidData67 = (TextView)view.findViewById(R.id.pidData67);
        pidData68 = (TextView)view.findViewById(R.id.pidData68);
        pidData69 = (TextView)view.findViewById(R.id.pidData69);
        pidData6A = (TextView)view.findViewById(R.id.pidData6A);
        pidData6B = (TextView)view.findViewById(R.id.pidData6B);
        pidData6C = (TextView)view.findViewById(R.id.pidData6C);
        pidData6D = (TextView)view.findViewById(R.id.pidData6D);
        pidData6E = (TextView)view.findViewById(R.id.pidData6E);
        pidData6F = (TextView)view.findViewById(R.id.pidData6F);
        pidData70 = (TextView)view.findViewById(R.id.pidData70);
        pidData71 = (TextView)view.findViewById(R.id.pidData71);
        pidData72 = (TextView)view.findViewById(R.id.pidData72);
        pidData73 = (TextView)view.findViewById(R.id.pidData73);
        pidData74 = (TextView)view.findViewById(R.id.pidData74);
        pidData75 = (TextView)view.findViewById(R.id.pidData75);
        pidData76 = (TextView)view.findViewById(R.id.pidData76);
        pidData77 = (TextView)view.findViewById(R.id.pidData77);
        pidData78 = (TextView)view.findViewById(R.id.pidData78);
        pidData79 = (TextView)view.findViewById(R.id.pidData79);
        pidData7A = (TextView)view.findViewById(R.id.pidData7A);
        pidData7B = (TextView)view.findViewById(R.id.pidData7B);
        pidData7C = (TextView)view.findViewById(R.id.pidData7C);
        pidData7D = (TextView)view.findViewById(R.id.pidData7D);
        pidData7E = (TextView)view.findViewById(R.id.pidData7E);
        pidData7F = (TextView)view.findViewById(R.id.pidData7F);
        pidData80 = (TextView)view.findViewById(R.id.pidData80);
        pidData81 = (TextView)view.findViewById(R.id.pidData81);
        pidData82 = (TextView)view.findViewById(R.id.pidData82);
        pidData83 = (TextView)view.findViewById(R.id.pidData83);


        pidName60 = (TextView)view.findViewById(R.id.pidName60);
        pidName60.setTextColor(Color.RED);
        pidName61 = (TextView)view.findViewById(R.id.pidName61);
        pidName61.setTextColor(Color.RED);
        pidName62 = (TextView)view.findViewById(R.id.pidName62);
        pidName62.setTextColor(Color.RED);
        pidName63 = (TextView)view.findViewById(R.id.pidName63);
        pidName63.setTextColor(Color.RED);
        pidName64 = (TextView)view.findViewById(R.id.pidName64);
        pidName64.setTextColor(Color.RED);
        pidName65 = (TextView)view.findViewById(R.id.pidName65);
        pidName65.setTextColor(Color.RED);
        pidName66 = (TextView)view.findViewById(R.id.pidName66);
        pidName66.setTextColor(Color.RED);
        pidName67 = (TextView)view.findViewById(R.id.pidName67);
        pidName67.setTextColor(Color.RED);
        pidName68 = (TextView)view.findViewById(R.id.pidName68);
        pidName68.setTextColor(Color.RED);
        pidName69 = (TextView)view.findViewById(R.id.pidName69);
        pidName69.setTextColor(Color.RED);
        pidName6A = (TextView)view.findViewById(R.id.pidName6A);
        pidName6A.setTextColor(Color.RED);
        pidName6B = (TextView)view.findViewById(R.id.pidName6B);
        pidName6B.setTextColor(Color.RED);
        pidName6C = (TextView)view.findViewById(R.id.pidName6C);
        pidName6C.setTextColor(Color.RED);
        pidName6D = (TextView)view.findViewById(R.id.pidName6D);
        pidName6D.setTextColor(Color.RED);
        pidName6E = (TextView)view.findViewById(R.id.pidName6E);
        pidName6E.setTextColor(Color.RED);
        pidName6F = (TextView)view.findViewById(R.id.pidName6F);
        pidName6F.setTextColor(Color.RED);
        pidName70 = (TextView)view.findViewById(R.id.pidName70);
        pidName70.setTextColor(Color.RED);
        pidName71 = (TextView)view.findViewById(R.id.pidName71);
        pidName71.setTextColor(Color.RED);
        pidName72 = (TextView)view.findViewById(R.id.pidName72);
        pidName72.setTextColor(Color.RED);
        pidName73 = (TextView)view.findViewById(R.id.pidName73);
        pidName73.setTextColor(Color.RED);
        pidName74 = (TextView)view.findViewById(R.id.pidName74);
        pidName74.setTextColor(Color.RED);
        pidName75 = (TextView)view.findViewById(R.id.pidName75);
        pidName75.setTextColor(Color.RED);
        pidName76 = (TextView)view.findViewById(R.id.pidName76);
        pidName76.setTextColor(Color.RED);
        pidName77 = (TextView)view.findViewById(R.id.pidName77);
        pidName77.setTextColor(Color.RED);
        pidName78 = (TextView)view.findViewById(R.id.pidName78);
        pidName78.setTextColor(Color.RED);
        pidName79 = (TextView)view.findViewById(R.id.pidName79);
        pidName79.setTextColor(Color.RED);
        pidName7A = (TextView)view.findViewById(R.id.pidName7A);
        pidName7A.setTextColor(Color.RED);
        pidName7B = (TextView)view.findViewById(R.id.pidName7B);
        pidName7B.setTextColor(Color.RED);
        pidName7C = (TextView)view.findViewById(R.id.pidName7C);
        pidName7C.setTextColor(Color.RED);
        pidName7D = (TextView)view.findViewById(R.id.pidName7D);
        pidName7D.setTextColor(Color.RED);
        pidName7E = (TextView)view.findViewById(R.id.pidName7E);
        pidName7E.setTextColor(Color.RED);
        pidName7F = (TextView)view.findViewById(R.id.pidName7F);
        pidName7F.setTextColor(Color.RED);
        pidName80 = (TextView)view.findViewById(R.id.pidName80);
        pidName80.setTextColor(Color.RED);
        pidName81 = (TextView)view.findViewById(R.id.pidName81);
        pidName81.setTextColor(Color.RED);
        pidName82 = (TextView)view.findViewById(R.id.pidName82);
        pidName82.setTextColor(Color.RED);
        pidName83 = (TextView)view.findViewById(R.id.pidName83);
        pidName83.setTextColor(Color.RED);
    }

    private void nameHandler(){
        pidTestView4_nameHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 97:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName60.setTextColor(Color.GREEN);
                        }

                        break;
                    case 98:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName61.setTextColor(Color.GREEN);
                        }

                        break;
                    case 99:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName62.setTextColor(Color.GREEN);
                        }

                        break;
                    case 100:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName63.setTextColor(Color.GREEN);
                        }

                        break;
                    case 101:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName64.setTextColor(Color.GREEN);
                        }

                        break;
                    case 102:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName65.setTextColor(Color.GREEN);
                        }

                        break;
                    case 103:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName66.setTextColor(Color.GREEN);
                        }

                        break;
                    case 104:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName67.setTextColor(Color.GREEN);
                        }

                        break;
                    case 105:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName68.setTextColor(Color.GREEN);
                        }

                        break;
                    case 106:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName69.setTextColor(Color.GREEN);
                        }

                        break;
                    case 107:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 108:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 109:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 110:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 111:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 112:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName6F.setTextColor(Color.GREEN);
                        }

                        break;
                    case 113:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName70.setTextColor(Color.GREEN);
                        }

                        break;
                    case 114:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName71.setTextColor(Color.GREEN);
                        }

                        break;
                    case 115:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName72.setTextColor(Color.GREEN);
                        }

                        break;
                    case 116:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName73.setTextColor(Color.GREEN);
                        }

                        break;
                    case 117:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName74.setTextColor(Color.GREEN);
                        }

                        break;
                    case 118:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName75.setTextColor(Color.GREEN);
                        }

                        break;
                    case 119:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName76.setTextColor(Color.GREEN);
                        }

                        break;
                    case 120:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName77.setTextColor(Color.GREEN);
                        }

                        break;
                    case 121:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName78.setTextColor(Color.GREEN);
                        }

                        break;
                    case 122:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName79.setTextColor(Color.GREEN);
                        }

                        break;
                    case 123:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 124:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 125:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 126:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 127:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 128:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName7F.setTextColor(Color.GREEN);
                        }

                        break;
                    case 129:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName80.setTextColor(Color.GREEN);
                        }

                        break;
                    case 130:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName81.setTextColor(Color.GREEN);
                        }

                        break;
                    case 131:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName82.setTextColor(Color.GREEN);
                        }

                        break;
                    case 132:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName83.setTextColor(Color.GREEN);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void dataHandler(){
        pidTestView4_dataHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 97: pidData60.setText(String.valueOf(msg.obj));
                        break;
                    case 98: pidData61.setText(String.valueOf(msg.obj));
                        break;
                    case 99: pidData62.setText(String.valueOf(msg.obj));
                        break;
                    case 100: pidData63.setText(String.valueOf(msg.obj));
                        break;
                    case 101: pidData64.setText(String.valueOf(msg.obj));
                        break;
                    case 102: pidData65.setText(String.valueOf(msg.obj));
                        break;
                    case 103: pidData66.setText(String.valueOf(msg.obj));
                        break;
                    case 104: pidData67.setText(String.valueOf(msg.obj));
                        break;
                    case 105: pidData68.setText(String.valueOf(msg.obj));
                        break;
                    case 106: pidData69.setText(String.valueOf(msg.obj));
                        break;
                    case 107: pidData6A.setText(String.valueOf(msg.obj));
                        break;
                    case 108: pidData6B.setText(String.valueOf(msg.obj));
                        break;
                    case 109: pidData6C.setText(String.valueOf(msg.obj));
                        break;
                    case 110: pidData6D.setText(String.valueOf(msg.obj));
                        break;
                    case 111: pidData6E.setText(String.valueOf(msg.obj));
                        break;
                    case 112: pidData6F.setText(String.valueOf(msg.obj));
                        break;
                    case 113: pidData70.setText(String.valueOf(msg.obj));
                        break;
                    case 114: pidData71.setText(String.valueOf(msg.obj));
                        break;
                    case 115: pidData72.setText(String.valueOf(msg.obj));
                        break;
                    case 116: pidData73.setText(String.valueOf(msg.obj));
                        break;
                    case 117: pidData74.setText(String.valueOf(msg.obj));
                        break;
                    case 118: pidData75.setText(String.valueOf(msg.obj));
                        break;
                    case 119: pidData76.setText(String.valueOf(msg.obj));
                        break;
                    case 120: pidData77.setText(String.valueOf(msg.obj));
                        break;
                    case 121: pidData78.setText(String.valueOf(msg.obj));
                        break;
                    case 122: pidData79.setText(String.valueOf(msg.obj));
                        break;
                    case 123: pidData7A.setText(String.valueOf(msg.obj));
                        break;
                    case 124: pidData7B.setText(String.valueOf(msg.obj));
                        break;
                    case 125: pidData7C.setText(String.valueOf(msg.obj));
                        break;
                    case 126: pidData7D.setText(String.valueOf(msg.obj));
                        break;
                    case 127: pidData7E.setText(String.valueOf(msg.obj));
                        break;
                    case 128: pidData7F.setText(String.valueOf(msg.obj));
                        break;
                    case 129: pidData80.setText(String.valueOf(msg.obj));
                        break;
                    case 130: pidData81.setText(String.valueOf(msg.obj));
                        break;
                    case 131: pidData82.setText(String.valueOf(msg.obj));
                        break;
                    case 132: pidData83.setText(String.valueOf(msg.obj));
                        break;
                }
                return true;
            }
        });
    }
}
