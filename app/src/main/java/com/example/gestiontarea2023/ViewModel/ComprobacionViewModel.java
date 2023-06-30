package com.example.gestiontarea2023.ViewModel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestiontarea2023.Conexion.Servidor;
import com.example.gestiontarea2023.Model.Comprobacion;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Tarea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComprobacionViewModel {
    private Context context;
    private Servidor conexion = new Servidor();
    private Tarea tarea;
    private Comprobacion comprobacion;
    private MutableLiveData<List<Comprobacion>> listaComprobacionLiveData = new MutableLiveData<>();
    public LiveData<List<Comprobacion>> getListaComprobacionLiveData() { return listaComprobacionLiveData;}
    private MutableLiveData<Boolean> registro = new MutableLiveData<>();
    public LiveData<Boolean> getRegistro() {
        return registro;
    }
    private MutableLiveData<Boolean> update = new MutableLiveData<>();
    public LiveData<Boolean> getUpdate() {
        return update;
    }
    private MutableLiveData<Boolean> eliminar = new MutableLiveData<>();
    public LiveData<Boolean> getEliminar(){
        return  eliminar;
    }
    public ComprobacionViewModel(Context context) {
        this.context = context;
    }
    public Tarea ReturTarea(Tarea tarea) {
        this.tarea = tarea;
        return this.tarea;
    }

    public void Registro(Comprobacion comprobacion) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "comprobacion.php?accion=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            registro.setValue(true);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registro.setValue(false);
                        Toast.makeText(context, "Error al registrar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_tarea", String.valueOf(comprobacion.getId_tarea()));
                params.put("titulo_comprobacion", comprobacion.getTitulo_comprobacion());
                params.put("estado_comprobante", String.valueOf(comprobacion.getEstado_comprobante()));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Lista() {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "comprobacion.php?accion=2&id_tarea=" + tarea.getId_tarea();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Comprobacion> comprobacionList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                comprobacion = new Comprobacion();
                                comprobacion.setId_comprobacion(jsonObject.getInt("id_comprobacion"));
                                comprobacion.setId_tarea(jsonObject.getInt("id_tarea"));
                                comprobacion.setTitulo_comprobacion(jsonObject.getString("titulo_comprobacion"));
                                comprobacion.setEstado_comprobante(jsonObject.getBoolean("estado_comprobante"));
                                comprobacionList.add(comprobacion);
                            }
                            listaComprobacionLiveData.setValue(comprobacionList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public void Eliminar(Comprobacion comprobacion){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "comprobacion.php?accion=3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            eliminar.setValue(true);
                            Lista();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        eliminar.setValue(false);
                        Toast.makeText(context, "Error al eliminar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_comprobacion", String.valueOf(comprobacion.getId_comprobacion()));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Actualizar(Comprobacion comprobacion) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "comprobacion.php?accion=4";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            update.setValue(true);
                            Lista();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        update.setValue(false);
                        Toast.makeText(context, "Error al actualizar. "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_comprobacion", String.valueOf(comprobacion.getId_comprobacion()));
                params.put("estado_comprobante", String.valueOf(comprobacion.getEstado_comprobante()));
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
