package cekresi.jonesrandom.com.cekresi.Api;


import cekresi.jonesrandom.com.cekresi.Model.ModelData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/cek-resi/")
    Call<ModelData> getDataResi(@Query("pengirim") String pengirim, @Query("resi") String resi, @Query("k") String k);

}
