package mureung.mureungtest.View.Wifi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

public class WifiTestFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wifitest,container,false);

        PageStr.setPageStrData(PageStr.WifiTest);
        return view;
    }
}
