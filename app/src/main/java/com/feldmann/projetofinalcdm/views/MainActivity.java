package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
//
public class MainActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.instanceController();
        msg.logD("onCreate");
        view.getContext().startActivity( new Intent ( view.getContext(), LoginActivity.class ) );
    }//fim onCreate
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController(){
        this.view = new ViewController(this );
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
    }//fim instanceController
}//fim class