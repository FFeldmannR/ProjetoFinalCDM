package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.views.ListadeCompras;

import java.util.ArrayList;
import java.util.List;
//
public class ComprasRepository {
    private static ComprasRepository instance = null;
    private static List<Compras> compras;
    private static Controller.msg msg;
    private static DBListas db;
    //
    public ComprasRepository(Context contexto) {
        this.msg = new MsgController(contexto, this.getClass().getName() );
        this.db = new DBListas(contexto);
        if (instance == null){ compras = new ArrayList<>(); }
    }//fim construtor
    public static ComprasRepository getInstanceCompras(Context context, String nomeLista, String usuarioLogado){
        instance = new ComprasRepository(context);
        //
        Cursor cursor = db.getWritableDatabase().rawQuery("SELECT * FROM compras", null);
        if (cursor.moveToFirst()){
            do {
                //verifica qual a lista atual, para preencher o
                // arraylist apenas com as compras dessa lista
                int idDB = Integer.parseInt( cursor.getString(0) );
                String donoListaDB = cursor.getString(1);
                String nomeListaDB = cursor.getString(2);
                String nomeItemDB = cursor.getString(3);
                String quantidadeDB = cursor.getString(4);
                int completedDB = Integer.parseInt( cursor.getString(5) );
                if ( nomeLista.equals( nomeListaDB ) && usuarioLogado.equals( donoListaDB ) ){
                    compras.add(new Compras(
                            idDB,           // _id (INTEGER)
                            donoListaDB,    // donoLista (TEXT)
                            nomeListaDB,    // nomeLista (TEXT)
                            nomeItemDB,     // nomeItem (TEXT)
                            quantidadeDB,   // quantidade (TEXT)
                            completedDB     // completed (INTEGER)
                            )
                    );//fim compras.add
                }//fim if
            }while (cursor.moveToNext());
        }else{ msg.logD("NÃO TEM REGISTROS NA TABELA listas"); }
        cursor.close();
        return instance;
    }
    public static List<Compras> getCompras() {
        return compras;
    }
    //
    public static void createItem(String usuarioLogado,
                                  String nomeLista, String nomeItem,
                                  String qntdItem, int completed){
        if ( nomeItem.equals("") ){
            msg.messageToast("DIGITE NOME DO ITEM");
        }else{
            try{
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", usuarioLogado);
                ctv.put("nomeLista", nomeLista);
                ctv.put("nomeItem", nomeItem );
                ctv.put("quantidade", qntdItem );
                ctv.put("completed", completed);
                db.getWritableDatabase().insert("compras", null, ctv);
            }catch (SQLException sqlE){ msg.logD("ERRO AO CRIAR ITEM\n"+sqlE.getMessage() ); }//fim try catch
        }//fim if else
    }//fim createItem
    public static void updateItem(String novoNomeItem, String novaQuantidade, int idItem){
        try{
            db.getWritableDatabase().execSQL(
                    "UPDATE compras"+
                    " SET nomeItem='"+novoNomeItem+"'"+
                    " , quantidade='"+novaQuantidade+"'"+
                    " WHERE _id='"+idItem+"'" );
        }catch (SQLException sqlE){ msg.logD("ERRO AO ATUALIZAR\n"+sqlE.getMessage() ); }
    }
    public static void deleteItem(Compras compras) {
        try{
            db.getWritableDatabase().execSQL(
                "DELETE FROM compras"+
                " WHERE donoLista='"+compras.getDonoLista()+"'"+
                " AND nomeLista='"+compras.getNomeLista()+"'"+
                " AND nomeItem='"+compras.getNomeItem()+"'" );
            msg.logD("SUCESSO AO DELETAR");
        }catch (SQLException sqlE){ msg.logD("ERRO AO DELETAR\n"+sqlE.getMessage() ); }
    }
}//fim classe
