drop database last_subject;
create database last_subject;

-- アカウント
INSERT INTO ACCOUNT (email, password, username) VALUES ('a@comnic.co.jp', 'a', 'コムニック太郎');
INSERT INTO ACCOUNT (email, password, username) VALUES ('b@comnic.co.jp', 'b', 'コムニック次郎');
INSERT INTO ACCOUNT (email, password, username) VALUES ('c@comnic.co.jp', 'c', 'コムニック三郎');


-- スケジュール
INSERT INTO SCHEDULE (email, starttime, endtime, memo, title) VALUES ('a@comnic.co.jp', '2017-6-26-12:00', '2017-6-26-12:10', '', '会議');


