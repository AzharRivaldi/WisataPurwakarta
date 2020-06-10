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
import com.azhar.wisatapurwakarta.model.ModelKuliner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class KulinerAdapter extends RecyclerView.Adapter<KulinerAdapter.ViewHolder> {

    private List<ModelKuliner> items;
    private KulinerAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelKuliner modelKuliner);
    }

    public KulinerAdapter(Context context, List<ModelKuliner> items, KulinerAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kuliner, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelKuliner data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarKuliner())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgKuliner);

        holder.tvKategori.setText(data.getKategoriKuliner());
        holder.tvKuliner.setText(data.getTxtNamaKuliner());
        holder.cvKuliner.setOnClickListener(new View.OnClickListener() {
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

        public TextView tvKuliner;
        public TextView tvKategori;
        public CardView cvKuliner;
        public ImageView imgKuliner;

        public ViewHolder(View itemView) {
            super(itemView);
            cvKuliner = itemView.findViewById(R.id.cvKuliner);
            tvKuliner = itemView.findViewById(R.id.tvKuliner);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgKuliner = itemView.findViewById(R.id.imgKuliner);
        }
    }

}
