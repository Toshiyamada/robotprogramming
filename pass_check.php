<?php
// バッファリング開始
ob_start();
?>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body>
<br><br>
<br><br>
<br><br>
<font size="6">
名前 :
<?php
    // データベースに接続
    $pdo = new PDO(
        'mysql:dbname=androidweb_server;host=mysql1.php.xdomain.ne.jp;charset=utf8',
        'androidweb_user',
        'password',
        [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
        ]
    );

    if ("" == $_POST["word"]){
        echo "nothing";
    }else{
        $name = $_POST['word'];
        echo $name;
    }

    //データの追加
    $sql=$pdo->prepare('insert into orientation values(?, ?)');
    if ($_REQUEST['word'] != NULL ) {
        $sql->execute(array ($_REQUEST['id'],$_REQUEST['word']));
        echo '追加!';
    } else {
        echo '追加に失敗しました。';
    }
?>
</font>
<br><br><br><br>
</body>
</html>
 
<?php
// 同階層の pass_check.html にphp実行結果を出力
file_put_contents( 'pass_check.html', ob_get_contents() );
 
// 出力用バッファをクリアしてオフ
ob_end_clean();
?>