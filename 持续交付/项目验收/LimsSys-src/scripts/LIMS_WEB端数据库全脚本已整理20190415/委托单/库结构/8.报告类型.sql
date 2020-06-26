
drop synonym NC_REPORT_TYPE;
create table NC_REPORT_TYPE 
(
   PK_REPORT_TYPE       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_TYPE primary key (PK_REPORT_TYPE)
);

alter table NC_REPORT_TYPE
   add constraint FK_NC_REPOR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_REPORT_TYPE
   add constraint FK_NC_REPOR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
      
insert into NC_REPORT_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_COA_FORMAT'







