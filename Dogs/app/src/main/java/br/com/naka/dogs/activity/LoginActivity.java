package br.com.naka.dogs.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.naka.dogs.R;
import br.com.naka.dogs.bean.request.SigupRequestBean;
import br.com.naka.dogs.bean.response.SigupResponseBean;
import br.com.naka.dogs.util.ServicoParametro;
import br.com.naka.dogs.util.Singleton;
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
    private ProgressDialog progressDialog;

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

                checkInternet();

                break;
        }
    }

    private void initWebservice(){

        progress();
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

                    progressDialog.dismiss();
                }else if (statusCode == 400){
                    alert("Email is not valid");
                    progressDialog.dismiss();
                }else {
                    alert("Error validating email");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SigupResponseBean> call, Throwable t) {
                alert("Error validating email");
                progressDialog.dismiss();
            }
        });

    }

    private void checkInternet() {

        if (!Singleton.isDeviceOnline(context)) {

            new AlertDialog.Builder(context)
                    .setTitle("Atenção")
                    .setMessage("Sem conexão com a internet")
                    .setCancelable(false)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            checkInternet();
                        }
                    })
                    .create().show();

        } else {

            initWebservice();
        }
    }

    private void progress(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog = ProgressDialog.show(context, getString(R.string.activity_login_str_txt_progress),
                   getString(R.string.activity_login_str_txt_msg_progress), false);
        progressDialog.show();

    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
