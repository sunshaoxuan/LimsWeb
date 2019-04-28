--基本信息
alter table NC_PRODAUTH_TYPE
   drop constraint FK_NC_PRODA_REFERENCE_LIST;

alter table NC_PRODAUTH_TYPE
   drop constraint FK_NC_PRODA_REFERENCE_LIST_ENT;

drop table NC_PRODAUTH_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_PRODAUTH_TYPE                                      */
/*==============================================================*/
create table NC_PRODAUTH_TYPE 
(
   PK_PRODAUTH_TYPE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_PRODAUTH_CODE     VARCHAR(20),
   NC_PRODAUTH_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_PRODAUTH_TYPE primary key (PK_PRODAUTH_TYPE)
);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_PRODAUTH_TYPE
   add constraint FK_NC_PRODA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

--------------------
alter table NC_TESTREQUEST_TYPE
   drop constraint FK_NC_TESTR_REFERENCE_LIST;

alter table NC_TESTREQUEST_TYPE
   drop constraint FK_NC_TESTR_REFERENCE_LIST_ENT;

drop table NC_TESTREQUEST_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_TESTREQUEST_TYPE                                   */
/*==============================================================*/
create table NC_TESTREQUEST_TYPE 
(
   PK_TESTREQUEST_TYPE  CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_TESTREQUEST_CODE  VARCHAR(20),
   NC_TESTREQUEST_NAME  VARCHAR(100),
   ISENABLE             SMALLINT,
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
----------------------------------------------------
alter table NC_CUSTOMER_TYPE
   drop constraint FK_NC_CUSTO_REFERENCE_LIST_ENT;

alter table NC_CUSTOMER_TYPE
   drop constraint FK_NC_CUSTO_REFERENCE_LIST;

drop table NC_CUSTOMER_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_CUSTOMER_TYPE                                      */
/*==============================================================*/
create table NC_CUSTOMER_TYPE 
(
   PK_CUSTOMER_TYPE     CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CUSTOMER_CODE     VARCHAR(20),
   NC_CUSTOMER_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CUSTOMER_TYPE primary key (PK_CUSTOMER_TYPE)
);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_CUSTOMER_TYPE
   add constraint FK_NC_CUSTO_REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);
--------------------------------------------------------------
alter table NC_SAFE_TYPE
   drop constraint FK_NC_SAFE__REFERENCE_LIST_ENT;

alter table NC_SAFE_TYPE
   drop constraint FK_NC_SAFE__REFERENCE_LIST;

drop table NC_SAFE_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_SAFE_TYPE                                          */
/*==============================================================*/
create table NC_SAFE_TYPE 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAFE_TYPE primary key (PK_SAFE_TYPE)
);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST_ENT foreign key (LIST, NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);

alter table NC_SAFE_TYPE
   add constraint FK_NC_SAFE__REFERENCE_LIST foreign key (LIS_NAME)
      references LIMS_DATA.LIST (NAME);
---------------------------------------------------------------
alter table NC_CONTACT_BRAND
   drop constraint FK_NC_CONTA_REFERENCE_LIST;

alter table NC_CONTACT_BRAND
   drop constraint FK_NC_CONTA_REFERENCE_LIST_ENT;

drop table NC_CONTACT_BRAND cascade constraints;

/*==============================================================*/
/* Table: NC_CONTACT_BRAND                                      */
/*==============================================================*/
create table NC_CONTACT_BRAND 
(
   PK_CONTACT_BRAND     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CONTACT_CODE      VARCHAR(20),
   NC_CONTACT_NAME      VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CONTACT_BRAND primary key (PK_CONTACT_BRAND)
);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_CONTACT_BRAND
   add constraint FK_NC_CONTA_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
----------------------------------------------------------
alter table NC_SAMPLE_GROUP
   drop constraint FK_NC_SAMPL_REFERENCE_LIST;

alter table NC_SAMPLE_GROUP
   drop constraint FK_NC_SAMPL_REFERENCE_LIST_ENT;

drop table NC_SAMPLE_GROUP cascade constraints;

/*==============================================================*/
/* Table: NC_SAMPLE_GROUP                此处开发方先自行增加A/B/C/D四组，上线时，甲方按开发方主键建立原始数据                         */
/*==============================================================*/
create table NC_SAMPLE_GROUP 
(
   PK_SAMPLE_GROUP      CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAMPLE_CODE       VARCHAR(20),
   NC_SAMPLE_NAME       VARCHAR(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAMPLE_GROUP primary key (PK_SAMPLE_GROUP)
);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_SAMPLE_GROUP
   add constraint FK_NC_SAMPL_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
-----------------------------------------------------------
alter table NC_STATUS_TYPE
   drop constraint FK_NC_STATU_REFERENCE_LIST;

alter table NC_STATUS_TYPE
   drop constraint FK_NC_STATU_REFERENCE_LIST_ENT;

drop table NC_STATUS_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_STATUS_TYPE                                        */
/*==============================================================*/
create table NC_STATUS_TYPE 
(
   PK_STATUS_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_STATUS_CODE       VARCHAR(20),
   NC_STATUS_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_STATUS_TYPE primary key (PK_STATUS_HANDLE)
);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_STATUS_TYPE
   add constraint FK_NC_STATU_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
---------------------------------------------------------------
alter table NC_TEST_TYPE
   drop constraint FK_NC_TEST__REFERENCE_LIST;

alter table NC_TEST_TYPE
   drop constraint FK_NC_TEST__REFERENCE_LIST_ENT;

drop table NC_TEST_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_TEST_TYPE                                          */
/*==============================================================*/
create table NC_TEST_TYPE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_TEST_TYPE primary key (PK_RATAIN_HANDLE)
);

alter table NC_TEST_TYPE
   add constraint FK_NC_TEST__REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_TEST_TYPE
   add constraint FK_NC_TEST__REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
--------------------------------------------------------------

alter table NC_RATAIN_HANDLE
   drop constraint FK_NC_RATAI_REFERENCE_LIST;

alter table NC_RATAIN_HANDLE
   drop constraint FK_NC_RATAI_REFERENCE_LIST_ENT;

drop table NC_RATAIN_HANDLE cascade constraints;

/*==============================================================*/
/* Table: NC_RATAIN_HANDLE                                      */
/*==============================================================*/
create table NC_RATAIN_HANDLE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_RATAIN_HANDLE primary key (PK_RATAIN_HANDLE)
);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST foreign key (NAME)
      references LIMS_DATA.LIST (NAME);

alter table NC_RATAIN_HANDLE
   add constraint FK_NC_RATAI_REFERENCE_LIST_ENT foreign key (LIST, LIS_NAME)
      references LIMS_DATA.LIST_ENTRY (LIST, NAME);
---------------------------------------------------------------
alter table NC_REPORT_LANG
   drop constraint FK_NC_REPOR_REFERENCE_LIST_ENT;

alter table NC_REPORT_LANG
   drop constraint FK_NC_REPOR_REFERENCE_LIST;

drop table NC_REPORT_LANG cascade constraints;

/*==============================================================*/
/* Table: NC_REPORT_LANG                                        */
/*==============================================================*/
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
------------------------------------------------------------------
alter table NC_REPORT_TYPE
   drop constraint FK_NC_REPOR_REFERENCE_LIST;

alter table NC_REPORT_TYPE
   drop constraint FK_NC_REPOR_REFERENCE_LIST_ENT;

drop table NC_REPORT_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_REPORT_TYPE                                        */
/*==============================================================*/
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







