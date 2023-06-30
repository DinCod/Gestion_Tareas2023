package com.example.gestiontarea2023.Model;
import java.io.Serializable;

public class Tarea implements Serializable {
    private int id_tarea;
    private int id_tablero;
    private String titulo_tarea;
    private String descripcion;
    private String estado_tarea;
    private String fecha_ini;
    private String fecha_fin;
    private int estado;
    private String fecharegistro;
    private String fechaactualiza;
    private int id_usuarioregistro;
    private int id_usuarioactualiza;

    public Tarea(int id_tablero, String titulo_tarea, String descripcion, String estado_tarea, String fecha_ini, String fecha_fin) {
        this.id_tablero = id_tablero;
        this.titulo_tarea = titulo_tarea;
        this.descripcion = descripcion;
        this.estado_tarea = estado_tarea;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
    }

    public Tarea(int id_tarea, String titulo_tarea, String descripcion, String estado_tarea, String fecha_ini, String fecha_fin, int estado) {
        this.id_tarea = id_tarea;
        this.titulo_tarea = titulo_tarea;
        this.descripcion = descripcion;
        this.estado_tarea = estado_tarea;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public Tarea() {
    }

    public String getEstado_tarea() {
        return estado_tarea;
    }

    public void setEstado_tarea(String estado_tarea) {
        this.estado_tarea = estado_tarea;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getId_tablero() {
        return id_tablero;
    }

    public void setId_tablero(int id_tablero) {
        this.id_tablero = id_tablero;
    }

    public String getTitulo_tarea() {
        return titulo_tarea;
    }

    public void setTitulo_tarea(String titulo_tarea) {
        this.titulo_tarea = titulo_tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getFechaactualiza() {
        return fechaactualiza;
    }

    public void setFechaactualiza(String fechaactualiza) {
        this.fechaactualiza = fechaactualiza;
    }

    public int getId_usuarioregistro() {
        return id_usuarioregistro;
    }

    public void setId_usuarioregistro(int id_usuarioregistro) {
        this.id_usuarioregistro = id_usuarioregistro;
    }

    public int getId_usuarioactualiza() {
        return id_usuarioactualiza;
    }

    public void setId_usuarioactualiza(int id_usuarioactualiza) {
        this.id_usuarioactualiza = id_usuarioactualiza;
    }
}
