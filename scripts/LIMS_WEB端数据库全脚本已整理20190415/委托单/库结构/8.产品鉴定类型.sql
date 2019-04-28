
drop synonym NC_PRODAUTH_TYPE;

create table NC_PRODAUTH_TYPE 
(
   PK_PRODAUTH_TYPE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_PRODAUTH_CODE     VARCHAR(20),
   NC_PRODAUTH_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_PRODAUTH_TYPE primary key (PK_PRODAUTH_TYPE)
);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);





----------------------     
insert into NC_PRODAUTH_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name like '产品鉴定类型'

and le.value like '%A类%'


select * from NC_TESTREQUEST_TYPE





