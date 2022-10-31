package com.feldmann.projetofinalcdm.controller;

import android.app.Activity;
import android.content.Context;

public class Controller {
    public interface view{
        public Activity getActivity();
        public Context getContext();
    }
    public interface msg{
        public void messageToast(String msg);
        public void logD(String msg);
    }
}
