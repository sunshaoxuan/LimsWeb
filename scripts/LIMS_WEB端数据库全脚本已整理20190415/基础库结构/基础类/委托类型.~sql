--Î¯ÍÐÀàÐÍ
create table NC_PROJ_TYPE 
(
   PK_PROJ_TYPE         CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   VERSION              NUMBER(10),
   PROJ_TYPE_CODE       VARCHAR(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_PROJ_TYPE primary key (PK_PROJ_TYPE)
);

alter table NC_PROJ_TYPE
   add constraint FK_NC_PROJ__REFERENCE_PROJECT_ foreign key (NAME, VERSION)
      references LIMS_DATA.PROJECT_TEMPLATE (NAME, VERSION);
-------
Insert into NC_PROJ_TYPE
select substr(SYS_GUID(),1,20),p.name,p.version,RANK() over(order by p.name asc),1
from project_template p
where p.group_name = 'DEFAULT'
