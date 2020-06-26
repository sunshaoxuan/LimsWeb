-- 试验前参数详单
create table NC_TEST_INIT 
(
   PK_TEST_INIT         CHAR(20 CHAR)        not null,
   PK_RESULT_TYPE       CHAR(20 CHAR),
   PK_UNITS_TYPE        CHAR(20 CHAR),
   PK_BASEN_TYPE        CHAR(20 CHAR),
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

alter table NC_TEST_INIT
   add constraint FK_NC_TEST__REFERENCE_NC_BASEN foreign key (PK_BASEN_TYPE)
      references NC_BASEN_TYPE (PK_BASEN_TYPE);
