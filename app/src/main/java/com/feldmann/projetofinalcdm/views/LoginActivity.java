package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.UserRepository;

//
public class LoginActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    private Controller.controllerLogin login;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        //
        UserRepository.getInstance( view.getContext() );
        //
        login.setLoginField(getIntent().getStringExtra("NOMEUSER"), ((EditText) findViewById(R.id.etLoginL)));
        ((Button) findViewById(R.id.btnEntrar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.Login(
                        ((EditText) findViewById(R.id.etLoginL)).getText().toString(),
                        ((EditText) findViewById(R.id.etSenhaL)).getText().toString()
                );//fim Login
            }//fim onClick
        });//fim clickListener
        login.cadastrarUser((Button) findViewById(R.id.btnCadastrarL));
        login.setTvEMS(((TextView) findViewById(R.id.tvEMS)));
        //
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    //
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
        db = new DBListas(view.getContext());
        this.login = new LoginController(view.getContext(), db.getReadableDatabase());
    }//fim instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finishAffinity();
                break;
        }//fim switch
        return true;
    }//fim onOptionsItemSelected
    @Override public void onBackPressed() {
        finishAffinity();
    }
}//fim class