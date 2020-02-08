package com.example.emelinda.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.emelinda.DAO.Data;
import com.example.emelinda.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_a);
        setTitle(R.string.app_name);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng location = new LatLng(List.library.getLatitude(), List.library.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(location)
                /*.icon(bitmapDescriptorFromVector(this, R.drawable.ic_marker))*/
                .title(List.library.getName()));
        CameraPosition camera = CameraPosition.builder().target(location).zoom(17).bearing(90).tilt(30).build();
        //mMap.setMinZoomPreference(6.0f);
        //mMap.setMaxZoomPreference(25.0f);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
        //mMap.getUiSettings().setMapToolbarEnabled(true);
        //googleMap.getUiSettings().setZoomGesturesEnabled(true);
        //googleMap.getUiSettings().setRotateGesturesEnabled(true);

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));








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

    /*private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }*/
}




