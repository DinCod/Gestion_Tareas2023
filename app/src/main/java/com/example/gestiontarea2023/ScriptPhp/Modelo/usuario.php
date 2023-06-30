<?php

include_once("../GestorMySQL/conexion.php");

class usuario extends conexion{

    var $id_usuario;
    var $nombre;
    var $apellido_materno;
    var $apellido_paterno;
    var $dni;
    var $telefono;
    var $correo;
    var $clave;
    var $foto_perfil;
    var $estado;
    var $fecharegistro;
    var $fechaactualiza;
    var $id_usuarioregistro;
    var $id_usuarioactualiza;

    public function consult(){
        $query="select * from usuario where correo='$this->correo'";
        $rs=mysqli_query($this->Mysql(),$query);
        if($fila = mysqli_fetch_array($rs)){
            $this->clave = $fila['clave'];
            $this->id_usuario = $fila["id_usuario"];
        }
        mysqli_close($this->Mysql());
    }

    public function login(){
        $query = "SELECT * FROM usuario WHERE id_usuario='$this->id_usuario'";
        $rs = mysqli_query($this->Mysql(), $query);
        $list_usuario = array();
        if($fila = mysqli_fetch_array($rs)){
            array_push($list_usuario,array(
            'id_usuario'=>$fila['id_usuario'],
            'nombre'=>$fila['nombre'],
            'apellido_materno'=>$fila['apellido_materno'],
            'apellido_paterno'=>$fila['apellido_paterno'],
            'telefono'=>$fila['telefono'],
            'correo'=>$fila['correo'],
            'dni'=>$fila['dni'],
            'clave'=>$fila['clave'],
            'foto_perfil'=>$fila['foto_perfil'],
            'estado'=>$fila['estado'],
            'fecharegistro'=>$fila['fecharegistro'],
            'fechaactualiza'=>$fila['fechaactualiza'],
            'id_usuarioregistro'=>$fila['id_usuarioregistro'],
            'id_usuarioactualiza'=>$fila['id_usuarioactualiza'],
            ));
        }
        mysqli_close($this->Mysql());
        return $list_usuario;
    }

    public function list(){
        $query = "SELECT * FROM usuario WHERE id_usuario!='$this->id_usuario'";
        $rs = mysqli_query($this->Mysql(), $query);
        $list = array();
        while($fila = mysqli_fetch_array($rs)){
            array_push($list,array(
            'id_usuario'=>$fila['id_usuario'],
            'nombre'=>$fila['nombre'],
            'apellido_materno'=>$fila['apellido_materno'],
            'apellido_paterno'=>$fila['apellido_paterno'],
            'telefono'=>$fila['telefono'],
            'correo'=>$fila['correo'],
            'dni'=>$fila['dni'],
            'clave'=>$fila['clave'],
            'foto_perfil'=>$fila['foto_perfil'],
            'estado'=>$fila['estado'],
            'fecharegistro'=>$fila['fecharegistro'],
            'fechaactualiza'=>$fila['fechaactualiza'],
            'id_usuarioregistro'=>$fila['id_usuarioregistro'],
            'id_usuarioactualiza'=>$fila['id_usuarioactualiza'],
            ));
        }
        mysqli_close($this->Mysql());
        return $list;
    }

    public function create(){
        $query="INSERT INTO usuario VALUES(0, '$this->nombre', '$this->apellido_materno','$this->apellido_paterno', '$this->dni', '$this->telefono','$this->correo','$this->clave','$this->foto_perfil', 1, now(), now(), 1, 1)";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function detele(){
        $query="DELETE FROM usuario WHERE id_usuario='$this->id_usuario'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

    public function update(){
        $query="UPDATE usuario SET nombre='$this->nombre', apellido_materno='$this->apellido_materno', apellido_paterno='$this->apellido_paterno', dni ='$this->dni' , telefono='$this->telefono', correo='$this->correo', clave='$this->clave', foto_perfil='$this->foto_perfil', fechaactualiza=now(), id_usuarioactualiza=1 
        WHERE id_usuario ='$this->id_usuario'";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

}
?>
