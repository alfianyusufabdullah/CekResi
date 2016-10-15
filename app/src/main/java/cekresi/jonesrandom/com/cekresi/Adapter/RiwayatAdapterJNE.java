package cekresi.jonesrandom.com.cekresi.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cekresi.jonesrandom.com.cekresi.Model.RiwayatItem;
import cekresi.jonesrandom.com.cekresi.R;

public class RiwayatAdapterJNE extends RecyclerView.Adapter<RiwayatHolder> {

    List<RiwayatItem> list;

    public RiwayatAdapterJNE(List<RiwayatItem> list) {
        this.list = list;
    }

    @Override
    public RiwayatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_riwayat, parent, false);
        RiwayatHolder holder = new RiwayatHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RiwayatHolder holder, int position) {

        String keterangan = list.get(position).getKeterangan();
        String lokasi = list.get(position).getLokasi();
        String tanggal = list.get(position).getTanggal();

        if (lokasi.isEmpty()) {
            holder.list_lokasi.setVisibility(View.GONE);
        }

        holder.cart.setText("JNE");
        holder.list_keterangan.setText(keterangan);
        holder.list_lokasi.setText(lokasi);
        holder.list_tanggal.setText(tanggal);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
