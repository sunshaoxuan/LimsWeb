--付费单位
create table NC_SOURCE_COMY 
(
   PK_SOURCE_COMY       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   SOURCE_COMY_CODE     VARCHAR(20),
   constraint PK_NC_SOURCE_COMY primary key (PK_SOURCE_COMY)
);

alter table NC_SOURCE_COMY
   add constraint FK_NC_SOURC_REFERENCE_CUSTOMER foreign key (NAME)
      references LIMS_DATA.CUSTOMER (NAME);
--
--Insert into NC_SOURCE_COMY
select substr(SYS_GUID(),1,20),c.name,RANK() over(order by c.name asc) ncode
from customer c
where c.group_name = 'DEFAULT'
--委托单位
create table NC_PROJ_COMY 
(
   PK_PROJ_COMY         CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   PROJ_COMY_CODE       VARCHAR(20),
   constraint PK_NC_PROJ_COMY primary key (PK_PROJ_COMY)
);

alter table NC_PROJ_COMY
   add constraint FK_NC_PROJ__REFERENCE_CUSTOMER foreign key (NAME)
      references LIMS_DATA.CUSTOMER (NAME);
--Insert into NC_PROJ_COMY
select substr(SYS_GUID(),1,20),c.name,RANK() over(order by c.name asc) ncode
from customer c
where c.group_name = 'DEFAULT'
