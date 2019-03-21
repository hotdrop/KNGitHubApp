## KNGitHubApp
このリポジトリはKotlin/NativeでiOSアプリとロジック共有するに当たって現在どんな感じになっているのか確認するために作成したサンプルアプリです。  
作成したサンプルアプリはDroidKaigi2019のGitHubリポジトリからContributorを取得し一覧表示します。  
叩くAPIは`https://api.github.com/repos/takahirom/conference-app-2019/contributors`です。  

## 共通環境
1. Kotlin 1.3.20
2. gradle 3.3.0(gradle-4.10.1-all)

## commonモジュールについて
今回はとても簡単なアプリだったので可能な限りcommonMainで書くことにしました。  
ただ、iOS側でsuspend関数が扱えない関係でどうしてもiOSMainにCoroutinesの補完処理を書いてやる必要がありiOSMainはコードを入れています。    
また、正直Repositoryだけで事足りる内容ですがUseCaseを各OSのインタフェースとしたらどうか試したかったのでUseCaseを作成しています。  
`GithubRepositoryImpl`のコンストラクタ引数でendpointのURLを渡しています。これ本当はBuildConfigで扱いたかったのですがそうするとgradleの書き方をちょっと工夫しないとならず今回は時間なかったので諦めました。  
悪あがきでテスト時にURLを変えられるよう、定数定義してコード中で参照するのではなくコンストラクタ引数で渡しています。  

使用したライブラリは`ktor`のみです。    
本当は`kodein`を使いたかったのですがこれを入れるとGradleが  
>Could not determine the dependencies of task ':app:transformNativeLibsWithMergeJniLibsForDebug'.

というエラーをはいてどうにもならなかったので使うのをやめました。  
また、`koin`も試したのですがまだMPPに対応していないことが途中で分かったのでやめました。  
ただ`koin`自体はとても良いライブラリで気に入ったのでAndroid本体側のコードで使っています。  

## appモジュールについて
これも過剰ですが検証したかったのでAACのLifecycle,ViewModel,LiveDataを使っています。  
あとは上記に書いた通りクラス間の依存度を低くするため`koin`を使っているのと、取得したContributor情報のavatar画像を読み込むためGlideを使っています。   
定番のTimberもですね。詳細はappモジュールのbuild.gradleをご確認ください。 
非同期処理は全部Coroutineです。  

## iosについて
同じリポジトリに置いてますがiosディレクトリはXCodeプロジェクトとなっています。  
commonモジュールのビルド時に作成した.frameworkを読み込んでXcodeからビルドします。  
commonモジュールで作成したusecaseを読んでContributor一覧を取得するところまではすんなり出来たのですが、AutoLayoutに慣れておらず一覧表示にえらい苦労しました。  
iOSの慣習が理解できていないのでコードの書き方も結構ひどいかもしれません。。
