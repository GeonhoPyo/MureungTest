package mureung.mureungtest.View.Camera;

/**
 * Created by user on 2018-03-20.
 */

public class CameraData {

    public byte[] cameraData ;
    public int cameraWidth ;
    public int cameraHeight ;
    public int cameraFormat;

    public CameraData(byte[] cameraData, int cameraWidth , int cameraHeight , int cameraFormat){
        this.cameraData = cameraData;
        this.cameraWidth = cameraWidth ;
        this.cameraHeight = cameraHeight;
        this.cameraFormat = cameraFormat;
    }
}
