package it.rizzoli.ifts_2024_03_09_database_2;

import android.os.Parcel;
import android.os.Parcelable;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Sito implements Serializable {

    private int id;
    private String nome;
    private String url;


    public Sito(int id, String nome, String url) {
        this.id = id;
        this.nome = nome;
        this.url = url;
    }
    public Sito(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static String addHttps(String sito) {
        sito = "https://" + sito;
        return sito;
    }

    public static String getNomeSito(String url) {
        int start = url.indexOf('.');
        int end = url.lastIndexOf('.');
        return url.substring(start + 1, end);
    }

}
