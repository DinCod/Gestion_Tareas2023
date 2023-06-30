<?php

include_once("../GestorMySQL/conexion.php");

class comprobacion extends conexion{
    var $id_comprobacion;
    var $id_tarea;
    var $titulo_comprobacion;
    var $estado_comprobante;
    var $estado;
    var $fecharegistro;
    var $fechaactualiza;
    var $id_usuarioregistro;
    var $id_usuarioactualiza;

    public function list(){
        $query="select * from comprobacion where id_tarea='$this->id_tarea'";
        $rs=mysqli_query($this->Mysql(),$query);
        $list_comprobacion = array();
        while($fila = mysqli_fetch_assoc($rs)){
            array_push($list_comprobacion,array(
            'id_comprobacion'=>$fila['id_comprobacion'],
            'id_tarea'=>$fila['id_tarea'],
            'titulo_comprobacion'=>$fila['titulo_comprobacion'],
            'estado_comprobante'=>$fila['estado_comprobante'],
            'estado'=>$fila['estado'],
            'fecharegistro'=>$fila['fecharegistro'],
            'fechaactualiza'=>$fila['fechaactualiza'],
            'id_usuarioregistro'=>$fila['id_usuarioregistro'],
            'id_usuarioactualiza'=>$fila['id_usuarioactualiza'],
            ));
        }
        mysqli_close($this->Mysql());
        return $list_comprobacion;
    }

    public function create(){
        $query="INSERT INTO comprobacion VALUES(0, '$this->id_tarea', '$this->titulo_comprobacion', '$this->estado_comprobante', 1, now(), now(), 1, 1)";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function detele(){
        $query="DELETE FROM comprobacion WHERE id_comprobacion='$this->id_comprobacion'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }
    public function update(){
        $query="UPDATE comprobacion SET estado_comprobante='$this->estado_comprobante'  WHERE id_comprobacion='$this->id_comprobacion'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }
}

?>