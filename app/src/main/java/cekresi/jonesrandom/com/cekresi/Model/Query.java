package cekresi.jonesrandom.com.cekresi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query{
	@SerializedName("pengirim")
	@Expose
	private String pengirim;
	@SerializedName("resi")
	@Expose
	private String resi;

	public String getResi(){
		return resi; 
	}

	public void setResi(String resi){
		this.resi = resi; 
	}

	public void setPengirim(String pengirim){
		this.pengirim = pengirim; 
	}

	public String getPengirim(){
		return pengirim; 
	}

}