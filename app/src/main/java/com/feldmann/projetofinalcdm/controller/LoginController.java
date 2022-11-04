package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.views.CadastroActivity;
import com.feldmann.projetofinalcdm.views.ListaActivity;

public class LoginController implements Controller.controllerLogin{
    private Controller.msg msg;
    private SQLiteDatabase sqlRead;

    public LoginController(Context context, SQLiteDatabase sqlRead) {
        this.msg = new MsgController(context, this.getClass().getName().toString() );
        this.sqlRead = sqlRead;
    }

    @Override
    public void Login(Button btn, EditText etLogin, EditText etSenha) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                verificaUser(v.getContext(), etLogin, etSenha);
            }
        });
    }
    private void verificaUser(Context context, EditText login, EditText senha){
        String etNome = login.getText().toString();
        String etSenha = senha.getText().toString();
        //
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM users", null);

        if (cursor.moveToFirst()){
            do {
                String dbNome = cursor.getString(1);
                String dbSenha = cursor.getString(2);
                //
                if (etNome.equals(dbNome) && etSenha.equals(dbSenha)){
                    //válido
                    msg.messageToast("Bem Vindo(a) "+dbNome);
                    Intent toListActivity = new Intent(context, ListaActivity.class);
                    toListActivity.putExtra("NOMEUSER", dbNome);
                    context.startActivity(toListActivity);
                }else{
                    //inválido
                    msg.messageToast("USUARIO INVÁLIDO\nTENTE NOVAMENTE");
                    msg.logD(""+
                            "etNome: "+etNome+" | etSenha: "+etSenha+
                            "\ndbNome: "+dbNome+" | dbSenha: "+dbSenha);
                }
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS");
        }
    }

    @Override
    public void cadastrarUser(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastroActivity.class);
                v.getContext().startActivity(in);
            }
        });
    }

}