package com.example.gestiontarea2023.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.ViewModel.TableroViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetalleTableroActivity extends AppCompatActivity {
    private Tablero tablero;
    private TableroViewModel tableroViewModel;
    private Context context;
    private Bundle bundle;
    private TareaFragment tareaFragment = new TareaFragment();
    private AlertDialog dialog;
    private ModalUsuarioDialog modalUsuarioDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tablero);
        this.context  = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_tarea);
        navigation.setOnNavigationItemSelectedListener(itemSelected);
        tablero = (Tablero) getIntent().getSerializableExtra("tablero");
        bundle  = new Bundle();
        bundle.putSerializable("tablero", tablero);
        tareaFragment.setArguments(bundle);
        loadFragment(tareaFragment);
        tableroViewModel = new TableroViewModel(context);
        tableroViewModel.ReturnTablero(tablero);
        setTitle("Mis Tareas");
        tableroViewModel.getEditar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Título Modificado.");
                    tareaFragment.setTitle("Tablero: "+ tablero.getNombre_tablero());
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() { //flecha del celular, animación
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //crea la barra de menu superior
        getMenuInflater().inflate(R.menu.top_navigation,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.eliminar) {
            message("btn eliminar");
        } else if (itemId == R.id.editar_tablero) {
            modal_titulo_tablero();
        } else if (itemId == R.id.agregar_persona) {
            modalUsuarioDialog = new ModalUsuarioDialog(tablero);
            modalUsuarioDialog.show(getSupportFragmentManager(),"MyFragment");
        } else if (itemId == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void modal_titulo_tablero(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.modal_agregar_tablero, null);
        EditText titulo = alertLayout.findViewById(R.id.dialog_titulo_tablero);
        titulo.setText(tablero.getNombre_tablero());
        AlertDialog.Builder alert = new AlertDialog.Builder(DetalleTableroActivity.this);
        alert.setTitle("Editar Tablero");
        alert.setView(alertLayout);
        alert.setNegativeButton("Cancelar",null);
        alert.setPositiveButton( "Aceptar",null); //https://www.youtube.com/watch?v=veOZTvAdzJ8
        dialog = alert.create();
        dialog.show();
        Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String titulo_tablero  = titulo.getText().toString().trim();
                if(titulo_tablero.isEmpty()){
                    message("Complete todos los campos");
                }else{
                    tableroViewModel.Editar(titulo_tablero);
                    tablero.setNombre_tablero(titulo_tablero);
                }
            }});

    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_tarea,fragment);
        transaction.commit();
        fragment.setArguments(bundle);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener itemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if(itemId == R.id.lista_tarea){
                loadFragment(tareaFragment);
                return true;
            } else if (itemId == R.id.detalle_tarea) {
                return true;
            }
            return false;
        }
    };
}