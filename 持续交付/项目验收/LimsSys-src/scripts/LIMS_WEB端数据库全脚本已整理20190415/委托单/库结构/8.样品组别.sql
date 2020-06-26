
drop synonym NC_SAMPLE_GROUP;

create table NC_SAMPLE_GROUP 
(
   PK_SAMPLE_GROUP      CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAMPLE_CODE       VARCHAR(20),
   NC_SAMPLE_NAME       VARCHAR(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAMPLE_GROUP primary key (PK_SAMPLE_GROUP)
);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);


select * from NC_SAMPLE_GROUP for update;



----------------------     
insert into NC_SAFE_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = 'C_SAMPLE_GROUP'






