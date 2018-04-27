package br.com.naka.dogs.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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


public class HoudListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private HoudListFragment fragment;
    private Activity context;
    private View ll;
    private int tipo;
    private FragmentActivity fa;
    private DogsAdapter adapter;
    private List<String> listHound;
    private GridView gridHound;
    private String token;
    private ServicoParametro service;

    public static HoudListFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        HoudListFragment f = new HoudListFragment();
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
        ll = inflater.inflate(R.layout.fragment_houd_list, container, false);
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
        gridHound = (GridView) view.findViewById(R.id.fragment_hound_list_gridview);
        gridHound.setOnItemClickListener(this);

        initWebservice();
    }

    private void initWebservice(){
        token = UtilsPreferences.getToken();
        String category = "hound";

        Call<FeedResponse> feedResponseCall = service.getFeed(token,category);
        feedResponseCall.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                int statusCode = response.code();

                if (statusCode == 200){

                    FeedResponse feedResponse = response.body();
                    listHound = feedResponse.getList();

                    adapter = new DogsAdapter(context, listHound);
                    gridHound.setAdapter(adapter);

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

        String url = listHound.get((int) id);
        int posicao = position;


    }

    private void alert(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
