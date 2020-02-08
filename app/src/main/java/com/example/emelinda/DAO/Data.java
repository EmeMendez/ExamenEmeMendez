package com.example.emelinda.DAO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.emelinda.R;
import java.util.ArrayList;

public class Data {
    private static final String TABLE_TYPE = "CREATE TABLE type (id INTEGER PRIMARY KEY AUTOINCREMENT,description TEXT)";
    private static final String TABLE_REGION = "CREATE TABLE region (id INTEGER PRIMARY KEY AUTOINCREMENT,description TEXT)";
    private static final String TABLE_CITY = "CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT,description TEXT,region INTEGER, FOREIGN KEY(region) REFERENCES region(id))";
    private static final String TABLE_LIBRARY = "CREATE TABLE library(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT ,address TEXT,phone TEXT,website TEXT,region INTEGER,city INTEGER,type INTEGER,img INTEGER,latitude TEXT,longitude TEXT,FOREIGN KEY (region) REFERENCES region(id),FOREIGN KEY (city) REFERENCES city(id),FOREIGN KEY (type) REFERENCES type(id))";
    private static final String TABLE_FAVORITES = "CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY AUTOINCREMENT,library INTEGER NOT NULL,state INTEGER NOT NULL,FOREIGN KEY(library) REFERENCES library(id))";

    public static  ArrayList<String> Default_values() {
        ArrayList<String> default_values = new ArrayList<String>();
        default_values.add(TABLE_TYPE);
        default_values.add(TABLE_REGION);
        default_values.add("INSERT INTO type (description) values ('Pública')");
        default_values.add("INSERT INTO type (description) values ('Privada')");
        default_values.add("INSERT INTO region (description) values ('Atacama')");
        default_values.add("INSERT INTO region (description) values ('Coquimbo')");
        default_values.add("INSERT INTO region (description) values ('Valparaiso')");
        default_values.add(TABLE_CITY);
        default_values.add("INSERT INTO city (description,region) values ('Copiapó',1)");
        default_values.add("INSERT INTO city (description,region) values ('Vallenar',1)");
        default_values.add("INSERT INTO city (description,region) values ('Coquimbo',2)");
        default_values.add("INSERT INTO city (description,region) values ('La Serena',2)");
        default_values.add("INSERT INTO city (description,region) values ('Valparaiso',3)");
        default_values.add("INSERT INTO city (description,region) values ('Viña del Mar',3)");
        default_values.add(TABLE_LIBRARY);

        // Biblioteca Copiapó
        default_values.add(addLibrary("Biblioteca Salón José Joaquín Vallejo", "Colipi 565, Copiapó", "(51) 287 5600","-",1,1,1,R.drawable.books,String.valueOf(-27.365426),String.valueOf(-70.331240)));
        // Bibliotecas Vallenar
        default_values.add(addLibrary("Biblioteca Horacio Canales Guzmán Vallenar","Brasil S/N & Ramírez, Vallenar","(51) 287 5600","-",1,2,1, R.drawable.books,String.valueOf(-28.575489),String.valueOf(-70.758929)));
        default_values.add(addLibrary("Biblioteca Pública/Municipalidad de Vallenar", "Brasil 480, Vallenar", "(51) 261 5372","-",1,2,2,R.drawable.books,String.valueOf(-28.575414),String.valueOf(-70.758511)));
        // Bibliotecas Coquimbo
        default_values.add(addLibrary("Biblioteca UCN Coquimbo", " Larrondo 973-975, Coquimbo", "(51) 220 9891","www.online.ucn.cl",2,3,2, R.drawable.books,String.valueOf(-29.965403),String.valueOf(-71.351858)));
        default_values.add(addLibrary("Biblioteca Publica Municipal 354 Guillermo Francis Jones", "Av. Costanera 701, Coquimbo", "(51) 208 8888","-",2,3,1,R.drawable.books,String.valueOf(-29.950366),String.valueOf(-71.336666)));
        //Bibliotecas La Serena
        default_values.add(addLibrary("Biblioteca Pública de La Serena", "Cristóbal Colón 495, La Serena", "(51) 256 2500","www.biblioredes.cl",2,4,1, R.drawable.books,String.valueOf(-29.899756),String.valueOf(-71.249760)));
        default_values.add(addLibrary("Biblioteca Regional Gabriela Mistral", " Juan José Latorre 782, La Serena", "(56)9 6421 3929","www.bibliotecagabrielamistral.cl",2,4,2,R.drawable.books,String.valueOf(-29.904643),String.valueOf(-71.263534)));

        default_values.add(addLibrary("Biblioteca Central Irma Salas Silva", "Anfión Muñoz 875, La Serena", "(51) 233 4791","www.sibuls.userena.cl",2,4,2,R.drawable.books,String.valueOf(-29.909477),String.valueOf(-71.245838)));
        //Bibliotecas Valparaiso
        default_values.add(addLibrary("Biblioteca Santiago Severín", "Edwards 301, Valparaíso", "(32) 221 3375","bibliotecaseverin.gob.cl",3,5,1, R.drawable.books,String.valueOf(-33.045074),String.valueOf(-71.619412)));
        default_values.add(addLibrary("Biblioteca Libro Alegre", "San Enrique 339, Valparaíso", "(32) 321 6401","www.libroalegre.cl",3,5,2, R.drawable.books,String.valueOf(-33.042342),String.valueOf(-71.630470)));
        default_values.add(addLibrary("Biblioteca Central UTFSM", "1680, Av. España, Valparaíso", "(32) 265 4147","bibliotecas.usm.cl",3,5,2, R.drawable.books,String.valueOf(-33.034998),String.valueOf(-71.595108)));
        //Bibliotecas Viña
        default_values.add(addLibrary("Biblioteca 095 BC1", " Avenida Bellavista 1663, Viña del Mar", "(32) 227 7533","-",3,6,1,R.drawable.books,String.valueOf(-33.027575),String.valueOf(-71.520063)));
        default_values.add(addLibrary("Biblioteca 136 BC1", " Av. Las Azucenas 21, Viña del Mar", "(32) 325 7842","-",3,6,2, R.drawable.books,String.valueOf(-33.009247),String.valueOf(-71.501160)));
        return default_values;
    }

    public static String addLibrary(String name, String address, String phone, String website, int region, int city, int type, int img, String latitude, String longitude) {
        return "INSERT INTO library (name,address,phone,website,region,city,type,img,latitude,longitude) values ('"+name+"','"+address+"','"+phone+"','"+website+"','"+region+"','"+city+"','"+type+"','"+img+"','"+latitude+"','"+longitude+"')";
    }


    public static void alertDialog(Context c){
        AlertDialog.Builder alert = new AlertDialog.Builder(c);
        LayoutInflater factory = LayoutInflater.from(c);
        View view = factory.inflate(R.layout.alert_dialog, null);
        alert.setView(view);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {
            }
        });
        alert.show();

    }

    public static void addFavorite(Context c){
        Connection cn;
        cn = new Connection(c);
        try{
            SQLiteDatabase db = cn.getWritableDatabase();
            String query = TABLE_FAVORITES;
            Cursor row = db.rawQuery(query,null);
            if(row.moveToNext()){
                //Table favorites was created successfull
            }else{
                //Table Favorite wasn't created because exists
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(c,"Error  while you tried create favorites" + e,Toast.LENGTH_SHORT).show();
        }
    }
}
