package com.example.emelinda.DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.emelinda.Classes.City;
import com.example.emelinda.Classes.Library;
import com.example.emelinda.Classes.Type;
import com.example.emelinda.R;


import java.util.ArrayList;

public class DAO_Library {
    Connection cn;
    Context c;


    public DAO_Library(Context c) {
        this.c = c;
    }


    public ArrayList<Library> getLibraries(String region,String city,String type){
        ArrayList<Library> libraries= new ArrayList<Library>();
        try {
            cn = new Connection(c);
            SQLiteDatabase db = cn.getWritableDatabase();
            String query = "SELECT * FROM library l,city c,type t,region r WHERE l.city='" + city + "' AND l.region='" + region + "' AND l.type='" + type + "' AND l.region=r.id AND l.city=c.id AND l.type=t.id";
            Cursor row = db.rawQuery(query, null);
            while (row.moveToNext()) {
                Library l = new Library();
                l.setCity(new City());
                l.setType(new Type());
                l.setId(row.getString(0));
                l.setName(row.getString(1));
                l.setImg(R.drawable.books);
                l.getType().setDescription(row.getString(15));
                libraries.add(l);
            }
        }catch (Exception e){
            Toast.makeText(c,"Error en getLibraries " + e,Toast.LENGTH_LONG).show();
        }
        return libraries;
    }

    public Library getLibrary(String id) {
        cn = new Connection(c);
        SQLiteDatabase db = cn.getWritableDatabase();
        String query = "SELECT * FROM library l,city c,type t,region r WHERE l.id='" + id + "' AND l.region=r.id AND l.city=c.id AND l.type=t.id";
        Cursor row = db.rawQuery(query, null);
        Library l = new Library();
        if (row.moveToFirst()) {
            l.setCity(new City());
            l.setType(new Type());
            l.setId(row.getString(0));
            l.setName(row.getString(1));
            l.setAddress(row.getString(2));
            l.setPhone(row.getString(3));
            l.setWebsite(row.getString(4));
            l.setLatitude(row.getDouble(9));
            l.setLongitude(row.getDouble(10));
            l.setImg(R.drawable.books);
            l.getType().setDescription(row.getString(15));
        }
        return l;
    }
}
