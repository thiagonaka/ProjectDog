package br.com.naka.dogs.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.naka.dogs.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        initComponents();
    }


    private void initComponents(){

        txtEmail = (TextView) findViewById(R.id.activity_login_txt_email);
        txtEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()){

            case R.id.activity_login_txt_email:

                i = new Intent(context, DogListActivity.class);
                startActivity(i);

                break;
        }
    }
}
