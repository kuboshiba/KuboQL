# KuboQL

## Compile
`javac KuboQL.java`

## Execution
`java KuboQL`

## Description
【 データベース・テーブル一覧表示 】
show databases
show tables     ※ 要データベースを選択


【 指定したテーブルのデータを全て出力 】
select * from [テーブル名]
select *from [テーブル名]


【 指定したカラムのデータを出力 】
select [コンマ区切りでカラムを指定] from [テーブル名]


【 データベースを選択 】
use [データベース名]


【 指定したテーブルのカラム一覧を出力 】
desc [テーブル名]


【 データベース・テーブルを作成 】
create database
create table


【 指定したテーブルにカラムを追加・データの挿入 】
insert table [テーブル名]


【データベース・テーブルを消去】
drop database [データベース名]
drop table [テーブル名]


【テーブルの要素を消去】
delete from [テーブル名] where [条件]


【 プログラムを終了 】
exit
