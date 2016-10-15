package cekresi.jonesrandom.com.cekresi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cekresi.jonesrandom.com.cekresi.Api.APIService;
import cekresi.jonesrandom.com.cekresi.Model.ModelData;
import cekresi.jonesrandom.com.cekresi.Model.RiwayatItem;
import cekresi.jonesrandom.com.cekresi.RiwayatPencarian.RiwayatDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    AutoCompleteTextView NO_RESI;

    Button cek;
    Button clear;
    Button dialog_jne;
    Button dialog_pos;
    Button dialog_tiki;

    ImageView LOGO;

    ProgressDialog loading;

    String JASA_PENGIRIMAN = "JNE";
    int LOGO_PENGIRIMAN;

    AlertDialog DIALOG_PENGIRIMAN = null;

    RiwayatDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new RiwayatDatabase(this);

        toolbar = (Toolbar) findViewById(R.id.resi_toolbar);
        toolbar.setTitle("Cek Resi");
        setSupportActionBar(toolbar);

        NO_RESI = (AutoCompleteTextView) findViewById(R.id.edit_noresi);
        String[] riwayat = database.riw();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, riwayat);
        NO_RESI.setThreshold(1);
        NO_RESI.setAdapter(adapter);

        cek = (Button) findViewById(R.id.btn_cek);
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Resi = NO_RESI.getText().toString();
                if (Resi.isEmpty()) {
                    dialog("Masukan Nomor Resi Sebelum Melanjutkan");
                } else {
                    getData(JASA_PENGIRIMAN, Resi, "UNTUK API KEY SILAHKAN KE SITUS IBACOR.COM/API");
                }
            }
        });

        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NO_RESI.setText("");
            }
        });

        LOGO = (ImageView) findViewById(R.id.pengirim);
        LOGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View vi = getLayoutInflater().inflate(R.layout.jasa_pengiriman, null);
                dialog_jne = (Button) vi.findViewById(R.id.dialog_jne);
                dialog_pos = (Button) vi.findViewById(R.id.dialog_pos);
                dialog_tiki = (Button) vi.findViewById(R.id.dialog_tiki);
                dialog_jne.setOnClickListener(dialogPengiriman);
                dialog_pos.setOnClickListener(dialogPengiriman);
                dialog_tiki.setOnClickListener(dialogPengiriman);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(vi);
                DIALOG_PENGIRIMAN = builder.create();
                DIALOG_PENGIRIMAN.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (DIALOG_PENGIRIMAN != null) {
                            DIALOG_PENGIRIMAN = null;
                        }

                    }
                });
                DIALOG_PENGIRIMAN.show();
            }
        });

    }

    View.OnClickListener dialogPengiriman = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String jasa = null;

            switch (v.getId()) {

                case R.id.dialog_jne:
                    jasa = "JNE";
                    LOGO_PENGIRIMAN = R.drawable.jne;
                    break;
                case R.id.dialog_pos:
                    jasa = "POS";
                    LOGO_PENGIRIMAN = R.drawable.pos;
                    break;
                case R.id.dialog_tiki:
                    jasa = "TIKI";
                    LOGO_PENGIRIMAN = R.drawable.tiki;
                    break;
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), LOGO_PENGIRIMAN);
            LOGO.setImageBitmap(bitmap);
            JASA_PENGIRIMAN = jasa;
            DIALOG_PENGIRIMAN.dismiss();
        }
    };

    public void getData(final String jasa, final String resi, String key) {

        loading = ProgressDialog.show(MainActivity.this, "Tracking", "Sedang mencari data No. Resi " + resi);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ibacor.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ModelData> call = service.getDataResi(jasa, resi, key);
        call.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {

                if (response.isSuccessful()) {
                    String Status = response.body().getStatus();

                    Log.d("Response server", "Data : " + Status);

                    if (Status.equals("success")) {
                        Intent detail = new Intent(MainActivity.this, Detail.class);

                        Bundle bind = new Bundle();
                        List<RiwayatItem> list = response.body().getData().getRiwayat();

                        bind.putString("pengirim", jasa);
                        bind.putString("resi", response.body().getQuery().getResi());
                        bind.putString("status", response.body().getData().getDetail().getStatus());
                        bind.putString("service", response.body().getData().getDetail().getService());
                        bind.putString("tanggal", response.body().getData().getDetail().getTanggal());

                        bind.putString("asal_nama", response.body().getData().getDetail().getAsal().getNama());
                        bind.putString("asal_alamat", response.body().getData().getDetail().getAsal().getAlamat());

                        if (jasa.equals("JNE") || jasa.equals("POS")) {
                            bind.putString("tujuan_nama", response.body().getData().getDetail().getTujuan().getNama());
                            bind.putString("tujuan_alamat", response.body().getData().getDetail().getTujuan().getAlamat());
                        }
                        detail.putParcelableArrayListExtra("riwayat", (ArrayList<? extends Parcelable>) list);
                        detail.putExtras(bind);
                        startActivity(detail);

                        Cursor cek = database.cek(resi);
                        if (cek.getCount() < 1) {
                            database.ins(resi, jasa);
                        }

                    } else {
                        dialog("Tracking Data Dengan Nomor Resi " + resi + " Tidak Di Temukan");
                    }
                } else {
                    dialog("Something Wrong With Connection " + response.errorBody());
                }
                loading.dismiss();

            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {
                dialog("Error " + t.getMessage());
                loading.dismiss();
            }
        });


    }

    public void dialog(String pesan) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Tracking Nomor Resi");
        builder.setMessage(pesan);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] riwayat = database.riw();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, riwayat);
        NO_RESI.setThreshold(1);
        NO_RESI.setAdapter(adapter);

    }


}
