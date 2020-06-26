-- 单位类型
create table NC_UNITS_TYPE
(
  pk_units_type        CHAR(20 CHAR) not null,
  unit_code            VARCHAR2(20 CHAR),
  nc_units_code        CHAR(50 CHAR),
  nc_units_name        CHAR(200 CHAR),
  nc_units_description VARCHAR2(100),
  nc_units_disp        CHAR(200 CHAR)
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
alter table NC_UNITS_TYPE
  add constraint PK_NC_UNITS_TYPE primary key (PK_UNITS_TYPE)
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
alter table NC_UNITS_TYPE
  add constraint FK_NC_UNITS_REFERENCE_UNITS foreign key (UNIT_CODE)
  references LIMS_DATA.UNITS (UNIT_CODE);
