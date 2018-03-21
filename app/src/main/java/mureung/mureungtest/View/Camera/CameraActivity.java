package mureung.mureungtest.View.Camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import mureung.mureungtest.MainActivity;

/**
 * Created by user on 2018-03-19.
 */

public class CameraActivity extends AppCompatActivity{

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(MainActivity.mainContext.getPackageManager())!= null){
            startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extra.get("data");
            if(CameraPushFragment.cameraTestHandler != null){
                CameraPushFragment.cameraTestHandler.obtainMessage(CameraPushFragment.cameraSetIamgeView,imageBitmap).sendToTarget();
            }
        }
    }
}
