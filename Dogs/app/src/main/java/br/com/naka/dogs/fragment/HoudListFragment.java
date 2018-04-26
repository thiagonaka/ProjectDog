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

import br.com.naka.dogs.R;


public class HoudListFragment extends Fragment {

    private HoudListFragment fragment;
    private Activity context;
    private View ll;
    private int tipo;
    private FragmentActivity fa;

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


        initComponents(ll);


        return ll;
    }

    private void initComponents(View view) {


    }

}
