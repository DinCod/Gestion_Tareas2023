package com.example.gestiontarea2023.Utilidades;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class DialogDatePicker {
    Context context;

    public DialogDatePicker(Context context){
         this.context = context;
    }

    public void showModalDatePickerDialog(EditText editText){
        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anio, int mes, int dia) {
                editText.setText(anio + "-" + (mes + 1) + "-" + dia);
            }},anio, mes, dia);
        datePickerDialog.show();
    }
}
