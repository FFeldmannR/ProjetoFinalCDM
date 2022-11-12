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
import com.feldmann.projetofinalcdm.views.CadastroActivity;
import com.feldmann.projetofinalcdm.views.ListaActivity;
//
public class LoginController implements Controller.controllerLogin{
    private Context context;
    private Controller.msg msg;
    private SQLiteDatabase sqlRead;
    //
    public LoginController(Context context, SQLiteDatabase sqlRead) {
        this.context = context;
        this.msg = new MsgController(context, this.getClass().getName().toString() );
        this.sqlRead = sqlRead;
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
        if (verificaUser(login, senha)){
            try{
                Intent toListActivity = new Intent(context, ListaActivity.class);
                toListActivity.putExtra("NOMEUSER", login );
                msg.messageToast("Bem Vindo(a) "+login );
                context.startActivity(toListActivity);
            }catch (Exception e){
                msg.logD("ERRO AO MUDAR DE ACTIVITY\n"+e.getMessage() );
            }
        }else{
            //
        }
    }//fim Login
    private boolean verificaUser(String login, String senha){
        //
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM users", null);
        //
        if (cursor.moveToFirst()){
            do {
                String dbNome = cursor.getString(1);
                String dbSenha = cursor.getString(2);
                //
                if (login.equals(dbNome) && senha.equals(dbSenha)){
                    //válido
                    cursor.close();
                    return true;
                }else{
                    //invalido
                    cursor.close();
                    return false;
                }//fim if else
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS DE USUARIOS");
            cursor.close();
            return false;
        }//fim if else
    }//fim verificaUser
    //
    @Override
    public void cadastrarUser(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastroActivity.class);
                v.getContext().startActivity(in);
            }//fim onClick
        }); //fim ClickListener
    }//fim cadastrarUser
}//fim class