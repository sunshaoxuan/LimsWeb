/*create table NC_PROD_TYPE 
(
   PK_PROD_TYPE         CHAR(20 CHAR)        not null,
   PROD_TYPE            VARCHAR2(20),
   C_P_PROD_TYPE        VARCHAR2(50),
   NAME                 VARCHAR2(50),
   C_P_PROD_TYPE2       VARCHAR2(50),
   C_PROD_TYPE_C1       VARCHAR2(50),
   P_NAME               VARCHAR2(50),
   F_PROD_TYPE          VARCHAR2(20),
   S_PROD_TYPE          VARCHAR2(20),
   T_PROD_TYPE          VARCHAR2(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_PROD_TYPE primary key (PK_PROD_TYPE)
);

grant references on lims_data.C_PROD_TYPE to nc_admin;
grant references on lims_data.C_PROD_TYPE_C1 to nc_admin;
grant references on lims_data.C_PROD_TYPE_C2 to nc_admin;

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_A foreign key (PROD_TYPE)
      references C_PROD_TYPE (PROD_TYPE);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE, NAME)
      references C_PROD_TYPE_C1 (PROD_TYPE, NAME);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD_REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE2, C_PROD_TYPE_C1, P_NAME)
      references C_PROD_TYPE_C2 (PROD_TYPE, C_PROD_TYPE_C1, P_NAME);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (PROD_TYPE)
      references C_PROD_TYPE (PROD_TYPE);
 
      
select * from NC_PROD_TYPE*/

select * from NC_PROD_TYPE;
select *
from C_PROD_TYPE;
select *
from C_PROD_TYPE_C1;
select *
from C_PROD_TYPE_C2;

select substr(SYS_GUID(),1,20),c.prod_type,
c1.prod_type,c1.name,
c2.prod_type,c2.c_prod_type_c1,c2.p_name,
c1.report_name,c1.report_name,c2.report_name
from C_PROD_TYPE c,C_PROD_TYPE_C1 c1,C_PROD_TYPE_C2 c2
where c.prod_type = c1.prod_type and c1.name = c2.c_prod_type_c1 and c.prod_type = c2.prod_type
;

Insert into NC_PROD_TYPE select substr(SYS_GUID(),1,20),c.prod_type,
c1.prod_type,c1.name,
c2.prod_type,c2.c_prod_type_c1,c2.p_name,
c1.report_name,c1.report_name,c2.report_name,'1'
from C_PROD_TYPE c,C_PROD_TYPE_C1 c1,C_PROD_TYPE_C2 c2
where c.prod_type = c1.prod_type and c1.name = c2.c_prod_type_c1 and c.prod_type = c2.prod_type
;

select * from  NC_PROD_TYPE


select * from dba_directories


expdp IMP_DB

expdp nc_admin/limslims@lims DIRECTORY=IMP_DB DUMPFILE=exp_4tab.dmp LOGFILE=exp_4tab.log SCHEMAS=nc_admin INCLUDE=TABLE:\"IN ('NC_PROD_TYPE', 'C_PROD_TYPE' , 'C_PROD_TYPE_C1' , 'C_PROD_TYPE_C2')\"



