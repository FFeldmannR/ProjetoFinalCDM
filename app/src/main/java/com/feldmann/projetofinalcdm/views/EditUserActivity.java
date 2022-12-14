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
    private UserController user;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        this.instanceController();
        msg.logD("onCreate");
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
                user.updateUser ( usuarioLogado,
                        ((EditText) findViewById(R.id.etNomeUserEdit)).getText().toString(),
                        ((TextView) findViewById(R.id.tvNomeUserErrado)) );
            }
        });
        ((FrameLayout) findViewById(R.id.FLbtnDeleteUser)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                msg.logD("Click no botao deletar usuario");
                user.deleteUser ( usuarioLogado );
            }
        });
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    @Override public void instanceController() {
        this.view = new ViewController (this );
        this.msg = new MsgController ( view.getContext(), this.getClass().getName() );
        this.user = new UserController ( view.getContext() );
    }//fim instanceController
    // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(String title){
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity(intent);
            finish();
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