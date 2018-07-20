package mureung.mureungtest.DataBase.SRCREC;

public class SRCREC {

    public int _id;
    public int srcValue;
    public int userSN;
    public String realTime;
    public double srcLatitude;
    public double srcLongitude;
    public float srcSpeed;
    public float srcRPM;
    public float srcAPS;
    public float srcTPS;
    public float srcRPS;
    public float srcMAF;
    public float srcFuelLevel;
    public float srcTorque;
    public float srcFuelTrimB1S;
    public float srcFuelTrimB2S;
    public float srcFuelTrimB1L;
    public float srcFuelTrimB2L;
    public float srcEngineCoolantTemp;
    public float srcEngineOilTemp;
    public float srcEngineLoad;
    public float srcIntakePress;
    public float srcAmbientAirTemp;
    public float srcAbsolutePress;
    public float srcHybridBatteryT;
    public float srcDPF;
    public float srcDPFTemp;
    public float srcIntakeAirTemp;
    public float srcEGT1;
    public float srcEGT2;
    public String srcUploadTime ;



    public  SRCREC(int _id,int srcValue, int userSN, String realTime, double srcLatitude, double srcLongitude, float srcSpeed, float srcRPM,
                   float srcAPS, float srcTPS, float srcRPS, float srcMAF, float srcFuelLevel, float srcTorque,float srcEngineLoad, float srcFuelTrimB1S,float srcFuelTrimB2S,
                   float srcIntakePress, float srcEngineCoolantTemp, float srcEngineOilTemp, float srcFuelTrimB1L, float srcFuelTrimB2L,
                   float srcAmbientAirTemp, float srcAbsolutePress, float srcHybridBatteryT, float srcDPF, float srcDPFTemp,
                   float srcIntakeAirTemp, float srcEGT1, float srcEGT2, String srcUploadTime) {
        this._id = _id;
        this.srcValue = srcValue;
        this.userSN = userSN;
        this.realTime = realTime;
        this.srcLatitude = srcLatitude;
        this.srcLongitude = srcLongitude;
        this.srcSpeed = srcSpeed;
        this.srcRPM = srcRPM;
        this.srcAPS = srcAPS;
        this.srcTPS = srcTPS;
        this.srcRPS = srcRPS;
        this.srcMAF = srcMAF;
        this.srcFuelLevel = srcFuelLevel;
        this.srcTorque = srcTorque;
        this.srcFuelTrimB1S = srcFuelTrimB1S;
        this.srcFuelTrimB2S = srcFuelTrimB2S;
        this.srcFuelTrimB1L = srcFuelTrimB1L;
        this.srcFuelTrimB2L = srcFuelTrimB2L;
        this.srcEngineCoolantTemp = srcEngineCoolantTemp;
        this.srcEngineOilTemp = srcEngineOilTemp;
        this.srcEngineLoad = srcEngineLoad;
        this.srcIntakePress = srcIntakePress;
        this.srcAmbientAirTemp = srcAmbientAirTemp;
        this.srcAbsolutePress = srcAbsolutePress;
        this.srcHybridBatteryT = srcHybridBatteryT;
        this.srcDPF = srcDPF;
        this.srcDPFTemp = srcDPFTemp;
        this.srcIntakeAirTemp = srcIntakeAirTemp;
        this.srcEGT1 = srcEGT1;
        this.srcEGT2 = srcEGT2;
        this.srcUploadTime = srcUploadTime;
    }

}
