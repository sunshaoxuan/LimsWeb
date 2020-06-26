-- 样品信息
create table NC_SAMPLE_INFO
(
  pk_sample_info    CHAR(20 CHAR) not null,
  pk_prod_type      CHAR(20 CHAR),
  sample_info_code  VARCHAR2(200 CHAR),
  "def1"            VARCHAR2(200 CHAR),
  "def2"            VARCHAR2(200 CHAR),
  "def3"            VARCHAR2(200 CHAR),
  "def4"            VARCHAR2(200 CHAR),
  "def5"            VARCHAR2(200 CHAR),
  name              VARCHAR2(20 CHAR),
  c_prod_type_c1    VARCHAR2(20 CHAR),
  description       VARCHAR2(254 CHAR),
  enstard           VARCHAR2(20 CHAR),
  test_list         VARCHAR2(40 CHAR),
  sampling_point    VARCHAR2(254 CHAR),
  grade             VARCHAR2(20 CHAR),
  c_allowed_contact VARCHAR2(100 CHAR),
  stage             VARCHAR2(20 CHAR),
  isenable          INTEGER
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
alter table NC_SAMPLE_INFO
  add constraint PK_NC_SAMPLE_INFO primary key (PK_SAMPLE_INFO)
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
alter table NC_SAMPLE_INFO
  add constraint FK_NC_SAMPL_REFERENCE_NC_PROD_ foreign key (PK_PROD_TYPE)
  references NC_PROD_TYPE (PK_PROD_TYPE);
