# robotprogramming
ロボットプログラミングの課題



androidアプリを通してサーバーとの通信を行うシステムの開発を行った。




スマートフォンにはジャイロセンサが搭載されており、それを用いることで3軸における本体の向きや角度を計算することができる。
精度の高いデータを得ることには適していないが、簡単な作業の場合誰もが持っているスマートフォンで角度を求めるアプリがあると有効的に活用することができる。
私はスマートフォンから角度を算出し、そのデータをデータサーバーに保存することで後からその情報をどこでも誰でも閲覧することができるシステムを開発した。
※androidアプリを実際に配信するには登録費が発生するためデバックモードでの提供で対応している。








【システムの導入方法】

パソコンにandroidアプリ開発ソフト「android studio」をインストールする。
android studioで「Sender」という名前の新規プロジェク卜を作成する。
そのプロジェクトの中の「src」を本ページに乗せてある「src」ファイルに置き換えると、サーバーに接続することができるアプリのプロジェクトが完成する。
プログラムはjavaで作成しており、
$ src/main/java/com/example/sender/
の中にある「MainActivity.java」と「UploadTask.java」で細かい記載を行っている。
完成したプロジェクトをビルドし、開発者モードに設定したandroidスマートフォンにデバックモードでインストールをする。



サーバーには本サイトに乗せてある「PHP」というフォルダの中にあるphpファイルを置いておく。
※既に私が用意したオンラインサーバーにphpファイルは置いてあるので、実行する際にサーバー用意をする必要はない。



http://androidweb.php.xdomain.jp/show.php
上記のURLに飛ぶことでデータサーバーに保存された角度データを閲覧することができる。




【使い方】
アプリ起動後、
「input name」に登録したい名前を入力する。
その後測りたい角度の場所にスマートフォンの側面を合わせて、「SEND DATA」をタップする。
これにより名前と角度がデータサーバーに送信される。
これを確認するには「BROWSER」をタップし、サーバーを閲覧できるwebページにアクセスする。
ページの左上に登録した情報が載っている。
※誤送信をした場合、webページ上の間違えて送信したデータの隣にある青色で書かれた「削除」をタップし、戻るボタンをタップしてから再びページを更新すると削除することができる。



本ページの「android_system.mp4」で実際に動作している様子を確認して下さい。
