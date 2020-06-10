package com.azhar.wisatapurwakarta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.adapter.PrayPlaceAdapter;
import com.azhar.wisatapurwakarta.api.Api;
import com.azhar.wisatapurwakarta.model.ModelPrayPlace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrayPlaceActivity extends AppCompatActivity {

    RecyclerView rvPrayPlace;
    PrayPlaceAdapter prayPlaceAdapter;
    ProgressDialog progressDialog;
    List<ModelPrayPlace> modelPrayPlace = new ArrayList<>();
    Toolbar tbPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray_place);

        tbPlace = findViewById(R.id.toolbar_place);
        tbPlace.setTitle("Daftar Tempat Ibadah");
        setSupportActionBar(tbPlace);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvPrayPlace = findViewById(R.id.rvPrayPlace);
        rvPrayPlace.setHasFixedSize(true);
        rvPrayPlace.setLayoutManager(new LinearLayoutManager(this));

        getPrayPlace();
    }

    private void getPrayPlace() {
        progressDialog.show();
        AndroidNetworking.get(Api.TempatIbadah)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("tempat_ibadah");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelPrayPlace dataApi = new ModelPrayPlace();
                                dataApi.setTxtTempatIbadah(temp.getString("nama"));
                                dataApi.setLatitude(temp.getDouble("latitude"));
                                dataApi.setLongitude(temp.getDouble("longitude"));
                                modelPrayPlace.add(dataApi);
                                showPrayPlace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PrayPlaceActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PrayPlaceActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showPrayPlace() {
        prayPlaceAdapter = new PrayPlaceAdapter(modelPrayPlace);
        rvPrayPlace.setAdapter(prayPlaceAdapter);
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
