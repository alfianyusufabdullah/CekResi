package cekresi.jonesrandom.com.cekresi;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cekresi.jonesrandom.com.cekresi.Adapter.RiwayatAdapterJNE;
import cekresi.jonesrandom.com.cekresi.Adapter.RiwayatAdapterPOS;
import cekresi.jonesrandom.com.cekresi.Adapter.RiwayatAdapterTIKI;
import cekresi.jonesrandom.com.cekresi.Model.RiwayatItem;

public class Detail extends AppCompatActivity {

    TextView detail_pengirim;
    TextView detail_resi;
    TextView detail_status;
    TextView detail_service;
    TextView detail_tanggal;

    TextView asal_nama;
    TextView asal_alamat;

    TextView tujuan_nama;
    TextView tujuan_alamat;

    List<RiwayatItem> list;

    String d_PENGIRIM = "n/a";
    String d_RESI = "n/a";
    String d_STATUS = "n/a";
    String d_SERVICE = "n/a";
    String d_TANGGAL = "n/a";

    String a_NAMA = "n/a";
    String a_ALAMAT = "n/a";

    String t_NAMA = "n/a";
    String t_ALAMAT = "n/a";

    Button riwayat;

    BottomSheetDialog sheetDialog;

    Toolbar toolbar;

    Bundle bind;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        toolbar = (Toolbar) findViewById(R.id.resi_toolbar);
        toolbar.setTitle("Cek Resi");
        setSupportActionBar(toolbar);

        list = new ArrayList<RiwayatItem>();

        bind();

        riwayat = (Button) findViewById(R.id.riwayat);
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riwayatDetail();
            }
        });

    }

    public void bind() {

        detail_pengirim = (TextView) findViewById(R.id.detail_pengirim);
        detail_resi = (TextView) findViewById(R.id.detail_resi);
        detail_status = (TextView) findViewById(R.id.detail_status);
        detail_service = (TextView) findViewById(R.id.detail_service);
        detail_tanggal = (TextView) findViewById(R.id.detail_tanggal);

        asal_nama = (TextView) findViewById(R.id.asal_nama);
        asal_alamat = (TextView) findViewById(R.id.asal_alamat);

        tujuan_nama = (TextView) findViewById(R.id.tujuan_nama);
        tujuan_alamat = (TextView) findViewById(R.id.tujuan_alamat);

        bind = getIntent().getExtras();

        String pengirim = bind.getString("pengirim");
        if (pengirim != null && !pengirim.isEmpty()) {
            d_PENGIRIM = pengirim;
        }

        String resi = bind.getString("resi");
        if (resi != null && !resi.isEmpty()) {
            d_RESI = resi;
        }

        String status = bind.getString("status");
        if (status != null && !status.isEmpty()) {
            d_STATUS = status;
        }

        String service = bind.getString("service");
        if (service != null && !service.isEmpty()) {
            d_SERVICE = service;
        }

        String tanggal = bind.getString("tanggal");
        if (tanggal != null && !tanggal.isEmpty()) {
            d_TANGGAL = tanggal;
        }

        String nama_a = bind.getString("asal_nama");
        if (nama_a != null && !nama_a.isEmpty()) {
            a_NAMA = nama_a;
        }

        String alamat_a = bind.getString("asal_alamat");
        if (alamat_a != null && !alamat_a.isEmpty()) {
            a_ALAMAT = alamat_a;
        }

        String nama_t = bind.getString("tujuan_nama");
        if (nama_t != null && !nama_t.isEmpty()) {
            t_NAMA = nama_t;
        }

        String alamat_t = bind.getString("tujuan_alamat");
        if (alamat_t != null && !alamat_t.isEmpty()) {
            t_ALAMAT = alamat_t;
        }

        detail_pengirim.setText(": " + d_PENGIRIM);
        detail_resi.setText(": " + d_RESI);
        detail_status.setText(": " + d_STATUS);
        detail_service.setText(": " + d_SERVICE);
        detail_tanggal.setText(": " + d_TANGGAL);

        asal_nama.setText(": " + a_NAMA);
        asal_alamat.setText(": " + a_ALAMAT);

        tujuan_nama.setText(": " + t_NAMA);
        tujuan_alamat.setText(": " + t_ALAMAT);

        list = getIntent().getParcelableArrayListExtra("riwayat");


    }

    public void riwayatDetail() {

        View v = getLayoutInflater().inflate(R.layout.riwayat_detail, null);

        TextView resi = (TextView) v.findViewById(R.id.nomor_resi);
        resi.setText("No Resi : " + bind.getString("resi"));

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list_riwayat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Detail.this));

        if (d_PENGIRIM.equals("POS")) {
            recyclerView.setAdapter(new RiwayatAdapterPOS(list));
        } else if (d_PENGIRIM.equals("JNE")) {
            recyclerView.setAdapter(new RiwayatAdapterJNE(list));
        } else {
            recyclerView.setAdapter(new RiwayatAdapterTIKI(list));
        }


        sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setContentView(v);
        sheetDialog.show();
        sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                sheetDialog = null;
            }
        });
    }

    public void BottomSheetClose(View view) {
        if (sheetDialog != null) {
            sheetDialog.dismiss();
        }
    }

}
