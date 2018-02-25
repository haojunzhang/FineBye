package idv.haojun.finebye.retrofit.apis;

import idv.haojun.finebye.data.Cam;
import idv.haojun.finebye.retrofit.GovResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;


public interface CamApis {

    /**
     *
     */
    @GET("api/v1/rest/datastore/A01010000C-000674-011")
    Call<GovResponse<Cam>> getCams();

}
