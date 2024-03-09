package database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBadapter {


    private Context ctx;
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    // Database field
    private static final String DATABASE_TABLE = "sitiweb";
    public static final String KEY_ID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_URL = "url";


    public DBadapter(Context ctx){
        this.ctx=ctx;
    }


    public DBadapter open(){
        dbHelper = new DbHelper(ctx);
        database = dbHelper.getWritableDatabase();
        return this;
    }



    public void close(){
        dbHelper.close();
    }
   private ContentValues creaRecord(String nome, String url){
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_URL, url);
        return values;
    }


    public long aggiungiSito (String nome, String url){
        ContentValues initialValues = creaRecord(nome, url);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    // modifica un sito web esistente
    public boolean modificaSito(long sitoID, String nome, String url){
        ContentValues updateValues = creaRecord(nome, url);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + sitoID, null) >0;
    }

    // cancella sito
    public boolean cancellaSito (long sitoID){
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + sitoID, null)>0;
    }

    // recupera tutti i dati
    public Cursor ottieniSiti(){
        return database.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NOME, KEY_URL}, null, null, null, null, null);
    }

    // controlla se l'url è già presente nel db
    public boolean checkIfExists(String currentUrl){
        Cursor cursor = ottieniSiti();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(this.KEY_URL));
            if (url.equals(currentUrl)) return true;
        }
        return false;
    }








}
