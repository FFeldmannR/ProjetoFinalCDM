package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.LoginController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.model.User;
import com.feldmann.projetofinalcdm.repository.DBListas;
//
public class LoginActivity extends AppCompatActivity {
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    private Controller.controllerLogin login;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    //
    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        //
        this.setLogin(getIntent().getStringExtra("NOMEUSER"));
        login.Login(
                (Button) findViewById(R.id.btnEntrar),
                (EditText) findViewById(R.id.etLoginL),
                (EditText) findViewById(R.id.etSenhaL)
        );
        login.cadastrarUser((Button) findViewById(R.id.btnCadastrarL));
    }//fim onResume
    //
    private void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
        db = new DBListas(view.getContext());
        this.login = new LoginController(view.getContext(), db.getReadableDatabase());
    }//fim instanceController
    //
    private void setLogin(String nomeUser){
        if (nomeUser != null){
            ((EditText) findViewById(R.id.etLoginL)).setText(nomeUser);
        }
    }
}//fim class