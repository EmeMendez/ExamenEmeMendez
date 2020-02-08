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
import android.widget.ListView;
import android.widget.TextView;

import com.example.emelinda.Adapters.LibrariesAdapter;
import com.example.emelinda.Classes.Library;
import com.example.emelinda.DAO.DAO_Library;
import com.example.emelinda.DAO.Data;
import com.example.emelinda.R;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView listView;
    public static Library library;
    TextView noresult;
    ArrayList<Library> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_a);
        setTitle(R.string.app_name);
        listView = (ListView) findViewById(R.id.list_library);
        noresult = (TextView) findViewById(R.id.noresult);
        setLibraries(listView);
        if(array.isEmpty()){
            noresult.setVisibility(View.VISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                String hid = (String) ((TextView) view.findViewById(R.id.hid)).getText();
                library = getLibrary(hid);
                Intent i = new Intent(List.this,Detail.class);
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
            Intent i = new Intent(this, Main.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.menu_favorites) {
        Intent i = new Intent(this, Favorites.class);
        startActivity(i);
        return true;
    }
        return super.onOptionsItemSelected(item);    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater charge_menu = getMenuInflater();
        charge_menu.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void setLibraries(ListView listView){
        DAO_Library dao = new DAO_Library(this);
        array = dao.getLibraries(Main.region,Main.city,Main.type);
        LibrariesAdapter customAdapter = new LibrariesAdapter(this, array);
        listView.setAdapter(customAdapter);
    }


    public Library getLibrary(String idLibrary){
        Library library = new Library();
        DAO_Library dao = new DAO_Library(this);
        library = dao.getLibrary(idLibrary);
        return library;
    }


}
