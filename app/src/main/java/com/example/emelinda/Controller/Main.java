package com.example.emelinda.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emelinda.Classes.City;
import com.example.emelinda.Classes.Region;
import com.example.emelinda.Classes.Type;
import com.example.emelinda.DAO.DAO_City;
import com.example.emelinda.DAO.DAO_Region;
import com.example.emelinda.DAO.DAO_Type;
import com.example.emelinda.DAO.Data;
import com.example.emelinda.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
   public static String region, city, type;

    Spinner spinner_region;
    Spinner spinner_city;
    Spinner spinner_type;
    Button btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_a);
        setTitle(R.string.app_name);
        spinner_region = (Spinner) findViewById(R.id.spinner_region);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);
        btn_search = (Button) findViewById(R.id.btn_search);
        setRegions(spinner_region, spinner_city);
        setTypes(spinner_type);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region = ((Region) spinner_region.getSelectedItem()).getId();
                city = ((City) spinner_city.getSelectedItem()).getId();
                type = ((Type) spinner_type.getSelectedItem()).getId();
                Intent i = new Intent(Main.this, List.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            Data.alertDialog(this);
            return true;
        }
        if (id == R.id.menu_search) {
            Toast.makeText(this, "Te encuentras en búsqueda", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.menu_favorites) {
            Intent i = new Intent(this, Favorites.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater charge_menu = getMenuInflater();
        charge_menu.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void setRegions(Spinner spinner_region, final Spinner spinner_city) {
        ArrayList<Region> array = new ArrayList<Region>();
        DAO_Region dao_region = new DAO_Region(this);
        final DAO_City dao_city = new DAO_City(this);



        array = dao_region.getRegions();
        ArrayAdapter<Region> adapter_region = new ArrayAdapter<Region>(this, android.R.layout.simple_spinner_item, array);
        adapter_region.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_region.setAdapter(adapter_region);
        spinner_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Region region = (Region) parent.getSelectedItem();
                String id_region = region.getId();
                ArrayList<City> cities = dao_city.getCities(id_region);

                ArrayAdapter<City> adapter_cities = new ArrayAdapter<City>(Main.this, android.R.layout.simple_spinner_item, cities);
                adapter_cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_city.setAdapter(adapter_cities);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setTypes(Spinner spinner_type){
        ArrayList<Type> array = new ArrayList<Type>();
        DAO_Type dao_type = new DAO_Type(this);
        array = dao_type.getTypes();
        ArrayAdapter<Type> adapter_type = new ArrayAdapter<Type>(this, android.R.layout.simple_spinner_item,array);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter_type);
    }




}






