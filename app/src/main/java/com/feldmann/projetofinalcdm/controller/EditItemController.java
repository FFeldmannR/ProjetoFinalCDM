package com.feldmann.projetofinalcdm.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.views.ListadeCompras;

public class EditItemController implements Controller.controllerEditItem{
    //
    @Override public void updateItem(Button btnSalvar, SQLiteDatabase sqlWrite,
                                     EditText novoNomeItem, EditText novaQuantidade,
                                     Compras objCompras) {
        Log.d("EDIT_ITEM", "antesdoBotao/SET: "+novoNomeItem.getText().toString()+" | "+novaQuantidade.getText().toString()+" WHERE "+objCompras.getDonoLista()+" | "+ objCompras.getNomeLista() );
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if ( novoNomeItem.getText().toString().equals(null) || novaQuantidade.getText().toString().equals(null) ){
                    Toast.makeText(v.getContext(), "Um dos campos está vazio", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        String nomeItem = novoNomeItem.getText().toString();
                        String qntd = novaQuantidade.getText().toString();
                        Log.d("EDIT_ITEM", "antesDoUpdateSET: "+nomeItem+" | "+qntd+" WHERE "+objCompras.getDonoLista()+" | "+ objCompras.getNomeLista() );
                        sqlWrite.execSQL(
                            "UPDATE compras"+
                            " SET nomeItem='"+nomeItem+"'"+
                            " AND quantidade='"+qntd+"'"+
                            " WHERE _id='"+objCompras.getId()+"'" );
                        Log.d("EDIT_ITEM", "depoisDoUpdateSET: "+nomeItem+" | "+qntd+" WHERE "+objCompras.getDonoLista()+" | "+ objCompras.getNomeLista() );
                        Intent in = new Intent(v.getContext(), ListadeCompras.class);
                        in.putExtra("USUARIO", objCompras.getDonoLista() );
                        in.putExtra("NOMELISTA", objCompras.getNomeLista() );
                        v.getContext().startActivity(in);
                    }catch (Exception e){ Log.d("EDIT_ITEM", "FALHA AO ATUALIZAR\n"+e.getMessage() ); }//fim try catch

                }//fim if else
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
                    //Log.d("EDIT_ITEM", "DELETE: "+novoNomeItem+" | "+novaQuantidade+" WHERE "+donoLista+" | "+nomeLista);
                }catch (Exception e){ Log.d("DELETE_ITEM", "FALHA AO DELETAR\n"+e.getMessage() ); }
            }//fim onClick
        });//fim clickListener
    }//fim metodo deleteItem
}//fim class
