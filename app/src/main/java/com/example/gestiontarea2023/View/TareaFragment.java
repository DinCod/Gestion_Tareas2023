package com.example.gestiontarea2023.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Tarea;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.DialogDatePicker;
import com.example.gestiontarea2023.Utilidades.ListAdapterTarea;
import com.example.gestiontarea2023.ViewModel.TareaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TareaFragment extends Fragment implements View.OnClickListener{
    private Tablero tablero;
    private TareaViewModel tareaViewModel;
    private Context context;
    private ListAdapterTarea listAdapterTarea;
    private TextView titulo_tablero , count_tarea;
    private FloatingActionButton btn_agregar_tarea;
    private AlertDialog dialog;
    private Tarea tarea;
    private DialogDatePicker dialogDatePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tablero = (Tablero) getArguments().getSerializable("tablero");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarea, container, false);
        dialogDatePicker = new DialogDatePicker(context);
        btn_agregar_tarea = view.findViewById(R.id.btn_agregar_tarea);
        btn_agregar_tarea.setOnClickListener(this);
        titulo_tablero = view.findViewById(R.id.titulo_tablero);
        titulo_tablero.setText("Tablero: "+tablero.getNombre_tablero());
        count_tarea = view.findViewById(R.id.count_tarea);
        tareaViewModel = new TareaViewModel(context);
        tareaViewModel.ReturnTablero(tablero);
        tareaViewModel.getListaTareaLiveData().observe(getViewLifecycleOwner(), new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {
                listAdapterTarea = new ListAdapterTarea(tareas,context);
                RecyclerView recyclerView = view.findViewById(R.id.listRecycleView_tarea);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(listAdapterTarea);
                if(listAdapterTarea.getItemCount()<=0){
                    count_tarea.setVisibility(View.VISIBLE);
                }
            }
        });
        tareaViewModel.getRegistro().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                message("Creando Tarea...");
                dialog.dismiss();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        tareaViewModel.Lista();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        tareaViewModel.Lista();
    }
    public void message(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void setTitle(String titulo){
        titulo_tablero.setText(titulo);
    }

    @Override
    public void onClick(View event_click) {
        if(btn_agregar_tarea==event_click){
            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.modal_agregar_tarea, null);
            EditText titulo_tarea      = alertLayout.findViewById(R.id.dialog_titulo_tarea);
            EditText descripcion_tarea = alertLayout.findViewById(R.id.dialog_descripcion_tarea);
            EditText fecha_vencimiento = alertLayout.findViewById(R.id.dialog_fecha_tarea);
            fecha_vencimiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogDatePicker.showModalDatePickerDialog(fecha_vencimiento);
                }
            });
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Crear Tarea");
            alert.setView(alertLayout);
            alert.setNegativeButton("Cancelar",null);
            alert.setPositiveButton( "Aceptar",null);
            dialog = alert.create();
            dialog.show();
            Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positive.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    String titulo     = titulo_tarea.getText().toString().trim();
                    String descripcion  = descripcion_tarea.getText().toString().trim();
                    String fecha_finalizacion = fecha_vencimiento.getText().toString().trim();
                    if(titulo.isEmpty() || descripcion.isEmpty() || fecha_finalizacion.isEmpty()){
                        message("Complete todos los campos.");
                    }else{
                        tarea = new Tarea(tablero.getId_tablero(), titulo, descripcion,"No iniciada",fecha_inicio(),fecha_finalizacion);
                        tareaViewModel.Registro(tarea);
                    }
                }});
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String fecha_inicio(){{
        ZoneId zona = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now(zona);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = localDate.format(f);
        return currentDate;
    }}
}

/*
@RequiresApi(api = Build.VERSION_CODES.O)
public String obtenerFechaInicio() {
    ZoneId zona = ZoneId.of("America/Lima");
    LocalDate localDate = LocalDate.now(zona);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String currentDate = localDate.format(formatter);
    return currentDate;
}
*/