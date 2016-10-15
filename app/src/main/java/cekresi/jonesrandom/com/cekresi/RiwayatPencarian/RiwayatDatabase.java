package cekresi.jonesrandom.com.cekresi.RiwayatPencarian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RiwayatDatabase extends SQLiteOpenHelper {

    public static final String db_name = "riwayat";
    public static final String db_tabel = "tabel_pencarian";
    SQLiteDatabase db;


    public RiwayatDatabase(Context context) {
        super(context, db_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + db_tabel + "(riwayat_pencarian text, pengirim text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + db_tabel);
        onCreate(db);
    }

    public void ins(String riwayat, String pengirim) {
        ContentValues values = new ContentValues();
        values.put("riwayat_pencarian", riwayat);
        values.put("pengirim", pengirim);
        db.insert(db_tabel, null, values);
    }

    public String[] riw() {
        Cursor cek = db.rawQuery("select * from " + db_tabel, null);

        if (cek.getCount() > 0) {
            String[] str = new String[cek.getCount()];
            int i = 0;
            while (cek.moveToNext()) {
                str[i] = cek.getString(cek.getColumnIndex("riwayat_pencarian"));
                i++;
            }
            return str;
        } else {
            return new String[]{};
        }
    }

    public Cursor cek(String cek) {
        Cursor cur = db.rawQuery("select * from " + db_tabel + " where riwayat_pencarian='" + cek + "'",null );
        return cur;
    }
}
