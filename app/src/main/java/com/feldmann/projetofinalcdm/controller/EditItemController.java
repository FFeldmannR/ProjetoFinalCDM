package com.feldmann.projetofinalcdm.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EditItemController implements Controller.controllerEditItem{
    //
    @Override public void updateItem(SQLiteDatabase sqlWrite, String novoNomeItem,
                           String novaQuantidade, String donoLista,
                           String nomeLista) {
        try{
            sqlWrite.execSQL(
                    "UPDATE compras"+
                    " SET nomeItem='"+novoNomeItem+"'"+
                    " AND quantidade='"+novaQuantidade+"'"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'" );
        }catch (Exception e){
            Log.d("EDIT_ITEM", "FALHA AO ATUALIZAR\n"+e.getMessage() );
        }
    }//fim metodo updateItem
    //
    @Override public void deleteItem(SQLiteDatabase sqlWrite, String donoLista,
                           String nomeLista, String nomeItem) {
        try{
            sqlWrite.execSQL(
                    "DELETE FROM compras"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'"+
                    " AND nomeItem='"+nomeItem+"'" );
        }catch (Exception e){
            Log.d("EDIT_ITEM", "FALHA AO DELETAR\n"+e.getMessage() );
        }
    }//fim metodo deleteItem
}//fim class
