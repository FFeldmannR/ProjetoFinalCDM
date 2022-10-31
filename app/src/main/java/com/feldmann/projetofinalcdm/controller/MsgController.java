package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MsgController implements Controller.msg{
    private final Context context;
    private final String tag;

    public MsgController(Context context, String tag) {
        this.context = context;
        this.tag = tag;
    }

    @Override
    public void messageToast(String msg) { Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); }
    @Override
    public void logD(String msg) { Log.d(tag, msg); }
}
