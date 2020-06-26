--产品类别
-- Create table
create table NC_PROD_TYPE
(
  pk_prod_type   CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(20),
  c_p_prod_type  VARCHAR2(50),
  name           VARCHAR2(50),
  c_p_prod_type2 VARCHAR2(50),
  c_prod_type_c1 VARCHAR2(50),
  p_name         VARCHAR2(50),
  f_prod_type    VARCHAR2(20),
  s_prod_type    VARCHAR2(20),
  t_prod_type    VARCHAR2(20),
  isenable       INTEGER
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
alter table NC_PROD_TYPE
  add constraint PK_NC_PROD_TYPE primary key (PK_PROD_TYPE)
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
alter table NC_PROD_TYPE
  add constraint FK_NC_PROD_REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE2, C_PROD_TYPE_C1, P_NAME)
  references LIMS_DATA.C_PROD_TYPE_C2 (PROD_TYPE, C_PROD_TYPE_C1, P_NAME);
alter table NC_PROD_TYPE
  add constraint FK_NC_PROD__REFERENCE_C_PROD_A foreign key (PROD_TYPE)
  references LIMS_DATA.C_PROD_TYPE (PROD_TYPE);
alter table NC_PROD_TYPE
  add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE, NAME)
  references LIMS_DATA.C_PROD_TYPE_C1 (PROD_TYPE, NAME);
--一级分类
-- Create table
create table NC_FIRST_TYPE
(
  pk_first_type CHAR(20 CHAR) not null,
  prod_type     VARCHAR2(20),
  nc_first_code CHAR(50 CHAR),
  nc_first_name CHAR(200 CHAR),
  is_enable     INTEGER
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
alter table NC_FIRST_TYPE
  add constraint PK_NC_FIRST_TYPE primary key (PK_FIRST_TYPE)
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
alter table NC_FIRST_TYPE
  add constraint FK_NC_FIRST_REFERENCE_C_PROD_T foreign key (PROD_TYPE)
  references LIMS_DATA.C_PROD_TYPE (PROD_TYPE);
--二级分类
-- Create table
create table NC_SECOND_TYPE
(
  pk_second_type CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(50),
  name           VARCHAR2(50),
  pk_first_type  CHAR(20 CHAR),
  nc_second_code CHAR(50 CHAR),
  nc_second_name CHAR(200 CHAR),
  is_enable      INTEGER,
  nc_descript    CHAR(200 CHAR)
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
alter table NC_SECOND_TYPE
  add constraint PK_NC_SECOND_TYPE primary key (PK_SECOND_TYPE)
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
alter table NC_SECOND_TYPE
  add constraint FK_NC_SECON_REFERENCE_C_PROD_T foreign key (PROD_TYPE, NAME)
  references LIMS_DATA.C_PROD_TYPE_C1 (PROD_TYPE, NAME);
alter table NC_SECOND_TYPE
  add constraint FK_NC_SECON_REFERENCE_NC_FIRST foreign key (PK_FIRST_TYPE)
  references NC_FIRST_TYPE (PK_FIRST_TYPE);
--三级分类
-- Create table
create table NC_THIRD_TYPE
(
  pk_third_type  CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(50),
  c_prod_type_c1 VARCHAR2(50),
  p_name         VARCHAR2(50),
  pk_first_type  CHAR(20 CHAR),
  pk_second_type CHAR(20 CHAR),
  nc_third_code  CHAR(50 CHAR),
  nc_third_name  CHAR(200 CHAR),
  is_enable      INTEGER,
  nc_descript    CHAR(200 CHAR)
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
alter table NC_THIRD_TYPE
  add constraint PK_NC_THIRD_TYPE primary key (PK_THIRD_TYPE)
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
alter table NC_THIRD_TYPE
  add constraint FK_NC_THIRD_REFERENCE_C_PROD_T foreign key (PROD_TYPE, C_PROD_TYPE_C1, P_NAME)
  references LIMS_DATA.C_PROD_TYPE_C2 (PROD_TYPE, C_PROD_TYPE_C1, P_NAME);
alter table NC_THIRD_TYPE
  add constraint FK_NC_THIRD_REFERENCE_NC_FIRST foreign key (PK_FIRST_TYPE)
  references NC_FIRST_TYPE (PK_FIRST_TYPE);
alter table NC_THIRD_TYPE
  add constraint FK_NC_THIRD_REFERENCE_NC_SECON foreign key (PK_SECOND_TYPE)
  references NC_SECOND_TYPE (PK_SECOND_TYPE);
