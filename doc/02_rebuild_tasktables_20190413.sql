--NC分析表
drop table LIMS_DATA.NC_ANALYSIS_LIST cascade constraints;

/*==============================================================*/
/* Table: NC_ANALYSIS_LIST                                      */
/*==============================================================*/
create table LIMS_DATA.NC_ANALYSIS_LIST 
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

      
--
insert into LIMS_DATA.NC_ANALYSIS_LIST
select substr(SYS_GUID(),1,20),b.name,b.version,RANK() over(order by b.name,b.version asc),
b.name,1,b.c_test_condition,'','','','',''
from (select distinct a.name,a.version,a.c_test_condition  from analysis a where a.removed = 'F') b;

--任务添加模板大集合(此处考虑做成view，20位主键每次重新生成，通过name回去查找，此处只做查询筛选模板?)
drop table LIMS_DATA.NC_TASK_ADDUNION cascade constraints;

/*==============================================================*/
/* Table: NC_TASK_ADDUNION                                      */
/*==============================================================*/
create table LIMS_DATA.NC_TASK_ADDUNION 
(
   PK_TASK_ADDUNION     CHAR(20 CHAR)        not null,
   NC_TASK_ADDCODE      CHAR(50 CHAR),
   NC_TASK_ADDNAME      CHAR(200 CHAR),
   NC_TESTLIST_NAME     VARCHAR(200),
   NC_ANALYSIS_METHOD   VARCHAR(200),
   NC_REPORT_NAME       VARCHAR(200),
   NC_TASK_TYPE         VARCHAR(200),
   NC_TASK_DES          VARCHAR(1000),
   NC_TASK_NAME         VARCHAR(200),
   NC_INCLUDE_PROTYPE   VARCHAR(1000),
   NC_CB_PLAN           CHAR(1 CHAR),
   NC_ORDER_NUMBER      NUMBER(10),
   "def1"               VARCHAR(1000),
   "def2"               VARCHAR(1000),
   "def3"               VARCHAR(1000),
   "def4"               VARCHAR(1000),
   "def5"               VARCHAR(1000),
   constraint PK_NC_TASK_ADDUNION primary key (PK_TASK_ADDUNION)
);




---------------
insert into LIMS_DATA.NC_TASK_ADDUNION
  select substr(SYS_GUID(), 1, 20),
         RANK() over(order by un.name, un.order_number, un.description asc),
         un.Name,
         un.TEST_LIST,
         un.T_ANALYSIS_METHOD,
         un.REPORTED_NAME,
         un.ANALYSIS_TYPE,
         un.DESCRIPTION,
         un.NAME,
         un.C_ALLOWED_PROD_TYPE,
         un.C_B_PLAN,
         un.order_number,
         '',
         '',
         '',
         '',
         ''
    from (select t.name AS TEST_LIST,
                 A.T_ANALYSIS_METHOD,
                 A.REPORTED_NAME,
                 A.ANALYSIS_TYPE,
                 A.DESCRIPTION,
                 A1.NAME,
                 A.C_ALLOWED_PROD_TYPE,
                 (case T.C_B_PLAN
                   when 'T' then
                    'T'
                   else
                    ''
                 end) as C_B_PLAN,
                 t.order_number
            from test_list_entry t, analysis a, analysis a1
           where a.name = t.analysis
             AND A.C_TEST_TYPE = '测试条件'
             and t.version = a.version
             and a.group_name <> 'ABANDON'
             and a.active = 'T'
             and a.name = a1.c_test_condition
          
          UNION
          
          select '_NA' AS TEST_LIST,
                 A.T_ANALYSIS_METHOD,
                 A.REPORTED_NAME,
                 A.ANALYSIS_TYPE,
                 A.DESCRIPTION,
                 A.NAME,
                 A.C_ALLOWED_PROD_TYPE,
                 '',
                 999
            from analysis a
           where A.ACTIVE = 'T'
             and a.group_name <> 'ABANDON'
             AND A.C_TEST_TYPE = '测试结果'
           order by 1, 9) un;
		   
		   
