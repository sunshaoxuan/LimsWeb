drop table REPORT_PATH cascade constraints;

/*==============================================================*/
/* Table: REPORT_PATH                                           */
/*==============================================================*/
create table REPORT_PATH 
(
   PK_REPORT_PATH       CHAR(20 CHAR)        not null,
   NC_REPORT_CODE       CHAR(50 CHAR),
   NC_REPORT_NAME       CHAR(200 CHAR),
   NC_REPORT_VALUE      VARCHAR2(500),
   NC_REPORT_TYPE       CHAR(20 CHAR),
   IS_ENABLE            SMALLINT,
   VDEF1                VARCHAR2(500),
   VDEF2                VARCHAR2(500),
   VDEF3                VARCHAR2(500),
   VDEF4                VARCHAR2(500),
   VDEF5                VARCHAR2(500),
   constraint PK_REPORT_PATH primary key (PK_REPORT_PATH)
);


insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('12345678901234567890', '1                                                 ', '委托单预览                                                                                                                                                                                                   ', 'HF_PROJ_VIEW.CPT', 'PROJ                ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('23456789012345678901', '2                                                 ', '任务流程卡                                                                                                                                                                                                   ', 'HF_TASK_PROCESS.CPT', 'TASK                ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('34567890123456789012', '3                                                 ', '报价单                                                                                                                                                                                                     ', 'HF_QUOTE_VIEW.CPT', 'QUOTE               ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('45678901234567890123', '4                                                 ', '计费单                                                                                                                                                                                                     ', 'HF_COST_VIEW.CPT', 'COST                ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('56789012345678901234', '5                                                 ', '单项报告                                                                                                                                                                                                    ', 'HF_SINGLE_REPORT.CPT', 'SINGLE              ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('67890123456789012345', '6                                                 ', '成套报告                                                                                                                                                                                                    ', 'HF_WHOLE_REPORT.CPT', 'WHOLE               ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);

insert into REPORT_PATH (PK_REPORT_PATH, NC_REPORT_CODE, NC_REPORT_NAME, NC_REPORT_VALUE, NC_REPORT_TYPE, IS_ENABLE, VDEF1, VDEF2, VDEF3, VDEF4, VDEF5)
values ('78901234567890123456', '7                                                 ', '测试进度                                                                                                                                                                                                    ', 'HF_CURRENT_TIME', 'CURRENT             ', 1, 'http://10.0.11.151:8080/Reportlets/', 'http://183.250.164.150:8083/Reportlets/', null, null, null);


