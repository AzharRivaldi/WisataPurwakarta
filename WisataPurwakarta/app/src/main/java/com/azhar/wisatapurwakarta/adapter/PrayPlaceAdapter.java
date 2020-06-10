package com.azhar.wisatapurwakarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.model.ModelMapLocation;
import com.azhar.wisatapurwakarta.model.ModelPrayPlace;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class PrayPlaceAdapter extends RecyclerView.Adapter<PrayPlaceAdapter.ViewHolder> {

    private List<ModelPrayPlace> items;
    private GoogleMap mMap;
    private HashSet<MapView> mMapViews = new HashSet<>();

    public PrayPlaceAdapter(List<ModelPrayPlace> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pray_place, parent, false);

        ViewHolder viewHolder = new ViewHolder(parent.getContext(), v);
        mMapViews.add(viewHolder.mapView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelPrayPlace data = items.get(position);

        holder.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng latLng = new LatLng(data.getLatitude(), data.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(data.getTxtTempatIbadah()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                mMap.getUiSettings().setAllGesturesEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setTrafficEnabled(true);
            }
        });

        holder.mapView.onResume();
        holder.txtPlaceName.setText(data.getTxtTempatIbadah());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public HashSet<MapView> getMapViews() {
        return mMapViews;
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        public TextView txtPlaceName;
        public CardView cvPrayList;
        public MapView mapView;
        private GoogleMap mGoogleMap;
        private ModelMapLocation mMapLocation;
        private Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            mContext = context;

            cvPrayList = itemView.findViewById(R.id.cvPrayList);
            mapView = itemView.findViewById(R.id.mapView);
            txtPlaceName = itemView.findViewById(R.id.txtPlaceName);

            mapView.onCreate(null);
            mapView.getMapAsync(this);
        }

        public void setMapLocation(ModelMapLocation mapLocation) {
            mMapLocation = mapLocation;

            if (mGoogleMap != null) {
                updateMapContents();
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;

            MapsInitializer.initialize(mContext);
            googleMap.getUiSettings().setMapToolbarEnabled(false);

            if (mMapLocation != null) {
                updateMapContents();
            }
        }

        private void updateMapContents() {

            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation.center));

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation.center, 10f);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }
}
