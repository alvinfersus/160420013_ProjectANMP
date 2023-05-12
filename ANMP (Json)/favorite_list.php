<?php
$arr = array(
    array("user_id"=>"160420013", "id"=>"1", "book_name"=>"Atomic Habbits", "writer"=>"Alvin Fernando", "rate"=> "5",
    "image_url"=>"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//91/MTA-41397102/brd-44261_original-aku-anak-pintar-dinosaurus-buku-komik-manga_full02.jpg",
    "sinopsis"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "category"=>"Romance, Action", "edition"=>"19", "languages"=>"English", "pages"=>"239", "publisher"=>"Gramedia"),

    array("user_id"=>"160420003", "id"=>"2", "book_name"=>"Atomic", "writer"=>"Alvin Fernando", "rate"=> "3.5",
    "image_url"=>"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//91/MTA-41397102/brd-44261_original-aku-anak-pintar-dinosaurus-buku-komik-manga_full02.jpg",
    "sinopsis"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "category"=>"Education", "edition"=>"19", "languages"=>"English", "pages"=>"239", "publisher"=>"Gramedia"),

    array("user_id"=>"160420002", "id"=>"3", "book_name"=>"Edudu Books", "writer"=>"Susanto Fernando", "rate"=> "5",
    "image_url"=>"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//91/MTA-41397102/brd-44261_original-aku-anak-pintar-dinosaurus-buku-komik-manga_full02.jpg",
    "sinopsis"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 
    "category"=>"Coding", "edition"=>"20", "languages"=>"Indonesia", "pages"=>"200", "publisher"=>"Gramedia"),

    array("user_id"=>"160420013", "id"=>"4", "book_name"=>"Psychology of Money", "writer"=>"Susanto Fernando", "rate"=> "4.5",
    "image_url"=>"https://images.tokopedia.net/img/cache/500-square/VqbcmM/2022/3/11/c44cd5a1-a8b6-4203-81e4-5253175da228.jpg",
    "sinopsis"=>"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    "category"=>"Motivation", "edition"=>"20", "languages"=>"Indonesia", "pages"=>"200", "publisher"=>"Gramedia")
);

$result = [];
if(isset($_GET["user_id"])){
    $user_id = $_GET["user_id"];
    foreach ($arr as $fav){
        if($fav["user_id"] == $user_id){
            unset($fav["user_id"]);
            array_push($result, $fav);
        }
    }
    echo json_encode($result);
}
?>