--LIST主表
drop table  LIMS_DATA.NC_LIST_TABLE ;

/*==============================================================*/
/* Table: NC_LIST_TABLE                                         */
/*==============================================================*/
create table LIMS_DATA.NC_LIST_TABLE 
(
   PK_LIST_TABLE        CHAR(20 CHAR)        not null,
   NC_LIST_CODE         CHAR(50 CHAR),
   NC_LIST_NAME         CHAR(200 CHAR),
   NAME                 VARCHAR2(20 char),
   GROUP_NAME           VARCHAR2(20 char),
   DESCRIPTION          VARCHAR2(254 char),
   CHANGED_ON           DATE,
   CHANGED_BY           VARCHAR2(20 char),
   REMOVED              VARCHAR2(1 char),
   EXT_LINK             VARCHAR2(254 char),
   SORT                 VARCHAR2(1 char),
   constraint SYS_LIST_TABLE primary key (PK_LIST_TABLE)

);

---- 
insert into LIMS_DATA.NC_LIST_TABLE
select  substr(SYS_GUID(),1,20),RANK() over(order by l.name asc),l.name,l.*
from list l;



--NC_List_ENTRY辅表
drop table LIMS_DATA.NC_LIST_ENTRY;
create table LIMS_DATA.NC_LIST_ENTRY 
(
   PK_LIST_ENTRY        CHAR(20 CHAR)        not null,
   PK_LIST_TABLE        CHAR(20 CHAR),
   NC_LIST_CODE         CHAR(50 CHAR),
   NC_LIST_NAME         CHAR(2000 CHAR),
   LIST                 VARCHAR2(200 char),
   NAME                 VARCHAR2(200 char),
   VALUE                VARCHAR2(2540 char),
   ORDER_NUMBER         NUMBER(10),
   C_EN_VALUE           VARCHAR2(254 char),
   C_CONT_VALUE         NUMBER(10),
   constraint SYS_LIST_ENTRY primary key (PK_LIST_ENTRY)
);

--
insert into LIMS_DATA.NC_LIST_ENTRY
select substr(SYS_GUID(),1,20),l.pk_list_table,
RANK() over(order by le.name,le.value asc),le.value,le.*
from list_entry le , nc_list_table l
where le.list = l.name;




--分析中的值类型
DROP TABLE LIMS_DATA.NC_RESULT_TYPE;

create table LIMS_DATA.NC_RESULT_TYPE 
(
   PK_RESULT_TYPE       CHAR(20 CHAR)        not null,
   NC_RESULT_CODE       CHAR(50 CHAR),
   NC_RESULT_NAME       CHAR(200 CHAR),
   NC_RESULT_DESCRIPTION VARCHAR(100),
   NC_RESULT_NAMECN     CHAR(200 CHAR),
   constraint PK_NC_RESULT_TYPE primary key (PK_RESULT_TYPE)
);

DELETE FROM LIMS_DATA.NC_RESULT_TYPE ;

insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'1','N','Numberic','数值');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'2','T','TEXT','文本');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'3','L','List','列表');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'4','U','List Allow User Entry','列表允许用户输入');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'5','D','Date','日期');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'6','K','Caculated','计算型');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'7','F','File Name','文件名');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'8','E','Exponential','指数型');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'9','R','RTF Note','RTF备注');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'10','H','Html Note','HTML备注');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'11','S','Standard','标准品');
insert into LIMS_DATA.NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'12','Y','DateTime','日期时间');




--无TEST_LIST查找表
drop table LIMS_DATA.NC_COMPONENT_TABLE cascade constraints;

/*==============================================================*/
/* Table: NC_COMPONENT_TABLE 

千万注意：nc_result_type表需要trim()                                   */
/*==============================================================*/
create table LIMS_DATA.NC_COMPONENT_TABLE 
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

