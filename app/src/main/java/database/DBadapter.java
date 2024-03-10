package database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public SQLiteDatabase getDB(){
        return database;
    }


   private ContentValues creaRecord(String nome, String url){
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_URL, url);
        return values;
    }


    public long addSite(String nome, String url){
        ContentValues initialValues = creaRecord(nome, url);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    // modifica un sito web esistente
    public boolean updateSite(long sitoID, String nome, String url){
        ContentValues updateValues = creaRecord(nome, url);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + sitoID, null) >0;
    }

    // cancella sito
    public int deleteSite(long sitoID){
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + sitoID, null);
    }

    // recupera tutti i dati
    public Cursor getAllSites(){
        return database.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NOME, KEY_URL}, null, null, null, null, null);
    }

    // recupera i dati con rawQuery
    public Cursor getFromRawQuery (String rawQuery, String[]args){
        return database.rawQuery(rawQuery, args);
    }

    // controlla se l'url è già presente nel db
    public boolean checkIfUrlExists(String currentUrl){
        Cursor cursor = getAllSites();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(this.KEY_URL));
            if (url.equals(currentUrl)) return true;
        }
        return false;
    }








}
