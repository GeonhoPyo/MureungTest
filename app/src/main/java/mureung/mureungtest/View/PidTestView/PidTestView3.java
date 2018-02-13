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


public class PidTestView3 extends Fragment {

    TextView pidName40, pidName41, pidName42, pidName43, pidName44, pidName45, pidName46, pidName47, pidName48
            , pidName49, pidName4A, pidName4B, pidName4C, pidName4D, pidName4E, pidName4F, pidName50, pidName51
            , pidName52, pidName53, pidName54, pidName55, pidName56, pidName57, pidName58, pidName59, pidName5A
            , pidName5B, pidName5C, pidName5D, pidName5E, pidName5F
            , pidData40, pidData41, pidData42, pidData43, pidData44, pidData45, pidData46, pidData47, pidData48
            , pidData49, pidData4A, pidData4B, pidData4C, pidData4D, pidData4E, pidData4F, pidData50, pidData51
            , pidData52, pidData53, pidData54, pidData55, pidData56, pidData57, pidData58, pidData59, pidData5A
            , pidData5B, pidData5C, pidData5D, pidData5E, pidData5F;

    public static Handler pidTestView3_nameHandler = null;
    public static Handler pidTestView3_dataHandler = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pidtestview3,container,false);
        initView(view);

        nameHandler();
        dataHandler();



        return view;
    }

    private void initView(View view){
        pidData40 = (TextView)view.findViewById(R.id.pidData40);
        pidData41 = (TextView)view.findViewById(R.id.pidData41);
        pidData42 = (TextView)view.findViewById(R.id.pidData42);
        pidData43 = (TextView)view.findViewById(R.id.pidData43);
        pidData44 = (TextView)view.findViewById(R.id.pidData44);
        pidData45 = (TextView)view.findViewById(R.id.pidData45);
        pidData46 = (TextView)view.findViewById(R.id.pidData46);
        pidData47 = (TextView)view.findViewById(R.id.pidData47);
        pidData48 = (TextView)view.findViewById(R.id.pidData48);
        pidData49 = (TextView)view.findViewById(R.id.pidData49);
        pidData4A = (TextView)view.findViewById(R.id.pidData4A);
        pidData4B = (TextView)view.findViewById(R.id.pidData4B);
        pidData4C = (TextView)view.findViewById(R.id.pidData4C);
        pidData4D = (TextView)view.findViewById(R.id.pidData4D);
        pidData4E = (TextView)view.findViewById(R.id.pidData4E);
        pidData4F = (TextView)view.findViewById(R.id.pidData4F);
        pidData50 = (TextView)view.findViewById(R.id.pidData50);
        pidData51 = (TextView)view.findViewById(R.id.pidData51);
        pidData52 = (TextView)view.findViewById(R.id.pidData52);
        pidData53 = (TextView)view.findViewById(R.id.pidData53);
        pidData54 = (TextView)view.findViewById(R.id.pidData54);
        pidData55 = (TextView)view.findViewById(R.id.pidData55);
        pidData56 = (TextView)view.findViewById(R.id.pidData56);
        pidData57 = (TextView)view.findViewById(R.id.pidData57);
        pidData58 = (TextView)view.findViewById(R.id.pidData58);
        pidData59 = (TextView)view.findViewById(R.id.pidData59);
        pidData5A = (TextView)view.findViewById(R.id.pidData5A);
        pidData5B = (TextView)view.findViewById(R.id.pidData5B);
        pidData5C = (TextView)view.findViewById(R.id.pidData5C);
        pidData5D = (TextView)view.findViewById(R.id.pidData5D);
        pidData5E = (TextView)view.findViewById(R.id.pidData5E);
        pidData5F = (TextView)view.findViewById(R.id.pidData5F);



        pidName40 = (TextView)view.findViewById(R.id.pidName40);
        pidName40.setTextColor(Color.RED);
        pidName41 = (TextView)view.findViewById(R.id.pidName41);
        pidName41.setTextColor(Color.RED);
        pidName42 = (TextView)view.findViewById(R.id.pidName42);
        pidName42.setTextColor(Color.RED);
        pidName43 = (TextView)view.findViewById(R.id.pidName43);
        pidName43.setTextColor(Color.RED);
        pidName44 = (TextView)view.findViewById(R.id.pidName44);
        pidName44.setTextColor(Color.RED);
        pidName45 = (TextView)view.findViewById(R.id.pidName45);
        pidName45.setTextColor(Color.RED);
        pidName46 = (TextView)view.findViewById(R.id.pidName46);
        pidName46.setTextColor(Color.RED);
        pidName47 = (TextView)view.findViewById(R.id.pidName47);
        pidName47.setTextColor(Color.RED);
        pidName48 = (TextView)view.findViewById(R.id.pidName48);
        pidName48.setTextColor(Color.RED);
        pidName49 = (TextView)view.findViewById(R.id.pidName49);
        pidName49.setTextColor(Color.RED);
        pidName4A = (TextView)view.findViewById(R.id.pidName4A);
        pidName4A.setTextColor(Color.RED);
        pidName4B = (TextView)view.findViewById(R.id.pidName4B);
        pidName4B.setTextColor(Color.RED);
        pidName4C = (TextView)view.findViewById(R.id.pidName4C);
        pidName4C.setTextColor(Color.RED);
        pidName4D = (TextView)view.findViewById(R.id.pidName4D);
        pidName4D.setTextColor(Color.RED);
        pidName4E = (TextView)view.findViewById(R.id.pidName4E);
        pidName4E.setTextColor(Color.RED);
        pidName4F = (TextView)view.findViewById(R.id.pidName4F);
        pidName4F.setTextColor(Color.RED);
        pidName50 = (TextView)view.findViewById(R.id.pidName50);
        pidName50.setTextColor(Color.RED);
        pidName51 = (TextView)view.findViewById(R.id.pidName51);
        pidName51.setTextColor(Color.RED);
        pidName52 = (TextView)view.findViewById(R.id.pidName52);
        pidName52.setTextColor(Color.RED);
        pidName53 = (TextView)view.findViewById(R.id.pidName53);
        pidName53.setTextColor(Color.RED);
        pidName54 = (TextView)view.findViewById(R.id.pidName54);
        pidName54.setTextColor(Color.RED);
        pidName55 = (TextView)view.findViewById(R.id.pidName55);
        pidName55.setTextColor(Color.RED);
        pidName56 = (TextView)view.findViewById(R.id.pidName56);
        pidName56.setTextColor(Color.RED);
        pidName57 = (TextView)view.findViewById(R.id.pidName57);
        pidName57.setTextColor(Color.RED);
        pidName58 = (TextView)view.findViewById(R.id.pidName58);
        pidName58.setTextColor(Color.RED);
        pidName59 = (TextView)view.findViewById(R.id.pidName59);
        pidName59.setTextColor(Color.RED);
        pidName5A = (TextView)view.findViewById(R.id.pidName5A);
        pidName5A.setTextColor(Color.RED);
        pidName5B = (TextView)view.findViewById(R.id.pidName5B);
        pidName5B.setTextColor(Color.RED);
        pidName5C = (TextView)view.findViewById(R.id.pidName5C);
        pidName5C.setTextColor(Color.RED);
        pidName5D = (TextView)view.findViewById(R.id.pidName5D);
        pidName5D.setTextColor(Color.RED);
        pidName5E = (TextView)view.findViewById(R.id.pidName5E);
        pidName5E.setTextColor(Color.RED);
        pidName5F = (TextView)view.findViewById(R.id.pidName5F);
        pidName5F.setTextColor(Color.RED);

    }

    private void nameHandler(){
        pidTestView3_nameHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 65:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName40.setTextColor(Color.GREEN);
                        }

                        break;
                    case 66:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName41.setTextColor(Color.GREEN);
                        }

                        break;
                    case 67:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName42.setTextColor(Color.GREEN);
                        }

                        break;
                    case 68:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName43.setTextColor(Color.GREEN);
                        }

                        break;
                    case 69:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName44.setTextColor(Color.GREEN);
                        }

                        break;
                    case 70:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName45.setTextColor(Color.GREEN);
                        }

                        break;
                    case 71:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName46.setTextColor(Color.GREEN);
                        }

                        break;
                    case 72:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName47.setTextColor(Color.GREEN);
                        }

                        break;
                    case 73:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName48.setTextColor(Color.GREEN);
                        }

                        break;
                    case 74:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName49.setTextColor(Color.GREEN);
                        }

                        break;
                    case 75:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 76:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 77:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 78:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 79:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 80:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName4F.setTextColor(Color.GREEN);
                        }

                        break;
                    case 81:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName50.setTextColor(Color.GREEN);
                        }

                        break;
                    case 82:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName51.setTextColor(Color.GREEN);
                        }

                        break;
                    case 83:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName52.setTextColor(Color.GREEN);
                        }

                        break;
                    case 84:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName53.setTextColor(Color.GREEN);
                        }

                        break;
                    case 85:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName54.setTextColor(Color.GREEN);
                        }

                        break;
                    case 86:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName55.setTextColor(Color.GREEN);
                        }

                        break;
                    case 87:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName56.setTextColor(Color.GREEN);
                        }

                        break;
                    case 88:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName57.setTextColor(Color.GREEN);
                        }

                        break;
                    case 89:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName58.setTextColor(Color.GREEN);
                        }

                        break;
                    case 90:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName59.setTextColor(Color.GREEN);
                        }

                        break;
                    case 91:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5A.setTextColor(Color.GREEN);
                        }

                        break;
                    case 92:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5B.setTextColor(Color.GREEN);
                        }

                        break;
                    case 93:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5C.setTextColor(Color.GREEN);
                        }

                        break;
                    case 94:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5D.setTextColor(Color.GREEN);
                        }

                        break;
                    case 95:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5E.setTextColor(Color.GREEN);
                        }

                        break;
                    case 96:
                        if(Boolean.parseBoolean(String.valueOf(msg.obj))){
                            pidName5F.setTextColor(Color.GREEN);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void dataHandler(){
        pidTestView3_dataHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 65: pidData40.setText(String.valueOf(msg.obj));
                        break;
                    case 66: pidData41.setText(String.valueOf(msg.obj));
                        break;
                    case 67: pidData42.setText(String.valueOf(msg.obj));
                        break;
                    case 68: pidData43.setText(String.valueOf(msg.obj));
                        break;
                    case 69: pidData44.setText(String.valueOf(msg.obj));
                        break;
                    case 70: pidData45.setText(String.valueOf(msg.obj));
                        break;
                    case 71: pidData46.setText(String.valueOf(msg.obj));
                        break;
                    case 72: pidData47.setText(String.valueOf(msg.obj));
                        break;
                    case 73: pidData48.setText(String.valueOf(msg.obj));
                        break;
                    case 74: pidData49.setText(String.valueOf(msg.obj));
                        break;
                    case 75: pidData4A.setText(String.valueOf(msg.obj));
                        break;
                    case 76: pidData4B.setText(String.valueOf(msg.obj));
                        break;
                    case 77: pidData4C.setText(String.valueOf(msg.obj));
                        break;
                    case 78: pidData4D.setText(String.valueOf(msg.obj));
                        break;
                    case 79: pidData4E.setText(String.valueOf(msg.obj));
                        break;
                    case 80: pidData4F.setText(String.valueOf(msg.obj));
                        break;
                    case 81: pidData50.setText(String.valueOf(msg.obj));
                        break;
                    case 82: pidData51.setText(String.valueOf(msg.obj));
                        break;
                    case 83: pidData52.setText(String.valueOf(msg.obj));
                        break;
                    case 84: pidData53.setText(String.valueOf(msg.obj));
                        break;
                    case 85: pidData54.setText(String.valueOf(msg.obj));
                        break;
                    case 86: pidData55.setText(String.valueOf(msg.obj));
                        break;
                    case 87: pidData56.setText(String.valueOf(msg.obj));
                        break;
                    case 88: pidData57.setText(String.valueOf(msg.obj));
                        break;
                    case 89: pidData58.setText(String.valueOf(msg.obj));
                        break;
                    case 90: pidData59.setText(String.valueOf(msg.obj));
                        break;
                    case 91: pidData5A.setText(String.valueOf(msg.obj));
                        break;
                    case 92: pidData5B.setText(String.valueOf(msg.obj));
                        break;
                    case 93: pidData5C.setText(String.valueOf(msg.obj));
                        break;
                    case 94: pidData5D.setText(String.valueOf(msg.obj));
                        break;
                    case 95: pidData5E.setText(String.valueOf(msg.obj));
                        break;
                    case 96: pidData5F.setText(String.valueOf(msg.obj));
                        break;
                }
                return true;
            }
        });
    }
}
