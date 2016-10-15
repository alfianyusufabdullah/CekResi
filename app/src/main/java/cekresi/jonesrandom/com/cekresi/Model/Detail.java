package cekresi.jonesrandom.com.cekresi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail{
	@SerializedName("no_resi")
	@Expose
	private String no_resi;
	@SerializedName("asal")
	@Expose
	private Asal asal;
	@SerializedName("service")
	@Expose
	private String service;
	@SerializedName("tanggal")
	@Expose
	private String tanggal;
	@SerializedName("tujuan")
	@Expose
	private Tujuan tujuan;
	@SerializedName("status")
	@Expose
	private String status;

	public void setNo_resi(String no_resi){
		this.no_resi = no_resi; 
	}

	public String getNo_resi(){
		return no_resi; 
	}

	public void setService(String service){
		this.service = service; 
	}

	public String getService(){
		return service; 
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal; 
	}

	public String getTanggal(){
		return tanggal; 
	}

	public void setStatus(String status){
		this.status = status; 
	}

	public Asal getAsal(){
		return asal; 
	}

	public void setTujuan(Tujuan tujuan){
		this.tujuan = tujuan; 
	}

	public Tujuan getTujuan(){
		return tujuan; 
	}

	public void setAsal(Asal asal){
		this.asal = asal; 
	}

	public String getStatus(){
		return status; 
	}

}