
drop synonym NC_SAFE_TYPE;

create table NC_SAFE_TYPE 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAFE_TYPE primary key (PK_SAFE_TYPE)
);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);






----------------------     
insert into NC_SAFE_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name like '安全认证类型'

and le.value like '%A类%'


select * from NC_TESTREQUEST_TYPE





