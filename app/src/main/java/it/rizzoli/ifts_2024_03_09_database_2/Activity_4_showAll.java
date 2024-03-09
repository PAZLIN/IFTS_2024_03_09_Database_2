package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import database.DBadapter;

public class Activity_4_showAll extends AppCompatActivity {

    private DBadapter dBadapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_show_all);

        dBadapter=new DBadapter(this);
        dBadapter.open();
        cursor=dBadapter.ottieniSiti();

        ArrayList<Sito> lista = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DBadapter.KEY_ID));
            String nome = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_NOME));
            String url = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_URL));
            lista.add(new Sito(id, nome, url));
        }

        ListView lv = (ListView) findViewById(R.id.act4_listView);
        SitoAdapter_2 adapter = new SitoAdapter_2(this, R.layout.act4_lw, lista);
        lv.setAdapter(adapter);




    }


}