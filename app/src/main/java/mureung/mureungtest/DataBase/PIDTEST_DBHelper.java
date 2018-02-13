package mureung.mureungtest.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 2018-01-29.
 */

public class PIDTEST_DBHelper extends SQLiteOpenHelper {
    public PIDTEST_DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PIDTEST (_id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT, pidUpdateTime TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void pidTestInsert(PIDTEST pidtest, String pidUpdateTime){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO PIDTEST VALUES (null, "+"'"+pidtest.value+"'"+ ", " +"'"+ pidUpdateTime +"'"+");");

        db.close();
    }

    public ArrayList<PIDTEST> getPidTestArrayList(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PIDTEST ",null);
        ArrayList<PIDTEST> pidtestArrayList = new ArrayList<PIDTEST>();

        while(cursor.moveToNext()){

            pidtestArrayList.add(new PIDTEST(cursor.getInt(0),cursor.getString(0),cursor.getString(1)));
        }

        db.close();

        return pidtestArrayList;
    }

    public int getCurrentValue(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PIDTEST  ORDER BY _id DESC",null);
        ArrayList<PIDTEST> pidtestArrayList = new ArrayList<PIDTEST>();
        int currentValue = 0;

        while(cursor.moveToNext()){

            pidtestArrayList.add(new PIDTEST(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));

        }

        db.close();

        if(!pidtestArrayList.isEmpty()){
            currentValue =pidtestArrayList.get(0)._id;
            //Log.e("DB","currentValue : " + currentValue);
        }else {
            currentValue = 0;
        }


        return currentValue;
    }

}
