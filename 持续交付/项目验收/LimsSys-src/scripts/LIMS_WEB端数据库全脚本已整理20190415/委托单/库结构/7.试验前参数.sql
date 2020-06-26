--试验前参数
--建表
create table NC_TEST_INIT 
(
   PK_TEST_INIT         CHAR(20 CHAR)        not null,
   PK_RESULT_TYPE       CHAR(20 CHAR),
   PK_UNITS_TYPE        CHAR(20 CHAR),
   TEST_INIT_CODE       CHAR(50 CHAR),
   TEST_INIT_NAME       CHAR(200 CHAR),
   NC_ENSTARD           NVARCHAR2(100),
   NC_ENTRY             NUMBER(10),
   NC_ANALYSIS_NAME     VARCHAR2(50 CHAR),
   NC_ANALYSIS_VERSION  NUMBER(10),
   NC_ANALYSIS_TYPE     VARCHAR2(1 CHAR),
   NC_SPEC_RULE         VARCHAR2(50 CHAR),
   NC_MIN_VALUE         VARCHAR2(53 CHAR),
   NC_MAX_VALUE         VARCHAR2(53 CHAR),
   NC_TEXT_VALUE        VARCHAR2(256 CHAR),
   NC_STAGE             VARCHAR2(20 CHAR),
   NC_SAMPLE_POINT      VARCHAR2(20 CHAR),
   NC_CONTACT_TYPE      VARCHAR2(254),
   NC_COIL_TYPE         VARCHAR2(100),
   NC_COIL_CURRENT      VARCHAR2(100),
   DEF1                 VARCHAR2(256),
   DEF2                 VARCHAR2(256),
   DEF3                 VARCHAR2(256),
   DEF4                 VARCHAR2(256),
   DEF5                 VARCHAR2(256),
   constraint PK_NC_TEST_INIT primary key (PK_TEST_INIT)
);

alter table NC_TEST_INIT
   add constraint FK_NC_TEST__REFERENCE_NC_RESUL foreign key (PK_RESULT_TYPE)
      references NC_RESULT_TYPE (PK_RESULT_TYPE);

alter table NC_TEST_INIT
   add constraint FK_NC_TEST__REFERENCE_NC_UNITS foreign key (PK_UNITS_TYPE)
      references NC_UNITS_TYPE (PK_UNITS_TYPE);
--插入数据
insert into NC_TEST_INIT
SELECT substr(SYS_GUID(),1,20) ,nrt.pk_result_type,u.pk_units_type,RANK() over(order by p.product,p.entry_code asc),c.name,
p.product,p.entry_code,p.analysis,p.version,'',p.spec_rule, p.min_value,p.max_value,p.text_value,p.stage,p.sampling_point,
c.c_contact_type,c.C_COIL_TYPE,p.grade,c.order_number,'5EA5CA7222E54CD38815','','',''
  FROM COMPONENT c, product_spec p,nc_result_type nrt,NC_UNITS_TYPE u
 WHERE c.ANALYSIS = '继电器企标初始参数'
   and c.version = 1
   and c.analysis = p.analysis
   and c.name = p.component
   and trim(nrt.nc_result_name) = c.result_type
   and u.unit_code = c.units;
--应用时获取语句(选择样品自动筛选)
SELECT * from NC_TEST_INIT p
   where p.nc_enstard = 'QFVF4039-2016'
     and p.nc_sample_point = '005D-镀金触点'
   AND ' ' || p.Nc_contact_type || ',' like '% 1Z,%'
   and ' ' || p.NC_COIL_TYPE || ',' like '% 单稳态,%'
   and p.nc_coil_current = '单稳态'
   and p.nc_stage = '常温'
 ORDER BY p.def1
 

