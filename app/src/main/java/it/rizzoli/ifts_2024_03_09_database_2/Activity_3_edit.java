package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

import database.DBadapter;

public class Activity_3_edit extends AppCompatActivity {

    private DBadapter dbAdapter;
    String nome;
    String url;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_edit);

        dbAdapter = new DBadapter(this);
        dbAdapter.open();

        EditText editText1 =(EditText) findViewById(R.id.act3_et1);
        EditText editText2 =(EditText) findViewById(R.id.act3_et2);
        Button button = (Button) findViewById(R.id.act3_btn1);

        boolean nuovoInsert;

        if (getIntent().getExtras()!=null) {
            Sito sito = (Sito) getIntent().getSerializableExtra("sito");
            nome = sito.getNome();
            url = sito.getUrl();
            id = sito.getId();
            editText1.setText(nome);
            editText2.setText(url);
            nuovoInsert=false;
        } else {
            editText1.setHint("Inserisci nome sito");
            editText2.setHint("Inserisci url");
            nuovoInsert = true;
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = editText1.getText().toString();
                url = editText2.getText().toString();
                if (!(url.contains("http")))
                   url =  Sito.addHttps(url);

                // se ci sono arrivato da OptionsMenu allora devo chiamare il metodo crud insertOrThrow()
                if (nuovoInsert) {
                    if (!((dbAdapter.checkIfExists(url))))
                        dbAdapter.aggiungiSito(nome, url);
                    dbAdapter.close();
                    startActivity(new Intent(Activity_3_edit.this, MainActivity.class));
                } else {
                    // in caso contrario devo chiamare il metodo crud update()
                    dbAdapter.modificaSito(id, nome, url);
                    dbAdapter.close();
                    startActivity(new Intent(Activity_3_edit.this, MainActivity.class));
                }
            }
        });





    } // fine OnCreate **************************




}