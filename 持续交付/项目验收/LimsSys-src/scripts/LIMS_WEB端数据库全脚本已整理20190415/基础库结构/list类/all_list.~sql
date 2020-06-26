/*LIST 类*/
--产品鉴定类型
create table NC_PRODAUTH_TYPE 
(
   PK_PRODAUTH_TYPE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_PRODAUTH_CODE     VARCHAR(20),
   NC_PRODAUTH_NAME     VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_PRODAUTH_TYPE primary key (PK_PRODAUTH_TYPE)
);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--import data
Insert into NC_PRODAUTH_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = '产品鉴定类型'
and l.name = le.list
----------------------------------------------------------------------------------------------------------
--报告类型
create table NC_REPORT_TYPE 
(
   PK_REPORT_TYPE       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_TYPE primary key (PK_REPORT_TYPE)
);

alter table NC_REPORT_TYPE
   add constraint FK_NC_REPOR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_REPORT_TYPE
   add constraint FK_NC_REPOR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--
Insert into NC_REPORT_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_COA_FORMAT'
and l.name = le.list
-----------------------------------------------------------------------------------------------------------
--测试类型
create table NC_TESTREQUEST_TYPE 
(
   PK_TESTREQUEST_TYPE  CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_TESTREQUEST_CODE  VARCHAR(20),
   NC_TESTREQUEST_NAME  VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_TESTREQUEST_TYPE primary key (PK_TESTREQUEST_TYPE)
);

comment on table NC_TESTREQUEST_TYPE is
'测试需求';

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--
Insert into NC_TESTREQUEST_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = '客户要求-测试类型'
and l.name = le.list
-----------------------------------------------------------------------------------------------------------
--客户类型
create table NC_CUSTOMER_TYPE 
(
   PK_CUSTOMER_TYPE     CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CUSTOMER_CODE     VARCHAR(20),
   NC_CUSTOMER_NAME     VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_CUSTOMER_TYPE primary key (PK_CUSTOMER_TYPE)
);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);
--
Insert into NC_CUSTOMER_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_CUST_REQ_TYPE'
and l.name = le.list
---------------------------------------------------------------------------------------------
--安全认证类型
create table NC_SAFE_TYPE 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_SAFE_TYPE primary key (PK_SAFE_TYPE)
);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);
--
Insert into NC_SAFE_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = '安全认证类型'
and l.name = le.list
---------------------------------------------------------------------------------------
--触点牌号
create table NC_CONTACT_BRAND 
(
   PK_CONTACT_BRAND     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CONTACT_CODE      VARCHAR(20),
   NC_CONTACT_NAME      VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_CONTACT_BRAND primary key (PK_CONTACT_BRAND)
);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--
Insert into NC_CONTACT_BRAND
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_CONTACT_BRAND'
and l.name = le.list
----------------------------------------------------------------------------------------------
--报告语言
create table NC_REPORT_LANG 
(
   PK_REPORT_LANG       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_LANG primary key (PK_REPORT_LANG)
);

alter table NC_REPORT_LANG
   add constraint FK_NC_REPOR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_REPORT_LANG
   add constraint FK_NC_REPOR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);
--
Insert into NC_REPORT_LANG
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_COA_LANGUAGE'
and l.name = le.list
-------------------------------------------------------------------------------------------------------
--试验后样品处理
create table NC_RATAIN_HANDLE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_RATAIN_HANDLE primary key (PK_RATAIN_HANDLE)
);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--
Insert into NC_RATAIN_HANDLE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_RATAIN_HANDLE'
and l.name = le.list
---------------------------------------------------------------------------------------------------
--检验性质
create table NC_TEST_TYPE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_TEST_TYPE primary key (PK_RATAIN_HANDLE)
);

alter table NC_TEST_TYPE
   add constraint FK_NC_TEST__REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_TEST_TYPE
   add constraint FK_NC_TEST__REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
---
Insert into NC_TEST_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = '检验性质类型'
and l.name = le.list
--------------------------------------------------------------------------------------------------
--产品属性
create table NC_STATUS_TYPE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_STATUS_TYPE primary key (PK_RATAIN_HANDLE)
);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--
Insert into NC_STATUS_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = '产品属性'
and l.name = le.list
--------------------------------------------------------------------------------------------------------------
--样品组别
create table NC_SAMPLE_GROUP 
(
   PK_SAMPLE_GROUP      CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAMPLE_CODE       VARCHAR(20),
   NC_SAMPLE_NAME       VARCHAR(20),
   ISENALE              SMALLINT,
   constraint PK_NC_SAMPLE_GROUP primary key (PK_SAMPLE_GROUP)
);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
---------
Insert into NC_STATUS_TYPE
select substr(SYS_GUID(),1,20),l.name,le.list,le.name,le.order_number,le.value,1
from list l,list_entry le
where l.name = 'C_SAMPLE_GROUP'
and l.name = le.list
