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
        addItemToDB(
                db.getWritableDatabase(),
                (Button) findViewById(R.id.btnSalvar),
                nomeLista,
                (EditText) findViewById(R.id.etNomeItem),
                (EditText) findViewById(R.id.etQuantidade)
        );
    }
    //
    private void addItemToDB(SQLiteDatabase sqlWrite, Button btnSalvar, String nomeLista, EditText etNomeItem, EditText etQntdItem){
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ContentValues ctv = new ContentValues();
                ctv.put("nomeLista", nomeLista);
                ctv.put("nomeItem", etNomeItem.getText().toString() );
                ctv.put("quantidade", etQntdItem.getText().toString() );
                ctv.put("completed", "0");
                sqlWrite.insert("compras", null, ctv);
                //
                Intent in = new Intent(v.getContext(), ListadeCompras.class);
                in.putExtra("NOMELISTA", nomeLista);
                v.getContext().startActivity(in);
            }
        });
    }
    @Override
    public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
    }
}