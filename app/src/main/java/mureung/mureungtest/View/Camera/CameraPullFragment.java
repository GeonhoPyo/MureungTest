package mureung.mureungtest.View.Camera;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
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


        Log.e("test","Bitmap Init");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.facetest,options);
        Log.e("test","Bitmap Size  getWidth  : " + bitmap.getWidth() + " , getHeight : " + bitmap.getHeight());
        Log.e("test","Bitmap get");
        /*Paint rectPaint1 = new Paint();
        rectPaint1. setStrokeWidth(5);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);*/
        Paint rectPaint2 = new Paint();
        rectPaint2. setStrokeWidth(5);
        rectPaint2.setColor(Color.GREEN);
        rectPaint2.setStyle(Paint.Style.STROKE);

        Bitmap testBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.RGB_565);
        Canvas testCanvas = new Canvas(testBitmap);
        testCanvas.drawBitmap(bitmap,0,0,null);

        try {
            Log.e("test","Bitmap faceDetector Start");
            FaceDetector faceDetector = new FaceDetector.Builder(getContext())
                    .setTrackingEnabled(false)
                    .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                    .build();


            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<Face> faces = faceDetector.detect(frame);
            Log.e("test","Bitmap faceDetector detect frame");
            for(int i = 0 ; i < faces.size(); ++i){
                Face face = faces.valueAt(i);
                /*float x1 = face.getPosition().x;
                float y1 = face.getPosition().y;
                float x2 = x1 + face.getWidth();
                float y2 = y1 + face.getHeight();
                testCanvas.drawRoundRect(new RectF(x1,y1,x2,y2), 2, 2, rectPaint1);*/

                for(Landmark landmark : face.getLandmarks()){
                    int cx = (int) (landmark.getPosition().x);
                    int cy = (int) (landmark.getPosition().y);
                    testCanvas.drawCircle(cx, cy, 10, rectPaint2);
                    Log.e("CameraPullFragment","cx : " + cx + " , cy : " + cy);

                }

            }

            cameraPullImage.setImageDrawable(new BitmapDrawable(getResources(),testBitmap));
            faceDetector.release();
            Log.e("test","Bitmap faceDetector Finish");
        }catch (Exception e){
            e.printStackTrace();
        }
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
