<?php
$pdo = new PDO ( 'mysql:dbname=androidweb_server;host=mysql1.php.xdomain.ne.jp;charset=utf8','androidweb_user', 'password');
$sql = $pdo->prepare ( 'delete from orientation where id=?' );

if ($sql->execute([$_REQUEST['id']]))
{
    echo '削除に成功しました。';
} else {
    echo '削除に失敗しました。';
}
?>
<p>
    <button onclick="history.back()">戻る</button>
</p>