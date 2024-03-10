package database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import it.rizzoli.ifts_2024_03_09_database_2.R;
import it.rizzoli.ifts_2024_03_09_database_2.Sito;

public class SiteCursorAdapter extends CursorAdapter {

    public SiteCursorAdapter(Context ctx, Cursor cursor) {
        super(ctx, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.act4_lw, null);
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View resultView = inflater.inflate(R.layout.act4_lw, parent, false);
//        return resultView;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewId = (TextView) view.findViewById(R.id.act4_lw_id);
        TextView textViewNome = (TextView) view.findViewById(R.id.act4_lw_nome);
        TextView textViewUrl = (TextView) view.findViewById(R.id.act4_lw_url);

        textViewId.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(DBadapter.KEY_ID))));
        textViewNome.setText(cursor.getString(cursor.getColumnIndex(DBadapter.KEY_NOME)));
        textViewUrl.setText(cursor.getString(cursor.getColumnIndex(DBadapter.KEY_URL)));

    }


    @Override
    public Sito getItem(int position) {
        Cursor c = getCursor();
        if (c.moveToPosition(position)) {
            int id = c.getInt(0);
            String nome = c.getString(1);
            String url = c.getString(2);
            return new Sito(id, nome, url);
        }
        return null;
    }
}
