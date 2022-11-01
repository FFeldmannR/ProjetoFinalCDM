package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;

public class MainActivity extends AppCompatActivity{
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
        msg.logD("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
    }
}