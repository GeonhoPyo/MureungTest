package mureung.mureungtest.Tool;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ErrorLogManager {

    public void saveErrorLog(String logToString){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LOG/";
        File file = new File(dirPath);

        if (!file.exists()){
            file.mkdirs();
        }else{
            File saveFile = new File(dirPath + "log.txt");
            try {
                BufferedWriter bfw = new BufferedWriter(new FileWriter(saveFile, true));
                bfw.write(logToString);
                bfw.write("\n");
                bfw.flush();
                bfw.close();
            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }

    public void saveDeviceRotationErrorLog( String logToString){

        try {
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LOG/";
            File file = new File(dirPath);

            if (!file.exists()){
                file.mkdirs();
            }else{
                File saveFile = new File(dirPath + "Rotation.txt");
                try {
                    BufferedWriter bfw = new BufferedWriter(new FileWriter(saveFile, true));
                    bfw.write(logToString);
                    bfw.write("\n");
                    bfw.flush();
                    bfw.close();
                }catch (Exception e){

                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }




    }



    public void saveErrorLog(String fileName,String logToString){

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LOG/";
        File file = new File(dirPath);

        if (!file.exists()){
            file.mkdirs();
        }else{
            File saveFile = new File(dirPath + fileName+".txt");
            try {
                BufferedWriter bfw = new BufferedWriter(new FileWriter(saveFile, true));
                StringBuilder test  = new StringBuilder();
                test.append(logToString);
                test.append("\r");
                bfw.write(test.toString());
                bfw.newLine();
                //bfw.write("\n");
                bfw.flush();
                bfw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
