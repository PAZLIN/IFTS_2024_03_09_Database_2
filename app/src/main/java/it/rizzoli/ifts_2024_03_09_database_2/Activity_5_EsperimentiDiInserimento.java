package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.DBadapter;

public class Activity_5_EsperimentiDiInserimento extends AppCompatActivity {

    private DBadapter dBadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_esperimenti_di_inserimento);

        dBadapter = new DBadapter(this);
        dBadapter.open();


        EditText et = (EditText) findViewById(R.id.act5_et1);
        Button btn1 = (Button) findViewById(R.id.act5_btn1);
        Button btn2 = (Button) findViewById(R.id.act5_btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserimentoLento(Integer.valueOf(et.getText().toString()));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserimentoVeloce(Integer.valueOf(et.getText().toString()));
            }
        });


    }// fine onCreate


    public void inserimentoLento(int quanteVolte) {
        for (int i = 0; i < quanteVolte; i++) {
            Sito sito = new Sito("NOME: " + i, "URL: " + i);
            dBadapter.addSite(sito.getNome(), sito.getUrl());

        }
        dBadapter.close();
        startActivity(new Intent(Activity_5_EsperimentiDiInserimento.this, MainActivity.class));
    }

    public void inserimentoVeloce(int quanteVolte){
        String sql = "INSERT OR REPLACE INTO sitiweb (nome, url) VALUES (?, ?)";
        SQLiteDatabase db = dBadapter.getDB();
        db.beginTransactionNonExclusive();
        SQLiteStatement stmt = db.compileStatement(sql);
        for (int i = 0; i<quanteVolte; i++){
            stmt.bindString(1, "Nome: " + i);
            stmt.bindString(2, "URL:" + i);
            stmt.execute();
            stmt.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        startActivity(new Intent(Activity_5_EsperimentiDiInserimento.this, MainActivity.class));
    }


}