insert into LIMS_DATA.NC_COMPONENT_TABLE
select substr(SYS_GUID(),1,20),--结果类型主键,单位类型主键,NCLIST主键
(select nrt.pk_result_type from nc_result_type nrt where trim(nrt.nc_result_name) = trim(b.result_type) ),
(select nut.pk_units_type from nc_units_type nut where nut.unit_code = b.units ),
(select nlt.pk_list_table from nc_list_table nlt where nlt.name = b.list_key ),
RANK() over(order by b.analysis,b.name,b.version asc),b.name,b.*
from (select distinct * from component c ) b;





--有TestList查找试验条件表
drop table LIMS_DATA.NC_TESTLIST_COMP cascade constraints;

/*==============================================================*/
/* Table: NC_TESTLIST_COMP                                      
*/--此表去找nc_component_table与其analysis字段关联，以获取单位
/*==============================================================*/
create table LIMS_DATA.NC_TESTLIST_COMP 
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


truncate table LIMS_DATA.NC_TESTLIST_COMP;
--
insert into LIMS_DATA.NC_TESTLIST_COMP
select substr(SYS_GUID(),1,20),
(select nut.pk_units_type from nc_units_type nut where nut.unit_code = tlc.units ),
(select nlt.pk_list_table from nc_list_table nlt where nlt.name = tlc.list_key ),
RANK() over(order by tlc.test_list,tlc.analysis,tlc.component,tlc.analysis_count,tlc.order_number,tlc.result_order_no,tlc.units,tlc.round,tlc.places,
tlc.replicate_count,tlc.min_limit,tlc.max_limit,tlc.reportable,tlc.optional,tlc.displayed,tlc.factor_values,tlc.analysis_version,tlc.c_default_value,
tlc.c_en_default_value,tlc.list_key,tlc.c_default_db_file asc),tlc.component,tlc.*
from 
(select distinct l.* 
from test_list_comp l )  tlc;



--试验后参数详单，应用参照试验前
DROP TABLE LIMS_DATA.NC_TEST_AFTER;
create table LIMS_DATA.NC_TEST_AFTER
(
  pk_test_after        CHAR(20 CHAR) not null,
  pk_result_type      CHAR(20 CHAR),
  pk_units_type       CHAR(20 CHAR),
  test_after_code      CHAR(50 CHAR),
  test_after_name      CHAR(200 CHAR),
  nc_enstard          NVARCHAR2(100),
  nc_entry            NUMBER(10),
  nc_analysis_name    VARCHAR2(50 CHAR),
  nc_analysis_version NUMBER(10),
  nc_analysis_type    VARCHAR2(1 CHAR),
  nc_spec_rule        VARCHAR2(50 CHAR),
  nc_min_value        VARCHAR2(53 CHAR),
  nc_max_value        VARCHAR2(53 CHAR),
  nc_text_value       VARCHAR2(256 CHAR),
  nc_stage            VARCHAR2(20 CHAR),
  nc_sample_point     VARCHAR2(20 CHAR),
  nc_contact_type     VARCHAR2(254),
  nc_coil_type        VARCHAR2(100),
  nc_coil_current     VARCHAR2(100),
  def1                VARCHAR2(256),
  def2                VARCHAR2(256),
  def3                VARCHAR2(256),
  def4                VARCHAR2(256),
  def5                VARCHAR2(256)
);

insert into LIMS_DATA.NC_TEST_AFTER
SELECT substr(SYS_GUID(),1,20) ,nrt.pk_result_type,u.pk_units_type,RANK() over(order by p.product,p.entry_code asc),c.name,
p.product,p.entry_code,p.analysis,p.version,'',p.spec_rule, p.min_value,p.max_value,p.text_value,p.stage,p.sampling_point,
c.c_contact_type,c.C_COIL_TYPE,p.grade,c.order_number,'5EA5CA7222E54CD38816','','',''
  FROM COMPONENT c, product_spec p,nc_result_type nrt,NC_UNITS_TYPE u
 WHERE c.ANALYSIS = '继电器企标试验后参数'
   and c.version = 1
   and c.analysis = p.analysis
   and c.name = p.component
   and trim(nrt.nc_result_name) = c.result_type
   and u.unit_code = c.units;

