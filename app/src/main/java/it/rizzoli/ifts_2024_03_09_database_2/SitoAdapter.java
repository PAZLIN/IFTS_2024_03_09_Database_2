package it.rizzoli.ifts_2024_03_09_database_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class SitoAdapter extends ArrayAdapter<Sito>{

    private Context ctx = null;

    private int res= 0;

    public SitoAdapter(Context context, int resource, List<Sito> list) {
        super(context, resource, list);
        ctx=context;
        res=resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sito sito = getItem(position);
        View view = convertView;
        if (convertView==null) {
            view = (View) LayoutInflater.from(ctx).inflate(res, parent, false);
        }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(sito.getNome());

            return view;

    }



}
