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
public class EditUserActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Controller.msg msg;
    private Controller.view view;
    private String usuarioLogado;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        msg.logD("onCreate");
        this.instanceController();
        usuarioLogado = getIntent().getStringExtra("USUARIOLOGADO");
    }
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar("Editar usuario: "+usuarioLogado );
        EditText etNomeUser = ((EditText) findViewById(R.id.etNomeUserEdit));
        etNomeUser.setText( usuarioLogado );
        //
        ((FrameLayout) findViewById(R.id.FLbtnSalvarUser)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                msg.logD("Click no botao salvar usuario");
                //
            }
        });
        ((FrameLayout) findViewById(R.id.FLbtnDeleteUser)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                msg.logD("Click no botao deletar usuario");
                //
            }
        });
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController( view.getContext(), this.getClass().getName() );
    }//fim instanceController
    // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(String title){
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ) {
            case android.R.id.home:
                Intent intent = new Intent(this, ListaActivity.class);
                intent.putExtra("NOMEUSER", usuarioLogado);
                startActivity( intent );
                finish();
                break;
        }//fim switch
        return true;
    }//fimonOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }//fim try catch
    }//fim onBackPressed
}//fim class