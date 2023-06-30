<?php 

header('Access-Control-Allow-Origin: *');
require_once("../Utilidades/StrCaracter.php");
include_once("../Modelo/usuario.php");

$obj_usuario = new usuario();
$accion = $_GET["accion"];

switch($accion){

    case 1:{
        $obj_usuario->correo = strClean($_REQUEST["correo"]);
        $obj_usuario->consult();
        $rs=[];
        if(password_verify(strClean($_REQUEST["clave"]), $obj_usuario->clave)){
            $rs = $obj_usuario->login();
        }
        echo json_encode($rs);
        break;
    }

    case 2:{
        $obj_usuario->nombre = strClean($_REQUEST["nombre"]);
        $obj_usuario->apellido_paterno = strClean($_REQUEST["apellido_paterno"]);
        $obj_usuario->apellido_materno = strClean($_REQUEST["apellido_materno"]);
        $obj_usuario->dni = strClean($_REQUEST["dni"]);
        $obj_usuario->telefono = strClean($_REQUEST["telefono"]);
        $obj_usuario->correo = strClean($_REQUEST["correo"]);
        $obj_usuario->clave = password_hash(strClean($_REQUEST["clave"]), PASSWORD_DEFAULT);
        $rs = $obj_usuario->create();
        echo json_encode($rs);
        break;
    }
    
    case 3:{
        $obj_usuario->id_usuario = intval(strClean($_REQUEST["id_usuario"]));
        $rs = $obj_usuario->list();
        echo json_encode($rs);
        break;
    }
}

?>

