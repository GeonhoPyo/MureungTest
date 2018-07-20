package mureung.mureungtest.DataManager.DataBridge;

import java.util.Timer;
import java.util.TimerTask;

public class Srcrec_DataBridge {
    private static int srcValue;
    private static int userSN;
    private static String realTime;
    private static double srcLatitude;
    private static double srcLongitude;
    private static float srcSpeed;
    private static float srcRPM;
    private static float srcAPS;
    private static float srcTPS;
    private static float srcRPS;
    private static float srcMAF;
    private static float srcFuelLevel;
    private static float srcTorque;
    private static float srcEngineLoad;
    private static float srcFuelTrimB1S;
    private static float srcFuelTrimB2S;
    private static float srcIntakePress;
    private static float srcEngineCoolantTemp;
    private static float srcEngineOilTemp;
    private static float srcFuelTrimB1L;
    private static float srcFuelTrimB2L;
    private static float srcAmbientAirTemp;
    private static float srcAbsolutePress;
    private static float srcHybridBatteryT;
    private static float srcDPF;
    private static float srcDPFTemp;
    private static float srcIntakeAirTemp;
    private static float srcEGT1;
    private static float srcEGT2;
    private static String srcUploadTime;

    public static int getSrcValue() {
        return srcValue;
    }

    public static void setSrcValue(int srcValue) {
        Srcrec_DataBridge.srcValue = srcValue;
    }

    public static int getUserSN() {
        return userSN;
    }

    public static void setUserSN(int userSN) {
        Srcrec_DataBridge.userSN = userSN;
    }

    public static String getRealTime() {
        return realTime;
    }

    public static void setRealTime(String realTime) {
        Srcrec_DataBridge.realTime = realTime;
    }

    public static double getSrcLatitude() {
        return srcLatitude;
    }

    public static void setSrcLatitude(double srcLatitude) {
        Srcrec_DataBridge.srcLatitude = srcLatitude;
    }

    public static double getSrcLongitude() {
        return srcLongitude;
    }

    public static void setSrcLongitude(double srcLongitude) {
        Srcrec_DataBridge.srcLongitude = srcLongitude;
    }

    public static float getSrcSpeed() {
        return srcSpeed;
    }

    public static void setSrcSpeed(float srcSpeed) {
        Srcrec_DataBridge.srcSpeed = srcSpeed;
    }

    public static float getSrcRPM() {
        return srcRPM;
    }

    public static void setSrcRPM(float srcRPM) {
        Srcrec_DataBridge.srcRPM = srcRPM;
    }

    public static float getSrcAPS() {
        return srcAPS;
    }

    public static void setSrcAPS(float srcAPS) {
        Srcrec_DataBridge.srcAPS = srcAPS;
    }

    public static float getSrcTPS() {
        return srcTPS;
    }

    public static void setSrcTPS(float srcTPS) {
        Srcrec_DataBridge.srcTPS = srcTPS;
    }

    public static float getSrcRPS() {
        return srcRPS;
    }

    public static void setSrcRPS(float srcRPS) {
        Srcrec_DataBridge.srcRPS = srcRPS;
    }

    public static float getSrcMAF() {
        return srcMAF;
    }

    public static void setSrcMAF(float srcMAF) {
        Srcrec_DataBridge.srcMAF = srcMAF;
    }

    public static float getSrcFuelLevel() {
        return srcFuelLevel;
    }

    public static void setSrcFuelLevel(float srcFuelLevel) {
        Srcrec_DataBridge.srcFuelLevel = srcFuelLevel;
    }

    public static float getSrcTorque() {
        return srcTorque;
    }

    public static void setSrcTorque(float srcTorque) {
        Srcrec_DataBridge.srcTorque = srcTorque;
    }

    public static float getSrcEngineLoad() {
        return srcEngineLoad;
    }

    public static void setSrcEngineLoad(float srcEngineLoad) {
        Srcrec_DataBridge.srcEngineLoad = srcEngineLoad;
    }

    public static float getSrcFuelTrimB1S() {
        return srcFuelTrimB1S;
    }

