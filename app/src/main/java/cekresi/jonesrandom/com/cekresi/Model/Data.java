package cekresi.jonesrandom.com.cekresi.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{
	@SerializedName("riwayat")
	@Expose
	private List<RiwayatItem> riwayat;
	@SerializedName("detail")
	@Expose
	private Detail detail;

	public Detail getDetail(){
		return detail; 
	}

	public List<RiwayatItem> getRiwayat(){
		return riwayat; 
	}

	public void setRiwayat(List<RiwayatItem> riwayat){
		this.riwayat = riwayat; 
	}

	public void setDetail(Detail detail){
		this.detail = detail; 
	}

}