package br.com.naka.dogs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.naka.dogs.R;

public class DogsAdapter extends ArrayAdapter<String>{

    private Context context;
    private List<String> listDogs;

    public DogsAdapter(Context context, List<String> listDogs) {
        super(context, R.layout.adapter_dogs, listDogs);
        this.listDogs = listDogs;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String db = listDogs.get(position);

        DogsAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            //METODO VIEW HOLDER
            viewHolder = new DogsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_dogs, parent, false);

            //PEGANDO TEXTVIEW PELO ID
            viewHolder.imgDogs = (ImageView) convertView.findViewById(R.id.adapter_dogs_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DogsAdapter.ViewHolder) convertView.getTag();
        }

        String url = db;
        Picasso.with(context).load(url).fit().into(viewHolder.imgDogs);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgDogs;

    }
}
