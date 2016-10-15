package cekresi.jonesrandom.com.cekresi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatItem implements Parcelable {
	@SerializedName("keterangan")
	@Expose
	private String keterangan;
	@SerializedName("lokasi")
	@Expose
	private String lokasi;
	@SerializedName("tanggal")
	@Expose
	private String tanggal;
	@SerializedName("status")
	@Expose
	private String status;

	protected RiwayatItem(Parcel in) {
		keterangan = in.readString();
		lokasi = in.readString();
		tanggal = in.readString();
        status = in.readString();
	}

	public static final Creator<RiwayatItem> CREATOR = new Creator<RiwayatItem>() {
		@Override
		public RiwayatItem createFromParcel(Parcel in) {
			return new RiwayatItem(in);
		}

		@Override
		public RiwayatItem[] newArray(int size) {
			return new RiwayatItem[size];
		}
	};

	public void setTanggal(String tanggal){
		this.tanggal = tanggal; 
	}

	public String getTanggal(){
		return tanggal; 
	}

	public void setLokasi(String lokasi){
		this.lokasi = lokasi; 
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan; 
	}

	public String getKeterangan(){
		return keterangan; 
	}

	public String getLokasi(){
		return lokasi; 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(keterangan);
		dest.writeString(lokasi);
		dest.writeString(tanggal);
        dest.writeString(status);

	}
}