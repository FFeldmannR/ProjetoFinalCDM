package com.feldmann.projetofinalcdm.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.feldmann.projetofinalcdm.model.Lista;
import java.util.ArrayList;
import java.util.List;

public class ListasRepository {
    private static List<Lista> listas;
    private static ListasRepository instance = null;

    public ListasRepository() {
        if (instance == null){
            listas = new ArrayList<>();
        }
    }

    public static ListasRepository getInstance(SQLiteDatabase sqlRead){
        instance = new ListasRepository();
        listas.removeAll(getListas());

        //
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                //
                listas.add( new Lista(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString(1)
                ));
                //
                Log.d("ListasRepository", ""+
                        "(" + cursor.getString(0) + ") " + cursor.getString(1)+"\n" );
                //
            }while (cursor.moveToNext());
        }else{
            Log.d("ListasRepository", "NÃO TEM REGISTROS");
        }
        //
        cursor.close();
        return instance;
    }

    private void removeFromList(){

    }
    public static List<Lista> getListas() {
        return listas;
    }
}