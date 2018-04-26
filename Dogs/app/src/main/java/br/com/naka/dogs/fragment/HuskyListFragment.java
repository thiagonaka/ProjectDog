package br.com.naka.dogs.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.naka.dogs.R;

public class HuskyListFragment extends Fragment {

    private HuskyListFragment fragment;
    private Activity context;
    private View ll;
    private int tipo;
    private FragmentActivity fa;

    public static HuskyListFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        HuskyListFragment f = new HuskyListFragment();
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
        ll = inflater.inflate(R.layout.fragment_husky_list, container, false);
        context = getActivity();


        initComponents(ll);


        return ll;
    }

    private void initComponents(View view) {


    }
}
