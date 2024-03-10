package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import database.DBadapter;

public class Activity_4_showAll extends AppCompatActivity {

    private DBadapter dBadapter;
    private Cursor cursor;

    private String LOG_TAG = "CV - Activity_4";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inizializza();
        Log.i(LOG_TAG, "ONCREATE");
    } // fine onCreate()

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "ONSTART");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "ONPAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "ONRESUME");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "ONRESTART");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "ONSTOP");

    }

    public void inizializza(){
        setContentView(R.layout.activity4_show_all);

        dBadapter=new DBadapter(this);
        dBadapter.open();
        cursor=dBadapter.ottieniSiti();

        ArrayList<Sito> lista = new ArrayList<>();

        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBadapter.KEY_ID));
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_NOME));
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_URL));
            lista.add(new Sito(id, nome, url));
        }
        cursor.close();
        //dBadapter.close();

        ListView lv = (ListView) findViewById(R.id.act4_listView);
        SitoAdapter_2 adapter = new SitoAdapter_2(this, R.layout.act4_lw, lista);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @SuppressLint("Range")
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sito sito = adapter.getItem(position);
                dBadapter.cancellaSito(sito.getId());
                lista.remove(sito);
                adapter.notifyDataSetChanged();
                return  true;

            }
        });
    }




}