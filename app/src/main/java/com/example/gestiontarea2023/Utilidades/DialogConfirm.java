package com.example.gestiontarea2023.Utilidades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
public class DialogConfirm {
    public interface ConfirmDialogListener {
        void onConfirm();
        void onCancel();
    }
    public static void showConfirmDialog(Context context, String title, String message, ConfirmDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onConfirm();
                    }
                }
            })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onCancel();
                    }
                }
            })
            .setCancelable(false)
            .create()
            .show();
    }
}
