create table NC_PROJ_TYPE 
(
   PK_PROJ_TYPE         CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   VERSION              NUMBER(10),
   PROJ_TYPE_CODE       VARCHAR(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_PROJ_TYPE primary key (PK_PROJ_TYPE)
);

alter table NC_PROJ_TYPE
   add constraint FK_NC_PROJ__REFERENCE_PROJECT_ foreign key (NAME, VERSION)
      references LIMS_DATA.PROJECT_TEMPLATE (NAME, VERSION);
      
      
create table NC_PROJ_PREFIX 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_PROJ_PREFIX primary key (PK_SAFE_TYPE)
);

create table NC_PROJ_COMY 
(
   PK_PROJ_COMY         CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   PROJ_COMY_CODE       VARCHAR(20),
   constraint PK_NC_PROJ_COMY primary key (PK_PROJ_COMY)
);

alter table NC_PROJ_COMY
   add constraint FK_NC_PROJ__REFERENCE_CUSTOMER foreign key (NAME)
      references LIMS_DATA.CUSTOMER (NAME);
      
create table NC_SOURCE_COMY 
(
   PK_SOURCE_COMY       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   SOURCE_COMY_CODE     VARCHAR(20),
   constraint PK_NC_SOURCE_COMY primary key (PK_SOURCE_COMY)
);

alter table NC_SOURCE_COMY
   add constraint FK_NC_SOURC_REFERENCE_CUSTOMER foreign key (NAME)
      references LIMS_DATA.CUSTOMER (NAME);


create table NC_PROD_TYPE 
(
   PK_PROD_TYPE         CHAR(20 CHAR)        not null,
   PROD_TYPE            VARCHAR2(20),
   C_P_PROD_TYPE        VARCHAR2(50),
   NAME                 VARCHAR2(50),
   C_P_PROD_TYPE2       VARCHAR2(50),
   C_PROD_TYPE_C1       VARCHAR2(50),
   P_NAME               VARCHAR2(50),
   F_PROD_TYPE          VARCHAR2(20),
   S_PROD_TYPE          VARCHAR2(20),
   T_PROD_TYPE          VARCHAR2(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_PROD_TYPE primary key (PK_PROD_TYPE)
);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (PROD_TYPE)
      references LIMS_DATA.C_PROD_TYPE (PROD_TYPE);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE, NAME)
      references LIMS_DATA.C_PROD_TYPE_C1 (PROD_TYPE, NAME);

alter table NC_PROD_TYPE
   add constraint FK_NC_PROD__REFERENCE_C_PROD_T foreign key (C_P_PROD_TYPE2, C_PROD_TYPE_C1, P_NAME)
      references LIMS_DATA.C_PROD_TYPE_C2 (PROD_TYPE, C_PROD_TYPE_C1, P_NAME);
      
      
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
'≤‚ ‘–Ë«Û';

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_TESTREQUEST_TYPE
   add constraint FK_NC_TESTR_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);


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




