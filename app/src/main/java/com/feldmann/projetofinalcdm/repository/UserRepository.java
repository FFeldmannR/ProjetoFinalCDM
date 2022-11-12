package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
//
public class UserRepository {
    private static UserRepository instance = null;
    private static Controller.msg msg;
    private static DBListas db;
    //
    public UserRepository(Context context) {
        msg = new MsgController( context, this.getClass().getName() );
        db = new DBListas( context );
    }//fim construtor
    public static UserRepository getInstance(Context context) {
        instance = new UserRepository( context );
        return instance;
    }//fim getInstance
    public static void createUserinDB(String nomeUser, String senhaUser){
        try{
            ContentValues ctv = new ContentValues();
            ctv.put("nome", nomeUser );
            ctv.put("senha", senhaUser );
            db.getWritableDatabase().insert("users", null, ctv);
            msg.messageToast(nomeUser + " ADICIONADO");
        }catch (SQLException sqlE){
            msg.logD("ERRO AO ADICIONAR USUARIO\n"+sqlE.getMessage());
        }//fim try catch
    }//fim createUserinDB
}//fim classe
