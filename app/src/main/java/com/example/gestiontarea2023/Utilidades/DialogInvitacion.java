package com.example.gestiontarea2023.Utilidades;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gestiontarea2023.R;

public class DialogInvitacion {

    private Dialog dialog;
    private EditText slt_usuario;
    private String[] opciones  = {"Invitación a colaborar", "invitación a ver"};
    private AutoCompleteTextView items;
    private ArrayAdapter<String> Items_Select;
    private Button btn_agregar;

    public String getSelectedItem() {
        return items.getText().toString();
    }
    public DialogInvitacion(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_detalle_invitar_usuario);
        slt_usuario = dialog.findViewById(R.id.slt_usuario);
        items = dialog.findViewById(R.id.select_invitacion);
        Items_Select    =  new ArrayAdapter<String>(context,R.layout.select_invitacion,opciones);
        items.setAdapter(Items_Select);
        btn_agregar = dialog.findViewById(R.id.btn_agregar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void show(String correo, View.OnClickListener agregarClickListener) {
        slt_usuario.setText(correo);
        btn_agregar.setOnClickListener(agregarClickListener);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void dismiss() {
        dialog.dismiss();
    }

}
