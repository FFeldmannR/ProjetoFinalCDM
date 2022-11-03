package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.ListasRepository;

public class MainActivity extends AppCompatActivity{
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.instanceController();
        msg.logD("onCreate");
        //carregar banco de dados
        DBListas db = new DBListas(view.getContext());
        try {
            ListasRepository.getInstance(db.getReadableDatabase());
            msg.logD("INSTANCIADO COM SUCESSO");
            Intent in = new Intent(view.getActivity(), ListasActivity.class);
            view.getActivity().startActivity(in);
        }catch (Exception e){
            msg.logD("!!! ERRO !!! : "+e.getMessage() );
            msg.messageToast("ERRO NA INSTANCIA");
            System.exit(0);
        }
        //
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
    }

    private void instanceController(){
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
    }
}