alter table NC_REPORT_LANG
   drop constraint FK_NC_REPOR_REFERENCE_LIST_ENT;

drop synonym NC_RATAIN_HANDLE;
/*==============================================================*/
/* Table: NC_REPORT_LANG                                        */
/*==============================================================*/
create table NC_RATAIN_HANDLE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_RATAIN_HANDLE primary key (PK_RATAIN_HANDLE)
);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);


----------------------     
insert into NC_RATAIN_HANDLE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_RATAIN_HANDLE'





