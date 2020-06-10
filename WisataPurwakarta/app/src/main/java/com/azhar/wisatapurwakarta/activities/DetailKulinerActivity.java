package com.azhar.wisatapurwakarta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.api.Api;
import com.azhar.wisatapurwakarta.model.ModelKuliner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailKulinerActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailKuliner;
    GoogleMap googleMaps;
    TextView tvNamaKuliner, tvAddressKuliner, tvPhoneKuliner, tvOpenTime, tvDesc;
    String idKuliner, NamaKuliner, AddressKuliner, PhoneKuliner, OpenTime, Desc;
    ModelKuliner modelKuliner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);

        tbDetailKuliner = findViewById(R.id.tbDetailKuliner);
        tbDetailKuliner.setTitle("Detail Kuliner");
        setSupportActionBar(tbDetailKuliner);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelKuliner = (ModelKuliner) getIntent().getSerializableExtra("detailKuliner");
        if (modelKuliner != null) {
            idKuliner = modelKuliner.getIdKuliner();
            NamaKuliner = modelKuliner.getTxtNamaKuliner();

            //set Id
            tvNamaKuliner = findViewById(R.id.tvNamaKuliner);
            tvAddressKuliner = findViewById(R.id.tvAddressKuliner);
            tvPhoneKuliner = findViewById(R.id.tvPhoneKuliner);
            tvOpenTime = findViewById(R.id.tvOpenTime);
            tvDesc = findViewById(R.id.tvDesc);
            getDetailKuliner();
        }
    }

    private void getDetailKuliner() {
        AndroidNetworking.get(Api.DetailKuliner)
                .addPathParameter("id", idKuliner)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                //get String Api
                                NamaKuliner = response.getString("nama");
                                AddressKuliner = response.getString("alamat");
                                PhoneKuliner = response.getString("nomor_telp");
                                OpenTime = response.getString("jam_buka_tutup");
                                Desc = response.getString("deskripsi");

                                //set Text
                                tvNamaKuliner.setText(NamaKuliner);
                                tvAddressKuliner.setText(AddressKuliner);
                                tvPhoneKuliner.setText(PhoneKuliner);
                                tvOpenTime.setText(OpenTime);
                                tvDesc.setText(Desc);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailKulinerActivity.this,
                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailKulinerActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        String[] latlong = modelKuliner.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NamaKuliner));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
