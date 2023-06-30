package com.example.gestiontarea2023.Utilidades;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress {
    private ProgressDialog loadingBar;
    private Context context;

    public Progress(Context context){
        this.context = context;
    }

    public  void mostarDialogo(String mensaje){
        loadingBar=new ProgressDialog(context);
        loadingBar.setMessage(mensaje);
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
    }

    public void cerrarDialogo(){
        loadingBar.dismiss();
    }
}
