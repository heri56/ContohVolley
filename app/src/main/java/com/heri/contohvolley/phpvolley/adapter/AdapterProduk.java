package com.heri.contohvolley.phpvolley.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heri.contohvolley.R;
import com.heri.contohvolley.R;
import com.heri.contohvolley.phpvolley.model.Produk;

import java.util.List;

/**
 * Created by heri on 10/30/17.
 */

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.ProduckViewHolder> {
    private Context context;
    private List<Produk> listproduk;

    public AdapterProduk(Context context, List<Produk> listproduk) {
        this.context = context;
        this.listproduk = listproduk;
    }

    @Override
    public ProduckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lfinflater = LayoutInflater.from(context);
        View view = lfinflater.inflate(R.layout.product_list, null);
        return new ProduckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProduckViewHolder holder, int position) {
        Produk produk = listproduk.get(position);
        Glide.with(context).load(produk.getImage()).into(holder.imview);
        holder.texviewtile.setText(produk.getTitle());
        holder.textviewShort.setText(produk.getShortdesc());
        holder.texviewRating.setText(String.valueOf(produk.getRating()));
        holder.textviewPrice.setText(String.valueOf(produk.getPrice()));


    }

    @Override
    public int getItemCount() {
        return listproduk.size();
    }

    public class ProduckViewHolder extends RecyclerView.ViewHolder {
        TextView texviewtile, textviewShort, texviewRating, textviewPrice;
        ImageView imview;
        public ProduckViewHolder(View itemview) {
            super(itemview);
            texviewtile = itemview.findViewById(R.id.Text);
            textviewShort = itemview.findViewById(R.id.Bawah);
            texviewRating = itemview.findViewById(R.id.Rating);
            textviewPrice = itemview.findViewById(R.id.Harga);
            imview = itemview.findViewById(R.id.Gambar);
        }
    }
}
