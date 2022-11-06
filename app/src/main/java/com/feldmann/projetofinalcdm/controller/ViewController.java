package com.feldmann.projetofinalcdm.controller;
//
import android.app.Activity;
import android.content.Context;
//
public class ViewController implements Controller.view{
    private Context context;
    private Activity activity;
    //
    public ViewController(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    //
    @Override
    public Activity getActivity() {
        return this.activity;
    }
    @Override
    public Context getContext() {
        return this.context;
    }
}// fim class