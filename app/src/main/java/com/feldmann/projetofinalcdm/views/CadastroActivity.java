package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.CadastroController;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;

public class CadastroActivity extends AppCompatActivity {
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    private Controller.controllerCadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        cadastro.getCampos((EditText) findViewById(R.id.etLoginC), (EditText) findViewById(R.id.etSenhaC));
        cadastro.addToDB((Button) findViewById(R.id.btnCadastrarC), db.getWritableDatabase());
    }

    private void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
        this.cadastro = new CadastroController(msg);
    }
}