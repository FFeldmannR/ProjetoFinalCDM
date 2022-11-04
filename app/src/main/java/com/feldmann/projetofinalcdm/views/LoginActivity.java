package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.LoginController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;

public class LoginActivity extends AppCompatActivity {
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerLogin login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.instanceController();
        msg.logD("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        login.cadastrarUser((Button) findViewById(R.id.btnCadastrarL));
    }

    private void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
        this.login = new LoginController(msg);
    }
}