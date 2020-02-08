package com.example.emelinda.DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.emelinda.Classes.City;
import com.example.emelinda.Classes.Region;
import java.util.ArrayList;

public class DAO_City {
    Connection cn;
    Context c;


    public DAO_City(Context c) {
        this.c = c;
    }



    public ArrayList<City> getCities(String regionId){
        ArrayList<City> cities= new ArrayList<City>();
        cn = new Connection(c);
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            String query = "SELECT * FROM city WHERE region='"+regionId+"'";
            Cursor row = db.rawQuery(query,null);
            while(row.moveToNext()){
                City c = new City();
                c.setRegion(new Region());
                c.setId(row.getString(0));
                c.setDescription(row.getString(1));
                c.getRegion().setId(row.getString(2));
                cities.add(c);
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(c,"Error en getCities " + e,Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
        return cities;
    }
}
