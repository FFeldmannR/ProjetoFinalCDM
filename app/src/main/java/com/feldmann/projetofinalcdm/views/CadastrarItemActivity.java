package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.ComprasRepository;
import com.feldmann.projetofinalcdm.repository.DBListas;
//
public class CadastrarItemActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }
    //
    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        String nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle("Adicionar item em "+nomeLista);
        ComprasRepository.addItemToDB(
                db.getWritableDatabase(),
                (Button) findViewById(R.id.btnSalvar),
                nomeLista,
                (EditText) findViewById(R.id.etNomeItem),
                (EditText) findViewById(R.id.etQuantidade),
                0
        );
    }
    //
    @Override
    public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
    }
}