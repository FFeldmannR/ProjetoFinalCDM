package com.feldmann.projetofinalcdm.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.feldmann.projetofinalcdm.model.Lista;

import java.util.ArrayList;
import java.util.List;

public class ItemListRepository {
    private static List<Lista> itemList;
    private static ItemListRepository instance = null;

    public ItemListRepository() {
        if (instance == null){
            itemList = new ArrayList<>();
        }
    }

    public static ItemListRepository getInstance(SQLiteDatabase sqlRead){
        instance = new ItemListRepository();
        itemList.removeAll(getItemList());

        //
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM itemList", null);
        if (cursor.moveToFirst()){
            do {
                //
                itemList.add( new Lista(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString(1)
                ));
                //
                Log.d("ItemListRepository", ""+
                        "(" + cursor.getString(0) + ") " + cursor.getString(1)+"\n" );
                //
            }while (cursor.moveToNext());
        }else{
            Log.d("ItemListRepository", "NÃO TEM REGISTROS");
        }
        //
        cursor.close();
        return instance;
    }

    private void removeFromList(){

    }
    public static List<Lista> getItemList() {
        return itemList;
    }
}
