
drop synonym NC_TESTREQUEST_TYPE;

create table NC_TESTREQUEST_TYPE 
(
   PK_TESTREQUEST_TYPE  CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_TESTREQUEST_CODE  VARCHAR(20),
   NC_TESTREQUEST_NAME  VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_TESTREQUEST_TYPE primary key (PK_TESTREQUEST_TYPE)
);

comment on table NC_TESTREQUEST_TYPE is
'测试需求';

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);



----------------------     
insert into NC_TESTREQUEST_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '产品鉴定类型'

and le.value like '%DV%'


select * from NC_TESTREQUEST_TYPE





