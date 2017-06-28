drop database last_subject;
create database last_subject;


-- アカウント
INSERT INTO ACCOUNT (email, password, username, id) VALUES ('watanabe@comnic.co.jp', 'watanabe', '渡邉要一', '1');
INSERT INTO ACCOUNT (email, password, username, id) VALUES ('ishiduka@comnic.co.jp', 'ishiduka', '石塚北斗', '2');
INSERT INTO ACCOUNT (email, password, username, id) VALUES ('hirata@comnic.co.jp', 'hirata', '平田和輝', '3');


-- 部署テーブル
INSERT INTO DEPARTMENT (id, name) VALUES ('1', '人事部');
INSERT INTO DEPARTMENT (id, name) VALUES ('2', '営業部');
INSERT INTO DEPARTMENT (id, name) VALUES ('3', '開発部');


-- スケジュール
INSERT INTO SCHEDULE (email, id, creatingday, work_category_id, starttime, endtime, memo) VALUES ('watanabe@comnic.co.jp', '1', '2017-06-23', '1', '2017-06-23-00:00', '2017-06-23-00:10', '');


-- 仕事カテゴリー
INSERT INTO WORK_CATEGORY (id, name) VALUES ('1', '会議');
INSERT INTO WORK_CATEGORY (id, name) VALUES ('2', '出張');
