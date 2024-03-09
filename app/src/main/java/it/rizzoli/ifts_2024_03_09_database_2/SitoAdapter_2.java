package it.rizzoli.ifts_2024_03_09_database_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SitoAdapter_2 extends ArrayAdapter<Sito> {

    Context ctx = null;
    int res = 0;


    public SitoAdapter_2(Context context, int resource, List<Sito> lista) {
        super(context, resource, lista);
        this.ctx = context;
        res= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sito sito = (Sito) getItem(position);
        View view = convertView;
        if (convertView == null) {
            view = (View) LayoutInflater.from(ctx).inflate(res, parent, false);
        }
        ((TextView) view.findViewById(R.id.act4_lw_id)).setText(String.valueOf(sito.getId()));
        ((TextView) view.findViewById(R.id.act4_lw_nome)).setText(sito.getNome());
        ((TextView) view.findViewById(R.id.act4_lw_url)).setText(sito.getUrl());

        return view;

    }
}
