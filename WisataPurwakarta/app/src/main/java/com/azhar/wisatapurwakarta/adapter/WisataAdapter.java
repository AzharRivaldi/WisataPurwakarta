package com.azhar.wisatapurwakarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.model.ModelWisata;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    private List<ModelWisata> items;
    private WisataAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelWisata modelWisata);
    }

    public WisataAdapter(Context context, List<ModelWisata> items, WisataAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wisata, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelWisata data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarWisata())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgWisata);

        holder.tvKategori.setText(data.getKategoriWisata());
        holder.tvWisata.setText(data.getTxtNamaWisata());
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKategori;
        public TextView tvWisata;
        public CardView cvWisata;
        public ImageView imgWisata;

        public ViewHolder(View itemView) {
            super(itemView);
            cvWisata = itemView.findViewById(R.id.cvWisata);
            tvWisata = itemView.findViewById(R.id.tvWisata);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgWisata = itemView.findViewById(R.id.imgWisata);
        }
    }

}
