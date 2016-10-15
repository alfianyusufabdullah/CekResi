package cekresi.jonesrandom.com.cekresi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tujuan{
	@SerializedName("nama")
	@Expose
	private String nama;
	@SerializedName("alamat")
	@Expose
	private String alamat;

	public String getAlamat(){
		return alamat; 
	}

	public void setAlamat(String alamat){
		this.alamat = alamat; 
	}

	public String getNama(){
		return nama; 
	}

	public void setNama(String nama){
		this.nama = nama; 
	}

}