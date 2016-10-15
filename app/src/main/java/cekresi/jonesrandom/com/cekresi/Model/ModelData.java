package cekresi.jonesrandom.com.cekresi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {
	@SerializedName("pesan")
	@Expose
	private String pesan;
	@SerializedName("website")
	@Expose
	private String website;
	@SerializedName("data")
	@Expose
	private Data data;
	@SerializedName("query")
	@Expose
	private Query query;
	@SerializedName("status")
	@Expose
	private String status;

	public String getPesan(){
		return pesan; 
	}

	public String getWebsite(){
		return website; 
	}

	public void setData(Data data){
		this.data = data; 
	}

	public void setStatus(String status){
		this.status = status; 
	}

	public void setWebsite(String website){
		this.website = website; 
	}

	public void setQuery(Query query){
		this.query = query; 
	}

	public Data getData(){
		return data; 
	}

	public void setPesan(String pesan){
		this.pesan = pesan; 
	}

	public String getStatus(){
		return status; 
	}

	public Query getQuery(){
		return query; 
	}

}