package mureung.mureungtest.View.PidTestView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mureung.mureungtest.Comunication.Bluetooth_Protocol;
import mureung.mureungtest.MainView;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;
import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.MakeData;

/**
 * Created by user on 2018-01-29.
 */

public class PidTestMainView extends Fragment {

    public static ViewPager pidTestViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pidtest,container,false);
        PageStr.setPageStrData(PageStr.PidTestView);


        if(MainView.PID != null){
            new Bluetooth_Protocol().checkPage(MainView.PID);
            Log.e("test","MainView.PID : " + MainView.PID);
        }




        pidTestViewPager = ( ViewPager) view.findViewById(R.id.pidTestViewPager);
        pidTestViewPager.setAdapter(new PidTest_ViewPagerAdapter(getActivity().getSupportFragmentManager()));
        pidTestViewPager.setCurrentItem(0);
        pidTestViewPager.setOffscreenPageLimit(4);

        MainView.PidTestStart_FLAG = false;



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(MakeData.fileName!=null){
            new ErrorLogManager().saveErrorLog(MakeData.fileName,"----------------------------------------//");
        }
    }
}
