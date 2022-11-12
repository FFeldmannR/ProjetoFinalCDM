package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
//
public class CadastrarItemActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerCadastro cadastro;
    private String nomeLista, usuarioLogado;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);
        this.instanceController();
        msg.logD("onCreate");
    }
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        usuarioLogado = getIntent().getStringExtra("USUARIOLOGADO");
        nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle("Adicionar item em "+nomeLista);
        ((Button) findViewById(R.id.btnSalvar)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cadastro.addItemToDB(
                        usuarioLogado, nomeLista,
                        ((EditText) findViewById(R.id.etNomeItem)).getText().toString(),
                        ((EditText) findViewById(R.id.etQuantidade)).getText().toString(),
                        0 );
            }//fim onClick
        });//fim clickListener
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.cadastro = new CadastroController( view.getContext() );
    }
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                try {
                    Intent intent = new Intent(this, ListadeCompras.class);
                    intent.putExtra("NOMELISTA", nomeLista);
                    startActivity(intent);
                    msg.logD("nova activity iniciada: ListadeCompras");
                    finishAffinity();
                    break;
                }catch (Exception e){ msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage()); }//fim try catch
        }//fim switch
        return true;
    }//fim onOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, ListadeCompras.class);
            intent.putExtra("NOMELISTA", nomeLista);
            startActivity(intent);
            msg.logD("nova activity iniciada: ListadeCompras");
            finishAffinity();
        }catch (Exception e){ msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage()); }
    }//fim onBackPressed
}//fim CadastrarItemActivity