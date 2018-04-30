package br.com.naka.dogs.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.naka.dogs.R;
import br.com.naka.dogs.adapter.DogsAdapter;
import br.com.naka.dogs.bean.response.FeedResponse;
import br.com.naka.dogs.util.ServicoParametro;
import br.com.naka.dogs.util.UtilsPreferences;
import br.com.naka.dogs.util.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LabradorListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private LabradorListFragment fragment;
    private Activity context;
    private View ll;
    private int tipo;
    private FragmentActivity fa;
    private List<String> listLabrador;
    private ServicoParametro service;
    private GridView gridLabrador;
    private String token;
    private DogsAdapter adapter;
    private ImageView imgDog;

    public static LabradorListFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        LabradorListFragment f = new LabradorListFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getInt("tipo");
            getActivity();
            fragment = this;
            context = getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fa = super.getActivity();
        ll = inflater.inflate(R.layout.fragment_labrador_list, container, false);
        context = getActivity();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Webservice.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServicoParametro.class);

        initComponents(ll);

        return ll;
    }

    private void initComponents(View view) {
        gridLabrador = (GridView) view.findViewById(R.id.fragment_labrador_list_gridview);
        gridLabrador.setOnItemClickListener(this);

        initWebservice();
    }

    private void initWebservice() {
        token = UtilsPreferences.getToken();
        String category = "labrador";

        Call<FeedResponse> feedResponseCall = service.getFeed(token, category);
        feedResponseCall.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                int statusCode = response.code();

                if (statusCode == 200) {

                    FeedResponse feedResponse = response.body();
                    listLabrador = feedResponse.getList();

                    adapter = new DogsAdapter(context, listLabrador);
                    gridLabrador.setAdapter(adapter);

                } else {
                    alert("Erro ao carregar imagem");
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                alert("Erro ao carregar imagem");
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String url = listLabrador.get((int) id);

        detalheDialog(url);


    }

    private void detalheDialog(String urlImage) {
        //Declarando o Dialog
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.dialog_detail_img_dog, null);

        imgDog = (ImageView) mView.findViewById(R.id.dialog_img_dog);

        //Criando o Dialog de fato
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        String url = urlImage;
        Picasso.with(getActivity()).load(url).into(imgDog);

    }

    private void alert(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
