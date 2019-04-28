create table NC_CONTACT_BRAND 
(
   PK_CONTACT_BRAND     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CONTACT_CODE      VARCHAR(20),
   NC_CONTACT_NAME      VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CONTACT_BRAND primary key (PK_CONTACT_BRAND)
);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);




----------------------     
insert into NC_CONTACT_BRAND 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = 'C_CONTACT_BRAND'






