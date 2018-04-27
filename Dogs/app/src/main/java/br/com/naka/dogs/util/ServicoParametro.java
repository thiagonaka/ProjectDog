package br.com.naka.dogs.util;

import br.com.naka.dogs.bean.request.SigupRequestBean;
import br.com.naka.dogs.bean.response.FeedResponse;
import br.com.naka.dogs.bean.response.SigupResponseBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServicoParametro {

    @POST("/signup")
    Call<SigupResponseBean> postSigup(
            @Body SigupRequestBean sigupRequestBean
    );

    @GET("/feed")
    Call<FeedResponse> getFeed(
            @Header("Authorization") String authorization,
            @Query("category") String category
    );

}
