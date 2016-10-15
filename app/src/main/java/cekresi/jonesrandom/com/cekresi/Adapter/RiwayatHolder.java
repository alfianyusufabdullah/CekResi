package cekresi.jonesrandom.com.cekresi.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cekresi.jonesrandom.com.cekresi.R;

public class RiwayatHolder extends RecyclerView.ViewHolder {

    TextView cart;
    TextView list_keterangan;
    TextView list_tanggal;
    TextView list_lokasi;

    public RiwayatHolder(View itemView) {
        super(itemView);
        cart = (TextView) itemView.findViewById(R.id.list_a);
        list_keterangan = (TextView) itemView.findViewById(R.id.list_keterangan);
        list_tanggal = (TextView) itemView.findViewById(R.id.list_tanggal);
        list_lokasi = (TextView) itemView.findViewById(R.id.list_lokasi);
    }
}

