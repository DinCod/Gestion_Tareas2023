package com.example.gestiontarea2023.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Comprobacion;
import com.example.gestiontarea2023.Model.Tarea;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.DialogConfirm;
import com.example.gestiontarea2023.Utilidades.DialogDatePicker;
import com.example.gestiontarea2023.ViewModel.ComprobacionViewModel;
import com.example.gestiontarea2023.ViewModel.TareaViewModel;

public class DetalleTareaActivity extends AppCompatActivity implements View.OnClickListener{

    private Tarea tarea;
    private Comprobacion comprobacion;
    private String[] estado_Tarea  = {"No iniciada", "En curso", "Completada"};
    private ArrayAdapter<String> Items_Select;
    private EditText txt_detalle_tarea_fecha_inicio, txt_detalle_tarea_fecha_vencimiento, txt_descripcion_detalle_tarea, txt_titulo_detalle_tarea, txt_agregar_comprobacion;
    private Button btn_modal_comprobacion, btn_guardar_comprobacion;
    private AutoCompleteTextView autoCompleteTextView;
    private Context context;
    private DialogDatePicker dialogDatePicker;
    private TareaViewModel tareaViewModel;
    private ComprobacionViewModel comprobacionViewModel;
    private ModalComprobacionDialog modalComprobacionDialog;
    private DialogConfirm dialogConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);
        setTitle("Detalle Tarea");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.context = this;
        dialogDatePicker = new DialogDatePicker(context);
        tarea = (Tarea) getIntent().getSerializableExtra("tarea");
        modalComprobacionDialog = new ModalComprobacionDialog(tarea);
        autoCompleteTextView = findViewById(R.id.select_progreso);
        Items_Select    =  new ArrayAdapter<String>(this,R.layout.list_estado_tarea,estado_Tarea);
        autoCompleteTextView.setAdapter(Items_Select);
        txt_titulo_detalle_tarea      = findViewById(R.id.titulo_detalle_tarea);
        txt_descripcion_detalle_tarea = findViewById(R.id.descripcion_detalle_tarea);
        txt_detalle_tarea_fecha_inicio =  findViewById(R.id.txt_detalle_tarea_fecha_inicio);
        txt_detalle_tarea_fecha_inicio.setOnClickListener(this);
        txt_detalle_tarea_fecha_vencimiento =  findViewById(R.id.txt_detalle_tarea_fecha_vencimiento);
        txt_detalle_tarea_fecha_vencimiento.setOnClickListener(this);
        txt_agregar_comprobacion = findViewById(R.id.txt_agregar_comprobacion);
        txt_agregar_comprobacion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                String agregar_comprobante = editable.toString().trim();
                btn_guardar_comprobacion.setEnabled(!agregar_comprobante.isEmpty());
            }
        });
        btn_modal_comprobacion = findViewById(R.id.btn_modal_comprobacion);
        btn_modal_comprobacion.setOnClickListener(this);
        btn_guardar_comprobacion = findViewById(R.id.btn_guardar_comprobacion);
        btn_guardar_comprobacion.setOnClickListener(this);
        tareaViewModel = new TareaViewModel(context);
        tareaViewModel.ReturnTarea(tarea);
        tareaViewModel.getEditar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Tarea Actualizada.");
                    finishWithTransition();
                }
            }
        });
        comprobacionViewModel = new ComprobacionViewModel(context);
        comprobacionViewModel.ReturTarea(tarea);
        comprobacionViewModel.getRegistro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Se agregó una comprocación.");
                    txt_agregar_comprobacion.setText("");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Cargar_Datos();
    }
        
    public void Cargar_Datos(){
        txt_titulo_detalle_tarea.setText(tarea.getTitulo_tarea());
        txt_descripcion_detalle_tarea.setText(tarea.getDescripcion());
        txt_detalle_tarea_fecha_inicio.setText(tarea.getFecha_ini());
        txt_detalle_tarea_fecha_vencimiento.setText(tarea.getFecha_fin());
        autoCompleteTextView.setText(tarea.getEstado_tarea(),false);
    }

    public void message(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public void guardarCambios(){
        dialogConfirm = new DialogConfirm();
        dialogConfirm.showConfirmDialog(context,"Modificaciones Pendientes", "¿Desea guardar los cambios?", new DialogConfirm.ConfirmDialogListener() {
            @Override
            public void onConfirm() {
                int id_tarea  = tarea.getId_tarea();
                String titulo  = txt_titulo_detalle_tarea.getText().toString().trim();
                String descripcion = txt_descripcion_detalle_tarea.getText().toString().trim();
                String fecha_ini   = txt_detalle_tarea_fecha_inicio.getText().toString().trim();
                String fecha_fin   = txt_detalle_tarea_fecha_vencimiento.getText().toString().trim();
                String estado_tarea     = autoCompleteTextView.getText().toString().trim();
                if (titulo.isEmpty() || descripcion.isEmpty() || fecha_ini.isEmpty() || fecha_fin.isEmpty() || estado_tarea.isEmpty()) {
                    message("No puedes dejar campos vacíos.\nPor favor, complete todos los campos.");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                } else {
                    tarea = new Tarea(id_tarea, titulo, descripcion, estado_tarea, fecha_ini, fecha_fin,1);
                    tareaViewModel.Editar(tarea);
                }
            }
            @Override
            public void onCancel() {
                finishWithTransition();
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (changedListenerInputTarea()) {
            guardarCambios();
        } else {
            finishWithTransition();
        }
    }
    private void finishWithTransition() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    public boolean changedListenerInputTarea() {
        return !txt_titulo_detalle_tarea.getText().toString().equals(tarea.getTitulo_tarea()) ||
        !txt_descripcion_detalle_tarea.getText().toString().equals(tarea.getDescripcion()) ||
        !txt_detalle_tarea_fecha_inicio.getText().toString().equals(tarea.getFecha_ini()) ||
        !txt_detalle_tarea_fecha_vencimiento.getText().toString().equals(tarea.getFecha_fin()) ||
        !autoCompleteTextView.getText().toString().equals(tarea.getEstado_tarea());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (changedListenerInputTarea()) {
                    guardarCambios();
                } else {
                    finishWithTransition();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View event_click) {
        if(txt_detalle_tarea_fecha_inicio==event_click){
            dialogDatePicker.showModalDatePickerDialog(txt_detalle_tarea_fecha_inicio);
        }else if(txt_detalle_tarea_fecha_vencimiento==event_click){
            dialogDatePicker.showModalDatePickerDialog(txt_detalle_tarea_fecha_vencimiento);
        }else if(btn_guardar_comprobacion == event_click){
            int id_tarea  = tarea.getId_tarea();
            String titulo_comprobacion = txt_agregar_comprobacion.getText().toString().trim();
            if(!txt_agregar_comprobacion.getText().toString().trim().equals("")) {
                comprobacion = new Comprobacion(id_tarea, titulo_comprobacion, false);
                comprobacionViewModel.Registro(comprobacion);
            }
        }else if(btn_modal_comprobacion == event_click){
            modalComprobacionDialog.show(getSupportFragmentManager(),"MyFragment");
        }
    }

}