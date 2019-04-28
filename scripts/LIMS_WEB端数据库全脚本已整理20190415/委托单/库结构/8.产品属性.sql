
drop synonym NC_STATUS_TYPE;

create table NC_STATUS_TYPE 
(
   PK_STATUS_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_STATUS_CODE       VARCHAR(20),
   NC_STATUS_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_STATUS_TYPE primary key (PK_STATUS_HANDLE)
);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);


----------------------     
insert into NC_STATUS_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '产品属性'

and le.value like '%老产品%'








