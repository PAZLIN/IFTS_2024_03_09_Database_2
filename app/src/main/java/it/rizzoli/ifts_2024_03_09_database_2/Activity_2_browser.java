package it.rizzoli.ifts_2024_03_09_database_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import database.DBadapter;

public class Activity_2_browser extends AppCompatActivity {
    public DBadapter dbAdapter;
    private SQLiteDatabase database;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_browser);

        dbAdapter=new DBadapter(this);
        dbAdapter.open();

        EditText editText = (EditText) findViewById(R.id.act2_et1);
        Button btn1 = (Button) findViewById(R.id.act2_btn1);
        Button btn2 = (Button) findViewById(R.id.act2_btn2);
        Button btn3 = (Button) findViewById(R.id.act2_btn3);
        WebView wv = (WebView) findViewById(R.id.act2_wv1);

        wv.getSettings().setJavaScriptEnabled(true);
        WebViewClient x = new WebViewClient();
        wv.setWebViewClient(x);

        Sito sito = (Sito) getIntent().getSerializableExtra("sito");
        String url = sito.getUrl();
        editText.setText(sito.getUrl());
        if (url != null)
            wv.loadUrl(url);


        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");

            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL) {
                    String url = Sito.addHttps(editText.getText().toString());
                    wv.loadUrl(url);
                    hideKeyboard();
                }
                return true;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                wv.loadUrl(url);
                hideKeyboard();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_2_browser.this, MainActivity.class));
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                String nome = Sito.getNomeSito(url);
                dbAdapter.aggiungiSito(nome, url);
                Toast.makeText(Activity_2_browser.this, "AGGIUNTO", Toast.LENGTH_SHORT).show();
            }
        });


    }
// ************************* fine OnCreate

    @Override
    protected void onStop() {
        super.onStop();
        dbAdapter.close();
    }


    // nascondi soft keyboard
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


}