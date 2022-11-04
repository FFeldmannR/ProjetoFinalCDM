package com.feldmann.projetofinalcdm.views;
//
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
//
public class CadastroActivity extends AppCompatActivity {
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    private Controller.controllerCadastro cadastro;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate
    //
    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        cadastro.getCampos((EditText) findViewById(R.id.etLoginC), (EditText) findViewById(R.id.etSenhaC));
        cadastro.addToDB((Button) findViewById(R.id.btnCadastrarC), db.getWritableDatabase());
    }//fim onResume
    //
    private void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
        this.cadastro = new CadastroController(view.getContext());
    }//fim instanceController
}//fim class