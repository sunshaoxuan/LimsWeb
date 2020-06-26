--试验后参数详单，应用参照试验前
-- Create table
create table NC_TEST_after
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
)
tablespace NNC_DATA01
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table NC_TEST_after
  add constraint PK_NC_TEST_after primary key (PK_TEST_after)
  using index 
  tablespace NNC_DATA01
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table NC_TEST_after
  add constraint FK_NC_F__REFERENCE_NC_RESUL foreign key (PK_RESULT_TYPE)
  references NC_RESULT_TYPE (PK_RESULT_TYPE);
alter table NC_TEST_after
  add constraint FK_NC_T__REFERENCE_NC_UNITS foreign key (PK_UNITS_TYPE)
  references NC_UNITS_TYPE (PK_UNITS_TYPE);


--
insert into nc_test_after
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
   
--应用
SELECT * from NC_TEST_AFTER p
   where p.nc_enstard = 'QFVF4350-2018'
     and p.nc_sample_point = '012D'
   AND ' ' || p.Nc_contact_type || ',' like '% 1H,%'
   and ' ' || p.NC_COIL_TYPE || ',' like '% 单稳态,%'
   and p.nc_coil_current = '单稳态'
   and p.nc_stage = '常温'
 ORDER BY p.def1
 
