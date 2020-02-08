package com.example.emelinda.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.emelinda.Classes.Library;
import com.example.emelinda.Classes.Type;
import com.example.emelinda.R;

import java.util.ArrayList;


public class DAO_Favorites {
    Connection cn;
    Context c;


    public DAO_Favorites(Context c) {
        this.c = c;
    }

    public boolean ifExists(String idLibrary,int option){
        boolean exists = false;
        String query = "";
        cn = new Connection(c);
        if(option ==1){
            query = "SELECT * FROM favorites WHERE library ='"+idLibrary+"'";
        }else if(option ==2){
            query = "SELECT * FROM favorites WHERE library ='"+idLibrary+"' AND state=0";
        }
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            Cursor row = db.rawQuery(query,null);
            if(row.getCount()!=0)
                exists = true;
        }catch (Exception e){
            Toast.makeText(c, "Error en ifExists Favorite" + e, Toast.LENGTH_LONG).show();
            System.out.println("Error en ifExists Favorite" + e);
        }
        finally {
            return exists;
        }
    }

    public boolean insertFavorite(String idLibrary){
        boolean insert = false;
        cn = new Connection(c);
        String query = "INSERT INTO favorites(library,state) values ('"+idLibrary+"',1)";
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            Cursor row = db.rawQuery(query,null);
            if(row.moveToFirst())
                insert = true;
        }catch (Exception e){
            Toast.makeText(c, "Error en insert Favorite", Toast.LENGTH_SHORT).show();
        }
        finally {
            return insert;
        }
    }

    public ArrayList<Library> getFavorites(){
        ArrayList<Library> fav= new ArrayList<Library>();
        cn = new Connection(c);
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            String query = "SELECT * FROM favorites f,library l,type t WHERE f.library=l.id AND l.type=t.id AND state = 1 ";
            Cursor row = db.rawQuery(query,null);
            while(row.moveToNext()){
                Library f = new Library();
                f.setType(new Type());
                f.setId(row.getString(1));
                f.setName(row.getString(4));
                f.getType().setDescription(row.getString(15));
                f.setImg(R.drawable.books);
                fav.add(f);
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(c,"Error en la lista de favoritos " + e,Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
        return fav;
    }

    public boolean updateFavorite(String idLibrary,int option){
        boolean insert = false;
        String query ="";
        cn = new Connection(c);
        if(option ==1) {
            query = "UPDATE favorites SET state=0 WHERE  library = '" + idLibrary + "'";
        }else if(option==2){
            query = "UPDATE favorites SET state=1 WHERE  library = '" + idLibrary + "'";
        }
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            Cursor row = db.rawQuery(query,null);
            if(row.moveToFirst())
                insert = true;
        }catch (Exception e){
            Toast.makeText(c, "Error en insert Favorite", Toast.LENGTH_SHORT).show();
        }
        finally {
            return insert;
        }
    }
}
