package com.example.emelinda.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.emelinda.Classes.Type;

import java.util.ArrayList;

public class DAO_Type {
    Connection cn;
    Context c;


    public DAO_Type(Context c) {
        this.c = c;
    }



    public ArrayList<Type> getTypes(){
        ArrayList<Type> types= new ArrayList<Type>();
        cn = new Connection(c);
        try{
        SQLiteDatabase db = cn.getWritableDatabase();
        String query = "SELECT * FROM type";
        Cursor row = db.rawQuery(query,null);
        while(row.moveToNext()){
            Type t = new Type();
            t.setId(row.getString(0));
            t.setDescription(row.getString(1));
            types.add(t);
        }
            db.close();
        }catch (Exception e){
            Toast.makeText(c,"Error en getTypes " + e,Toast.LENGTH_SHORT).show();
        }
        return types;
    }
}
