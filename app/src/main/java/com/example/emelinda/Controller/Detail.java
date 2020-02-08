package com.example.emelinda.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.emelinda.Adapters.FragmentAdapter;
import com.example.emelinda.DAO.DAO_Favorites;
import com.example.emelinda.DAO.Data;
import com.example.emelinda.R;
import com.google.android.material.tabs.TabLayout;

public class Detail extends AppCompatActivity implements  BasicInfo.OnFragmentInteractionListener,
    DetailInfo.OnFragmentInteractionListener, View.OnClickListener {
    Button btn_favorites;
    DAO_Favorites dao = new DAO_Favorites(this);
    AlertDialog.Builder dlg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_a);
        Button btn_map = (Button) findViewById(R.id.btn_map);
        btn_favorites = (Button) findViewById(R.id.btn_favorites);
        if(!dao.ifExists(List.library.getId(),1)) {
            btn_favorites.setText(R.string.favorite_add);
        }
        else if(dao.ifExists(List.library.getId(),2)){
                btn_favorites.setText(R.string.favorite_add);
        }else {
            btn_favorites.setText(R.string.favorite_delete);
        }
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        setTitle(R.string.app_name);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        FragmentAdapter fa = new FragmentAdapter(getSupportFragmentManager(),tabs.getTabCount());
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        pager.setAdapter(fa);
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        setTabs(tabs);
        btn_favorites.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
        } if (id == R.id.menu_favorites) {
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


    public void setTabs(TabLayout tabs){
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                }
                if (tab.getPosition() == 1) {
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_map) {
            Intent i = new Intent(Detail.this, Map.class);
            startActivity(i);
        }
        if (view.getId() == R.id.btn_favorites) {
            // si no ha existido nunca
            if (!dao.ifExists(List.library.getId(), 1)) {
                if (!dao.insertFavorite(List.library.getId())) {
                            Toast.makeText(getApplicationContext(), "Favorito añadido", Toast.LENGTH_SHORT).show();
                            btn_favorites.setText(R.string.favorite_delete);
                }
            // si existe pero su estado es eliminado
            } else if (dao.ifExists(List.library.getId(), 2)) {
                dao.updateFavorite(List.library.getId(), 2);
                btn_favorites.setText(R.string.favorite_delete);
                Toast.makeText(Detail.this, "Favorito añadido", Toast.LENGTH_SHORT).show();
                } else {
                    confirmDelete();
            }
        }
    }

    public void confirmDelete(){
        dlg1 = new AlertDialog.Builder(this);
        dlg1.setTitle(R.string.dialog_delete_title);
        dlg1.setMessage(R.string.dialog_delete_question);
        dlg1.setCancelable(false);
        dlg1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dao.updateFavorite(List.library.getId(), 1);
                Toast.makeText(getApplicationContext(), "Favorito eliminado", Toast.LENGTH_SHORT).show();
                btn_favorites.setText(R.string.favorite_add);
            }
        });
        dlg1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //
            }
        });
        dlg1.show();
    }
}


