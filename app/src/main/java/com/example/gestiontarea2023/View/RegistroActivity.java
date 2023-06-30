package com.example.gestiontarea2023.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.ViewModel.UsuarioViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity implements  View.OnClickListener {

    private TextInputLayout txtNombre,txtApellidoPaterno,txtApellidoMaterno,txtDni,txtEmail,txtPassword,txtNumeroTelefono;
    private Button btnRegistrar;
    private UsuarioViewModel usuarioViewModel;
    private Context context;
    private Activity activity;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.context      = this;
        activity          = (Activity) context;
        txtNombre         = findViewById(R.id.textInputName);
        txtApellidoPaterno= findViewById(R.id.textInputApellidoPaterno);
        txtApellidoMaterno= findViewById(R.id.textInputApellidoMaterno);
        txtDni            = findViewById(R.id.textInputDni);
        txtNumeroTelefono = findViewById(R.id.textInputMobile);
        txtEmail          = findViewById(R.id.textInputEmail);
        txtPassword       = findViewById(R.id.textInputPassword);
        btnRegistrar      = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);
        usuarioViewModel = new UsuarioViewModel(context);
        usuarioViewModel.getRegistroExitoso().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    context.startActivity(new Intent(context, LoginActivity.class));
                    activity.overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                    activity.finishAffinity();
                }
            }
        });
    }
    public void onLoginClickBack1(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finishAffinity();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
    public void onLoginClickBack2(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finishAffinity();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    public void message(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View event_click) {
        if(btnRegistrar == event_click){
            String nombre = txtNombre.getEditText().getText().toString().trim();
            String apellido_p = txtApellidoPaterno.getEditText().getText().toString().trim();
            String apellido_m = txtApellidoMaterno.getEditText().getText().toString().trim();
            String dni    = txtDni.getEditText().getText().toString().trim();
            String numero_telefono = txtNumeroTelefono.getEditText().getText().toString().trim();
            String email = txtEmail.getEditText().getText().toString().trim();
            String password = txtPassword.getEditText().getText().toString().trim();
            if(nombre.isEmpty() || apellido_p.isEmpty() || apellido_m.isEmpty() || dni.isEmpty() || numero_telefono.isEmpty() || email.isEmpty() || password.isEmpty()){
                message("Complete todos los campos.");
            }else{
                usuario = new Usuario(nombre,apellido_p,apellido_m,dni,numero_telefono,email,password);
                usuarioViewModel.Registro(usuario);
            }
        }
    }
}