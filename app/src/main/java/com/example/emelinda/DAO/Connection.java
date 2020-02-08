package com.example.emelinda.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emelinda.R;


public class Connection extends SQLiteOpenHelper {
    public static final String DB_NAME = "db_library";
    private static final int DB_SCHEME_VERSION = 2;


    public Connection(Context context){
        super(context,DB_NAME,null,DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String default_values : Data.Default_values()){
        db.execSQL(default_values);
        }
        db.execSQL(Data.addLibrary("Nicolás", "sin calle", "(51) 287 5600", "-", 1, 1, 2, R.drawable.books, String.valueOf(-27.365426), String.valueOf(-70.331240)));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
           db.execSQL("DROP TABLE IF EXISTS type");
           db.execSQL("DROP TABLE IF EXISTS region");
           db.execSQL("DROP TABLE IF EXISTS city");
           db.execSQL("DROP TABLE IF EXISTS library");
           db.execSQL("DROP TABLE IF EXISTS favorites");

        onCreate(db);

    }
}
