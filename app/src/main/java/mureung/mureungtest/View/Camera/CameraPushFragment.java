package mureung.mureungtest.View.Camera;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.media.FaceDetector;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import mureung.mureungtest.Comunication.Bluetooth_Camera_Protocol;
import mureung.mureungtest.MainActivity;
import mureung.mureungtest.PageStr;
import mureung.mureungtest.R;

/**
 * Created by user on 2018-03-19.
 */

public class CameraPushFragment extends Fragment implements View.OnClickListener,SurfaceHolder.Callback {

    Button cameraPushBtn , cameraPullBtn;
    TextView stateText;
    ImageView cameraImageView;

    public static Handler cameraTestHandler ;
    public static final int cameraStateText = 1;
    public static final int cameraSetIamgeView = 2;
    private static Bitmap pushBitmap ;
    Camera mCamera ;
    private SurfaceView mCameraView;
    private SurfaceHolder mCameraHolder;
    private MediaRecorder mediaRecorder;

    public static Bitmap testbitmap ;
    public static boolean testFlag = false ;

    int degrees = 0;

    private int count= 0;

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camerapush,container,false);

        cameraPushBtn = (Button)view.findViewById(R.id.cameraPushBtn);
        cameraPushBtn.setOnClickListener(this);
        cameraPullBtn = (Button)view.findViewById(R.id.cameraPullBtn);
        cameraPullBtn.setOnClickListener(this);
        stateText = ( TextView) view.findViewById(R.id.stateText);
        //cameraImageView = ( ImageView)view.findViewById(R.id.cameraImageView);



        cameraTestHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case cameraStateText :
                        stateText.setText(String.valueOf(msg.obj));
                        break;
                    case cameraSetIamgeView :
                        cameraImageView.setImageBitmap((Bitmap) msg.obj);
                        break;
                }

                return true;
            }
        });




        mCameraView = (SurfaceView)view.findViewById(R.id.cameraSurfaceView);

        PageStr.setPageStrData(PageStr.CameraPushTest);
        return view;
    }

    @Override
    public void onPause() {
        try {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
    }

    private void dispatchTakePictureIntent(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(MainActivity.mainContext.getPackageManager())!= null){
            getActivity().startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);
        }
    }

    public void setCameraStateText (String msg){
        if(cameraTestHandler != null){
            cameraTestHandler.obtainMessage(cameraStateText,msg).sendToTarget();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cameraPushBtn :
                //new CameraActivity().dispatchTakePictureIntent();
                //init();
                /*dispatchTakePictureIntent();*/
                break;

            case R.id.cameraPullBtn :
                //여기서 블루투스 리스트 다얄로그 만들어서 보여준뒤 선택하면 연결해야함
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            if(mCamera==null){
                mCamera.setPreviewDisplay(holder);

                mCamera.startPreview();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mCameraHolder.getSurface() == null){
            return;
        }

        try {
            mCamera.stopPreview();
        }catch (Exception e){
            e.printStackTrace();
        }

        Camera.Parameters parameters = mCamera.getParameters();
        List<String> focusModes = parameters.getSupportedFocusModes();
        if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        mCamera.setParameters(parameters);

        try {
            mCamera.setPreviewDisplay(mCameraHolder);
            mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    Log.e("CameraPush","onPreviewFrame");
                    Camera.Parameters parameters = mCamera.getParameters();
                    int width = parameters.getPreviewSize().width;
                    int height = parameters.getPreviewSize().height;

                    int format = parameters.getPreviewFormat();


                    /*if(!testFlag){

                        testFlag = true;
                        testbitmap =bitmap;

                    }*/


                    ByteArrayOutputStream outstr = new ByteArrayOutputStream();
                    Rect rect = new Rect(0,0,width,height);
                    YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21,width,height,null);
                    yuvImage.compressToJpeg(rect,100,outstr);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(outstr.toByteArray(),0,outstr.size(),options);



                    if(Bluetooth_Camera_Protocol.PushCameraDataReady_FLAG){
                        CameraData cameraData = new CameraData(data,width,height,format);
                        Log.e("CameraPushFragment","onPreviewFrame data : " + data + " , width : " + width + " , height : " + height + " , format : " + format );


                        try {

                            if(count > 5){
                                /*ByteArrayOutputStream outstr = new ByteArrayOutputStream();
                                Rect rect = new Rect(0,0,width,height);
                                YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21,width,height,null);
                                yuvImage.compressToJpeg(rect,80,outstr);
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inSampleSize = 8;
                                final Bitmap bitmap = BitmapFactory.decodeByteArray(outstr.toByteArray(),0,outstr.size(),options);*/

                                final byte[] bitmapByte = bitmapToByteArray(bitmap);
                                Log.e("test","bitmapByte.lenght : " + bitmapByte.length);
                                String length = "<length>"+String.valueOf(bitmapByte.length)+"</length>";
                                new Bluetooth_Camera_Protocol().write(length.getBytes());
                                new Bluetooth_Camera_Protocol().write("\n".getBytes());


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        new Bluetooth_Camera_Protocol().write(bitmapByte);
                                        new Bluetooth_Camera_Protocol().write("\n".getBytes());
                                    }
                                },5);
                                /*new Bluetooth_Camera_Protocol().write(bitmapToByteArray(bitmap));
                                new Bluetooth_Camera_Protocol().write("\r".getBytes());*/

                                count = 0;
                            }
                            count ++;

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }





                }
            });
            mCamera.startPreview();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("CameraPushFragment","surfaceDestroyed");
        try {
            if(mCamera!=null){
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init(){

        Log.e("CameraPushFragment","init");
        initCamera();
        mCamera.setDisplayOrientation(90);

        mCameraHolder = mCameraView.getHolder();
        mCameraHolder.addCallback(this);
        mCameraHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    public void initCamera(){
        Log.e("CameraPushFragment","initCamera");
        try {
            if (mCamera != null){
                mCamera.release();
                mCamera = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        // 카메라 객체 획득(카메라 퍼미션 필요)
        try {

        }catch (Exception e){
            e.printStackTrace();
        }


        //전방 카메라 실행
        //mCamera = Camera.open(findFrontSideCamera());
        //후방 카메라 실행
        mCamera = Camera.open();


        // 카메라 화면 터치시 자동 초점 설정
        mCameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                    }
                };
                try {
                    mCamera.autoFocus(autoFocusCallback);
                }catch (Exception e){

                    e.printStackTrace();
                    if (mCamera != null){
                        try {
                            mCamera.release();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                    }
                }
            }
        });




        setCameraRotation();
    }

    private int findFrontSideCamera(){
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for(int i = 0 ; i < numberOfCameras ; i++){
            Camera.CameraInfo cmInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i,cmInfo);
            if(cmInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }


    void setCameraRotation(){
        Log.e("CameraPushFragment","setCameraRotation");
        // 카메라 정보 객체 반환
        Camera.CameraInfo info = new Camera.CameraInfo();

        // 화면 회전 정보 반환
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();

        // 화면 회전값에 맞춰서 카메라도 같이 회전시켜줌
        switch (rotation){
            case Surface.ROTATION_0 :
                degrees = 0;
                break;
            case Surface.ROTATION_90 :
                degrees = 90;
                break;
            case Surface.ROTATION_270 :
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        }else{
            result = (info.orientation - degrees + 360) % 360;
        }

        if (result == 270){
            mCamera.setDisplayOrientation(result - 270);
        }else{
            mCamera.setDisplayOrientation(result + 90);
        }
        mCameraView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }


}
