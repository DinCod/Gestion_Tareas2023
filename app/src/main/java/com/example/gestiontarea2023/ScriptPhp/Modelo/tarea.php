<?php

include_once("../GestorMySQL/conexion.php");

class tarea extends conexion{

    var $id_tarea;
    var $id_tablero;
    var $titulo_tarea;
    var $descripcion;
    var $estado_tarea;
    var $fecha_ini;
    var $fecha_fin;
    var $estado;
    var $fecharegistro;
    var $fechaactualiza;
    var $id_usuarioregistro;
    var $id_usuarioactualiza;

    public function list(){
        $query="select * from tarea where id_tablero ='$this->id_tablero' ";
        $rs=mysqli_query($this->Mysql(),$query);
        $list_tareas = array();
        while($fila = mysqli_fetch_assoc($rs)){
            array_push($list_tareas,array(
            'id_tarea'=>$fila['id_tarea'],
            'id_tablero'=>$fila['id_tablero'],
            'titulo_tarea'=>$fila['titulo_tarea'],
            'descripcion'=>$fila['descripcion'],
            'estado_tarea'=>$fila['estado_tarea'],
            'fecha_ini'=>$fila['fecha_ini'],
            'fecha_fin'=>$fila['fecha_fin'],
            'estado'=>$fila['estado'],
            'fecharegistro'=>$fila['fecharegistro'],
            'fechaactualiza'=>$fila['fechaactualiza'],
            'id_usuarioregistro'=>$fila['id_usuarioregistro'],
            'id_usuarioactualiza'=>$fila['id_usuarioactualiza'],
            ));
        }
        mysqli_close($this->Mysql());
        return $list_tareas;
    }

    public function create(){
        $query="INSERT INTO tarea VALUES(0, '$this->id_tablero', '$this->titulo_tarea', '$this->descripcion', '$this->estado_tarea', '$this->fecha_ini', '$this->fecha_fin', 1, now(), now(), 1, 1)";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function update(){
        $query = "UPDATE tarea SET titulo_tarea = '$this->titulo_tarea', descripcion = '$this->descripcion', estado_tarea = '$this->estado_tarea', fecha_ini = '$this->fecha_ini', fecha_fin = '$this->fecha_fin', fechaactualiza=now(), id_usuarioactualiza=1 WHERE id_tarea='$this->id_tarea'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

}