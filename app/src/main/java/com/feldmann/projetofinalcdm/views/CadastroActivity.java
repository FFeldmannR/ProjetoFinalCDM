package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.CadastroController;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
//
public class CadastroActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    private Controller.controllerCadastro cadastro;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate
    //
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        cadastro.getCampos((EditText) findViewById(R.id.etLoginC), (EditText) findViewById(R.id.etSenhaC));
        cadastro.addUserToDB((Button) findViewById(R.id.btnCadastrarC), db.getWritableDatabase());
    }//fim onResume
    //
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    //
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
        this.cadastro = new CadastroController(view.getContext());
    }//fim instanceController
    //
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, LoginActivity.class) );
                finishAffinity();
                break;
        }
        return true;
    }
    @Override public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
        return;
    }
}//fim class