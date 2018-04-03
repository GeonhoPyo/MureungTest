package mureung.mureungtest.View.Camera;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-03-20.
 */

public class CameraPullFragment extends Fragment {
    ImageView cameraPullImage;
    static Handler cameraHandler ;

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

                        Bitmap bitmap = (Bitmap)msg.obj;
                        //Log.e("test","test bitmap : " + bitmap);
                        try {

                            FaceDetector faceDetector = new FaceDetector.Builder(getContext())
                                    .setTrackingEnabled(false)
                                    .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                                    .build();

                            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                            SparseArray<Face> faces = faceDetector.detect(frame);
                            for(int i = 0 ; i < faces.size(); ++i){
                                Face face = faces.valueAt(i);
                                for(Landmark landmark : face.getLandmarks()){
                                    int cx = (int) (landmark.getPosition().x);
                                    int cy = (int) (landmark.getPosition().y);
                                    Log.e("CameraPullFragment","cx");

                                }

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        cameraPullImage.setImageBitmap(bitmap);
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
            //Log.e("test","bitmap != null");
            if(cameraHandler!=null){
                //Log.e("test","cameraHandler!=null");
                cameraHandler.obtainMessage(1,bitmap).sendToTarget();
            }

        }

    }

}
