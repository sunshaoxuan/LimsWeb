--有TestList查找试验条件表
alter table NC_TESTLIST_COMP
   drop constraint FK_NC_TESTL_REFERENCE_NC_UNITS;

alter table NC_TESTLIST_COMP
   drop constraint FK_NC_TESTL_REFERENCE_NC_LIST_;

drop table NC_TESTLIST_COMP cascade constraints;

/*==============================================================*/
/* Table: NC_TESTLIST_COMP                                      
*/--此表去找nc_component_table与其analysis字段关联，以获取单位
/*==============================================================*/
create table NC_TESTLIST_COMP 
(
   PK_TESTLIST_COMP     CHAR(20 CHAR)        not null,
   PK_UNITS_TYPE        CHAR(20 CHAR),
   PK_LIST_TABLE        CHAR(20 CHAR),
   NC_TESTCOMP_CODE     CHAR(200 CHAR),
   NC_TESTCOMP_NAME     VARCHAR(200),
   NC_TESTLIST_NAME     VARCHAR(200),
   NC_ANALYSIS_NAME     VARCHAR(200),
   NC_TLC_COMPONENT     VARCHAR(200),
   ANALYSIS_COUNT       VARCHAR(200),
   ORDER_NUMBER         VARCHAR(200),
   RESULT_ORDER_NO      VARCHAR(200),
   UNITS                VARCHAR2(20 CHAR),
   ROUND                VARCHAR2(20 CHAR),
   PLACES               NUMBER(10),
   REPLICATE_COUNT      NUMBER(10),
   MIN_LIMIT            NUMBER(12,4),
   MAX_LIMIT            NUMBER(12,4),
   REPORTABLE           VARCHAR2(1 CHAR),
   OPTIONAL             VARCHAR2(1 CHAR),
   DISPLAYED            VARCHAR2(1 CHAR),
   FACTOR_VALUES        VARCHAR2(20 CHAR),
   ANALYSIS_VERSION     NUMBER(10),
   C_DEFAULT_VALUE      VARCHAR2(2000 CHAR),
   C_EN_DEFAULT_VALUE   VARCHAR2(2000 CHAR),
   LIST_KEY             VARCHAR2(20 CHAR),
   C_DEFAULT_DB_FILE    NUMBER(10),
   constraint PK_NC_TESTLIST_COMP primary key (PK_TESTLIST_COMP)
);

alter table NC_TESTLIST_COMP
   add constraint FK_NC_TESTL_REFERENCE_NC_UNITS foreign key (PK_UNITS_TYPE)
      references NC_UNITS_TYPE (PK_UNITS_TYPE);

alter table NC_TESTLIST_COMP
   add constraint FK_NC_TESTL_REFERENCE_NC_LIST_ foreign key (PK_LIST_TABLE)
      references NC_LIST_TABLE (PK_LIST_TABLE);
      
truncate table NC_TESTLIST_COMP;
--
insert into NC_TESTLIST_COMP
select substr(SYS_GUID(),1,20),
(select nut.pk_units_type from nc_units_type nut where nut.unit_code = tlc.units ),
(select nlt.pk_list_table from nc_list_table nlt where nlt.name = tlc.list_key ),
RANK() over(order by tlc.test_list,tlc.analysis,tlc.component,tlc.analysis_count,tlc.order_number,tlc.result_order_no,tlc.units,tlc.round,tlc.places,
tlc.replicate_count,tlc.min_limit,tlc.max_limit,tlc.reportable,tlc.optional,tlc.displayed,tlc.factor_values,tlc.analysis_version,tlc.c_default_value,
tlc.c_en_default_value,tlc.list_key,tlc.c_default_db_file asc),tlc.component,tlc.*
from 
(select distinct l.* 
from test_list_comp l )  tlc;


select * from NC_TESTLIST_COMP

