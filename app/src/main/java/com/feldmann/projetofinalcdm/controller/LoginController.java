package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.views.CadastroActivity;
import com.feldmann.projetofinalcdm.views.ListaActivity;
//
public class LoginController implements Controller.controllerLogin{
    private Context context;
    private DBListas db;
    private static Controller.msg msg;
    //
    public LoginController(Context context) {
        this.context = context;
        this.db = new DBListas( context );
        this.msg = new MsgController(context, this.getClass().getName().toString() );
    }//fim contrutor
    @Override public void setLoginField(String nomeUser, EditText etLoginL) {
        if (nomeUser != null){
            etLoginL.setText(nomeUser);
        }
    }//fim setLoginField
    @Override public void setTvEMS(TextView tvEMS) { //EMS = Esqueci Minha Senha
        tvEMS.setTextColor(Color.BLUE);
        tvEMS.setTypeface(null, Typeface.BOLD);
        tvEMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.messageToast("btn EMS clicado :D");
            }//fim onClick
        });//fim clickListener
    }//fim setTvEMS
    @Override public void Login(String login, String senha) {
        verificaUser(login, senha);
    }//fim Login
    private void verificaUser(String login, String senha){
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM users", null);
        //
        if (cursor.moveToFirst()){
            do {
                if (login.equals(cursor.getString(1)) && senha.equals(cursor.getString(2))){
                    //válido
                    Intent toListActivity = new Intent(context, ListaActivity.class);
                    toListActivity.putExtra("NOMEUSER", login );
                    msg.messageToast("Bem Vindo(a) "+login );
                    context.startActivity(toListActivity);
                    break;
                }
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS DE USUARIOS");
        }//fim if else
        cursor.close();
    }//fim verificaUser
    @Override public void cadastrarUser(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastroActivity.class);
                v.getContext().startActivity(in);
            }//fim onClick
        }); //fim ClickListener
    }//fim cadastrarUser
}//fim class