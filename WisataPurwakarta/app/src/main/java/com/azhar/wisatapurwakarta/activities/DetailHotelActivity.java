package com.azhar.wisatapurwakarta.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.model.ModelHotel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailHotelActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailHotel;
    GoogleMap googleMaps;
    TextView txtNameHotel, txtAddressHotel, txtPhoneHotel;
    String NameHotel, AddressHotel, PhoneHotel;
    ModelHotel modelHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        tbDetailHotel = findViewById(R.id.tbDetailHotel);
        tbDetailHotel.setTitle("Detail Hotel");
        setSupportActionBar(tbDetailHotel);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelHotel = (ModelHotel) getIntent().getSerializableExtra("detailHotel");
        if (modelHotel != null) {

            //get String
            NameHotel = modelHotel.getTxtNamaHotel();
            AddressHotel = modelHotel.getTxtAlamatHotel();
            PhoneHotel = modelHotel.getTxtNoTelp();

            //set Id
            txtNameHotel = findViewById(R.id.tvNamaHotel);
            txtAddressHotel = findViewById(R.id.tvAddressHotel);
            txtPhoneHotel = findViewById(R.id.tvPhoneHotel);

            //show String to Text
            txtNameHotel.setText(NameHotel);
            txtAddressHotel.setText(AddressHotel);
            txtPhoneHotel.setText(PhoneHotel);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        String[] latlong =  modelHotel.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NameHotel));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}
