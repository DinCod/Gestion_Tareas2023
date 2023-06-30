package com.example.gestiontarea2023.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.gestiontarea2023.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.Serializable;

public class MenuActivity extends AppCompatActivity {

    private TableroFragment primero = new TableroFragment();
    private SegundoFragment segundo = new SegundoFragment();
    private PerfilFragment tercero = new PerfilFragment();
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(itemSelected);
        if(getIntent()!=null){
            bundle = new Bundle();
            bundle.putSerializable("usuario", (Serializable) getIntent().getSerializableExtra("usuario"));
            primero.setArguments(bundle);
            segundo.setArguments(bundle);
            tercero.setArguments(bundle);
        }
        loadFragment(primero);
    }

    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option_exit) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener itemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.primer_fragment) {
                loadFragment(primero);
                return true;
            } else if (itemId == R.id.segundo_fragment) {
                loadFragment(segundo);
                return true;
            } else if (itemId == R.id.tercero_fragment) {
                loadFragment(tercero);
                return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
        fragment.setArguments(bundle);
    }
}


/*
*
*
* usuario = (Usuario) getIntent().getSerializableExtra("usuario");
            bundle  = new Bundle();
            bundle.putSerializable("usuario", usuario);
* */