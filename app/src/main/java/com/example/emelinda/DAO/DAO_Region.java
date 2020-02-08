package com.example.emelinda.DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.emelinda.Classes.Region;
import java.util.ArrayList;

public class DAO_Region {
    Connection cn;
    Context c;


    public DAO_Region(Context c) {
        this.c = c;
    }

    public ArrayList<Region> getRegions(){
        ArrayList<Region> types= new ArrayList<Region>();
        cn = new Connection(c);
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            String query = "SELECT * FROM region";
            Cursor row = db.rawQuery(query,null);
            while(row.moveToNext()){
                Region r = new Region();
                r.setId(row.getString(0));
                r.setDescription(row.getString(1));
                types.add(r);
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(c,"Error en getRegions " + e,Toast.LENGTH_SHORT).show();
        }
        return types;
    }

    /*public ArrayList<Region> getRegions(){
        regions = new ArrayList<Region>();
        Region tarapaca = new Region("1","Tarapaca");
        Region antofagasta = new Region("2","Antofagasta");
        Region atacama = new Region("3","Atacama");
        Region coquimbo = new Region("4","Coquimbo");
        Region valparaiso = new Region("5","Valparaiso");

        regions.add(tarapaca);
        regions.add(antofagasta);
        regions.add(atacama);
        regions.add(coquimbo);
        regions.add(valparaiso);
        return regions;
    }*/
}
