--�����е�ֵ����
create table NC_RESULT_TYPE 
(
   PK_RESULT_TYPE       CHAR(20 CHAR)        not null,
   NC_RESULT_CODE       CHAR(50 CHAR),
   NC_RESULT_NAME       CHAR(200 CHAR),
   NC_RESULT_DESCRIPTION VARCHAR(100),
   NC_RESULT_NAMECN     CHAR(200 CHAR),
   constraint PK_NC_RESULT_TYPE primary key (PK_RESULT_TYPE)
);

insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'1','N','Numberic','��ֵ');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'2','T','TEXT','�ı�');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'3','L','List','�б�');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'4','U','List Allow User Entry','�б������û�����');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'5','D','Date','����');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'6','K','Caculated','������');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'7','F','File Name','�ļ���');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'8','E','Exponential','ָ����');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'9','R','RTF Note','RTF��ע');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'10','H','Html Note','HTML��ע');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'11','S','Standard','��׼Ʒ');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'12','Y','DateTime','����ʱ��');
