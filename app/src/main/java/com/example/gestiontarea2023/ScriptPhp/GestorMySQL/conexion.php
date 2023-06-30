<?php

class conexion{

    public function Mysql(){
        $hostname_cn="localhost";
        $database_cn="practicas_2023";
        $user_cn="root";
        $pass_cn="";
        return mysqli_connect($hostname_cn,$user_cn,$pass_cn,$database_cn);
    }

    /*Servidor
    $hostname_cn="localhost";
    $database_cn="id20981192_gestiontarea2023";
    $user_cn="id20981192_gestiontarea";
    $pass_cn="newbpaih5W@";
    */

}
?>