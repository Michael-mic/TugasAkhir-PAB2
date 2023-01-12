package com.michael.yeslaundry.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michael.yeslaundry.R;
import com.michael.yeslaundry.models.Transaksi;
import com.michael.yeslaundry.services.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class TransaksiViewAdapter extends RecyclerView.Adapter<TransaksiViewAdapter.ViewHolder> {
    private List<Transaksi> data = new ArrayList<>();
    private ItemLongClickListener<Transaksi> itemLongClickListener;

    public void setData(List<Transaksi> data, ItemLongClickListener<Transaksi> itemLongClickListener) {
        this.data = data;
        this.itemLongClickListener = itemLongClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransaksiViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiViewAdapter.ViewHolder holder, int position) {
        int pos =holder.getAdapterPosition();
        Transaksi transaksi = data.get(pos);
        holder.tvNama.setText(transaksi.getNamapelanggan());
        holder.tvAlamat.setText(transaksi.getAlamat());
        holder.tvNoHp.setText(transaksi.getNohp());
        holder.tvJenis.setText(transaksi.getJenis());
        holder.tvKG.setText(Integer.toString(transaksi.getKg()));
        holder.tvHarga.setText(Integer.toString(transaksi.getHarga()));
        holder.tvStatus.setText(transaksi.getStatus());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickListener.onItemLongClick(view, transaksi, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvNoHp, tvAlamat, tvKG, tvJenis, tvHarga, tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvNoHp = itemView.findViewById(R.id.tv_nohp);
            tvKG = itemView.findViewById(R.id.tv_kg);
            tvJenis = itemView.findViewById(R.id.tv_jenis);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
