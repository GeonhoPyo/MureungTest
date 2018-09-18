package mureung.mureungtest.Communication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import mureung.mureungtest.Tool.ErrorLogManager;
import mureung.mureungtest.Tool.Time_DataBridge;

public class Sensor_Stream {

    private static ArrayList<Double> pitchArray = new ArrayList<Double>();
    private static ArrayList<Double> rollArray = new ArrayList<Double>();
    private static ArrayList<Double> yamArray = new ArrayList<Double>();


    private SensorManager sensorManager = null;
    private SensorLitener sensorLitener;
    private Sensor gyroscopeSensor = null;
    private Sensor accelerometer = null;

    /*Sensor variables*/
    private float[] gyroValues = new float[3];
    private float[] accValues = new float[3];
    private double accPitch, accRoll,accYam;

    /*for unsing complementary fliter*/
    private float a = 0.2f;
    private static final float NS2S = 1.0f/1000000000.0f;
    private double pitch = 0, roll = 0, yam = 0;
    private double timestamp;
    private double dt;
    private double temp;
    private boolean running;
    private boolean gyroRunning;
    private boolean accRunning;


    public void setSensorLitener(Context context){
        try {
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            sensorLitener = new SensorLitener();
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            sensorManager.registerListener(sensorLitener,gyroscopeSensor,SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(sensorLitener,accelerometer,SensorManager.SENSOR_DELAY_UI);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeSensorLitener(){
        try {
            if(sensorManager!=null){
                sensorManager.unregisterListener(sensorLitener);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private class SensorLitener implements SensorEventListener {


        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()){
                case Sensor.TYPE_GYROSCOPE :
                    gyroValues = event.values;
                    if(!gyroRunning){
                        gyroRunning = true;
                    }

                    break;

                case Sensor.TYPE_ACCELEROMETER :
                    accValues = event.values;
                    if(!accRunning){
                        accRunning = true;
                    }

                    break;
            }

            if(gyroRunning && accRunning){
                complementaty(event.timestamp);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    private void complementaty(double new_ts){

        gyroRunning = false;
        accRunning = false;

        if(timestamp == 0){
            timestamp = new_ts;
            return;
        }
        dt = (new_ts - timestamp) * NS2S; // ns->s 변환
        timestamp = new_ts;


        accRoll= Math.atan2(accValues[1], accValues[2]) * 180.0 / Math.PI; // X 축 기준
        accPitch = -Math.atan2(accValues[0], accValues[2]) * 180.0 / Math.PI; // Y 축 기준
        accYam = Math.atan2(accValues[0],accValues[1]) * 180.0 / Math.PI;

        //Z축은 안나옴?


        temp = (1/a) * (accRoll - roll) + gyroValues[0];
        roll = roll + (temp*dt);

        temp = (1/a) * (accPitch - pitch) + gyroValues[1];
        pitch = pitch + (temp*dt);

        temp = (1/a) * (accYam - yam) + gyroValues[2];
        yam = yam + (temp * dt);

        //Log.e("Sensor_Strem","roll = " + roll + " , pitch : " + pitch);

        if(pitchArray == null){
            pitchArray = new ArrayList<Double>();
        }
        if(rollArray == null){
            rollArray = new ArrayList<Double>();
        }
        if(yamArray == null){
            yamArray = new ArrayList<Double>();
        }


        pitchArray.add(pitch);
        rollArray.add(roll);
        yamArray.add(yam);

    }


    private void imageFilter(){

        double resultPitch = 0;
        double resultRoll = 0;
        double resultYam = 0;
        if(pitchArray != null){
            double addPitch = 0;
            for(int i = 0; i < pitchArray.size() ; i ++){
                if( i != 0 || i != pitchArray.size()-1){
                    if(pitchArray.get(i) != null){
                        addPitch += pitchArray.get(i);
                    }
                }
            }
            resultPitch = addPitch/ pitchArray.size();


            pitchArray = null;
        }

        if(rollArray != null){
            double addRoll = 0;
            for(int i = 0 ; i < rollArray.size() ; i++){
                if( i != 0 || i != rollArray.size() -1){
                    if(rollArray.get(i) != null){
                        addRoll += rollArray.get(i);
                    }

                }
            }
            resultRoll = addRoll / rollArray.size();

            rollArray = null;
        }

        if(yamArray != null){
            double addYam = 0;
            for (int i = 0 ; i < yamArray.size() ; i++){
                if(i != 0 || i != yamArray.size() -1 ){
                    if(yamArray.get(i) != null){
                        addYam += yamArray.get(i);
                    }

                }
            }
            resultYam = addYam / yamArray.size();

            yamArray = null;
        }

        /*String result = new Time_DataBridge().getDateTime() + " , resultPitch  " + resultPitch + "" + resultRoll + "" + resultYam;
        new ErrorLogManager().saveDeviceRotationErrorLog();*/


        Log.e("Sensor_Stream","imageFilter resultPitch = " + resultPitch + " , resultRoll : " + resultRoll + " , resultYam : " + resultYam);


    }

    /*public double getRotationResult(){
        return imageFilter();
    }*/




    public void testTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //여기서 1초마다 가져옴
                imageFilter();
            }
        },0,1000);



    }


}
