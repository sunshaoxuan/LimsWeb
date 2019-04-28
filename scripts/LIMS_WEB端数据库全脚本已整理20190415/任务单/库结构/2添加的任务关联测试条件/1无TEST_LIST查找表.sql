--无TEST_LIST查找表
alter table NC_COMPONENT_TABLE
   drop constraint FK_NC_COMPO_REFERENCE_NC_RESUL;

alter table NC_COMPONENT_TABLE
   drop constraint FK_NC_COMPO_REFERENCE_NC_UNITS;

alter table NC_COMPONENT_TABLE
   drop constraint FK_NC_COMPO_REFERENCE_NC_LIST_;

drop table NC_COMPONENT_TABLE cascade constraints;

/*==============================================================*/
/* Table: NC_COMPONENT_TABLE 

千万注意：nc_result_type表需要trim()                                   */
/*==============================================================*/
create table NC_COMPONENT_TABLE 
(
   PK_COMPONENT_TABLE   CHAR(20 CHAR)        not null,
   PK_RESULT_TYPE       CHAR(20 CHAR),
   PK_UNITS_TYPE        CHAR(20 CHAR),
   PK_LIST_TABLE        CHAR(20 CHAR),
   NC_COMPONENT_CODE    CHAR(50 CHAR),
   NC_COMPONENT_NAME    CHAR(500 CHAR),
   ANALYSIS             VARCHAR2(50 char),
   NAME                 VARCHAR2(40 char),
   VERSION              NUMBER(10),
   ORDER_NUMBER         NUMBER(10),
   RESULT_TYPE          VARCHAR2(1 char),
   UNITS                VARCHAR2(20 char),
   MINIMUM              VARCHAR2(20 char),
   MAXIMUM              VARCHAR2(20 char),
   ALLOW_OUT            VARCHAR2(1 char),
   PLACES               NUMBER(10),
   NUM_REPLICATES       NUMBER(10),
   USES_INSTRUMENT      VARCHAR2(1 char),
   USES_CODES           VARCHAR2(1 char),
   AUTO_CALC            VARCHAR2(1 char),
   LIST_KEY             VARCHAR2(20 char),
   ALLOW_CANCEL         VARCHAR2(1 char),
   CAS_NUMBER           VARCHAR2(20 char),
   ALIAS_NAME           VARCHAR2(40 char),
   REPORTABLE           VARCHAR2(1 char),
   OPTIONAL             VARCHAR2(1 char),
   CLAMP_LOW            VARCHAR2(20 char),
   CLAMP_HIGH           VARCHAR2(20 char),
   ROUND                VARCHAR2(20 char),
   STD_REAG_TEMPLATE    VARCHAR2(20 char),
   HAS_ATTRIBUTES       VARCHAR2(1 char),
   FORMAT_CALCULATION   VARCHAR2(20 char),
   DISPLAYED            VARCHAR2(1 char),
   VERSION_FILES        VARCHAR2(1 char),
   BROWSE_SUBROUTINE    VARCHAR2(50 char),
   STEP                 VARCHAR2(20 char),
   NWA_NAME             VARCHAR2(32 char),
   PLACES_2             NUMBER(10),
   REPORTED_RESULT      VARCHAR2(1 char),
   CONVERSION_TYPE      VARCHAR2(20 char),
   FROM_CATEGORY        VARCHAR2(20 char),
   TO_CATEGORY          VARCHAR2(20 char),
   FROM_UNITS           VARCHAR2(20 char),
   TO_UNITS             VARCHAR2(20 char),
   CONVERSION_FACTOR    NUMBER(12,4),
   T_SHORT_NAME         VARCHAR2(100 char),
   C_CONTACT_TYPE       VARCHAR2(254),
   C_COIL_TYPE          VARCHAR2(100),
   C_DEFAULT_VALUE      VARCHAR2(2000),
   C_EN_DEFAULT_VALUE   VARCHAR2(2000),
   REPORTED_NAME        VARCHAR2(50 char),
   C_ALIAS_NAME         VARCHAR2(40 char),
   C_UPDATE_EN_NAME     VARCHAR2(40 char),
   C_DETAILDED_FAILURE  VARCHAR2(40),
   C_INTERFACE_KEY      VARCHAR2(50 char),
   constraint SYS_C0011716 primary key (PK_COMPONENT_TABLE)
);


alter table NC_COMPONENT_TABLE
   add constraint FK_NC_COMPO_REFERENCE_NC_RESUL foreign key (PK_RESULT_TYPE)
      references NC_RESULT_TYPE (PK_RESULT_TYPE);

alter table NC_COMPONENT_TABLE
   add constraint FK_NC_COMPO_REFERENCE_NC_UNITS foreign key (PK_UNITS_TYPE)
      references NC_UNITS_TYPE (PK_UNITS_TYPE);

alter table NC_COMPONENT_TABLE
   add constraint FK_NC_COMPO_REFERENCE_NC_LIST_ foreign key (PK_LIST_TABLE)
      references NC_LIST_TABLE (PK_LIST_TABLE);



----
insert into NC_COMPONENT_TABLE
select substr(SYS_GUID(),1,20),--结果类型主键,单位类型主键,NCLIST主键
(select nrt.pk_result_type from nc_result_type nrt where trim(nrt.nc_result_name) = trim(b.result_type) ),
(select nut.pk_units_type from nc_units_type nut where nut.unit_code = b.units ),
(select nlt.pk_list_table from nc_list_table nlt where nlt.name = b.list_key ),
RANK() over(order by b.analysis,b.name,b.version asc),b.name,b.*
from (select distinct * from component c ) b

