<?php
$arr = array(
    array("user_id"=>"160420003", "user_name"=>"Valerin", "time"=> "16 weeks ago", "rate"=>"3",
    "image_url"=>"https://randomuser.me/api/portraits/women/60.jpg",
    "book_id"=>"1", "book_name"=>"Atomic Habbits",
    "review"=>"It's look nice to have. Wanna read for third bro!"),

    array("user_id"=>"160420013", "user_name"=>"Alvin Fernando Susanto", "time"=> "6 weeks ago", "rate"=>"4.5", 
    "image_url"=>"https://randomuser.me/api/portraits/men/66.jpg",
    "book_id"=>"1", "book_name"=>"Atomic Habbits",
    "review"=>"It's a good book that i ever read about!"),
    
    array("user_id"=>"160420002", "user_name"=>"Subrata", "time"=> "10 weeks ago", "rate"=>"5",
    "image_url"=>"https://randomuser.me/api/portraits/men/5.jpg",
    "book_id"=>"1", "book_name"=>"Atomic Habbits",
    "review"=>"It's look nice to have. Wanna read for twice!"),

    array("user_id"=>"160420004", "user_name"=>"Kenny Suhendra", "time"=> "9 weeks ago", "rate"=>"5",
    "image_url"=>"https://randomuser.me/api/portraits/men/44.jpg",
    "book_id"=>"1", "book_name"=>"Atomic Habbits",
    "review"=>"Gonna apply the advice from this books! Nice to have it"),

    array("user_id"=>"160420013", "user_name"=>"Alvin Fernando Susanto", "time"=> "3 weeks ago", "rate"=>"3", 
    "image_url"=>"https://randomuser.me/api/portraits/men/66.jpg",
    "book_id"=>"2", "book_name"=>"Atomic",
    "review"=>"Atom such a smallest particle in this world!"),
    
    array("user_id"=>"160420002", "user_name"=>"Subrata", "time"=> "10 weeks ago", "rate"=>"5",
    "image_url"=>"https://randomuser.me/api/portraits/men/5.jpg",
    "book_id"=>"2", "book_name"=>"Atomic",
    "review"=>"Atom atom atom atom particle..."),

    array("user_id"=>"160420003", "user_name"=>"Valerin", "time"=> "16 weeks ago", "rate"=>"5",
    "image_url"=>"hhttps://randomuser.me/api/portraits/women/60.jpg",
    "book_id"=>"2", "book_name"=>"Atomic",
    "review"=>"Atom is based of all particle made up"),

    array("user_id"=>"160420003", "user_name"=>"Valerin", "time"=> "16 weeks ago", "rate"=>"5",
    "image_url"=>"hhttps://randomuser.me/api/portraits/men/44.jpg",
    "book_id"=>"4", "book_name"=>"Psychology of Money",
    "review"=>"Gonna apply the advice from this books! Nice to have it")
);

$result = [];
if(isset($_GET["book_id"])){
    $book_id = $_GET["book_id"];
    foreach ($arr as $review){
        if($review["book_id"] == $book_id){
            array_push($result, $review);
        }
    }
    echo json_encode($result);
}
?>