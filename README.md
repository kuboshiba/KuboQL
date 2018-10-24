# KuboQL

## Compile
`javac KuboQL.java`

## Execution
`java KuboQL`

## Description
###【 データベース・テーブル一覧表示 】<br>

`show databases`<br>
`show tables     ※ 要データベースを選択`<br>


### 【 指定したテーブルのデータを全て出力 】<br>

`select * from [テーブル名]`<br>
`select *from [テーブル名]`<br>


### 【 指定したカラムのデータを出力 】<br>

`select [コンマ区切りでカラムを指定] from [テーブル名]`<br>


### 【 データベースを選択 】<br>

`use [データベース名]`<br>


### 【 指定したテーブルのカラム一覧を出力 】<br>

`desc [テーブル名]`<br>


### 【 データベース・テーブルを作成 】<br>

`create database`<br>
`create table`<br>


### 【 指定したテーブルにカラムを追加・データの挿入 】<br>

`insert table [テーブル名]`<br>


### 【データベース・テーブルを消去】<br>

`drop database [データベース名]`<br>
`drop table [テーブル名]`<br>


【テーブルの要素を消去】<br>

`delete from [テーブル名] where [条件]`<br>


【 プログラムを終了 】<br>
`exit`
