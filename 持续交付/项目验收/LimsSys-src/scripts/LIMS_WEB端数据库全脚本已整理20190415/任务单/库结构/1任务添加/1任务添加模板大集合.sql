--任务添加模板大集合(此处考虑做成view，20位主键每次重新生成，通过name回去查找，此处只做查询筛选模板?)
drop table NC_TASK_ADDUNION cascade constraints;

/*==============================================================*/
/* Table: NC_TASK_ADDUNION                                      */
/*==============================================================*/
create table NC_TASK_ADDUNION 
(
   PK_TASK_ADDUNION     CHAR(20 CHAR)        not null,
   NC_TASK_ADDCODE      CHAR(50 CHAR),
   NC_TASK_ADDNAME      CHAR(200 CHAR),
   NC_TESTLIST_NAME     VARCHAR(200),
   NC_ANALYSIS_METHOD   VARCHAR(200),
   NC_REPORT_NAME       VARCHAR(200),
   NC_TASK_TYPE         VARCHAR(200),
   NC_TASK_DES          VARCHAR(1000),
   NC_TASK_NAME         VARCHAR(200),
   NC_INCLUDE_PROTYPE   VARCHAR(1000),
   NC_CB_PLAN           CHAR(1 CHAR),
   NC_ORDER_NUMBER      NUMBER(10),
   "def1"               VARCHAR(1000),
   "def2"               VARCHAR(1000),
   "def3"               VARCHAR(1000),
   "def4"               VARCHAR(1000),
   "def5"               VARCHAR(1000),
   constraint PK_NC_TASK_ADDUNION primary key (PK_TASK_ADDUNION)
);




---------------
insert into NC_TASK_ADDUNION
  select substr(SYS_GUID(), 1, 20),
         RANK() over(order by un.name, un.order_number, un.description asc),
         un.Name,
         un.TEST_LIST,
         un.T_ANALYSIS_METHOD,
         un.REPORTED_NAME,
         un.ANALYSIS_TYPE,
         un.DESCRIPTION,
         un.NAME,
         un.C_ALLOWED_PROD_TYPE,
         un.C_B_PLAN,
         un.order_number,
         '',
         '',
         '',
         '',
         ''
    from (select t.name AS TEST_LIST,
                 A.T_ANALYSIS_METHOD,
                 A.REPORTED_NAME,
                 A.ANALYSIS_TYPE,
                 A.DESCRIPTION,
                 A1.NAME,
                 A.C_ALLOWED_PROD_TYPE,
                 (case T.C_B_PLAN
                   when 'T' then
                    'T'
                   else
                    ''
                 end) as C_B_PLAN,
                 t.order_number
            from test_list_entry t, analysis a, analysis a1
           where a.name = t.analysis
             AND A.C_TEST_TYPE = '测试条件'
             and t.version = a.version
             and a.group_name <> 'ABANDON'
             and a.active = 'T'
             and a.name = a1.c_test_condition
          
          UNION
          
          select '_NA' AS TEST_LIST,
                 A.T_ANALYSIS_METHOD,
                 A.REPORTED_NAME,
                 A.ANALYSIS_TYPE,
                 A.DESCRIPTION,
                 A.NAME,
                 A.C_ALLOWED_PROD_TYPE,
                 '',
                 999
            from analysis a
           where A.ACTIVE = 'T'
             and a.group_name <> 'ABANDON'
             AND A.C_TEST_TYPE = '测试结果'
           order by 1, 9) un
