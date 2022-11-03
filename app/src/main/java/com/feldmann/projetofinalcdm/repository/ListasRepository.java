package com.feldmann.projetofinalcdm.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.feldmann.projetofinalcdm.model.Lista;
import java.util.ArrayList;
import java.util.List;

public class ListasRepository {
    private List<Lista> listas;
    private static ListasRepository instance = null;

    public ListasRepository() {
        if (instance == null){
            listas = new ArrayList<>();
        }
    }

    public static ListasRepository getInstance(SQLiteDatabase sqlRead){
        instance = new ListasRepository();
        //
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                //
                this.listas.add( new Lista(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString(1)
                ));
                //
                Log.d("ListasRepository", ""+
                        "(" + cursor.getString(0) + ") " + cursor.getString(1) );
                //
            }while (cursor.moveToNext());
        }else{
            Log.d("ContatoRepository", "NÃO TEM REGISTROS");
        }
        //
        return instance;
    }

    public List<Lista> getListas() {
        return listas;
    }

    private void SelectSQL(SQLiteDatabase sqlRead){
        //

    }
}