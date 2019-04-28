alter table NC_REPORT_LANG
   drop constraint FK_NC_REPOR_REFERENCE_LIST_ENT;

alter table NC_REPORT_LANG
   drop constraint FK_NC_REPOR_REFERENCE_LIST;

drop table NC_REPORT_LANG cascade constraints;
drop synonym NC_REPORT_LANG;
/*==============================================================*/
/* Table: NC_REPORT_LANG                                        */
/*==============================================================*/
create table NC_REPORT_LANG 
(
   PK_REPORT_LANG       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_LANG primary key (PK_REPORT_LANG)
);

alter table NC_REPORT_LANG
   add constraint K_NC_REPOR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_REPORT_LANG
   add constraint K_NC_REPOR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

----------------------     
insert into NC_REPORT_LANG 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_COA_LANGUAGE'







