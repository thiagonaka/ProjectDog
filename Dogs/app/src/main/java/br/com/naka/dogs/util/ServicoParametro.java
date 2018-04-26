package br.com.naka.dogs.util;

import br.com.naka.dogs.bean.request.SigupRequestBean;
import br.com.naka.dogs.bean.response.SigupResponseBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicoParametro {

    @POST("/signup")
    Call<SigupResponseBean> postSigup(
            @Body SigupRequestBean sigupRequestBean
    );

}
