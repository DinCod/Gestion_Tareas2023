<?php

include_once("../GestorMySQL/conexion.php");

class tablero extends conexion{
    var $id_tablero;
    var $id_usuario;
    var $nombre_tablero;
    var $estado;
    var $fecharegistro;
    var $fechaactualiza;
    var $id_usuarioregistro;
    var $id_usuarioactualiza;

    public function list(){
        $query="select * from tablero where id_usuario ='$this->id_usuario' ORDER BY id_tablero DESC";
        $rs=mysqli_query($this->Mysql(),$query);
        $list_tableros = array();
        while($fila = mysqli_fetch_assoc($rs)){
            array_push($list_tableros,array(
            'id_tablero'=>$fila['id_tablero'],
            'id_usuario'=>$fila['id_usuario'],
            'nombre_tablero'=>$fila['nombre_tablero'],
            'estado'=>$fila['estado'],
            'fecharegistro'=>$fila['fecharegistro'],
            'fechaactualiza'=>$fila['fechaactualiza'],
            'id_usuarioregistro'=>$fila['id_usuarioregistro'],
            'id_usuarioactualiza'=>$fila['id_usuarioactualiza'],
            ));
        }
        mysqli_close($this->Mysql());
        return $list_tableros;
    }

    public function create(){
        $query="INSERT INTO tablero VALUES(0, '$this->id_usuario', '$this->nombre_tablero', 1, now(), now(), 1, 1)";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function detele(){
        $query="DELETE FROM tablero WHERE id_tablero='$this->id_tablero'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function update(){
        $query="UPDATE tablero SET nombre_tablero='$this->nombre_tablero', fechaactualiza=now(), id_usuarioactualiza=1 WHERE id_tablero ='$this->id_tablero'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function count(){
        $query = "select COUNT(*) as num_tareas from tablero tb INNER JOIN tarea ta on tb.id_tablero = ta.id_tablero where tb.id_tablero ='$this->id_tablero'";
        $rs = mysqli_query($this->Mysql(),$query);
        $num = $rs->fetch_assoc();
        mysqli_close($this->Mysql());
        return $num;
    }

}
?>