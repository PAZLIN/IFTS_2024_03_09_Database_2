package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import database.*;

public class MainActivity extends AppCompatActivity {


    private DBadapter dbAdapter;
    private DbHelper dbHelper;
    private ArrayList<Sito> listaSiti;
    private String LOG_TAG = "CV - MainActivity";

    protected void inizializza (){
        setContentView(R.layout.activity_main);


        dbAdapter = new DBadapter(this);
        dbAdapter.open();


//        OTTENGO UN CURSOR "FILTRATO" IN BASE A UNA RAWQUERY
//        String selectQuery = "SELECT * FROM sitiweb WHERE _id BETWEEN ? AND ?";
//        Cursor cursor = dbAdapter.getFromRawQuery(selectQuery, new String[]{"14540", "15200"});

        // OTTENGO UN CURSOR (SELECT ALL) E LO UTILIZZO PER RIEMPIRE LA LISTA CON
        // TUTTO QUELLO CHE TROVA NEL DATABASE
//        Cursor cursor = dbAdapter.getAllSites();
//        listaSiti=new ArrayList<Sito>();
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBadapter.KEY_ID));
//            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_NOME));
//            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_URL));
//            listaSiti.add(new Sito(id, nome, url));
//        }
//        ListView lv = (ListView) findViewById(R.id.act1_listView1);
//        SitoAdapter adapter = new SitoAdapter(this, android.R.layout.simple_list_item_1, listaSiti);
//        lv.setAdapter(adapter);

        ListView lv = (ListView) findViewById(R.id.act1_listView1);
        SiteCursorAdapter adapter = new SiteCursorAdapter(this, dbAdapter.getAllSites());
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sito sito = (Sito) adapter.getItem(position);
                Intent activity2 = new Intent(MainActivity.this, Activity_2_browser.class);
                activity2.putExtra("sito",sito);
                startActivity(activity2);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sito sito = (Sito) adapter.getItem(position);
                Intent activity3 = new Intent(MainActivity.this, Activity_3_edit.class);
                activity3.putExtra("sito", sito);
                startActivity(activity3);
                return true;
            }
        });
        dbAdapter.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inizializza();
        Log.i(LOG_TAG, "oncreate");
    } // ***** FINE ON CREATE


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onstart");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "mi trovo nella onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        inizializza();
        Log.i(LOG_TAG, "mi trovo nella ONRESTART");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_1)
            startActivity(new Intent(MainActivity.this, Activity_3_edit.class));
        if(item.getItemId() == R.id.menu_item_2)
            startActivity(new Intent(MainActivity.this, Activity_4_showAll.class));
        if(item.getItemId() == R.id.menu_item_3)
            startActivity(new Intent(MainActivity.this, Activity_5_EsperimentiDiInserimento.class));
        return true;
    }


}