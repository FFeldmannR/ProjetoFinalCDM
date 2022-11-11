package com.feldmann.projetofinalcdm.controller;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EditItemController implements Controller.controllerEditItem{
    //
    @Override public void updateItem(Button btnSalvar, SQLiteDatabase sqlWrite,
                                     String novoNomeItem, String novaQuantidade,
                                     String donoLista, String nomeLista) {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try{ sqlWrite.execSQL(
                            "UPDATE compras"+
                            " SET nomeItem='"+novoNomeItem+"'"+
                            " AND quantidade='"+novaQuantidade+"'"+
                            " WHERE donoLista='"+donoLista+"'"+
                            " AND nomeLista='"+nomeLista+"'" );
                }catch (Exception e){ Log.d("EDIT_ITEM", "FALHA AO ATUALIZAR\n"+e.getMessage() ); }//fim try catch
            }//fim onClick
        });//fim clickListener
    }//fim metodo updateItem
    //
    @Override public void deleteItem(Button btnDeletar, SQLiteDatabase sqlWrite,
                                     String donoLista, String nomeLista, String nomeItem) {
        btnDeletar.setBackgroundColor(Color.RED); //seta a cor do botao delete para vermelho
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try{ sqlWrite.execSQL(
                            "DELETE FROM compras"+
                            " WHERE donoLista='"+donoLista+"'"+
                            " AND nomeLista='"+nomeLista+"'"+
                            " AND nomeItem='"+nomeItem+"'" );
                }catch (Exception e){ Log.d("EDIT_ITEM", "FALHA AO DELETAR\n"+e.getMessage() ); }
            }//fim onClick
        });//fim clickListener
    }//fim metodo deleteItem
}//fim class
