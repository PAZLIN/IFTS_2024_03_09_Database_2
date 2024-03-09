package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import database.*;

public class MainActivity extends AppCompatActivity {

    private DBadapter dbAdapter;
    private ArrayList<Sito> listaSiti;
    private String LOG_TAG = "MainActivity STATO";

    protected void inizializza() {
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "mi trovo nella onCreate");

        dbAdapter = new DBadapter(this);
        dbAdapter.open();

        Cursor cursor = dbAdapter.ottieniSiti();

        listaSiti=new ArrayList<Sito>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBadapter.KEY_ID));
            String nome = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_NOME));
            String url = cursor.getString(cursor.getColumnIndex(DBadapter.KEY_URL));
            listaSiti.add(new Sito(id, nome, url));
        }


        ListView lv = (ListView) findViewById(R.id.act1_list1);
        SitoAdapter adapter = new SitoAdapter(this, android.R.layout.simple_list_item_1, listaSiti);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sito sito = adapter.getItem(position);
                Intent activity2 = new Intent(MainActivity.this, Activity_2_browser.class);
                activity2.putExtra("sito",sito);
                startActivity(activity2);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sito sito = adapter.getItem(position);
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
    } // ***** FINE ON CREATE


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "mi trovo nella onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "mi trovo nella onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "mi trovo nella onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "mi trovo nella onStop");
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
        return true;
    }


}