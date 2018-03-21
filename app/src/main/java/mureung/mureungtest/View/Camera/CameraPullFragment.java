package mureung.mureungtest.View.Camera;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-03-20.
 */

public class CameraPullFragment extends Fragment {
    ImageView cameraPullImage;
    Handler cameraHandler ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camerapull,container,false);

        cameraPullImage = (ImageView) view.findViewById(R.id.cameraPullImage);
        /*if(CameraPushFragment.testbitmap!=null){
            cameraPullImage.setImageBitmap(CameraPushFragment.testbitmap);
        }*/
        cameraHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1 :
                        Log.e("test","test 11111111");
                        cameraPullImage.setImageBitmap((Bitmap)msg.obj);
                        break;
                }
                return true;
            }
        });

        PageStr.setPageStrData(PageStr.CameraPullTest);

        return view;
    }

    public void setCameraImage(Bitmap bitmap){
        if(bitmap != null){
            Log.e("test","bitmap != null");
            if(cameraHandler!=null){
                Log.e("test","cameraHandler!=null");
                cameraHandler.obtainMessage(1,bitmap).sendToTarget();
            }

        }

    }

}
