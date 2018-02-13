package mureung.mureungtest.View.PidTestView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by user on 2018-01-30.
 */

public class PidTest_ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final int pidtestview1 = 0;
    private final int pidtestview2 = 1;
    private final int pidtestview3 = 2;
    private final int pidtestview4 = 3;

    public PidTest_ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case pidtestview1 :
                return new PidTestView1();
            case pidtestview2 :
                return new PidTestView2();
            case pidtestview3 :
                return new PidTestView3();
            case pidtestview4 :
                return new PidTestView4();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