    public static void setSrcFuelTrimB1S(float srcFuelTrimB1S) {
        Srcrec_DataBridge.srcFuelTrimB1S = srcFuelTrimB1S;
    }

    public static float getSrcFuelTrimB2S() {
        return srcFuelTrimB2S;
    }

    public static void setSrcFuelTrimB2S(float srcFuelTrimB2S) {
        Srcrec_DataBridge.srcFuelTrimB2S = srcFuelTrimB2S;
    }

    public static float getSrcIntakePress() {
        return srcIntakePress;
    }

    public static void setSrcIntakePress(float srcIntakePress) {
        Srcrec_DataBridge.srcIntakePress = srcIntakePress;
    }

    public static float getSrcEngineCoolantTemp() {
        return srcEngineCoolantTemp;
    }

    public static void setSrcEngineCoolantTemp(float srcEngineCoolantTemp) {
        Srcrec_DataBridge.srcEngineCoolantTemp = srcEngineCoolantTemp;
    }

    public static float getSrcEngineOilTemp() {
        return srcEngineOilTemp;
    }

    public static void setSrcEngineOilTemp(float srcEngineOilTemp) {
        Srcrec_DataBridge.srcEngineOilTemp = srcEngineOilTemp;
    }

    public static float getSrcFuelTrimB1L() {
        return srcFuelTrimB1L;
    }

    public static void setSrcFuelTrimB1L(float srcFuelTrimB1L) {
        Srcrec_DataBridge.srcFuelTrimB1L = srcFuelTrimB1L;
    }

    public static float getSrcFuelTrimB2L() {
        return srcFuelTrimB2L;
    }

    public static void setSrcFuelTrimB2L(float srcFuelTrimB2L) {
        Srcrec_DataBridge.srcFuelTrimB2L = srcFuelTrimB2L;
    }

    public static float getSrcAmbientAirTemp() {
        return srcAmbientAirTemp;
    }

    public static void setSrcAmbientAirTemp(float srcAmbientAirTemp) {
        Srcrec_DataBridge.srcAmbientAirTemp = srcAmbientAirTemp;
    }

    public static float getSrcAbsolutePress() {
        return srcAbsolutePress;
    }

    public static void setSrcAbsolutePress(float srcAbsolutePress) {
        Srcrec_DataBridge.srcAbsolutePress = srcAbsolutePress;
    }

    public static float getSrcHybridBatteryT() {
        return srcHybridBatteryT;
    }

    public static void setSrcHybridBatteryT(float srcHybridBatteryT) {
        Srcrec_DataBridge.srcHybridBatteryT = srcHybridBatteryT;
    }

    public static float getSrcDPF() {
        return srcDPF;
    }

    public static void setSrcDPF(float srcDPF) {
        Srcrec_DataBridge.srcDPF = srcDPF;
    }

    public static float getSrcDPFTemp() {
        return srcDPFTemp;
    }

    public static void setSrcDPFTemp(float srcDPFTemp) {
        Srcrec_DataBridge.srcDPFTemp = srcDPFTemp;
    }

    public static float getSrcIntakeAirTemp() {
        return srcIntakeAirTemp;
    }

    public static void setSrcIntakeAirTemp(float srcIntakeAirTemp) {
        Srcrec_DataBridge.srcIntakeAirTemp = srcIntakeAirTemp;
    }

    public static float getSrcEGT1() {
        return srcEGT1;
    }

    public static void setSrcEGT1(float srcEGT1) {
        Srcrec_DataBridge.srcEGT1 = srcEGT1;
    }

    public static float getSrcEGT2() {
        return srcEGT2;
    }

    public static void setSrcEGT2(float srcEGT2) {
        Srcrec_DataBridge.srcEGT2 = srcEGT2;
    }

    public static String getSrcUploadTime() {
        return srcUploadTime;
    }

    public static void setSrcUploadTime(String srcUploadTime) {
        Srcrec_DataBridge.srcUploadTime = srcUploadTime;
    }







}
