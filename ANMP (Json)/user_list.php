<?php
$arr = array(
    array("id"=>"160420013", "password"=> "alvin123", "image_url"=>"https://randomuser.me/api/portraits/men/66.jpg", "email"=>"s160420013@student.ubaya.ac.id", "name"=>"Alvin Fernando Susanto", "birth"=>"01 May 2002", "phone"=>"085733181815"),
    array("id"=>"160420002", "password"=> "alvin123", "image_url"=>"https://randomuser.me/api/portraits/men/5.jpg", "email"=>"s160420002@student.ubaya.ac.id", "name"=>"Subrata", "birth"=>"21 March 2002", "phone"=>"085733181815"),
    array("id"=>"160420003", "password"=> "alvin123", "image_url"=>"https://randomuser.me/api/portraits/women/60.jpg", "email"=>"s160420003@student.ubaya.ac.id", "name"=>"Valerin", "birth"=>"21 March 2002", "phone"=>"085733181815"),
    array("id"=>"160420004", "password"=> "alvin123", "image_url"=>"https://randomuser.me/api/portraits/men/44.jpg", "email"=>"s160420004@student.ubaya.ac.id", "name"=>"Kenny Suhendra", "birth"=>"21 March 2002", "phone"=>"085733181815")
);

$result = null;
if(isset($_GET["user_id"])){
    $user_id = (int)$_GET["user_id"];
    // if(isset($_GET["password"])){
    //     $password = (string)$_GET["password"];
    //     if($password != ""){
    //         foreach ($arr as $user){
    //             if($user["id"] == $user_id && $user["password"] == $password){
    //                 unset($user["password"]);
    //                 $result = $user;
    //                 echo json_encode($result);
    //                 break;
    //             }
    //         }
    //     }
    // } else {
    //     foreach ($arr as $user){
    //         if($user["id"] == $user_id){
    //             unset($user["password"]);
    //             $result = $user;
    //             echo json_encode($result);
    //             break;
    //         }
    //     }
    // }
    foreach ($arr as $user){
        if($user["id"] == $user_id){
            unset($user["password"]);
            $result = $user;
            echo json_encode($result);
            break;
        }
    }
} else {
    $result = ["result" => "success", "message" => "No user id catched"];
    echo json_encode($result);
}
?>