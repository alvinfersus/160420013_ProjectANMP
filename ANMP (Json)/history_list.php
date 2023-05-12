<?php
$arr = array(
    array("user_id"=>"160420013", "book_id"=>"1", "book_name"=>"Atomic Habbits", "writer"=>"Alvin Fernando", "rate"=> "5",
    "image_url"=>"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//91/MTA-41397102/brd-44261_original-aku-anak-pintar-dinosaurus-buku-komik-manga_full02.jpg",
    "category"=>"Romance, Action", "my_rate"=>"5", "my_review"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    
    array("user_id"=>"160420013", "book_id"=>"3", "book_name"=>"Edudu Books", "writer"=>"Susanto Fernando", "rate"=> "5",
    "image_url"=>"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//91/MTA-41397102/brd-44261_original-aku-anak-pintar-dinosaurus-buku-komik-manga_full02.jpg",
    "category"=>"Coding", "my_rate"=>"4", "my_review"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
);

$result = [];
if(isset($_GET["user_id"])){
    $user_id = $_GET["user_id"];
    foreach ($arr as $hist){
        if($hist["user_id"] == $user_id){
            unset($hist["user_id"]);
            array_push($result, $hist);
        }
    }
    echo json_encode($result);
}
?>