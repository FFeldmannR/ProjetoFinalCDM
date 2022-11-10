package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.LoginController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
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
    //
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");

        ((EditText) findViewById(R.id.etLoginL)).setText("fabio");
        ((EditText) findViewById(R.id.etSenhaL)).setText("111");

        this.toolBar();
        //
        login.setLoginField(getIntent().getStringExtra("NOMEUSER"), ((EditText) findViewById(R.id.etLoginL)));
        login.Login(
                (Button) findViewById(R.id.btnEntrar),
                (EditText) findViewById(R.id.etLoginL),
                (EditText) findViewById(R.id.etSenhaL)
        );
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
    //
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    //
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finishAffinity();
                break;
        }
        return true;
    }
    @Override public void onBackPressed() {
        finishAffinity();
    }
}//fim class