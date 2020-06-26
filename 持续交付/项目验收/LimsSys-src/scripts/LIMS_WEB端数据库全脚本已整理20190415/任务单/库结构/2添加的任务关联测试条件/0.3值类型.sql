--分析中的值类型
create table NC_RESULT_TYPE 
(
   PK_RESULT_TYPE       CHAR(20 CHAR)        not null,
   NC_RESULT_CODE       CHAR(50 CHAR),
   NC_RESULT_NAME       CHAR(200 CHAR),
   NC_RESULT_DESCRIPTION VARCHAR(100),
   NC_RESULT_NAMECN     CHAR(200 CHAR),
   constraint PK_NC_RESULT_TYPE primary key (PK_RESULT_TYPE)
);

insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'1','N','Numberic','数值');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'2','T','TEXT','文本');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'3','L','List','列表');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'4','U','List Allow User Entry','列表允许用户输入');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'5','D','Date','日期');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'6','K','Caculated','计算型');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'7','F','File Name','文件名');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'8','E','Exponential','指数型');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'9','R','RTF Note','RTF备注');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'10','H','Html Note','HTML备注');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'11','S','Standard','标准品');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'12','Y','DateTime','日期时间');
