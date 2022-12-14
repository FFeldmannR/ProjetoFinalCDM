package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
//
public class CadastroActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerCadastro cadastro;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        ((FrameLayout) findViewById(R.id.FLbtnCadastroC)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cadastro.addUserToDB(
                        ((EditText) findViewById(R.id.etLoginC)).getText().toString(),
                        ((EditText) findViewById(R.id.etSenhaC)).getText().toString(),
                        ((TextView) findViewById(R.id.tvCadastroIncorreto)) );//fim addUserToDB
            }//fim onClick
        });//fim clickListener
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this );
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
        this.cadastro = new CadastroController();
    }//fim instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar() {
        setTitle("Cadastrar Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, LoginActivity.class));
            finishAffinity();
        }
        return true;
    }
    @Override public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }
}//fim class