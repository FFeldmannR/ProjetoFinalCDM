package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
//
public class UserRepository {
    private static Controller.msg msg;
    private static DBListas db;
    //
    public UserRepository(Context context) {
        msg = new MsgController( context, this.getClass().getName().toString() );
        db = new DBListas( context );
    }//fim construtor
    public static void createUserinDB(String nomeUser, String senhaUser){
        try{
            ContentValues ctv = new ContentValues();
            ctv.put("nome", nomeUser );
            ctv.put("senha", senhaUser );
            db.getWritableDatabase().insert("users", null, ctv);
            msg.logD(nomeUser + " ADICIONADO");
        }catch (SQLException sqlE){
            msg.messageToast("ERRO AO ADICIONAR USUARIO\n"+sqlE.getMessage());
        }//fim try catch
    }//fim createUserinDB
}//fim classe
