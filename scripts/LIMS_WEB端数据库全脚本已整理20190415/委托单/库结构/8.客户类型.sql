
drop synonym NC_CUSTOMER_TYPE;

create table NC_CUSTOMER_TYPE 
(
   PK_CUSTOMER_TYPE     CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CUSTOMER_CODE     VARCHAR(20),
   NC_CUSTOMER_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CUSTOMER_TYPE primary key (PK_CUSTOMER_TYPE)
);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);


----------------------     
insert into NC_CUSTOMER_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '客户要求-需求类型'

and le.value like '%客户%'








