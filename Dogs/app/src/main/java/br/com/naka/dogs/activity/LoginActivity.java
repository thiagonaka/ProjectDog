package br.com.naka.dogs.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.naka.dogs.R;
import br.com.naka.dogs.bean.request.SigupRequestBean;
import br.com.naka.dogs.bean.response.SigupResponseBean;
import br.com.naka.dogs.util.ServicoParametro;
import br.com.naka.dogs.util.UtilsPreferences;
import br.com.naka.dogs.util.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView txtEmail;
    private ServicoParametro service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        initComponents();
    }


    private void initComponents() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Webservice.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServicoParametro.class);


        txtEmail = (TextView) findViewById(R.id.activity_login_txt_email);
        txtEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_login_txt_email:

                SigupRequestBean srb = new SigupRequestBean();
                srb.setEmail("your@email.com");

                final Call<SigupResponseBean> sigupResponseBeanCall = service.postSigup(srb);
                sigupResponseBeanCall.enqueue(new Callback<SigupResponseBean>() {
                    @Override
                    public void onResponse(Call<SigupResponseBean> call, Response<SigupResponseBean> response) {
                        int statusCode = response.code();

                        if (statusCode == 200) {

                            SigupResponseBean sigupResponseBean = response.body();
                            String token = sigupResponseBean.getUser().getToken();
                            UtilsPreferences.setToken(token);

                            Intent intent = new Intent(context, DogListActivity.class);
                            startActivity(intent);

                        }else if (statusCode == 400){

                            alert("Email is not valid");
                        }else {
                            alert("Error validating email");
                        }
                    }

                    @Override
                    public void onFailure(Call<SigupResponseBean> call, Throwable t) {
                        alert("Error validating email");
                    }
                });

                break;
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
