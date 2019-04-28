--NC·ÖÎö±í



alter table NC_ANALYSIS_LIST
   drop constraint FK_NC_ANALY_REFERENCE_ANALYSIS;

alter table NC_TEST_LIST_ENTRY
   drop constraint FK_NC_TEST__REFERENCE_NC_ANALY;

drop table NC_ANALYSIS_LIST cascade constraints;

/*==============================================================*/
/* Table: NC_ANALYSIS_LIST                                      */
/*==============================================================*/
create table NC_ANALYSIS_LIST 
(
   PK_ANALYSIS_LIST     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(50 char),
   VERSION              NUMBER(10),
   NC_ANALYSIS_CODE     CHAR(50 CHAR),
   NC_ANALYSIS_NAME     CHAR(200 CHAR),
   ISENABLE             SMALLINT,
   NC_TEST_CONDITION    VARCHAR(200),
   DEF1                 VARCHAR(200),
   DEF2                 VARCHAR(200),
   DEF3                 VARCHAR(200),
   DEF4                 VARCHAR(200),
   DEF5                 VARCHAR(200),
   constraint PK_NC_ANALYSIS_LIST primary key (PK_ANALYSIS_LIST)
);

alter table NC_ANALYSIS_LIST
   add constraint FK_NC_ANALY_REFERENCE_ANALYSIS foreign key (NAME, VERSION)
      references LIMS_DATA.ANALYSIS (NAME, VERSION);
      

      
--
insert into NC_ANALYSIS_LIST
select substr(SYS_GUID(),1,20),b.name,b.version,RANK() over(order by b.name,b.version asc),
b.name,1,b.c_test_condition,'','','','',''
from (select distinct a.name,a.version,a.c_test_condition  from analysis a where a.removed = 'F') b;
